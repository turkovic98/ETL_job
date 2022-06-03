package com.smartcat.etljob.services;

import com.smartcat.etljob.model.Allowance;
import com.smartcat.etljob.model.Break;
import com.smartcat.etljob.model.KPI;
import com.smartcat.etljob.model.Shift;
import com.smartcat.etljob.repositories.BreakRepository;
import com.smartcat.etljob.repositories.KPIRepository;
import com.smartcat.etljob.repositories.ShiftRepository;
import com.smartcat.etljob.util.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class KPIService {

	private final ShiftRepository shiftRepository;
	private final KPIRepository kpiRepository;
	private final BreakRepository breakRepository;

	@Autowired
	public KPIService(ShiftRepository shiftRepository, KPIRepository kpiRepository, BreakRepository breakRepository) {
		this.shiftRepository = shiftRepository;
		this.kpiRepository = kpiRepository;
		this.breakRepository = breakRepository;
	}

	public void calculateKPIs(){
		meanBreakLengthInMinutes();
		meanShiftCost();
		maxAllowanceCost14d();
		maxBreakFreeShiftPeriodInDays();
		minShiftLengthInHours();
		totalNumberOfPaidBreaks();
	}

	//mean_break_length_in_minutes
	private void meanBreakLengthInMinutes(){
		List<Break> breaks = breakRepository.findAll();
		int minutes = 0;
		for(Break item : breaks){
			minutes += TimeUnit.MILLISECONDS.toMinutes(item.getBreakFinish().getTime() - item.getBreakStart().getTime());
		}
		double mean = (double)minutes / breaks.size();
		System.out.println("mean_break_length_in_minutes: " + mean);
		KPI kpi = new KPI("mean_break_length_in_minutes", LocalDate.now(), DataMapper.trim2Decimals(mean));
		kpiRepository.save(kpi);
	}

	//mean_shift_cost
	private void meanShiftCost(){
		double mean = shiftRepository.getMeanCost();
		System.out.println("mean_shift_cost: " + mean);
		KPI kpi = new KPI("mean_shift_cost", LocalDate.now(), DataMapper.trim2Decimals(mean));
		kpiRepository.save(kpi);
	}

	//max_allowance_cost_14d
	private void maxAllowanceCost14d(){
		List<Shift> shifts = shiftRepository.findAll();
		List<Allowance> allowances = new ArrayList<>();
		double maxCost = 0;
		for(Shift shift : shifts){
			if(shift.getShiftDate().isAfter(LocalDate.now().minusDays(14))){
				allowances.addAll(shift.getAllowances());
			}
		}
		for(Allowance allowance : allowances){
			maxCost = Math.max(maxCost, allowance.getAllowanceCost());
		}
		System.out.println("max_allowance_cost_14d: " + maxCost);
		KPI kpi = new KPI("max_allowance_cost_14d", LocalDate.now(), DataMapper.trim2Decimals(maxCost));
		kpiRepository.save(kpi);
	}

	//max_break_free_shift_period_in_days
	private void maxBreakFreeShiftPeriodInDays(){
		List<Shift> shifts = shiftRepository.getShiftsWithoutBreaks();
		long maxDays = 0;
		if(shifts.size() < 2) {
			KPI kpi = new KPI("max_break_free_shift_period_in_days", LocalDate.now(), 0);
			kpiRepository.save(kpi);
			return;
		}
		for(int i = 1; i < shifts.size(); i++){
			LocalDate firstDate = shifts.get(i).getShiftDate();
			LocalDate secondDate = shifts.get(i - 1).getShiftDate();
			long days = ChronoUnit.DAYS.between(secondDate, firstDate);
			maxDays = Math.max(maxDays, days);
		}
		System.out.println("max_break_free_shift_period_in_days: " + maxDays);
		KPI kpi = new KPI("max_break_free_shift_period_in_days", LocalDate.now(), maxDays);
		kpiRepository.save(kpi);
	}

	//min_shift_length_in_hours
	private void minShiftLengthInHours(){
		List<Shift> shifts = shiftRepository.findAll();
		long minHours = Long.MAX_VALUE;
		for(Shift shift : shifts){
			long shiftHours = TimeUnit.MILLISECONDS.toHours(shift.getShiftFinish().getTime() - shift.getShiftStart().getTime());
			minHours = Math.min(minHours, shiftHours);
		}
		System.out.println("min_shift_length_in_hours: " + minHours);
		KPI kpi = new KPI("min_shift_length_in_hours", LocalDate.now(), minHours);
		kpiRepository.save(kpi);
	}

	//total_number_of_paid_breaks
	private void totalNumberOfPaidBreaks(){
		int count = breakRepository.getPaidBreaksCount();
		System.out.println("total_number_of_paid_breaks: " + count);
		KPI kpi = new KPI("total_number_of_paid_breaks", LocalDate.now(), count);
		kpiRepository.save(kpi);
	}

}

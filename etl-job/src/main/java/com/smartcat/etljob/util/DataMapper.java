package com.smartcat.etljob.util;

import com.smartcat.etljob.dtos.AllowanceDTO;
import com.smartcat.etljob.dtos.AwardInterpretationDTO;
import com.smartcat.etljob.dtos.BreakDTO;
import com.smartcat.etljob.dtos.ShiftDTO;
import com.smartcat.etljob.model.Allowance;
import com.smartcat.etljob.model.AwardInterpretation;
import com.smartcat.etljob.model.Break;
import com.smartcat.etljob.model.Shift;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DataMapper {

	public static Shift shiftDtoToShift(Shift shift, ShiftDTO dto){
		List<Allowance> allowances = getAllowances(dto.getAllowances(), shift);
		List<Break> breaks = getBreaks(dto.getBreaks(), shift);
		List<AwardInterpretation> awardInterpretations = getAwardInterpretations(dto.getAward_interpretations(), shift);

		shift.getAllowances().addAll(allowances);
		shift.getBreaks().addAll(breaks);
		shift.getAwardInterpretations().addAll(awardInterpretations);

		return shift;
	}

	private static List<Allowance> getAllowances(List<AllowanceDTO> dtos, Shift shift){
		List<Allowance> allowances = new ArrayList<>();
		for(AllowanceDTO dto : dtos){
			allowances.add(allowanceDtoToAllowance(dto, shift));
		}
		return allowances;
	}

	private static List<Break> getBreaks(List<BreakDTO> dtos, Shift shift){
		List<Break> breaks = new ArrayList<>();
		for(BreakDTO dto : dtos){
			breaks.add(breakDtoToBreak(dto, shift));
		}
		return breaks;
	}

	private static List<AwardInterpretation> getAwardInterpretations(List<AwardInterpretationDTO> dtos, Shift shift){
		List<AwardInterpretation> awards = new ArrayList<>();
		for(AwardInterpretationDTO dto : dtos){
			awards.add(awardInterpretationDtoToAwardInterpretation(dto, shift));
		}
		return awards;
	}

	private static Allowance allowanceDtoToAllowance(AllowanceDTO dto, Shift shift){
		return new Allowance(shift, dto.getValue(), trimDecimals(dto.getCost()));
	}
	private static Break breakDtoToBreak(BreakDTO dto, Shift shift){
		return new Break(shift, dto.getStart(), dto.getFinish(), dto.isPaid());
	}
	private static AwardInterpretation awardInterpretationDtoToAwardInterpretation(AwardInterpretationDTO dto, Shift shift){
		return new AwardInterpretation(shift, dto.getDate(), dto.getUnits(), trimDecimals(dto.getCost()));
	}

	public static double trimDecimals(double cost){
		DecimalFormat df = new DecimalFormat("#.####");
		df.setRoundingMode(RoundingMode.CEILING);
		return Double.parseDouble(df.format(cost));
	}

}

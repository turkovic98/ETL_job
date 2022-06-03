package com.smartcat.etljob.services;

import com.smartcat.etljob.clients.ShiftsAPIClient;
import com.smartcat.etljob.dtos.AllowanceDTO;
import com.smartcat.etljob.dtos.AwardInterpretationDTO;
import com.smartcat.etljob.dtos.ResponseDTO;
import com.smartcat.etljob.dtos.ShiftDTO;
import com.smartcat.etljob.exceptions.ShiftsAPIException;
import com.smartcat.etljob.model.Shift;
import com.smartcat.etljob.repositories.ShiftRepository;
import com.smartcat.etljob.util.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ETLJobService {

	private final int LIMIT = 30;
	private final ShiftRepository shiftRepository;
	private final ShiftsAPIClient client;


	@Autowired
	public ETLJobService(ShiftRepository shiftRepository, ShiftsAPIClient client) {
		this.shiftRepository = shiftRepository;
		this.client = client;
	}


	public void getShifts() throws ShiftsAPIException {
		List<ShiftDTO> shifts = callShiftsAPI();
		for(ShiftDTO dto : shifts){
			Shift shift = createShift(dto);
			Shift updatedShift = DataMapper.shiftDtoToShift(shift, dto);
			shiftRepository.save(updatedShift);
		}
	}

	private Shift createShift(ShiftDTO dto){
		double cost = 0.0;
		for(AllowanceDTO allowance : dto.getAllowances()){
			cost += allowance.getCost();
		}
		for(AwardInterpretationDTO award : dto.getAward_interpretations()){
			cost += award.getCost();
		}
		Shift shift = new Shift(dto.getDate(), dto.getStart(), dto.getFinish(), DataMapper.trimDecimals(cost));
		return shiftRepository.save(shift);
	}

	private List<ShiftDTO> callShiftsAPI() throws ShiftsAPIException {
		List<ShiftDTO> shifts;
		try {
			ResponseDTO response = client.getShifts();
			System.out.println(response);
			shifts = new ArrayList<>(response.getResults());

			while (response.getLinks().getNext() != null) {
				System.out.println("\n\n----------------------------------------");
				System.out.println(response.getStart() + "\n\n");
				response = client.getNext(response.getStart() + response.getLimit(), LIMIT);
				System.out.println(response);
				shifts.addAll(response.getResults());

			}
			System.out.println("\n\n K R A J !");
			System.out.println(shifts.size());
		} catch (Exception e) {
			throw new ShiftsAPIException();
		}
		return shifts;
	}

}

package com.smartcat.etljob.services;

import com.smartcat.etljob.clients.ShiftsAPIClient;
import com.smartcat.etljob.dtos.AllowanceDTO;
import com.smartcat.etljob.dtos.AwardInterpretationDTO;
import com.smartcat.etljob.dtos.ResponseDTO;
import com.smartcat.etljob.dtos.ShiftDTO;
import com.smartcat.etljob.exceptions.ShiftCreationException;
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


	public void getShifts() throws ShiftsAPIException, ShiftCreationException {
		List<ShiftDTO> shifts = callShiftsAPI();
		try {
			for(ShiftDTO dto : shifts){
				Shift shift = createShift(dto);
				Shift updatedShift = DataMapper.shiftDtoToShift(shift, dto);
				shiftRepository.save(updatedShift);
			}
		} catch(Exception e){
			throw new ShiftCreationException();
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
		Shift shift = new Shift(dto.getId(), dto.getDate(), dto.getStart(), dto.getFinish(), DataMapper.trimDecimals(cost));
		return shiftRepository.save(shift);
	}

	private List<ShiftDTO> callShiftsAPI() throws ShiftsAPIException {
		List<ShiftDTO> shifts;
		try {
			ResponseDTO response = client.getShifts();
			shifts = new ArrayList<>(response.getResults());

			while (response.getLinks().getNext() != null) {
				response = client.getNext(response.getStart() + response.getLimit(), LIMIT);
				shifts.addAll(response.getResults());

			}
		} catch (Exception e) {
			throw new ShiftsAPIException();
		}
		return shifts;
	}

}

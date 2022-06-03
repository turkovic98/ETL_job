package com.smartcat.etljob.services;

import com.smartcat.etljob.clients.ShiftsAPIClient;
import com.smartcat.etljob.dtos.ResponseDTO;
import com.smartcat.etljob.repositories.BreakRepository;
import com.smartcat.etljob.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class ETLJobService {

	private final BreakRepository breakRepository;
	private final ShiftRepository shiftRepository;
	private final ShiftsAPIClient client;

	@Autowired
	public ETLJobService(BreakRepository breakRepository, ShiftRepository shiftRepository, ShiftsAPIClient client) {
		this.breakRepository = breakRepository;
		this.shiftRepository = shiftRepository;
		this.client = client;
	}

	public void createBreakAndShift() throws ParseException {
		/*DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("23/09/2007");
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);

		Shift shift = new Shift(LocalDate.now(),
			timestamp,
			timestamp,
			13.2);
		Break firstBreak = new Break(shift, timestamp, timestamp);
		Allowance allowance = new Allowance(shift, 5.2f, 12.3);
		AwardInterpretation awardInterpretation = new AwardInterpretation(shift, LocalDate.now(), 12.5f, 45.5);
		shift.getBreaks().add(firstBreak);
		shift.getAllowances().add(allowance);
		shift.getAwardInterpretations().add(awardInterpretation);
		//shift.setId(new UUID("5ce547d9-af59-4558-a224-d19fc94f2b6f"));
		shift = shiftRepository.save(shift);*/
	}


	public void callShiftsAPI() {

		//prvi poziv
		ResponseDTO response = client.getShifts();
		System.out.println(response);


		//kriterijum zaustavljanja
		while (response.getLinks().getNext() != null) {
			System.out.println("\n\n----------------------------------------");
			System.out.println(response.getStart() + "\n\n");
			response = client.getNext(response.getStart() + response.getLimit(), response.getLimit());
			System.out.println(response);

		}
		System.out.println("\n\n K R A J !");
	}

}

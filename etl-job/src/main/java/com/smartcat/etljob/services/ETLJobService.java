package com.smartcat.etljob.services;

import com.smartcat.etljob.model.Break;
import com.smartcat.etljob.model.Shift;
import com.smartcat.etljob.repositories.BreakRepository;
import com.smartcat.etljob.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Service
public class ETLJobService {

	private final BreakRepository breakRepository;
	private final ShiftRepository shiftRepository;

	@Autowired
	public ETLJobService(BreakRepository breakRepository, ShiftRepository shiftRepository) {
		this.breakRepository = breakRepository;
		this.shiftRepository = shiftRepository;
	}

	public void createBreakAndShift() throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("23/09/2007");
		long time = date.getTime();
		Timestamp timestamp = new Timestamp(time);

		Shift shift = new Shift(LocalDate.now(),
			timestamp,
			timestamp,
			13.2);
		Break firstBreak = new Break(shift, timestamp, timestamp);
		shift.getBreaks().add(firstBreak);
		shift = shiftRepository.save(shift);


	}


}

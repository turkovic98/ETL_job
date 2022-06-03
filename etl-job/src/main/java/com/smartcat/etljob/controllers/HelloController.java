package com.smartcat.etljob.controllers;
import com.smartcat.etljob.model.KPI;
import com.smartcat.etljob.model.Shift;
import com.smartcat.etljob.repositories.KPIRepository;
import com.smartcat.etljob.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("hello")
public class HelloController {

	public KPIRepository kpiRepository;
	public final ShiftRepository shiftRepository;

	@Autowired
	public HelloController(KPIRepository kpiRepository, ShiftRepository shiftRepository){
		this.kpiRepository = kpiRepository;
		this.shiftRepository = shiftRepository;
	}

	@GetMapping("aaa")
	public ResponseEntity<?> hello(){
		KPI kpi = new KPI("emina1", LocalDate.now(), 0.0);
		kpi = kpiRepository.save(kpi);

		KPI kpi2 = new KPI("emina2", LocalDate.now(), 0.0);
		kpi2 = kpiRepository.save(kpi2);
		return new ResponseEntity<>(kpi.getKpiName(), HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<?> getShifts(){
		List<Shift> shifts = shiftRepository.findAll();
		return new ResponseEntity<>(shifts, HttpStatus.OK);
	}

	@GetMapping("break")
	public ResponseEntity<?> getShiftsWithoutBreak(){
		List<Shift> shifts = shiftRepository.getShiftsWithoutBreaks();
		return new ResponseEntity<>(shifts, HttpStatus.OK);
	}
}

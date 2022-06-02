package com.smartcat.etljob.controllers;
import com.smartcat.etljob.model.KPI;
import com.smartcat.etljob.repositories.KPIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("hello")
public class HelloController {

	public KPIRepository kpiRepository;

	@Autowired
	public HelloController(KPIRepository kpiRepository){
		this.kpiRepository = kpiRepository;
	}

	@GetMapping("aaa")
	public ResponseEntity<?> hello(){
		KPI kpi = new KPI("emina", LocalDate.now(), 0.0);
		kpi = kpiRepository.save(kpi);
		return new ResponseEntity<>(kpi.getKpiName(), HttpStatus.OK);
	}
}

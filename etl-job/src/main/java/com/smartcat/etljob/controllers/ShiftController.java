package com.smartcat.etljob.controllers;
import com.smartcat.etljob.model.Shift;
import com.smartcat.etljob.repositories.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("shift")
public class ShiftController {

	public final ShiftRepository shiftRepository;

	@Autowired
	public ShiftController(ShiftRepository shiftRepository){
		this.shiftRepository = shiftRepository;
	}

	@GetMapping("")
	public ResponseEntity<?> getShifts(){
		List<Shift> shifts = shiftRepository.findAll();
		return new ResponseEntity<>(shifts, HttpStatus.OK);
	}
}

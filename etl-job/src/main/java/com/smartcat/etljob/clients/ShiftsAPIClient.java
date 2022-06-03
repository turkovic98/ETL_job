package com.smartcat.etljob.clients;

import com.smartcat.etljob.dtos.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "shifts-api", url = "http://localhost:8000/api")
public interface ShiftsAPIClient {

	@RequestMapping(method = RequestMethod.GET, value = "/shifts", produces = "application/json")
	ResponseDTO getShifts();

	@RequestMapping(method = RequestMethod.GET, value = "/shifts?start={start}&limit={limit}", produces = "application/json")
	ResponseDTO getNext(@PathVariable("start") int start, @PathVariable("limit") int limit);
}

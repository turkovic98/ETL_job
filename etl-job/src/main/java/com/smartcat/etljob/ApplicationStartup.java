package com.smartcat.etljob;

import com.smartcat.etljob.services.ETLJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	private final ETLJobService etlJobService;

	@Autowired
	public ApplicationStartup(ETLJobService etlJobService) {
		this.etlJobService = etlJobService;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("EMINAAAAAAAAAAAAAAAAAAAAA");
		etlJobService.callShiftsAPI();
	}
}

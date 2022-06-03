package com.smartcat.etljob;

import com.smartcat.etljob.services.ETLJobService;
import com.smartcat.etljob.services.KPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

	private final ETLJobService etlJobService;
	private final KPIService kpiService;

	@Autowired
	public ApplicationStartup(ETLJobService etlJobService, KPIService kpiService) {
		this.etlJobService = etlJobService;
		this.kpiService = kpiService;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		System.out.println("EMINAAAAAAAAAAAAAAAAAAAAA");
		try {
			etlJobService.getShifts();
			kpiService.calculateKPIs();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

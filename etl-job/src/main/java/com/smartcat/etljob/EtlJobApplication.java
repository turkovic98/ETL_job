package com.smartcat.etljob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EtlJobApplication {

	public static void main(String[] args) {
		SpringApplication.run(EtlJobApplication.class, args);
	}

}

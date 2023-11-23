package com.onehealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class LabTestManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabTestManagementServiceApplication.class, args);
	}

	@GetMapping
	public String welcome() {
		return "Lab Test Managemnet";
	}
}

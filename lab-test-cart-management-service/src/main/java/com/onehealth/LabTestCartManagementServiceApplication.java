package com.onehealth;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LabTestCartManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabTestCartManagementServiceApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper();
	}
	
	@GetMapping
    public ResponseEntity<String> view(){
    	return ResponseEntity.ok("Lab Tests Cart !!");
    }

}

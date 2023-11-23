package com.onehealth;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.modelmapper.config.Configuration;

@SpringBootApplication
@RestController
public class LabTestOrderManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabTestOrderManagementServiceApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setFieldMatchingEnabled(true)
            .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        // Additional configuration here if needed

        return modelMapper;
	}
	
	@GetMapping
	public ResponseEntity<String> view(){
		return ResponseEntity.ok("Lab Tests Order !!");
	}
}

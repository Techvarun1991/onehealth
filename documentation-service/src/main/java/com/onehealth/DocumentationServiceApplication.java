package com.onehealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import jakarta.servlet.MultipartConfigElement;

//import com.onehealth.controller.CommonsMultipartResolver;

@SpringBootApplication
public class DocumentationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentationServiceApplication.class, args);
	}
	
	
	 @Bean
	    public MultipartConfigElement multipartConfigElement() {
	        return new MultipartConfigElement("");
	    }
	
}

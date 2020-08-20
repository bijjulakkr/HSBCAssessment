package com.hsbc.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EntityScan(basePackages = {"com.hsbc.assessment.entity"})
//@ComponentScan(basePackages = {"com.hsbc.assessment"} )
public class HsbcAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(HsbcAssessmentApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		return  new RestTemplate();
	}


}

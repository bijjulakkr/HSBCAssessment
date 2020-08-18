package com.hsbc.assessment.controller;

import java.time.LocalDate;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.hsbc.assessment.entity.RateEntity;
import com.hsbc.assessment.repository.Ratesrepository;

@EnableScheduling
@RestController
public class RatesController {

	private static String symbols = "https://api.ratesapi.io/api/{date}?base=EUR&symbols=USD,GBP,HKD";

	@Autowired
	private Ratesrepository repository;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/setRates")
	public Iterable<RateEntity> setRates() {

		ResponseEntity<RateEntity> re= null;

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		LocalDate currentDate = LocalDateTime.now().toLocalDate().withDayOfMonth(1);

		List<RateEntity> rateEntityList = new ArrayList<RateEntity>();
		int i = 0;
		if(validateForDuplicates( currentDate)) {
		do {
			
			re = restTemplate.exchange(symbols, HttpMethod.GET, entity, RateEntity.class, currentDate.toString());
			rateEntityList.add(re.getBody());
			repository.save(re.getBody());
			currentDate = currentDate.minusMonths(1);
			i++;
		} while (i < 12);
		}
		return rateEntityList;
	}
	
	//verifies the records with current date exist in DB. If exists, skip the call to external end-point.
	private boolean validateForDuplicates(LocalDate currentDate) {
				
		if( (repository.findByDate(currentDate.minusMonths(1)) == null) )
			return true;
		else 
			return false;
		
	}

	@Scheduled(cron=" 0 0 23 * * * ")
	@GetMapping("/setRatesByDay")
	public RateEntity setRateByDay() {
		ResponseEntity<RateEntity> re;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		LocalDate currentDate = LocalDateTime.now().toLocalDate();
		re = restTemplate.exchange(symbols, HttpMethod.GET, entity, RateEntity.class, currentDate.toString());
		repository.save(re.getBody());
		return re.getBody();
		
	}

	@GetMapping("/getRatesByDateRange/{dateRange}")
	public Iterable<RateEntity> getRatesByDateRange(@PathVariable String dateRange) {
		LocalDate date2 = LocalDateTime.now().toLocalDate().withDayOfMonth(1);
		LocalDate date1 = LocalDate.parse(dateRange).withDayOfMonth(1);

		return repository.findByDateRange(date1, date2);
	}

	@GetMapping("/getRatesByDate/{dateStr}")
	public RateEntity getRatesByDate(@PathVariable String dateStr) {
		
		LocalDate date = LocalDate.parse(dateStr);
		

		return repository.findByDate(date);
	}

}

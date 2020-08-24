package com.hsbc.assessment.controller;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hsbc.assessment.entity.RateEntity;
import com.hsbc.assessment.service.RatesServiceIntf;

@EnableScheduling
@RestController
public class RatesController {

	
	@Autowired
	private RatesServiceIntf service;

	@GetMapping("/GetCurrencyRates")
	public String setRates() {

		int result = service.saveRates();

		if (result == 1)
			return "Succesffuly saved the records into the repository";
		else
			return " Not saved the records into the repository";

	}

	@Scheduled(cron = " 0 0 23 * * * ")
	@GetMapping("/GetCurrencyRate")
	public String setRateByDay() {
		int result = service.saveRatesByDay();

		if (result == 1)
			return " Succesffuly saved the records into the repository";
		else
			return " Not saved the records into the repository";

	}

	@GetMapping("/GetCurrencyRates/{dateRange}")
	public List<RateEntity> getRatesByDateRange(@PathVariable String dateRange) {
		LocalDate date2 = LocalDateTime.now().toLocalDate().withDayOfMonth(1);
		LocalDate date1 = LocalDate.parse(dateRange).withDayOfMonth(1);
		return service.getRatesByDateRange(date1, date2);
	}

	@GetMapping("/GetCurrencyRate/{dateStr}")
	public RateEntity getRatesByDate(@PathVariable String dateStr) {

		LocalDate date = LocalDate.parse(dateStr);

		return service.getRatesByDate(date);
	}

}

package com.hsbc.assessment.service;

import java.time.LocalDate;
import java.util.List;

import com.hsbc.assessment.entity.RateEntity;


public interface RatesServiceIntf {
	int saveRates();
	int saveRatesByDay();
	List<RateEntity> getRatesByDateRange(LocalDate dateStart, LocalDate dateEnd);
	RateEntity getRatesByDate(LocalDate date);
	
}

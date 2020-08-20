package com.hsbc.assessment.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hsbc.assessment.entity.RateEntity;
import com.hsbc.assessment.repository.Ratesrepository;

public class RatesDAO {
	@Autowired
	Ratesrepository repository;
	public List<RateEntity> getRatesByDate(LocalDate date1, LocalDate date2){
		return repository.findByDateRange(date1, date2);		
	}

}

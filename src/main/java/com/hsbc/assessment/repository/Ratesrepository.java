package com.hsbc.assessment.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.hsbc.assessment.entity.RateEntity;

public interface Ratesrepository extends JpaRepository<RateEntity, Long> {

	
	  @Query(value=" from RateEntity re where re.date between ?1 and ?2")
	  Iterable<RateEntity> findByDateRange(LocalDate startDate, LocalDate endDate);
	  RateEntity findByDate(LocalDate date);
	 
}

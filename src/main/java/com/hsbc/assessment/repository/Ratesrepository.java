package com.hsbc.assessment.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hsbc.assessment.entity.RateEntity;

@Repository
public interface Ratesrepository extends JpaRepository<RateEntity, Long> {

	
	  @Query(value=" from RateEntity re where re.date between ?1 and ?2")
	  List<RateEntity> findByDateRange(LocalDate startDate, LocalDate endDate);
	  
	  RateEntity findByDate(LocalDate date);
	 
}

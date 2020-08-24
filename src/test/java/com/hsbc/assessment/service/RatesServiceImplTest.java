package com.hsbc.assessment.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.hsbc.assessment.entity.RateEntity;
import com.hsbc.assessment.entity.Rates;
import com.hsbc.assessment.repository.Ratesrepository;



@RunWith(MockitoJUnitRunner.class)
@DataJpaTest
public class RatesServiceImplTest {
	@Mock
	private Ratesrepository repository;
	
	@InjectMocks
	private RatesServiceIntf service ;
	
	
	
	private List<RateEntity> list;
	
	public RatesServiceImplTest() {
		
		this.service =  new RatesServiceImpl(this.repository);
	}
	
	@Test
	public void saveRatesForDateRangeTest() {
			
		list = new ArrayList<RateEntity>();
		  list.add(new RateEntity("EUR", new Rates(0.9900, 9.182, 1.1825), LocalDate.parse("2020-08-23")));
		  
		  Mockito.when(repository.findByDateRange(LocalDate.parse("2020-06-01"),
		  LocalDate.parse("2020-07-31"))) .thenReturn(list);
		 System.out.println(service.getRatesByDateRange(LocalDate.parse("2020-06-01"),LocalDate.parse("2020-07-31")));		  
		  
			assertEquals(list, service.getRatesByDateRange(LocalDate.parse("2020-06-01"),LocalDate.parse("2020-07-31")));
	}
	
	@Test
	public void saveRatesForDateTest() {
			
		RateEntity entity = new RateEntity("EUR", new Rates(0.9900, 9.182, 1.1825), LocalDate.parse("2020-08-23"));		  
		  
		  Mockito.when(repository.findByDate(LocalDate.parse("2020-06-01")
		 )) .thenReturn(entity);
		 
		  
			assertEquals(entity, service.getRatesByDate(LocalDate.parse("2020-06-01")));
	}
	
	@Test
	public void saveRatesForDateForValidateTest() {
			
		RateEntity entity = null;		  
		  
		  Mockito.when(repository.findByDate(LocalDate.parse("2020-06-01")
		 )) .thenReturn(entity);		 
		  
		  assertTrue(service.getRatesByDate(LocalDate.parse("2020-06-01"))== null);
			
	}

}

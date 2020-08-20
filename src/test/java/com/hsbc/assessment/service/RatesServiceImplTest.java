package com.hsbc.assessment.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hsbc.assessment.entity.RateEntity;
import com.hsbc.assessment.entity.Rates;
import com.hsbc.assessment.repository.Ratesrepository;


@RunWith(MockitoJUnitRunner.class)
public class RatesServiceImplTest {
	@Mock
	private Ratesrepository repository;
	
	@InjectMocks
	private RatesServiceImpl service = new RatesServiceImpl();
	
	@Before
	public void setup() {
		/*
		 * List<RateEntity> list = new ArrayList<RateEntity>(); list.add(new
		 * RateEntity("EUR", new Rates(0.9900, 9.182, 1.1825),
		 * LocalDate.parse("2020-08-23")));
		 * 
		 * List<RateEntity> result = new ArrayList<RateEntity>();
		 * 
		 * 
		 * 
		 * Mockito.when(repository.findByDateRange(Mockito.any(LocalDate.class),
		 * Mockito.any(LocalDate.class))) .thenReturn(list);
		 */
		
	}
	
	@Test
	public void saveRatesTest() {
		
		List<RateEntity> list = new ArrayList<RateEntity>();
		list.add(new RateEntity("EUR", new Rates(0.9900, 9.182, 1.1825), LocalDate.parse("2020-08-23")));
		
		List<RateEntity> result = new ArrayList<RateEntity>();				
		
		//Mockito.when(repository.findByDateRange(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class)))
		//.thenReturn(list);
		
		Mockito.when(repository.findByDateRange(Mockito.any(), Mockito.any()))
		.thenReturn(list);
		
		
		String expected = "[{\"base\":\"EUR\",\"rates\":{\"GBP\":0.99,\"HKD\":9.182,\"USD\":1.1825},\"date\":\"2020-08-23\"}]";
		//System.out.println(null== service.getRatesByDateRange(LocalDate.parse("2018-03-01"), LocalDate.now()));
		
		assertEquals(list.toString(),service.getRatesByDateRange(LocalDate.now(), LocalDate.parse("2018-03-01")).toString());
		
		
	}

}

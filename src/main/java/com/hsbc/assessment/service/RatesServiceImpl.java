package com.hsbc.assessment.service;

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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hsbc.assessment.entity.RateEntity;
import com.hsbc.assessment.repository.Ratesrepository;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class RatesServiceImpl implements RatesServiceIntf {

	
	  @Autowired private Ratesrepository repository;
	 
	
	//private Ratesrepository repository;
	
	@Autowired
	private RestTemplate restTemplate;

	private static String symbols = "https://api.ratesapi.io/api/{date}?base=EUR&symbols=USD,GBP,HKD";

	private ResponseEntity<RateEntity> re = null;
	
	@Autowired
	public RatesServiceImpl(Ratesrepository repository) {}

	@Override
	public RateEntity getRatesByDate(LocalDate date) {
		return repository.findByDate(date);
	}

	@Override
	public int saveRates() {

		LocalDate currentDate = LocalDateTime.now().toLocalDate().withDayOfMonth(1);

		List<RateEntity> rateEntityList = new ArrayList<RateEntity>();
		int i = 0;
		if (validateForDuplicates(currentDate)) {
			do {

				re = restTemplate.exchange(symbols, HttpMethod.GET, getHttpEntity(), RateEntity.class,
						currentDate.toString());
				rateEntityList.add(re.getBody());
				repository.save(re.getBody());
				currentDate = currentDate.minusMonths(1);
				i++;
			} while (i < 12);
		}
		if (!rateEntityList.isEmpty())
			return 1;
		else
			return 0;
	}

	@Override
	public List<RateEntity> getRatesByDateRange(LocalDate dateStart, LocalDate dateEnd) {
		return repository.findByDateRange(dateStart, dateEnd);
	}

	@Override
	public int saveRatesByDay() {
		LocalDate currentDate = LocalDateTime.now().toLocalDate();
		re = restTemplate.exchange(symbols, HttpMethod.GET, getHttpEntity(), RateEntity.class, currentDate.toString());
		System.out.println("Response from external end-point:"+re.getBody());
		RateEntity rntt = repository.save(re.getBody());
		if (null == rntt)
			return 0;
		else
			return 1;
		
	}

	// verifies the records with current date exist in DB. If exists, skip the call
	// to external end-point.
	private boolean validateForDuplicates(LocalDate currentDate) {

		
		  if ((repository.findByDate(currentDate.minusMonths(1)) == null))
			  return true;
		  else return false;
		 
	}

	private HttpEntity<String> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		return entity;
	}
}

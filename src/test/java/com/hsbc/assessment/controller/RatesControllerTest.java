package com.hsbc.assessment.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hsbc.assessment.entity.RateEntity;
import com.hsbc.assessment.entity.Rates;
import com.hsbc.assessment.service.RatesServiceIntf;

@RunWith(SpringRunner.class)
@WebMvcTest(value = RatesController.class)
@WithMockUser
public class RatesControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RatesServiceIntf service;

	RateEntity mockRateEntity = new RateEntity("EUR", new Rates(0.9900, 9.182, 1.1825), LocalDate.parse("2020-08-23"));

	@Test
	public void setRatesTest() throws Exception {
		Mockito.when(service.saveRates()).thenReturn(1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/setRates").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "Succesffuly saved the records into the repository";

		assertEquals(expected, result.getResponse().getContentAsString());

	}

	@Test
	public void getRatesByDateRangeTest() throws Exception {

		List<RateEntity> list = new ArrayList<RateEntity>();
		list.add(new RateEntity("EUR", new Rates(0.9900, 9.182, 1.1825), LocalDate.parse("2020-08-23")));

		Mockito.when(service.getRatesByDateRange(Mockito.any(LocalDate.class), Mockito.any(LocalDate.class)))
				.thenReturn(list);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getRatesByDateRange/2019-08-21")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expected = "[{\"base\":\"EUR\",\"rates\":{\"GBP\":0.99,\"HKD\":9.182,\"USD\":1.1825},\"date\":\"2020-08-23\"}]";

		System.out.println("Actual getRatesByDateRangeTest()" + result.getResponse().getContentAsString());

		assertEquals(expected, result.getResponse().getContentAsString());
	}

}

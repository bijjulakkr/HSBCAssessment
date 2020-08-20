package com.hsbc.assessment.entity;


import java.time.LocalDate;
import javax.persistence.Column;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Rates")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RateEntity {
	
	
	public RateEntity(String base, Rates rates, LocalDate date) {
		super();
		this.base = base;
		this.rates = rates;
		this.date = date;
	}
	@Override
	public String toString() {
		return "RateEntity [base=" + base + ", rates=" + rates + ", date=" + date + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;
	
	@Column(name = "base")
	private String base;
	
	@Embedded
	private Rates rates;
	
	@Column(name = "local_date", columnDefinition = "DATE")
	private LocalDate date;
	
	public RateEntity() {}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Rates getRates() {
		return rates;
	}

	public void setRates(Rates rates) {
		this.rates = rates;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	
	
	
	
	
	

}

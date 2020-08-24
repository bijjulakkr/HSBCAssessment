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

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Entity
@Table(name = "Rates")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RateEntity {
	
	
	
	  public RateEntity(String base, Rates rates, LocalDate date) { ;
	  this.base = base; this.rates = rates; this.date = date; }
	 
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((rates == null) ? 0 : rates.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RateEntity other = (RateEntity) obj;
		if (base == null) {
			if (other.base != null)
				return false;
		} else if (!base.equals(other.base))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (rates == null) {
			if (other.rates != null)
				return false;
		} else if (!rates.equals(other.rates))
			return false;
		return true;
	}
	
	
	
	
	
	
	

}

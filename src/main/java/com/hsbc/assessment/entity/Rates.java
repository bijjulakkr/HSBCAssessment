package com.hsbc.assessment.entity;

import javax.persistence.Embeddable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Embeddable
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Rates {
	@JsonProperty(value ="GBP")
	private double GBP;
	@JsonProperty(value="HKD")
	private double HKD;
	
	@JsonProperty(value="USD")
	private double USD;
	
	
	/*
	 * @JsonProperty(value ="IDR") private double IDR;
	 * 
	 * @JsonProperty(value="ILS") private double ILS;
	 * 
	 * @JsonProperty(value="DKK") private double DKK;
	 * 
	 * @JsonProperty(value="INR") private double INR;
	 * 
	 * @JsonProperty(value="CHF") private double CHF;
	 * 
	 * @JsonProperty(value="MXN") private double MXN;
	 * 
	 * @JsonProperty(value="CZK") private double CZK;
	 * 
	 * @JsonProperty(value="SGD") private double SGD;
	 * 
	 * @JsonProperty(value="THB") private double THB;
	 * 
	 * @JsonProperty(value="HRK") private double HRK;
	 * 
	 * @JsonProperty(value="MYR") private double MYR;
	 * 
	 * @JsonProperty(value="NOK") private double NOK;
	 * 
	 * @JsonProperty(value="CNY") private double CNY;
	 * 
	 * @JsonProperty(value="BGN") private double BGN;
	 * 
	 * @JsonProperty(value="PHP") private double PHP;
	 * 
	 * @JsonProperty(value="SEK") private double SEK;
	 * 
	 * @JsonProperty(value="PLN") private double PLN;
	 * 
	 * @JsonProperty(value="ZAR") private double ZAR;
	 * 
	 * @JsonProperty(value="CAD") private double CAD;
	 * 
	 * @JsonProperty(value="ISK") private double ISK;
	 * 
	 * @JsonProperty(value="BRL") private double BRL;
	 * 
	 * @JsonProperty(value="RON") private double RON;
	 * 
	 * @JsonProperty(value="NZD") private double NZD;
	 * 
	 * @JsonProperty(value="TRY") private double TRY;
	 * 
	 * @JsonProperty(value="JPY") private double JPY;
	 * 
	 * @JsonProperty(value="RUB") private double RUB;
	 * 
	 * @JsonProperty(value="KRW") private double KRW;
	 * 
	 * 
	 * @JsonProperty(value="HUF") private double HUF;
	 * 
	 * @JsonProperty(value="AUD") private double AUD;
	 */
	
	public Rates() {}
	
	
	

}

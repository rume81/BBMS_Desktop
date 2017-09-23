package com.ey.application.model;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* NoSpecification.java
* --------------------
* Created on Feb 24, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Feb 24, 2016: Original version (Monsur)
*
*/
public class NoSpecification {
	private Double bid_interest_rate;
	private Float interest_rate_by_bid_amount_of_money;
	private Double average_interest_rate;
	private Float total_amount_of_money;
	public Double getBid_interest_rate() {
		return bid_interest_rate;
	}
	public void setBid_interest_rate(Double bid_interest_rate) {
		this.bid_interest_rate = bid_interest_rate;
	}
	public Float getInterest_rate_by_bid_amount_of_money() {
		return interest_rate_by_bid_amount_of_money;
	}
	public void setInterest_rate_by_bid_amount_of_money(Float interest_rate_by_bid_amount_of_money) {
		this.interest_rate_by_bid_amount_of_money = interest_rate_by_bid_amount_of_money;
	}
	public Double getAverage_interest_rate() {
		return average_interest_rate;
	}
	public void setAverage_interest_rate(Double average_interest_rate) {
		this.average_interest_rate = average_interest_rate;
	}
	public Float getTotal_amount_of_money() {
		return total_amount_of_money;
	}
	public void setTotal_amount_of_money(Float total_amount_of_money) {
		this.total_amount_of_money = total_amount_of_money;
	}
}


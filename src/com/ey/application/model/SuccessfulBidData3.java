package com.ey.application.model;

import java.util.Date;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* SuccessfulBidData3.java
* --------------------
* Created on Mar 2, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Mar 2, 2016: Original version (Monsur)
*
*/
public class SuccessfulBidData3 extends BidDataInterestRateOrder {
	
	
	private double successful_bid_interest_rate;
	private float successful_bid_price;
	private int financial_institutions_by_flag;
	private String auction_date;
	private String department_name;
	
	public double getSuccessful_bid_interest_rate() {
		return successful_bid_interest_rate;
	}
	public void setSuccessful_bid_interest_rate(double successful_bid_interest_rate) {
		this.successful_bid_interest_rate = successful_bid_interest_rate;
	}
	public float getSuccessful_bid_price() {
		return successful_bid_price;
	}
	public void setSuccessful_bid_price(float successful_bid_price) {
		this.successful_bid_price = successful_bid_price;
	}
	public int getFinancial_institutions_by_flag() {
		return financial_institutions_by_flag;
	}
	public void setFinancial_institutions_by_flag(int financial_institutions_by_flag) {
		this.financial_institutions_by_flag = financial_institutions_by_flag;
	}
	public String getAuction_date() {
		return auction_date;
	}
	public void setAuction_date(String auction_date) {
		this.auction_date = auction_date;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}	
}


package com.ey.application.model;

import java.util.Date;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* SuccessfulBidData.java
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
public class SuccessfulBidData extends BidDataInterestRateOrder{

	private double successful_bid_interest_rate;
	private float successful_bid_amount_money;
	private String auction_date;
	
	public double getSuccessful_bid_interest_rate() {
		return successful_bid_interest_rate;
	}
	public void setSuccessful_bid_interest_rate(double successful_bid_interest_rate) {
		this.successful_bid_interest_rate = successful_bid_interest_rate;
	}
	public float getSuccessful_bid_amount_money() {
		return successful_bid_amount_money;
	}
	public void setSuccessful_bid_amount_money(float successful_bid_amount_money) {
		this.successful_bid_amount_money = successful_bid_amount_money;
	}
	public String getAuction_date() {
		return auction_date;
	}
	public void setAuction_date(String auction_date) {
		this.auction_date = auction_date;
	}
	
	 
}


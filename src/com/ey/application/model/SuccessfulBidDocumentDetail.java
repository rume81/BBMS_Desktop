package com.ey.application.model;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* SuccessfulBidDocument.java
* --------------------
* Created on Mar 11, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Mar 11, 2016: Original version (Monsur)
*
*/
public class SuccessfulBidDocumentDetail {
	private String department_code;
	private String department_name;
	private String business_category;
	private	String bank_code;
	private String financial_institution_name;
	private Integer bid_number1;
	private Double bid_interest_rate;
	private Float bid_amount_of_money;
	
	private Double successful_bid_interest_rate;
	private Float successful_bid_price;
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getBusiness_category() {
		return business_category;
	}
	public void setBusiness_category(String business_category) {
		this.business_category = business_category;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public String getFinancial_institution_name() {
		return financial_institution_name;
	}
	public void setFinancial_institution_name(String financial_institution_name) {
		this.financial_institution_name = financial_institution_name;
	}
	public Integer getBid_number1() {
		return bid_number1;
	}
	public void setBid_number1(Integer bid_number1) {
		this.bid_number1 = bid_number1;
	}
	public Double getBid_interest_rate() {
		return bid_interest_rate;
	}
	public void setBid_interest_rate(Double bid_interest_rate) {
		this.bid_interest_rate = bid_interest_rate;
	}
	public Float getBid_amount_of_money() {
		return bid_amount_of_money;
	}
	public void setBid_amount_of_money(Float bid_amount_of_money) {
		this.bid_amount_of_money = bid_amount_of_money;
	}
	public Double getSuccessful_bid_interest_rate() {
		return successful_bid_interest_rate;
	}
	public void setSuccessful_bid_interest_rate(Double successful_bid_interest_rate) {
		this.successful_bid_interest_rate = successful_bid_interest_rate;
	}
	public Float getSuccessful_bid_price() {
		return successful_bid_price;
	}
	public void setSuccessful_bid_price(Float successful_bid_price) {
		this.successful_bid_price = successful_bid_price;
	}
	
}


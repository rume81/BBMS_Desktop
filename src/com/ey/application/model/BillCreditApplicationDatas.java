package com.ey.application.model;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* BillCreaditApplicationDatas.java
* --------------------
* Created on Mar 15, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Mar 15, 2016: Original version (Monsur)
*
*/
public class BillCreditApplicationDatas {
	private String department_code;
	private String bank_code;
	private String financial_institution_name;
	private Double successful_bid_interest_rate;
	private Float successful_bid_price;
	private String bill_number;
	private String drawer_date;
	private String maturity_date;
	private Long number_of_days;
	private Double interest_rate;
	private Double amount_of_money;
	private Float interest_amount;
	private String remarks;
	private String auction_date;
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
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
	public String getBill_number() {
		return bill_number;
	}
	public void setBill_number(String bill_number) {
		this.bill_number = bill_number;
	}
	public String getDrawer_date() {
		return drawer_date;
	}
	public void setDrawer_date(String drawer_date) {
		this.drawer_date = drawer_date;
	}
	public String getMaturity_date() {
		return maturity_date;
	}
	public void setMaturity_date(String maturity_date) {
		this.maturity_date = maturity_date;
	}
	public Long getNumber_of_days() {
		return number_of_days;
	}
	public void setNumber_of_days(Long number_of_days) {
		this.number_of_days = number_of_days;
	}
	public Double getInterest_rate() {
		return interest_rate;
	}
	public void setInterest_rate(Double interest_rate) {
		this.interest_rate = interest_rate;
	}
	public Double getAmount_of_money() {
		return amount_of_money;
	}
	public void setAmount_of_money(Double amount_of_money) {
		this.amount_of_money = amount_of_money;
	}
	public Float getInterest_amount() {
		return interest_amount;
	}
	public void setInterest_amount(Float interest_amount) {
		this.interest_amount = interest_amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAuction_date() {
		return auction_date;
	}
	public void setAuction_date(String auction_date) {
		this.auction_date = auction_date;
	}
}


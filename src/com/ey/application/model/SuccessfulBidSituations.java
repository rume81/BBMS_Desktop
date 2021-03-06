package com.ey.application.model;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* SuccessfulBidSituations.java
* --------------------
* Created on Mar 8, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Mar 8, 2016: Original version (Monsur)
*
*/
public class SuccessfulBidSituations {
	private Long sno;
	private String department_code;
	private String department_name;
	private Double bid_interest_rate;
	private Double bid_interest_rate2;
	private Float interest_rate_by_bid_amount_of_money;
	private	Double average_rate;
	private Float total_amount_of_money;
	private Float cumulative_interest;
	private String business_category;
	private	String bank_code;
	private String financial_institution_name;
	private Integer bid_number;
	private Float bid_amount_of_money;
	public Long getSno() {
		return sno;
	}
	public void setSno(Long sno) {
		this.sno = sno;
	}
	public String getDepartment_code() {
		return department_code;
	}
	public void setDepartment_code(String department_code) {
		this.department_code = department_code;
	}
	public Double getBid_interest_rate() {
		return bid_interest_rate;
	}
	public void setBid_interest_rate(Double bid_interest_rate) {
		this.bid_interest_rate = bid_interest_rate;
	}
	public Double getBid_interest_rate2() {
		return bid_interest_rate2;
	}
	public void setBid_interest_rate2(Double bid_interest_rate2) {
		this.bid_interest_rate2 = bid_interest_rate2;
	}
	public Float getInterest_rate_by_bid_amount_of_money() {
		return interest_rate_by_bid_amount_of_money;
	}
	public void setInterest_rate_by_bid_amount_of_money(Float interest_rate_by_bid_amount_of_money) {
		this.interest_rate_by_bid_amount_of_money = interest_rate_by_bid_amount_of_money;
	}
	public Double getAverage_rate() {
		return average_rate;
	}
	public void setAverage_rate(Double average_rate) {
		this.average_rate = average_rate;
	}
	public Float getTotal_amount_of_money() {
		return total_amount_of_money;
	}
	public void setTotal_amount_of_money(Float total_amount_of_money) {
		this.total_amount_of_money = total_amount_of_money;
	}
	public Float getCumulative_interest() {
		return cumulative_interest;
	}
	public void setCumulative_interest(Float cumulative_interest) {
		this.cumulative_interest = cumulative_interest;
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
	public Integer getBid_number() {
		return bid_number;
	}
	public void setBid_number(Integer bid_number) {
		this.bid_number = bid_number;
	}
	public Float getBid_amount_of_money() {
		return bid_amount_of_money;
	}
	public void setBid_amount_of_money(Float bid_amount_of_money) {
		this.bid_amount_of_money = bid_amount_of_money;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

}


package com.ey.application.model;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* FinancialInstitutions.java
* --------------------
* Created on Feb 22, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Feb 22, 2016: Original version (Monsur)
*
*/
public class FinancialInstitutions {
	public String department_code;
	public String department_name;
	public String business_category;
	public String financial_institution_name;
	public String  bank_name;
	public String bank_code;
	public Integer bid_number;
	public Double bid_interest_rate;
	public Float bid_amount_money;
	
	
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
	public String getFinancial_institution_name() {
		return financial_institution_name;
	}
	public void setFinancial_institution_name(String financial_institution_name) {
		this.financial_institution_name = financial_institution_name;
	}
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public Integer getBid_number() {
		return bid_number;
	}
	public void setBid_number(Integer bid_number) {
		this.bid_number = bid_number;
	}
	public Double getBid_interest_rate() {
		return bid_interest_rate;
	}
	public void setBid_interest_rate(Double bid_interest_rate) {
		this.bid_interest_rate = bid_interest_rate;
	}
	public Float getBid_amount_money() {
		return bid_amount_money;
	}
	public void setBid_amount_money(Float bid_amount_money) {
		this.bid_amount_money = bid_amount_money;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	
	
}


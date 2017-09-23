package com.ey.application.model;

import java.util.List;

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
public class SuccessfulBidDocument {
	private	String bank_code;
	private List<SuccessfulBidDocumentDetail> SuccessfulBidDocumentDetail;
	
	public String getBank_code() {
		return bank_code;
	}
	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}
	public List<SuccessfulBidDocumentDetail> getSuccessfulBidDocumentDetail() {
		return SuccessfulBidDocumentDetail;
	}
	public void setSuccessfulBidDocumentDetail(List<SuccessfulBidDocumentDetail> successfulBidDocumentDetail) {
		SuccessfulBidDocumentDetail = successfulBidDocumentDetail;
	}
}


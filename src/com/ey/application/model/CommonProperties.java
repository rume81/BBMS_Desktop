package com.ey.application.model;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* CommonProperties.java
* --------------------
* Created on Feb 17, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Feb 17, 2016: Original version (Monsur)
*
*/
public class CommonProperties {
	private String workingDir;
	private String imageDir;
	
	public CommonProperties()
	{
		workingDir = System.getProperty("user.dir");
		imageDir = workingDir+"\\IMAGE\\";
	}

	public String getWorkingDir() {
		return workingDir;
	}

	public String getImageDir() {
		return imageDir;
	}
	
}


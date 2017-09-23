package com.ey.application.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ey.application.controller.DBManager;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* Excelsheet.java
* --------------------
* Created on Mar 31, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Mar 31, 2016: Original version (Monsur)
*
*/
public class Excelsheet {
	public void CreateBidDataExcel(String filePath) {
		try {
			DBManager db = new DBManager();
			ResultSet rs;
			try {
				rs = db.getRecord("SELECT * FROM bid_datas");
				
				// Create blank workbook
				XSSFWorkbook workbook = new XSSFWorkbook();
				// Create a blank sheet
				XSSFSheet spreadsheet = workbook.createSheet("入札データ");
				// Create row object
				XSSFRow row;
				// This data needs to be written (Object[])
				
				ResultSetMetaData rsMetaData = rs.getMetaData();
			    int numberOfColumns = rsMetaData.getColumnCount();
			    // get the column names; column indexes start from 1
			    int rowid = 0;
			    int cellidMeta = 0;
			    row = spreadsheet.createRow(rowid++);
			    for (int i = 1; i < numberOfColumns + 1; i++) {
			      String columnName = rsMetaData.getColumnName(i);
			      Cell cell = row.createCell(cellidMeta++);
			      cell.setCellValue(columnName);
			    }
			    
			    NumberFormat formatter = new DecimalFormat("#0.0000");
				while (rs.next()) {
					row = spreadsheet.createRow(rowid++);
					Object[] objectArr = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),String.valueOf(rs.getInt(5)),String.valueOf(formatter.format(rs.getDouble(6))),String.valueOf(rs.getFloat(7)),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)};
					int cellid = 0;
					for (Object obj : objectArr) {
						Cell cell = row.createCell(cellid++);
						cell.setCellValue((String) obj);
					}
				}
				
				// Write the workbook in file system
				FileOutputStream out = null;
				try {
					out = new FileOutputStream(new File(filePath + "\\入札データ.xlsx"));
					workbook.write(out);
					out.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//System.out.println("Writesheet.xlsx written successfully");
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void BillCreditApplicationDataExcel(String filePath) {
		try {
			DBManager db = new DBManager();
			ResultSet rs;
			try {
				rs = db.getRecord("SELECT * FROM bill_credit_application_datas");
				
				// Create blank workbook
				XSSFWorkbook workbook = new XSSFWorkbook();
				// Create a blank sheet
				XSSFSheet billsheet = workbook.createSheet("借入データ");
				// Create row object
				XSSFRow row;
				// This data needs to be written (Object[])
				
				ResultSetMetaData rsMetaData = rs.getMetaData();
			    int numberOfColumns = rsMetaData.getColumnCount();
			    // get the column names; column indexes start from 1
			    int rowid = 0;
			    int cellidMeta = 0;
			    row = billsheet.createRow(rowid++);
			    for (int i = 1; i < numberOfColumns + 1; i++) {
			      String columnName = rsMetaData.getColumnName(i);
			      Cell cell = row.createCell(cellidMeta++);
			      cell.setCellValue(columnName);
			    }
			    NumberFormat formatter = new DecimalFormat("#0.0000");	
			    NumberFormat formatter1 = new DecimalFormat("#0.00");
			    
				while (rs.next()) {
					row = billsheet.createRow(rowid++);
					Object[] objectArr = {rs.getString(1),rs.getString(2),rs.getString(3),String.valueOf(formatter.format(rs.getDouble(4))),String.valueOf(rs.getFloat(5)),rs.getString(6),rs.getString(7),rs.getString(8),String.valueOf(rs.getInt(9)),String.valueOf(formatter.format(rs.getDouble(10))),String.valueOf(formatter1.format(rs.getFloat(11))),String.valueOf(rs.getFloat(12)),rs.getString(13),rs.getString(14)};
					int cellid = 0;
					for (Object obj : objectArr) {
						Cell cell = row.createCell(cellid++);
						cell.setCellValue((String) obj);
					}
				}
				
				// Write the workbook in file system
				FileOutputStream out = null;
				try {
					out = new FileOutputStream(new File(filePath + "\\借入データ.xlsx"));
					workbook.write(out);
					out.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Writesheet.xlsx written successfully");
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void SuccessfullBidDdatas3DataExcel(String filePath) {
		try {
			DBManager db = new DBManager();
			ResultSet rs;
			try {
				rs = db.getRecord("SELECT * FROM successful_bid_datas_3");
				
				// Create blank workbook
				XSSFWorkbook workbook = new XSSFWorkbook();
				// Create a blank sheet
				XSSFSheet spreadsheet = workbook.createSheet("落札データ");
				// Create row object
				XSSFRow row;
				// This data needs to be written (Object[])
				
				ResultSetMetaData rsMetaData = rs.getMetaData();
			    int numberOfColumns = rsMetaData.getColumnCount();
			    // get the column names; column indexes start from 1
			    int rowid = 0;
			    int cellidMeta = 0;
			    row = spreadsheet.createRow(rowid++);
			    for (int i = 1; i < numberOfColumns + 1; i++) {
			      String columnName = rsMetaData.getColumnName(i);
			      Cell cell = row.createCell(cellidMeta++);
			      cell.setCellValue(columnName);
			    }			    
			    
			    NumberFormat formatter = new DecimalFormat("#0.0000");	
			    
				while (rs.next()) {
					row = spreadsheet.createRow(rowid++);
					Object[] objectArr = {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),String.valueOf(rs.getInt(5)),String.valueOf(formatter.format(rs.getDouble(6))),String.valueOf(rs.getFloat(7)),String.valueOf(formatter.format(rs.getDouble(8))),String.valueOf(rs.getFloat(9)),String.valueOf(rs.getInt(10)),rs.getString(11)};
					int cellid = 0;
					for (Object obj : objectArr) {
						Cell cell = row.createCell(cellid++);
						cell.setCellValue((String) obj);
					}
				}
				
				// Write the workbook in file system
				FileOutputStream out = null;
				try {
					out = new FileOutputStream(new File(filePath + "\\落札データ.xlsx"));
					workbook.write(out);
					out.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Writesheet.xlsx written successfully");
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}


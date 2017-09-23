package com.ey.application.view;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.ey.application.controller.DBManager;
import com.ey.application.model.FinancialInstitutions;
import com.ey.application.model.NoSpecification;
import com.ey.application.model.SuccessfulBidData3;
import com.ey.application.model.SuccessfulBidDocument;
import com.ey.application.model.SuccessfulBidDocumentDetail;
import com.ey.application.model.SuccessfulBidSituations;
import com.ey.application.model.ThereSpecification;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* ReportBeanList.java
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
public class ReportBeanList {
	public List<FinancialInstitutions> getFinancialInstitutionsBeanList(String bidDate) {
		List<FinancialInstitutions> dataBeanList = new ArrayList<FinancialInstitutions>();

		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				String sql = "SELECT bid_datas2.department_code, bid_datas2.department_name, bid_datas2.business_category, name_conversions.financial_institution_name," + 
							"bid_datas2.financial_institution_name  bank_name, bid_datas2.bank_code,bid_datas2.bid_number,bid_datas2.bid_interest_rate,bid_datas2.bid_amount_money " +
							"FROM bid_datas2 LEFT JOIN name_conversions ON bid_datas2.bank_code = name_conversions.bank_code " +
							"WHERE ((bid_datas2.auction_date) = '"+bidDate+"')";
				
				//System.out.println(sql);
				rs = db.getRecord(sql);
				while (rs.next()) {
					FinancialInstitutions fi = new FinancialInstitutions();
					fi.setDepartment_code(rs.getString(1));
					fi.setDepartment_name(rs.getString(2));
					fi.setBusiness_category(rs.getString(3));
					fi.setFinancial_institution_name(rs.getString(4));
					fi.setBank_name(rs.getString(5));
					fi.setBank_code(rs.getString(6));
					fi.setBid_number(rs.getInt(7));
					fi.setBid_interest_rate(rs.getDouble(8));
					fi.setBid_amount_money(rs.getFloat(9));
					
					dataBeanList.add(fi);
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "検索データが見つかりませんが、有効なデータをお試しください", "エラー", JOptionPane.ERROR_MESSAGE);
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataBeanList;
	}
	
	public List<ThereSpecification> getThereSpecificationBeanList() {
		List<ThereSpecification> dataBeanList = new ArrayList<ThereSpecification>();

		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				String sql = "SELECT bid_status.SNO, bid_status.department_code,bid_status.bid_interest_rate,"+
						"bid_status.bid_interest_rate2,bid_status.interest_rate_by_bid_amount_of_money,bid_status.average_rate,"+
						"bid_status.total_amount_of_money,bid_status.cumulative_interest,bid_status.business_category,"+
						"bid_status.bank_code,bid_status.financial_institution_name bank_name,name_conversions.financial_institution_name,"+
						"bid_status.bid_number,bid_status.bid_amount_of_money,bid_status.bid_interest_rate_key "+
						"FROM bid_status LEFT JOIN name_conversions ON bid_status.bank_code = name_conversions.bank_code";
				
				//System.out.println(sql);
				rs = db.getRecord(sql);
				while (rs.next()) {
					ThereSpecification fi = new ThereSpecification();
					fi.setSno(rs.getLong(1));
					fi.setDepartment_code(rs.getString(2));
					fi.setBid_interest_rate(rs.getDouble(3));
					fi.setBid_interest_rate2(rs.getDouble(4));
					fi.setInterest_rate_by_bid_amount_of_money(rs.getFloat(5));
					BigDecimal bg = new BigDecimal(rs.getDouble(6));
					BigDecimal bg1;
					bg1= bg.setScale(3, RoundingMode.FLOOR);
					//System.out.println(bg1);
					fi.setAverage_rate(Double.parseDouble(bg1.toString()));
					fi.setTotal_amount_of_money(rs.getDouble(7));
					fi.setCumulative_interest(rs.getDouble(8));
					fi.setBusiness_category(rs.getString(9));
					fi.setBank_code(rs.getString(10));
					fi.setBank_name(rs.getString(11));
					fi.setFinancial_institution_name(rs.getString(12));
					fi.setBid_number(rs.getInt(13));
					fi.setBid_amount_of_money(rs.getFloat(14));
					fi.setBid_interest_rate_key(rs.getString(15));
					
					dataBeanList.add(fi);
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "検索データが見つかりませんが、有効なデータをお試しください", "エラー", JOptionPane.ERROR_MESSAGE);
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataBeanList;
	}
	
	public List<NoSpecification> getNOSpecificationBeanList() {
		List<NoSpecification> dataBeanList = new ArrayList<NoSpecification>();

		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				String sql = "SELECT DISTINCT bid_status.bid_interest_rate," +
							 "Max(bid_status.interest_rate_by_bid_amount_of_money) AS interest_rate_by_bid_amount_of_money," +
							 "Max(bid_status.average_rate) AS average_interest_rate," +
							 "Max(bid_status.total_amount_of_money) AS total_amount_of_money " +
							 "FROM bid_status " +
							 "GROUP BY bid_status. bid_interest_rate " +
							 "HAVING bid_status.bid_interest_rate IS NOT NULL";
				
				//System.out.println(sql);
				rs = db.getRecord(sql);
				while (rs.next()) {
					NoSpecification fi = new NoSpecification();
					fi.setBid_interest_rate(rs.getDouble(1));
					fi.setInterest_rate_by_bid_amount_of_money(rs.getFloat(2));
					BigDecimal bg = new BigDecimal(rs.getDouble(3));
					BigDecimal bg1;
					bg1= bg.setScale(3, RoundingMode.FLOOR);
					fi.setAverage_interest_rate(Double.parseDouble(bg1.toString()));
					fi.setTotal_amount_of_money(rs.getFloat(4));
										
					dataBeanList.add(fi);
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "検索データが見つかりませんが、有効なデータをお試しください", "エラー", JOptionPane.ERROR_MESSAGE);
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataBeanList;
	}
	
	
	public List<SuccessfulBidData3> getSuccessfulBidStatusTableByBank(String bidDate, String dept_code) {
		List<SuccessfulBidData3> dataBeanList = new ArrayList<SuccessfulBidData3>();

		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				String sql = "SELECT DISTINCT successful_bid_datas_3.department_code, divisions.department_name, successful_bid_datas_3.business_category," + 
							 " successful_bid_datas_3.bank_code, successful_bid_datas_3.financial_institution_name, successful_bid_datas_3.bid_number,"  + 
							 " successful_bid_datas_3.bid_interest_rate, successful_bid_datas_3.bid_amount_money, successful_bid_datas_3.successful_bid_interest_rate," + 
							 " successful_bid_datas_3.successful_bid_price FROM successful_bid_datas_3 LEFT JOIN divisions ON successful_bid_datas_3.department_code = divisions.department_code" +
							 " WHERE (((successful_bid_datas_3.department_code) = '"+dept_code+"') AND ((successful_bid_datas_3.auction_date) = '"+bidDate+"'));";
				
				//System.out.println(sql);
				rs = db.getRecord(sql);
				while (rs.next()) {
					SuccessfulBidData3 fi = new SuccessfulBidData3();
					fi.setDepartment_code(rs.getString(1));
					fi.setDepartment_name(rs.getString(2));
					fi.setBusiness_category(rs.getString(3));
					fi.setBank_code(rs.getString(4));
					fi.setFinancial_institution_name(rs.getString(5));
					fi.setBid_number(rs.getInt(6));
					fi.setBid_interest_rate(rs.getDouble(7));
					fi.setBid_amount_money(rs.getFloat(8));
					if((fi.getBid_number()==98) || (fi.getBid_number()==99))
					{
						BigDecimal bg = new BigDecimal(rs.getDouble(9));
						BigDecimal bg1;
						bg1= bg.setScale(3, RoundingMode.FLOOR);
						fi.setSuccessful_bid_interest_rate(Double.parseDouble(bg1.toString()));
					} else
						fi.setSuccessful_bid_interest_rate(rs.getDouble(9));
					fi.setSuccessful_bid_price(rs.getFloat(10));
										
					dataBeanList.add(fi);
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "検索データが見つかりませんが、有効なデータをお試しください", "エラー", JOptionPane.ERROR_MESSAGE);
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataBeanList;
	}
	
	public List<SuccessfulBidSituations> getInterestRateBy(String bidDate, String dept_code) {
		List<SuccessfulBidSituations> dataBeanList = new ArrayList<SuccessfulBidSituations>();

		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				String sql = "SELECT DISTINCT  successful_bid_situations.department_code,divisions.department_name,successful_bid_situations.bid_interest_rate,successful_bid_situations.bid_interest_rate2, " +
							 "successful_bid_situations.interest_rate_by_bid_amount_of_money, successful_bid_situations.average_rate,successful_bid_situations.total_amount_of_money,successful_bid_situations.cumulative_interest,successful_bid_situations.business_category, " +
							 "successful_bid_situations.bank_code,successful_bid_situations.financial_institution_name, successful_bid_situations.bid_number, successful_bid_situations.bid_amount_of_money " +
							 "FROM successful_bid_situations LEFT JOIN divisions ON successful_bid_situations.department_code = divisions.department_code " +
							 "WHERE (((successful_bid_situations.department_code) = '"+dept_code+"')) ORDER BY successful_bid_situations.bid_interest_rate,successful_bid_situations.bank_code";
				
				//System.out.println(sql);
				rs = db.getRecord(sql);
				while (rs.next()) {
					SuccessfulBidSituations fi = new SuccessfulBidSituations();
					fi.setDepartment_code(rs.getString(1));
					fi.setDepartment_name(rs.getString(2));
					fi.setBid_interest_rate(rs.getDouble(3));
					fi.setBid_interest_rate2(rs.getDouble(4));
					fi.setInterest_rate_by_bid_amount_of_money(rs.getFloat(5));
					BigDecimal bg = new BigDecimal(rs.getDouble(6));
					BigDecimal bg1;
					bg1= bg.setScale(3, RoundingMode.FLOOR);
					fi.setAverage_rate(Double.parseDouble(bg1.toString()));
					fi.setTotal_amount_of_money(rs.getFloat(7));
					fi.setCumulative_interest(rs.getFloat(8));
					fi.setBusiness_category(rs.getString(9));
					fi.setBank_code(rs.getString(10));
					fi.setFinancial_institution_name(rs.getString(11));
					fi.setBid_number(rs.getInt(12));
 	 				fi.setBid_amount_of_money(rs.getFloat(13));
 	 														
					dataBeanList.add(fi);
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "検索データが見つかりませんが、有効なデータをお試しください", "エラー", JOptionPane.ERROR_MESSAGE);
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataBeanList;
	}
	
	/*public List<SuccessfulBidDocument> getSuccessfulBidDocument(String bidDate, String dept_code) {
		List<SuccessfulBidDocument> dataBeanList = new ArrayList<SuccessfulBidDocument>();
		List<String> banks = new ArrayList<String>(); 
		try {
			DBManager db = new DBManager();
			DBManager dbS = new DBManager();
			
			
			try {
				
				String sql = "SELECT DISTINCT successful_bid_datas_3.bank_code FROM (successful_bid_datas_3 LEFT JOIN divisions ON successful_bid_datas_3.department_code = divisions.department_code) INNER JOIN bid_datas ON (successful_bid_datas_3.department_code = bid_datas.department_code) AND (successful_bid_datas_3.bank_code = bid_datas.bank_code) AND (successful_bid_datas_3.auction_date = bid_datas.auction_date) "+
						  "WHERE (((successful_bid_datas_3.department_code) = '"+dept_code+"') AND ((successful_bid_datas_3.financial_institution_name) <> '合  計') AND ((successful_bid_datas_3.auction_date) = '"+bidDate+"') AND ((bid_datas.flag1) = '1'))";
			
				System.out.println(sql);
				ResultSet rs = db.getRecord(sql);
				while(rs.next()){
					banks.add(rs.getString(1));
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
			
			try{
				for(String bank:banks) {
					SuccessfulBidDocument fi = new SuccessfulBidDocument();
					List<SuccessfulBidDocumentDetail> listfid = new ArrayList<SuccessfulBidDocumentDetail>();
				
					fi.setBank_code(bank);
				
				
					String sql1 = "SELECT DISTINCT successful_bid_datas_3.department_code,divisions.department_name, successful_bid_datas_3.business_category, successful_bid_datas_3.bank_code, successful_bid_datas_3.financial_institution_name, " + 
							  "CASE WHEN successful_bid_datas_3.bid_number > 10 THEN '合計' ELSE successful_bid_datas_3.bid_number END AS bid_number1, " + 
							  "successful_bid_datas_3.bid_interest_rate, successful_bid_datas_3.bid_amount_money, successful_bid_datas_3.successful_bid_interest_rate, "+ 
							  "successful_bid_datas_3.successful_bid_price FROM (successful_bid_datas_3 LEFT JOIN divisions ON successful_bid_datas_3.department_code = divisions.department_code) INNER JOIN bid_datas ON (successful_bid_datas_3.department_code = bid_datas.department_code) AND (successful_bid_datas_3.bank_code = bid_datas.bank_code) AND (successful_bid_datas_3.auction_date = bid_datas.auction_date) "+
							  "WHERE (((successful_bid_datas_3.department_code) = '"+dept_code+"') AND ((successful_bid_datas_3.financial_institution_name) <> '合  計') AND ((successful_bid_datas_3.auction_date) = '"+bidDate+"') AND ((bid_datas.flag1) = '1') AND (successful_bid_datas_3.bank_code='"+bank+"'))";
					System.out.println(sql1);
					ResultSet rsS = dbS.getRecord(sql1);
					
					while(rsS.next()){
						SuccessfulBidDocumentDetail fid = new SuccessfulBidDocumentDetail();
						
						fid.setDepartment_code(rsS.getString(1));
						fid.setDepartment_name(rsS.getString(2));
						fid.setBusiness_category(rsS.getString(3));
						fid.setBank_code(rsS.getString(4));
						fid.setFinancial_institution_name(rsS.getString(5));
						fid.setBid_number1(rsS.getInt(6));
						fid.setBid_interest_rate(rsS.getDouble(7));
						fid.setBid_amount_of_money(rsS.getFloat(8));
						fid.setSuccessful_bid_interest_rate(rsS.getDouble(9));
						fid.setSuccessful_bid_price(rsS.getFloat(10));
						
						listfid.add(fid);
					}
					fi.setSuccessfulBidDocumentDetail(listfid);
					
					dataBeanList.add(fi);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				dbS.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataBeanList;
	}*/
	
	
	
	
	
	/*public ArrayList<SuccessfulBidDocument> getSuccessfulBidDocument(String bidDate, String dept_code) {
		ArrayList<SuccessfulBidDocument> dataBeanList = new ArrayList<SuccessfulBidDocument>();

		try {
			DBManager db = new DBManager();
			DBManager dbS = new DBManager();
			ResultSet rs;
			try {

				
				String sql = "SELECT DISTINCT successful_bid_datas_3.bank_code FROM (successful_bid_datas_3 LEFT JOIN divisions ON successful_bid_datas_3.department_code = divisions.department_code) INNER JOIN bid_datas ON (successful_bid_datas_3.department_code = bid_datas.department_code) AND (successful_bid_datas_3.bank_code = bid_datas.bank_code) AND (successful_bid_datas_3.auction_date = bid_datas.auction_date) "+
						  "WHERE (((successful_bid_datas_3.department_code) = '"+dept_code+"') AND ((successful_bid_datas_3.financial_institution_name) <> '合  計') AND ((successful_bid_datas_3.auction_date) = '"+bidDate+"') AND ((bid_datas.flag1) = '1'))";
			
				
				
				String sql1 = "SELECT DISTINCT successful_bid_datas_3.department_code,divisions.department_name, successful_bid_datas_3.business_category, successful_bid_datas_3.bank_code, successful_bid_datas_3.financial_institution_name, " + 
						  "CASE WHEN successful_bid_datas_3.bid_number > 10 THEN '合計' ELSE successful_bid_datas_3.bid_number END AS bid_number1, " + 
						  "successful_bid_datas_3.bid_interest_rate, successful_bid_datas_3.bid_amount_money, successful_bid_datas_3.successful_bid_interest_rate, "+ 
						  "successful_bid_datas_3.successful_bid_price FROM (successful_bid_datas_3 LEFT JOIN divisions ON successful_bid_datas_3.department_code = divisions.department_code) INNER JOIN bid_datas ON (successful_bid_datas_3.department_code = bid_datas.department_code) AND (successful_bid_datas_3.bank_code = bid_datas.bank_code) AND (successful_bid_datas_3.auction_date = bid_datas.auction_date) "+
						  "WHERE (((successful_bid_datas_3.department_code) = '"+dept_code+"') AND ((successful_bid_datas_3.financial_institution_name) <> '合  計') AND ((successful_bid_datas_3.auction_date) = '"+bidDate+"') AND ((bid_datas.flag1) = '1'))";
				
				
				//System.out.println(sql);
				rs = db.getRecord(sql);
				while (rs.next()) {
					SuccessfulBidSituations fi = new SuccessfulBidSituations();
					fi.setDepartment_code(rs.getString(1));
					fi.setDepartment_name(rs.getString(2));
					fi.setBid_interest_rate(rs.getDouble(3));
					fi.setBid_interest_rate2(rs.getDouble(4));
					fi.setInterest_rate_by_bid_amount_of_money(rs.getFloat(5));
					fi.setAverage_rate(rs.getDouble(6));
					fi.setTotal_amount_of_money(rs.getFloat(7));
					fi.setCumulative_interest(rs.getFloat(8));
					fi.setBusiness_category(rs.getString(9));
					fi.setBank_code(rs.getString(10));
					fi.setFinancial_institution_name(rs.getString(11));
					fi.setBid_number(rs.getInt(12));
 	 				fi.setBid_amount_of_money(rs.getFloat(13));
 	 														
					dataBeanList.add(fi);
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "検索データが見つかりませんが、有効なデータをお試しください", "エラー", JOptionPane.ERROR_MESSAGE);
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataBeanList;
	}*/
}


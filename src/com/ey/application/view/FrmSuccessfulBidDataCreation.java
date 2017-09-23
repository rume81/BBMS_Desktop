package com.ey.application.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JInternalFrame;

import com.ey.application.controller.DBManager;
import com.ey.application.model.BidDataInterestRateOrder;
import com.ey.application.model.SuccessfulBidData;
import com.ey.application.model.SuccessfulBidData3;
import com.ey.application.model.SuccessfulBidSituations;


/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* FrmSuccessfulBidDataCreation.java
* --------------------
* Created on Feb 16, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Feb 16, 2016: Original version (Monsur)
*
*/

public class FrmSuccessfulBidDataCreation extends JInternalFrame {   
	// Variables declaration - do not modify                     
    private JComboBox cmbDataUpdated;
    private JComboBox cmbDeptCode;
    private JButton cmdCloseup;
    private JButton cmdFinancialInstitutionsBy;
    private JButton cmdInterestRateBy;
    private JLabel lblBidAmountPlanned;
    private JLabel lblBillionsofYen;
    private JLabel lblBillionsofYen2;
    private JLabel lblDataUpdate;
    private JLabel lblDeptCode;
    private JLabel lblFootCutInterestRates;
    private JLabel lblMinAmount;
    private JLabel lblPercentage;
    private JPanel pnlMain;
    private JTextField txtBidAmountPland;
    private JTextField txtDeptName;
    private JTextField txtFootCutInterestRates;
    private JTextField txtMinimumAmount;
    private String bidDate;
    // End of variables declaration  
    
    public FrmSuccessfulBidDataCreation(Date date) {
    	try {
    		String datePattern = "dd-MM-yyyy";
		    SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
			bidDate = dateFormatter.format(date);
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    private void jbInit() throws Exception {
    	pnlMain = new JPanel();
        lblMinAmount = new JLabel();
        lblBillionsofYen2 = new JLabel();
        lblFootCutInterestRates = new JLabel();
        txtDeptName = new JTextField();
        txtFootCutInterestRates = new JTextField();
        lblPercentage = new JLabel();
        cmdCloseup = new JButton();
        cmdFinancialInstitutionsBy = new JButton();
        cmdInterestRateBy = new JButton();
        lblBidAmountPlanned = new JLabel();
        cmbDeptCode = new JComboBox();
        txtBidAmountPland = new JTextField();
        lblDeptCode = new JLabel();
        lblBillionsofYen = new JLabel();
        txtMinimumAmount = new JTextField();
        lblDataUpdate = new JLabel();
        cmbDataUpdated = new JComboBox();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        pnlMain.setBackground(new java.awt.Color(176, 224, 230));
        pnlMain.setLayout(null);

        lblMinAmount.setText("最低限度額");
        pnlMain.add(lblMinAmount);
        lblMinAmount.setBounds(50, 140, 100, 23);
        lblMinAmount.setVisible(false);

        lblBillionsofYen2.setText("（単位：億円）");
        pnlMain.add(lblBillionsofYen2);
        lblBillionsofYen2.setBounds(40, 160, 100, 23);
        lblBillionsofYen2.setVisible(false);

        lblFootCutInterestRates.setText("足切り利率");
        pnlMain.add(lblFootCutInterestRates);
        lblFootCutInterestRates.setBounds(50, 120, 90, 23);
        pnlMain.add(txtDeptName);
        txtDeptName.setBounds(160, 40, 170, 23);
        txtDeptName.setVisible(false);
        pnlMain.add(txtFootCutInterestRates);
        txtFootCutInterestRates.setBounds(160, 120, 110, 23);
        txtFootCutInterestRates.setHorizontalAlignment(JTextField.RIGHT);

        lblPercentage.setText("%");
        pnlMain.add(lblPercentage);
        lblPercentage.setBounds(270, 120, 11, 23);

        cmdCloseup.setText("閉じる");
        cmdCloseup.setToolTipText("");
        pnlMain.add(cmdCloseup);
        cmdCloseup.setBounds(210, 250, 150, 23);
        cmdCloseup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
        });

        cmdFinancialInstitutionsBy.setText("金融機関別");
        pnlMain.add(cmdFinancialInstitutionsBy);
        cmdFinancialInstitutionsBy.setBounds(40, 210, 150, 23);
        cmdFinancialInstitutionsBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RI_T_MAKE(1);
			}
        });

        cmdInterestRateBy.setText("利率別");
        pnlMain.add(cmdInterestRateBy);
        cmdInterestRateBy.setBounds(40, 250, 150, 23);
        cmdInterestRateBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RI_T_MAKE(2);
			}
        });

        lblBidAmountPlanned.setText("入札予定額");
        pnlMain.add(lblBidAmountPlanned);
        lblBidAmountPlanned.setBounds(50, 60, 100, 23);

        departmentCode();
        cmbDeptCode.setVisible(false);
        getDetpInfo(); 
        
        pnlMain.add(cmbDeptCode);
        cmbDeptCode.setBounds(160, 10, 170, 23);
        pnlMain.add(txtBidAmountPland);
        txtBidAmountPland.setBounds(160, 70, 170, 23);
        txtBidAmountPland.setHorizontalAlignment(JTextField.RIGHT);

        lblDeptCode.setText("勘定");
        pnlMain.add(lblDeptCode);
        lblDeptCode.setBounds(50, 10, 100, 23);
        lblDeptCode.setVisible(false);

        lblBillionsofYen.setText("（単位：億円）");
        pnlMain.add(lblBillionsofYen);
        lblBillionsofYen.setBounds(40, 80, 100, 23);

        
        pnlMain.add(txtMinimumAmount);
        txtMinimumAmount.setBounds(160, 150, 170, 23);
        txtMinimumAmount.setVisible(false);

        lblDataUpdate.setText("データの更新");
        pnlMain.add(lblDataUpdate);
        lblDataUpdate.setBounds(50, 180, 110, 23);
        lblDataUpdate.setVisible(false);

        cmbDataUpdated.setModel(new DefaultComboBoxModel<>(new String[] {"", "する", "しない" }));
        cmbDataUpdated.setSelectedItem("する");
        cmbDataUpdated.setVisible(false);
        pnlMain.add(cmbDataUpdated);
        cmbDataUpdated.setBounds(160, 180, 170, 23);

        getContentPane().add(pnlMain);
        pnlMain.setBounds(0, 0, 400, 290);
    }
    
    public void Close()
	{
		FrmBidMain nw = FrmBidMain.getInstance(bidDate);
		nw.setBounds(0, 0, 579, 412);
		nw.getContentPane().setBackground(new Color(176,224,230));
		nw.setForeground(Color.black);
		nw.setResizable(false);
		nw.setTitle("メイン");
		nw.getContentPane().setLayout(null);
		
		if (nw.isVisible()) {
		} else {
			getDesktopPane().add(nw,new Integer(Integer.MAX_VALUE) );
			nw.setVisible(true);
		}

		nw.setIconifiable(true);
		nw.setMaximizable(false);
		nw.setClosable(true);
		dispose();
	}
	private static FrmSuccessfulBidDataCreation myInstance;

 	public static FrmSuccessfulBidDataCreation getInstance(Date date) {
 	    if (myInstance == null) {
 	        myInstance = new FrmSuccessfulBidDataCreation(date);
 	    }
 	    return myInstance;
 	}
 	
 	public void departmentCode() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT DISTINCT divisions.department_code FROM divisions");
				cmbDeptCode.addItem("");
				//int i=0;
				//String deptCode="";
				while(rs.next()) {
					String code = rs.getString(1);
					cmbDeptCode.addItem(code);
					//if(i==0)
						//deptCode = code;
					//i++;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	
 	public void getDetpInfo() {
		try {
			DBManager db = new DBManager();
			try {
				ResultSet rsData;
				rsData = db.getRecord("SELECT department_code,department_name FROM base");
				if (rsData.next()) {
					cmbDeptCode.setSelectedItem(rsData.getString(1));
					txtDeptName.setText(rsData.getString(2));
				} else {
					txtDeptName.setText("");
				}
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	
 	public void getForMinimumAmountDetermined() {
		try {
			DBManager db = new DBManager();
			DBManager dbC = new DBManager();
			float minAmount = 0;
			if(!txtMinimumAmount.getText().equals(""))
				minAmount = Float.parseFloat(txtMinimumAmount.getText());
			try {
				boolean fo = db.doQuery("DROP VIEW IF EXISTS 'ForMinimumAmountDetermined'");	
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
			
			try{
				StringBuffer strSql = new StringBuffer("CREATE VIEW ForMinimumAmountDetermined AS SELECT DISTINCT bid_datas.bank_code, Sum(bid_datas.bid_amount_money) AS BasicPriceTotal FROM bid_datas "+  
						"GROUP BY bid_datas.bank_code HAVING Sum(bid_datas.bid_amount_money)>="+minAmount);
				boolean fo1= dbC.doQuery(strSql.toString());
				
				//System.out.println(strSql);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbC.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
 	
 	public void prorationDataClear(){
 		try {
			DBManager db = new DBManager();
			try {
				boolean fo = db.doQuery("DELETE FROM proration_datas");
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public void bidInterestRateOrderClear(){
 		try {
			DBManager db = new DBManager();
			try {
				boolean fo = db.doQuery("DELETE FROM bid_data_rate_orders");
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public void AddBidInterestRateOrder(double GENDO_RI)
 	{
 		try {
			DBManager db = new DBManager();
			String deptcode = String.valueOf(cmbDeptCode.getSelectedItem());
	 		try {
 				StringBuffer strSql = new StringBuffer("INSERT INTO bid_data_rate_orders (department_code, business_categorie, bank_code, financial_institution_name, bid_number, bid_interest_rate, bid_amount_money) "+ 
				"SELECT DISTINCT bid_datas.department_code, bid_datas.business_category, bid_datas.bank_code, " +
				"bid_datas.financial_institution_name, bid_datas.bid_number, bid_datas.bid_interest_rate, " +
				"bid_datas.bid_amount_money FROM bid_datas INNER JOIN ForMinimumAmountDetermined ON bid_datas.bank_code = ForMinimumAmountDetermined.bank_code " +
				"WHERE bid_datas.auction_date='"+bidDate+"' AND bid_datas.department_code='"+deptcode+"' AND bid_datas.bid_interest_rate<="+ GENDO_RI / 100  +
				" AND bid_datas.flag1='1' ORDER BY bid_datas.bid_interest_rate");
			
				//System.out.println(strSql);
				boolean dStatus=db.doQuery(strSql.toString());
	 		} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public List<BidDataInterestRateOrder> getBidDataRateOrder() {
 		List<BidDataInterestRateOrder> bdirs = new ArrayList<BidDataInterestRateOrder>();
 		try {
 			DBManager db = new DBManager();
 	 		try {
 				StringBuffer strSql = new StringBuffer("SELECT * FROM bid_data_rate_orders");
 				ResultSet rst = db.getRecord(strSql.toString());
 				while(rst.next())
 				{
 					BidDataInterestRateOrder obj = new BidDataInterestRateOrder();
 					obj.setDepartment_code(rst.getString(1));
 					obj.setBusiness_category(rst.getString(2));
 					obj.setBank_code(rst.getString(3));
 					obj.setFinancial_institution_name(rst.getString(4));
 					obj.setBid_number(rst.getInt(5));
 					obj.setBid_interest_rate(rst.getDouble(6));
 					obj.setBid_amount_money(rst.getFloat(7));
 					obj.setTotal_amount_money(rst.getFloat(8));
 					
 					bdirs.add(obj);
 				}
 				
 	 		} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			} finally {
 				db.close();
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
 		return bdirs;
 	}
 	
 	public void UpdateBidInterestRateOrder(BidDataInterestRateOrder obj){
 		try {
			DBManager db = new DBManager();
			try {
				boolean fo = db.doQuery("UPDATE bid_data_rate_orders SET total_amount_money =" +
										obj.getTotal_amount_money()+" WHERE department_code ='" +
										obj.getDepartment_code()+"' AND bank_code = '"+
										obj.getBank_code()+"' AND bid_number ="+obj.getBid_number());
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public List<BidDataInterestRateOrder> getProrationData() {
 		List<BidDataInterestRateOrder> bdirs = new ArrayList<BidDataInterestRateOrder>();
 		try {
 			DBManager db = new DBManager();
 	 		try {
 				StringBuffer strSql = new StringBuffer("SELECT * FROM proration_datas");
 				ResultSet rst = db.getRecord(strSql.toString());
 				while(rst.next()){
 					BidDataInterestRateOrder obj = new BidDataInterestRateOrder();
 					obj.setDepartment_code(rst.getString(1));
 					obj.setBusiness_category(rst.getString(2));
 					obj.setBank_code(rst.getString(3));
 					obj.setFinancial_institution_name(rst.getString(4));
 					obj.setBid_number(rst.getInt(5));
 					obj.setBid_interest_rate(rst.getDouble(6));
 					obj.setBid_amount_money(rst.getFloat(7));
 					obj.setTotal_amount_money(rst.getFloat(8));
 					
 					bdirs.add(obj);
 				}
 	 		} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			} finally {
 				db.close();
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
 		return bdirs;
 	}
 	
 	public void addProrationData(BidDataInterestRateOrder obj) {
 		try {
			DBManager db = new DBManager();
			try {
				StringBuffer str = new StringBuffer("INSERT into proration_datas VALUES('"+
										obj.getDepartment_code()+"','"+
										obj.getBusiness_category()+"','"+
										obj.getBank_code()+"','"+
										obj.getFinancial_institution_name()+"',"+
										obj.getBid_number()+","+
										obj.getBid_interest_rate()+","+
										obj.getBid_amount_money()+","+
										obj.getTotal_amount_money()+")");
				boolean fo = db.doQuery(str.toString());
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public void updateProrationData() {
 		
 		ResultSet rst2=null;
 		List<BidDataInterestRateOrder> allObj = new ArrayList<BidDataInterestRateOrder>();
 		try {
 			DBManager db = new DBManager();
 			DBManager dbs = new DBManager();
 			List<Double> pro = new ArrayList<Double>();
 	 		try {
 				StringBuffer strSql1 = new StringBuffer("SELECT * FROM ProrationDataInterestRateOrder");
 					
 				ResultSet rst = db.getRecord(strSql1.toString());
 				if(rst.next()){
 					pro.add(rst.getDouble(1));
 				}
 	 		} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			} finally {
 				db.close();
 			}
 	 		
 	 		try{
 	 			StringBuffer strSql2 = new StringBuffer("SELECT * FROM BidDataInterestRateOrderLastTotalAmount");
 	 			rst2= dbs.getRecord(strSql2.toString());
 	 			if(pro.size()!=0)
	 	 			while(rst2.next()){
						if(pro.get(0) == rst2.getDouble(7)){
							BidDataInterestRateOrder obj = new BidDataInterestRateOrder();
							obj.setDepartment_code(rst2.getString(2));
		 					obj.setBusiness_category(rst2.getString(3));
		 					obj.setBank_code(rst2.getString(4));
		 					obj.setFinancial_institution_name(rst2.getString(5));
		 					obj.setBid_number(rst2.getInt(6));
		 					obj.setBid_interest_rate(rst2.getDouble(7));
		 					obj.setBid_amount_money(rst2.getFloat(8));
		 					obj.setTotal_amount_money(0);
		 					
		 					allObj.add(obj);
							//addProrationData(obj);
							
							//UpdateBidInterestRateOrder(obj);
						}
					}
 	 		} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			} finally {
 				dbs.close();
 			}
 	 		
 	 		
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
 		for(BidDataInterestRateOrder obj:allObj)
 		{
 			addProrationData(obj);
				
			UpdateBidInterestRateOrder(obj);
 		}
 	}
 	
 	public void deleteProrationData() {
 	 	List<Integer> rowNum = new ArrayList<Integer>();
 		try {
 			DBManager db = new DBManager();
 			ResultSet rst=null;
 			try {
 				StringBuffer strSql = new StringBuffer("SELECT * FROM ProrationDataDupulicated");
 				 						
 				rst = db.getRecord(strSql.toString());
 				while(rst.next()){
 					rowNum.add(rst.getInt(1));
 				}
 	 		} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			} finally {
 				db.close();
 			}
 		
 			DBManager dbD = new DBManager();
 			try{
 				for(int row : rowNum){
 					boolean fo = dbD.doQuery("DELETE FROM proration_datas WHERE rowid="+row);
 				}
 			} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			} finally {
 				dbD.close();
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}
 	
 	public float getTotalAmount() {
 		float totalAmount = 0;
 		try {
 			DBManager db = new DBManager();
 	 		try {
 				StringBuffer strSql = new StringBuffer("SELECT * FROM TotalAmount");
 				ResultSet rst = db.getRecord(strSql.toString());
 				if(rst.next())
 				{
 					totalAmount = rst.getFloat(1);
 				}
 	 		} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			} finally {
 				db.close();
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
 		return totalAmount;
 	}
 	
 	public float getProratedBidPriceTotal() {
 		float BasicPriceTotal = 0;
 		try {
 			DBManager db = new DBManager();
 	 		try {
 				StringBuffer strSql = new StringBuffer("SELECT * FROM ProratedTotal");
 				ResultSet rst = db.getRecord(strSql.toString());
 				if(rst.next())
 				{
 					BasicPriceTotal = rst.getFloat(1);
 				}
 	 		} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			} finally {
 				db.close();
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
 		return BasicPriceTotal;
 	}
 	
 	public float getProratedTotal() {
 		float BasicPriceTotal = 0;
 		try {
 			DBManager db = new DBManager();
 	 		try {
 				StringBuffer strSql = new StringBuffer("SELECT * FROM ProratedTotal");
 				ResultSet rst = db.getRecord(strSql.toString());
 				if(rst.next())
 				{
 					BasicPriceTotal = rst.getFloat(2);
 				}
 	 		} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			} finally {
 				db.close();
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
 		return BasicPriceTotal;
 	}
 	
 	/*public boolean FormatLike(double ri)
 	{
 		boolean Fo = true;
 		
 		double num= ri;
 		int zeros = 0;
 		while (num < 1) {
 		    num *= 10;
 		    zeros++;
 		}
 		zeros -= 1;
 		if(zeros>=3)
 			Fo = false;
 		
 		return Fo;
 	}*/
 	
 	public float Format(double val, String pattern)
 	{
 		String formatedVal = "";
 		DecimalFormat decimalFormat = new DecimalFormat(pattern);
 		formatedVal =decimalFormat.format(val);
 		
 		return Float.parseFloat(formatedVal);
 	}
 	
 	public float Round(double val, long pattern)
 	{
 		double roundOff = (double) Math.round(val * pattern) / 100;
 		return (float) roundOff;
 	}
 	
 	public double RoundDouble(double val, long pattern)
 	{
 		double roundOff = (double) Math.round(val * pattern) / 100;
 		return roundOff;
 	}
 	
 	public void UpdateProrationData(BidDataInterestRateOrder obj){
 		try {
			DBManager db = new DBManager();
			try {
				boolean fo = db.doQuery("UPDATE proration_datas SET total_amount_of_money =" +
										obj.getTotal_amount_money()+" WHERE department_code ='" +
										obj.getDepartment_code()+"' AND bank_code = '"+
										obj.getBank_code()+"' AND bid_number ="+obj.getBid_number());
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public void UpdateProrationDataPrice(BidDataInterestRateOrder obj){
 		try {
			DBManager db = new DBManager();
			try {
				boolean fo = db.doQuery("UPDATE proration_datas SET bid_amount_of_money =" +
										obj.getBid_amount_money()+" WHERE department_code ='" +
										obj.getDepartment_code()+"' AND bank_code = '"+
										obj.getBank_code()+"' AND bid_number ="+obj.getBid_number());
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public void doDBData(StringBuffer strSql)
 	{
 		try {
			DBManager db = new DBManager();
	 		try {
 				//System.out.println(strSql);
				boolean dStatus=db.doQuery(strSql.toString());
	 		} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public List<SuccessfulBidData> SuccessfulBidData2() {
 		List<SuccessfulBidData> SuccessfulBidDatas = new ArrayList<SuccessfulBidData>();
 		String datePattern = "dd-MM-yyyy";
	    SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
 		try {
 			DBManager db = new DBManager();
 			DBManager dbP = new DBManager();
 	 		try {
 	 			StringBuffer strSql = new StringBuffer("SELECT DISTINCT bid_datas.department_code, bid_datas.business_category,bid_datas.bank_code,bid_datas.financial_institution_name,bid_datas.bid_number, " + 
						"bid_datas.bid_interest_rate,bid_datas.bid_amount_money, successful_bid_datas.bid_interest_rate AS successful_bid_interest_rate, " + 
						"successful_bid_datas.bid_amount_money AS successful_bid_amount_money,successful_bid_datas.auction_date FROM successful_bid_datas " +
						"LEFT JOIN bid_datas ON (successful_bid_datas.bank_code = bid_datas.bank_code) "+
						"AND (successful_bid_datas.bid_number = bid_datas.bid_number) "+
						"WHERE (((bid_datas.auction_date) = '"+bidDate+"')) ORDER BY bid_datas.department_code,bid_datas.bank_code,bid_datas.bid_number");

 	 			ResultSet rst = db.getRecord(strSql.toString());
 				while(rst.next())
 				{
 					SuccessfulBidData obj = new SuccessfulBidData();
 					obj.setDepartment_code(rst.getString(1));
 					obj.setBusiness_category(rst.getString(2));
 					obj.setBank_code(rst.getString(3));
 					obj.setFinancial_institution_name(rst.getString(4));
 					obj.setBid_number(rst.getInt(5));
 					obj.setBid_interest_rate(rst.getDouble(6));
 					obj.setBid_amount_money(rst.getFloat(7));
 					obj.setSuccessful_bid_interest_rate(rst.getDouble(8));
 					obj.setSuccessful_bid_amount_money(rst.getFloat(9));
 					String dt = rst.getString(10);
 					obj.setAuction_date(dt);
 					SuccessfulBidDatas.add(obj);
 				}
 				
 				StringBuffer strSql1 = new StringBuffer("SELECT DISTINCT bid_datas.department_code, bid_datas.business_category,bid_datas.bank_code,bid_datas.financial_institution_name,bid_datas.bid_number, "+ 
 														"bid_datas.bid_interest_rate,bid_datas.bid_amount_money FROM bid_datas WHERE bid_datas.auction_date = '"+bidDate+"'");
 				
 				ResultSet rst1 = dbP.getRecord(strSql1.toString());
 				while(rst1.next())
 				{
 					SuccessfulBidData obj = new SuccessfulBidData();
 					String deptCode = rst1.getString(1);
 					String bankCode = rst1.getString(3);
 					int bidNum = rst1.getInt(5);
 					obj.setDepartment_code(deptCode);
 					obj.setBusiness_category(rst1.getString(2));
 					obj.setBank_code(bankCode);
 					obj.setFinancial_institution_name(rst1.getString(4));
 					obj.setBid_number(bidNum);
 					obj.setBid_interest_rate(rst1.getDouble(6));
 					obj.setBid_amount_money(rst1.getFloat(7));
 					
 					//System.out.println(deptCode+"--"+bankCode+"--"+bidNum);
 					boolean Fo = false;
 					for(SuccessfulBidData bid:SuccessfulBidDatas)
 					{
 						if(bid.getDepartment_code().equals(obj.getDepartment_code()) && bid.getBank_code().equals(obj.getBank_code()) && bid.getBid_number()==obj.getBid_number())
 						{
 							Fo = true;
 							break;
 						}
 					}
 					if(!Fo)
 						SuccessfulBidDatas.add(obj);
 				}
						
 	 		} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			} finally {
 				db.close();
 				dbP.close();
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
 		return SuccessfulBidDatas;
 	}
 	
 	public float Nz(Float v)
 	{
 		float val=0;
 		if(null!=v)
 			val = v;
 		else
 			val = 0;
 		
 		return val;
 	}
 	
 	public double Nz(Double v)
 	{
 		Double val=0d;
 		if(null!=v)
 			val = v;
 		else
 			val = 0d;
 		
 		return val;
 	}
 	
 	public boolean Format(double num,int precision)
	{
 		boolean Fo = false;
		/*if(ri.equals(""))
			return Fo;
		double num = Double.parseDouble(ri);*/

		String[] splitter = String.valueOf(num).split("\\.");
		int decimalPlaces = splitter[1].length();   // After  Decimal Count

		int integer = (int)num;
		double decimal = num - integer;

		BigDecimal bd = new BigDecimal(decimal);
		// setScale is immutable
		bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
		decimal = bd.doubleValue();

		String dec = String.valueOf(decimal);
		int pointPlace = dec.indexOf(".")+1;
		dec = dec.substring(pointPlace);
		//System.out.println(num+"  |"+integer +"   |"+decimal+"    |"+dec+"    |"+dec.length());
		if(decimal == 0)
		{
			Fo = true;
			return Fo;
		}

		if(dec.length()>precision)
		{
			Fo = false;
			return Fo;
		} else{
			Fo =true;
		}
 		return Fo;
	}
 	
 	public boolean Like(Double toCompare, String by)
 	{
	    if(by != null){
	        if(toCompare != null){
	        	String toBeCompare = toCompare.toString();
	            if(by.startsWith("*") && by.endsWith("*")){
	                int index = toBeCompare.toLowerCase().indexOf(by.replace("*", "").toLowerCase());
	                if(index < 0){
	                    return false;
	                } else {
	                    return true;
	                }
	            } else if(by.startsWith("*")){
	                return toBeCompare.endsWith(by.replace("*", ""));
	            } else if(by.endsWith("*")){
	                return toBeCompare.startsWith(by.replace("*", ""));
	            } else {
	                return toBeCompare.equals(by.replace("*", ""));
	            }
	        } else {
	            return false;
	        }
	    } else {
	        return false;
	    }
	}
 	
 	
 	public void Updateflg_ByBankProcessing(BidDataInterestRateOrder obj,int flag)
 	{
 		try {
			DBManager db = new DBManager();
			try {
				boolean fo = db.doQuery("UPDATE successful_bid_datas_3 SET financial_institutions_by_flag =" +
										flag+" WHERE department_code ='" +
										obj.getDepartment_code()+"' AND bank_code = '"+
										obj.getBank_code()+"' AND bid_number ="+obj.getBid_number());
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public void ClearSuccessfulBidSituations()
 	{
 		StringBuffer SuccessfulBidStatusTableClear = new StringBuffer("DELETE FROM successful_bid_situations");
        doDBData(SuccessfulBidStatusTableClear);
 	}
 	
 	public void writeSuccessfulBidData2(List<SuccessfulBidData> SuccessfulBidDatas)
 	{
 		try {
			DBManager db = new DBManager();
			DBManager dbD = new DBManager();
			try {
				boolean fo = dbD.doQuery("DELETE FROM successful_bid_datas_2");
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbD.close();
			}
			try {
				for(SuccessfulBidData rst2:SuccessfulBidDatas)
			    {
					StringBuffer strSql = new StringBuffer("INSERT INTO successful_bid_datas_2 (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_interest_rate,bid_amount_money, successful_bid_interest_rate, successful_bid_price, auction_date) VALUES('"+ 
								rst2.getDepartment_code()+"','"+
								rst2.getBusiness_category()+"','"+
								rst2.getBank_code()+"','"+
								rst2.getFinancial_institution_name()+"',"+
								rst2.getBid_number()+","+
								rst2.getBid_interest_rate()+","+
								rst2.getBid_amount_money()+","+
								rst2.getSuccessful_bid_interest_rate()+","+
								rst2.getSuccessful_bid_amount_money()+","+
								rst2.getAuction_date()+")");
				
					boolean fo = db.doQuery(strSql.toString());
			    }
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public List<SuccessfulBidData> SuccessfulBidData3() {
 		List<SuccessfulBidData> SuccessfulBidDatas = new ArrayList<SuccessfulBidData>();
 		
 		try {
 			DBManager db = new DBManager();
 	 		try {
 	 			StringBuffer strSql = new StringBuffer("SELECT * FROM successful_bid_datas_2 ORDER BY department_code,bank_code,bid_number");

 	 			ResultSet rst = db.getRecord(strSql.toString());
 				while(rst.next())
 				{
 					SuccessfulBidData obj = new SuccessfulBidData();
 					obj.setDepartment_code(rst.getString(1));
 					obj.setBusiness_category(rst.getString(2));
 					obj.setBank_code(rst.getString(3));
 					obj.setFinancial_institution_name(rst.getString(4));
 					obj.setBid_number(rst.getInt(5));
 					obj.setBid_interest_rate(rst.getDouble(6));
 					obj.setBid_amount_money(rst.getFloat(7));
 					obj.setSuccessful_bid_interest_rate(rst.getDouble(8));
 					obj.setSuccessful_bid_amount_money(rst.getFloat(9));
 					String dt = rst.getString(10);
 					obj.setAuction_date(dt);
 					SuccessfulBidDatas.add(obj);
 				}		
 	 		} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			} finally {
 				db.close();
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
 		return SuccessfulBidDatas;
 	}
 	
 	
 	public void RI_T_MAKE(int SHORI)
 	{
	    float GENDO;
	    float saitei;
	    double GENDO_RI;
	    double BUF01;
	    double MAE_BUF01;
	    double BUF02;
	    double BUF03;
	    double BUF04;
	    double BUF05;
	    double BUF06;
	    float ICHIGYOME = 0f;
	    double ICHIGYOMERI = 0;
	    double ANBUN_GOKEI;
	    double MAE_RITSU;
	    float RUIKEI;
	    float RUIKEI_RI;
	    long COUNT01;
	    String MAE_KINYU;
	    String MAE_KINYU_N = "";
	    String MAE_BUMON;
	    int RAKU_COUNT;
	    String dum;
	    int han01 = 0;
	    int han02;
	    int SHORI01;
	    int co01;
	    int co02;
	    int endco;
	    int Fco;
	    int Page01;
	    String LBL_C; //Label caption
	    String DAT_S; //Data source
	    //Dim R As Report
	        
	    //Set F = Forms!落札データ作成
	    //Set F2 = Forms!main
	    //Set dbs = CurrentDb()
	    
		SHORI01 = 0;
		if(String.valueOf(cmbDataUpdated.getSelectedItem()).equals("しない"))
	        SHORI01 = 1;
		
		if(SHORI01 == 0)
	    {
			//han01 = MsgBox("All hand input data after the automatic calculation may be cleared by the Processing. is it OK?", 4)
	        if(han01 == 7) 
	        	return;
       	    if(txtBidAmountPland.getText().equals(""))
       	    {
       	    	GENDO = 1000000;
       	    } else
       	    {
       	    	GENDO = Float.parseFloat(txtBidAmountPland.getText());
       	    }
    
       	    if(txtFootCutInterestRates.getText().equals("")) 
       	    {
       	    		GENDO_RI = 100;
       	    } 
       	    else
       	    {
       	    		GENDO_RI = Double.parseDouble(txtFootCutInterestRates.getText());
       	    }
    
       	    if(txtMinimumAmount.getText().equals(""))
       	    {
       	    	saitei = 0;
       	    }
       	    else
       	    {
       	    	saitei = Float.parseFloat(txtMinimumAmount.getText());
       	    }

       	    getForMinimumAmountDetermined();
       	 
       	    prorationDataClear();

       	    bidInterestRateOrderClear();
			
			AddBidInterestRateOrder(GENDO_RI);
       	        
		    //Select up to required procurement Price from Sorting table in the order of InterestRate.
		    //Also to create data for apportioning together.
		    BUF01 = 0;
		    MAE_RITSU = 0;
		    List<BidDataInterestRateOrder> rsts = getBidDataRateOrder();
		    //ResultSet rst2 = getProrationData();
		
		    han01 = 0;
		    for(BidDataInterestRateOrder rst:rsts)
		    {
		        MAE_BUF01 = BUF01;
		        float BidPrice = rst.getBid_amount_money();
		        if(BidPrice==0)
		        	BidPrice = 0;
		        BUF01 = BUF01 + BidPrice;
		        if(BUF01 <= GENDO){
		            rst.setTotal_amount_money((float)BUF01);
		            UpdateBidInterestRateOrder(rst);
		        } else {
		            if(MAE_BUF01 == GENDO && han01 == 0)
		            {
		                //In the case of just limit in front of the data
		                if(rst.getBid_interest_rate() == MAE_RITSU)
		                {
		                	MAE_RITSU = rst.getBid_interest_rate();
		                	addProrationData(rst);
		                	han01 = 1;
		                } else {
		                    han01 = 1;
		                }
		            } else if(han01 == 0){
		                //If below the limit in front of the data
		            	MAE_RITSU = rst.getBid_interest_rate();
		            	addProrationData(rst);
		    			if(BUF01 >= GENDO){
		                    han01 = 1;
		                }
		            }
		            if ((MAE_RITSU == 0 || MAE_RITSU == rst.getBid_interest_rate()) && han01 == 0)
		            {
		            	MAE_RITSU = rst.getBid_interest_rate();
		            	addProrationData(rst);
		    			han01 = 1;
		            }
		        }
		        MAE_RITSU = rst.getBid_interest_rate();
		    }

		    MAE_RITSU = 0;
//////////////////////////////////////////////////
		    //updateProrationData();
		    List<BidDataInterestRateOrder> allObj = new ArrayList<BidDataInterestRateOrder>();
	 		try {
	 			ResultSet rst=null;
		 		ResultSet rst2=null;
	 			DBManager db = new DBManager();
	 			DBManager dbC = new DBManager();
	 	 		try {
	 				StringBuffer strSql1 = new StringBuffer("SELECT * FROM ProrationDataInterestRateOrder");
	 				StringBuffer strSql2 = new StringBuffer("SELECT * FROM BidDataInterestRateOrderLastTotalAmount");
	 						
	 				rst = db.getRecord(strSql1.toString());
	 				rst2= dbC.getRecord(strSql2.toString());
	 				
	 				if(rst.next()){
	 					while(rst2.next()){
	 						if(rst.getDouble(1) == rst2.getDouble(7)){
	 							BidDataInterestRateOrder obj = new BidDataInterestRateOrder();
	 							obj.setDepartment_code(rst2.getString(2));
	 		 					obj.setBusiness_category(rst2.getString(3));
	 		 					obj.setBank_code(rst2.getString(4));
	 		 					obj.setFinancial_institution_name(rst2.getString(5));
	 		 					obj.setBid_number(rst2.getInt(6));
	 		 					obj.setBid_interest_rate(rst2.getDouble(7));
	 		 					obj.setBid_amount_money(rst2.getFloat(8));
	 		 					obj.setTotal_amount_money(0);
	 		 					
	 		 					allObj.add(obj);
	 							//addProrationData(obj);
	 							
	 							//UpdateBidInterestRateOrder(obj);
	 						}
	 					}
	 				}
	 				
	 	 		} catch (SQLException sqle) {
	 				sqle.printStackTrace();
	 			} finally {
	 				db.close();
	 				dbC.close();
	 			}
	 		} catch (Exception e) {
	 			e.printStackTrace();
	 		}
	 		
	 		for(BidDataInterestRateOrder obj:allObj)
	 		{
	 			addProrationData(obj);
					
				UpdateBidInterestRateOrder(obj);
	 		}
//////////////////////////////////////////////////
		    //Duplication elimination of data
			deleteProrationData();
   
			BUF02 = GENDO - getTotalAmount();
   
			ANBUN_GOKEI = getProratedBidPriceTotal();
			    
			List<BidDataInterestRateOrder> ProrationData =  getProrationData();
			    
		    for(BidDataInterestRateOrder pro:ProrationData)
		    {
		    	if(Like(BUF02 * pro.getBid_amount_money() / ANBUN_GOKEI + 0.5,"*.*"))
		    	{
		    		pro.setTotal_amount_money(Format(((BUF02 * pro.getBid_amount_money()) / ANBUN_GOKEI), "0.000000"));
		    	}
		    	else{
		    		//rst!TotalAmount = Format(BUF02 * rst!BidPrice / ANBUN_GOKEI + 0.5, "0.000000")
		    		pro.setTotal_amount_money(Round(((BUF02 * pro.getBid_amount_money()) / ANBUN_GOKEI), 1000000));
		    	}
	    	
		    	//rst!TotalAmount = BUF02 * rst!BidPrice / ANBUN_GOKEI
		    	if(((long)(BUF02 * pro.getBid_amount_money() / ANBUN_GOKEI + 0.5)) == 0)
		    	{
		    		pro.setTotal_amount_money(0);
		    	}
	    	
		    	UpdateProrationData(pro);
		    }
		    
		    try {
	 			DBManager db = new DBManager();
	 			DBManager dbC = new DBManager();
	 	 		try {
	 				StringBuffer strSql = new StringBuffer("SELECT * FROM ProrationDataProcessing1");
	 				StringBuffer strSql1 = new StringBuffer("SELECT * FROM ProrationDataProcessing2");
		 			ResultSet rst = db.getRecord(strSql.toString());
		 			ResultSet rst2 = dbC.getRecord(strSql1.toString());
	 				if(rst2.next())
	 				{
	 					float total_amount = rst2.getFloat(1);
	 					if(total_amount>BUF02)
	 					{
	 						dum = rst.getString(2);
	 						BUF05 = rst.getFloat(1);
	 					}
	 				}
	 	 		} catch (SQLException sqle) {
	 				sqle.printStackTrace();
	 			} finally {
	 				db.close();
	 				dbC.close();
	 			}
	 		} catch (Exception e) {
	 			e.printStackTrace();
	 		}
  
			//When Total of apportioning is too large
			//If you rounded the Total it is, was over the amount planned.
			//(1) rounded up a fraction go something like this (more than 10 million yen, hereinafter the same) of the small ones from devalue in order → That five discard six input
			//(2) in the case of rounding up the fraction is more than in the same amount is rounded down to the order from the small ones of the amount subscribed → fact that the application amount is small, reasoning that it is reluctant
			//(3) justification of the amount subscribed is the case of the same amount, the applicant capitalization devalue in order from small ones → are reluctant to the entire bid
			//(4) If you are still not tell the superiority or inferiority, truncate from a small company of BankCode
		    han02 = 10;
		    //Set the total amount of the amount subscribed to proration data
		    //ResultSet rst=null;
		    try {
	 			DBManager db = new DBManager();
	 			DBManager dbS = new DBManager();
	 			DBManager dbD = new DBManager();
	 			DBManager dbP = new DBManager();
	 			DBManager dbS1 = new DBManager();
	 			try {
	 	 			boolean fo = dbD.doQuery("DELETE FROM bid_data_by_bank_totals");
	 	 			boolean fo1 = db.doQuery("INSERT INTO bid_data_by_bank_totals(department_code, bank_code, financial_institution_name, sum_of_bid_amount_money) SELECT department_code, bank_code, financial_institution_name, Sum(bid_amount_money) AS sum_of_bid_amount_money FROM bid_data_rate_orders GROUP BY department_code, bank_code, financial_institution_name");
	 	 			boolean fo2 = dbP.doQuery("UPDATE proration_datas SET bid_amount_of_money = (Select bid_data_by_bank_totals.sum_of_bid_amount_money from bid_data_by_bank_totals INNER JOIN proration_datas ON (bid_data_by_bank_totals.bank_code = proration_datas.bank_code) AND (bid_data_by_bank_totals.department_code = proration_datas.department_code))"); 					
	 				
	 	 			StringBuffer strSql = new StringBuffer("SELECT * FROM proration_datas order by total_amount_of_money,bid_amount_of_money,bank_code");
	 				ResultSet rst = dbS1.getRecord(strSql.toString());
	 				//float total = 0;
	 				BidDataInterestRateOrder obj = new BidDataInterestRateOrder();
	 				if(rst.next()){
 										
	 					obj.setDepartment_code(rst.getString(1));
	 					obj.setBusiness_category(rst.getString(2));
	 					obj.setBank_code(rst.getString(3));
	 					obj.setFinancial_institution_name(rst.getString(4));
	 					obj.setBid_number(rst.getInt(5));
	 					obj.setBid_interest_rate(rst.getDouble(6));
	 					obj.setBid_amount_money(rst.getFloat(7));
	 					obj.setTotal_amount_money(rst.getFloat(8));
	 				}
	 				
	 				StringBuffer strSql1 = new StringBuffer("SELECT * FROM ProrationDataProcessing2");
	 				ResultSet rst2 = dbS.getRecord(strSql1.toString());
	 				if(rst2.next())
	 				{
	 					float total_amount = rst2.getFloat(1);
	 					if(total_amount>BUF02)
	 					{
	 						//rst!TotalAmount = rst!TotalAmount - (Int(rst2!TotalAmountsTotal) - BUF02)
	 						obj.setTotal_amount_money((float) (obj.getTotal_amount_money() - (int)total_amount -BUF02));
	 					    han02 = 20;
	 					    UpdateProrationData(obj);
	 					}
	 				}
	 	 		} catch (SQLException sqle) {
	 				sqle.printStackTrace();
	 			} finally {
	 				dbD.close();
	 				db.close();
	 				dbP.close();
	 				dbS.close();
	 				dbS1.close();
	 			}
	 		} catch (Exception e) {
	 			e.printStackTrace();
	 		}

			//If Total of apportioning is too small
			//If you rounded the Total is, less than the estimated amount.
			//(1) go from the largest of the truncated fraction so of one billion yen rounded up to → That three discard four enter the order
			//(2) If the truncated fraction there is more than one in the same amount is rounded up to 1 billion yen order from the amount subscribed large → the fact that a large amount subscribed is, reasoning that it is actively
			//(3) If the amount subscribed is the same amount is, reasoning that it is positive from the applicant total is large company in the entire rounded up → bid to one billion yen order
			//(4) If you are still not tell the superiority or inferiority, rounded up from a large company of BankCode

		    if(han02 == 10)
		    {
	 			try {
		 			float TotalAmountsTotal= getProratedTotal();
		 			DBManager db = new DBManager();
		 			
		 			BidDataInterestRateOrder obj = new BidDataInterestRateOrder();
		 			try {
			 			StringBuffer strSql = new StringBuffer("SELECT * FROM proration_datas order by total_amount_of_money,bid_amount_of_money,bank_code");
		 				ResultSet rst = db.getRecord(strSql.toString());
		 				//float total = 0;
		 				
		 				if(rst.next())
		 				{
		 						 					
		 					obj.setDepartment_code(rst.getString(1));
		 					obj.setBusiness_category(rst.getString(2));
		 					obj.setBank_code(rst.getString(3));
		 					obj.setFinancial_institution_name(rst.getString(4));
		 					obj.setBid_number(rst.getInt(5));
		 					obj.setBid_interest_rate(rst.getDouble(6));
		 					obj.setBid_amount_money(rst.getFloat(7));
		 					obj.setTotal_amount_money(rst.getFloat(8));
		 				}
		 			} catch (SQLException sqle) {
		 				sqle.printStackTrace();
		 			} finally {
		 				db.close();
		 			}
		 			
		 			if(TotalAmountsTotal <= BUF02){
	 					obj.setTotal_amount_money((float)(obj.getTotal_amount_money() + BUF02 - TotalAmountsTotal));
 					    han02 = 20;
 					    UpdateProrationData(obj);
		 			}
	 			} catch (Exception e) {
		 			e.printStackTrace();
		 		}
	 		}

	 		List<BidDataInterestRateOrder> proratioDatas = getProrationData();
	 		for(BidDataInterestRateOrder pro:proratioDatas)
	 		{
	 			float total_amount = pro.getTotal_amount_money();
	 			pro.setBid_amount_money(total_amount);
	 			UpdateProrationDataPrice(pro);
	 		}
 		
	 		try {
				DBManager db = new DBManager();
				try {
					boolean fo = db.doQuery("DELETE FROM successful_bid_datas");
				} catch (SQLException sqle) {
					sqle.printStackTrace();
				} finally {
					db.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
 		
	 		StringBuffer strSql = new StringBuffer("INSERT INTO successful_bid_datas (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_interest_rate,bid_amount_money, total_amount_money, auction_date) "+ 
 											"SELECT DISTINCT bid_data_rate_orders.department_code, bid_data_rate_orders.business_categorie, bid_data_rate_orders.bank_code, "+ 
 											"bid_data_rate_orders.financial_institution_name, bid_data_rate_orders.bid_number, bid_data_rate_orders.bid_interest_rate, bid_data_rate_orders.bid_amount_money, "+ 
 											"bid_data_rate_orders.total_amount_money, '" + bidDate + "' AS Process1 FROM bid_data_rate_orders "+
 											"WHERE bid_data_rate_orders.total_amount_money>0");
	 		doDBData(strSql);
 		
	 		StringBuffer strSql1 = new StringBuffer("INSERT INTO successful_bid_datas (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_interest_rate,bid_amount_money, total_amount_money, auction_date) "+ 
 											"SELECT DISTINCT proration_datas.department_code, proration_datas.business_category, proration_datas.bank_code, proration_datas.financial_institution_name, proration_datas.bid_number, "+ 
 											"proration_datas.bid_interest_rate, cast(proration_datas.bid_amount_of_money as integer), cast(proration_datas.total_amount_of_money  as integer), '" + bidDate + "' AS Process1 FROM proration_datas");
	 		doDBData(strSql1);
 		
	 		StringBuffer SuccessfulBidData3Clear = new StringBuffer("DELETE FROM successful_bid_datas_3 WHERE successful_bid_datas_3.auction_date='" + bidDate + "' and successful_bid_datas_3.department_code='" +String.valueOf(cmbDeptCode.getSelectedItem())+ "'");
	 		doDBData(SuccessfulBidData3Clear);
 		
		    BUF01 = 0;
		    BUF02 = 0;
		    BUF03 = 0;
		    BUF04 = 0;
		    BUF05 = 0;
		    BUF06 = 0;
		    RAKU_COUNT = 0;
		    MAE_KINYU = "    ";
		    MAE_BUMON = "    ";
		    
		    List<SuccessfulBidData> SuccessfulBidDatas2 = SuccessfulBidData2();
		    writeSuccessfulBidData2(SuccessfulBidDatas2);
		    List<SuccessfulBidData> SuccessfulBidDatas = SuccessfulBidData3();
		    
		    //Set rst2 = dbs.OpenRecordset("SuccessfulBidData3", dbOpenDynaset)
		    for(SuccessfulBidData rst:SuccessfulBidDatas)
		    {
		    	if(rst.getDepartment_code().equals(String.valueOf(cmbDeptCode.getSelectedItem())))
		    	{
		    		if((!rst.getBank_code().equals(MAE_KINYU)) && (!MAE_KINYU.equals("    ")))
		    		{
		    			SuccessfulBidData3 rst2 = new SuccessfulBidData3();
			            rst2.setAuction_date(bidDate);
			            rst2.setDepartment_code(MAE_BUMON);
			            rst2.setBank_code(MAE_KINYU);
			            rst2.setFinancial_institution_name(MAE_KINYU_N + " 計");
			            rst2.setBid_number(98);
			            rst2.setBid_amount_money((float) BUF01);
			            rst2.setBusiness_category("");
			            if(BUF02 != 0 && ICHIGYOME != BUF02)
			            {
			                //rst2!SuccessfulInterestRate = Int((BUF03 / BUF02 + 0.00001) * 10000) / 10000
			                //If BUF02 > 0 Then MsgBox MAE_KINYU_N & Format(BUF03) & "    " & Format(BUF02) & "  " & Format((BUF03 / BUF02 * 10000))
			                //rst2!SuccessfulInterestRate = (Int((BUF03 / BUF02) * 10000)) / 10000
			                //rst2!SuccessfulInterestRate = Val(Format((((BUF03 / BUF02) * 10000)) / 10000, "0.0000"))
			              	if(Like((BUF03 / BUF02 * 100000),"*.*"))
			              	{
			              		String strInterestRate = new DecimalFormat("##.####").format((((BUF03 / BUF02) * 100000) / 100000));
			              		double interstRate = ((BUF03 / BUF02 * 100000) / 100000);
			              		if((!strInterestRate.equals("")) && (!strInterestRate.equals("0")))
			              			interstRate = Double.parseDouble(strInterestRate);
			              		rst2.setSuccessful_bid_interest_rate(interstRate);
			              	}
			              	else
			              	{
			              		rst2.setSuccessful_bid_interest_rate(BUF03 / BUF02);
			              	}
			            }
			            else if(BUF02 != 0 && ICHIGYOME == BUF02)
			            {
			            	rst2.setSuccessful_bid_interest_rate(ICHIGYOMERI);
			            }
			            else
			            {
			            	rst2.setSuccessful_bid_interest_rate(0);
			            }
			            if(BUF02 != 0)
			                rst2.setSuccessful_bid_price((float) BUF02);
			            else
			            {   
			            	rst2.setSuccessful_bid_price(0);
			            	rst2.setSuccessful_bid_interest_rate(0);
			            }
			            StringBuffer strSql5 = new StringBuffer("INSERT INTO successful_bid_datas_3 (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_amount_money, successful_bid_interest_rate,successful_bid_price,auction_date) VALUES('"+ 
									rst2.getDepartment_code()+"','"+rst2.getBusiness_category()+"','"+rst2.getBank_code()+"','"+rst2.getFinancial_institution_name()+"',"+rst2.getBid_number()+","+rst2.getBid_amount_money()+","+rst2.getSuccessful_bid_interest_rate()+","+rst2.getSuccessful_bid_price()+",'"+rst2.getAuction_date()+"')");
			            
			            doDBData(strSql5);
			            
			            BUF01 = 0;
			            BUF02 = 0;
			            BUF03 = 0;
		    		}
		    		
		    		SuccessfulBidData3 rst2 = new SuccessfulBidData3();
		            rst2.setAuction_date(bidDate);
		            rst2.setDepartment_code(rst.getDepartment_code());
		            rst2.setBusiness_category(rst.getBusiness_category());
		            rst2.setBank_code(rst.getBank_code());
		            MAE_BUMON = rst.getDepartment_code();
					MAE_KINYU = rst.getBank_code();
					rst2.setFinancial_institution_name(rst.getFinancial_institution_name());
		            rst2.setBid_number(rst.getBid_number());
		            rst2.setBid_interest_rate(rst.getBid_interest_rate());
		            rst2.setBid_amount_money(rst.getBid_amount_money());
		            BUF01 = BUF01 + Nz(rst.getBid_amount_money());
			        BUF04 = BUF04 + Nz(rst.getBid_amount_money());
			        rst2.setSuccessful_bid_interest_rate(Nz(rst.getSuccessful_bid_interest_rate()));
		            BUF03 = BUF03 + (Nz(rst.getSuccessful_bid_interest_rate()) * Nz(rst.getSuccessful_bid_amount_money()));
			        BUF06 = BUF06 + (Nz(rst.getSuccessful_bid_interest_rate()) * Nz(rst.getSuccessful_bid_amount_money()));
			        if(rst.getBid_number() == 1){
			            ICHIGYOME = Nz(rst.getSuccessful_bid_amount_money());
			            ICHIGYOMERI = Nz(rst.getSuccessful_bid_interest_rate());
			        }
			        if(Nz(rst.getSuccessful_bid_amount_money()) == 0){
			            rst2.setSuccessful_bid_price(0); 
			        } else {
			            rst2.setSuccessful_bid_price(Nz(rst.getSuccessful_bid_amount_money()));
			        }    		
			        
			        BUF02 = BUF02 + Nz(rst.getSuccessful_bid_amount_money());
			        BUF05 = BUF05 + Nz(rst.getSuccessful_bid_amount_money());
			        if(rst.getSuccessful_bid_amount_money() != 0){ 
			            RAKU_COUNT = RAKU_COUNT + 1;
		    		}
			        MAE_BUMON = rst.getDepartment_code();
			        MAE_KINYU = rst.getBank_code();
			        MAE_KINYU_N = rst.getFinancial_institution_name();
			        
			        StringBuffer strSql5 = new StringBuffer("INSERT INTO successful_bid_datas_3 (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_interest_rate,bid_amount_money, successful_bid_interest_rate,successful_bid_price,auction_date) VALUES('"+ 
							rst2.getDepartment_code()+"','"+rst2.getBusiness_category()+"','"+rst2.getBank_code()+"','"+rst2.getFinancial_institution_name()+"',"+rst2.getBid_number()+","+rst2.getBid_interest_rate()+","+rst2.getBid_amount_money()+","+rst2.getSuccessful_bid_interest_rate()+","+rst2.getSuccessful_bid_price()+",'"+rst2.getAuction_date()+"')");
	            
			        doDBData(strSql5);
			        
		    	}
		    }
    
		    SuccessfulBidData3 rst2 = new SuccessfulBidData3();
		    rst2.setAuction_date(bidDate);
            rst2.setDepartment_code(MAE_BUMON);
            rst2.setBank_code(MAE_KINYU);
            rst2.setFinancial_institution_name(MAE_KINYU_N + " 計");
        	rst2.setBid_number(98);
        	rst2.setBid_amount_money((float) BUF01);
        	rst2.setBusiness_category("");
        	if(BUF02 != 0 && ICHIGYOME != BUF02){
        		if(Like(((BUF03 / BUF02) * 100000), "*.*")){
        			String strInterestRate = new DecimalFormat("##.####").format(((BUF03 / BUF02) * 100000) / 100000);
              		double interstRate = (((BUF03 / BUF02) * 100000) / 100000);
              		if((!strInterestRate.equals("")) && (!strInterestRate.equals("0")))
              			interstRate = Double.parseDouble(strInterestRate);
        			rst2.setSuccessful_bid_interest_rate(interstRate);
        		} else {
        			rst2.setSuccessful_bid_interest_rate(BUF03 / BUF02);
        		}
        	} else if(BUF02 != 0 && ICHIGYOME == BUF02){
        		rst2.setSuccessful_bid_interest_rate(ICHIGYOMERI);
        	} else {
        		rst2.setSuccessful_bid_interest_rate(0);
        	}
        	rst2.setSuccessful_bid_price((float) BUF02);
        	StringBuffer strSql6 = new StringBuffer("INSERT INTO successful_bid_datas_3 (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_amount_money, successful_bid_interest_rate,successful_bid_price,auction_date) VALUES('"+ 
					rst2.getDepartment_code()+"','"+rst2.getBusiness_category()+"','"+rst2.getBank_code()+"','"+rst2.getFinancial_institution_name()+"',"+rst2.getBid_number()+","+rst2.getBid_amount_money()+","+rst2.getSuccessful_bid_interest_rate()+","+rst2.getSuccessful_bid_price()+",'"+rst2.getAuction_date()+"')");
        
	        doDBData(strSql6);
    	
	        SuccessfulBidData3 rst3 = new SuccessfulBidData3();
	        rst3.setAuction_date(bidDate);
            rst3.setDepartment_code(String.valueOf(cmbDeptCode.getSelectedItem()));
            rst3.setBank_code(MAE_KINYU);
            rst3.setFinancial_institution_name("合  計");
        	rst3.setBid_number(99);
        	rst3.setBid_amount_money((float) BUF04);
        	rst3.setBusiness_category("");
	        if(BUF05 != 0)
	        {
	        	if(Like(((BUF06 / BUF05) * 100000),"*.*"))
	        	{
	        		double a = ((int)((BUF06 / BUF05) * 100000));
	        		double b = a / 100000;
	        		rst3.setSuccessful_bid_interest_rate(b);
	        	} else{
	        		rst3.setSuccessful_bid_interest_rate(BUF06 / BUF05);
	        	}
	        }
	        else{
	        	rst3.setSuccessful_bid_interest_rate(0);
	        }
	        rst3.setSuccessful_bid_price((float) BUF05);
	        StringBuffer strSql7 = new StringBuffer("INSERT INTO successful_bid_datas_3 (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_amount_money, successful_bid_interest_rate,successful_bid_price,auction_date) VALUES('"+ 
					rst3.getDepartment_code()+"','"+rst3.getBusiness_category()+"','"+rst3.getBank_code()+"','"+rst3.getFinancial_institution_name()+"',"+rst3.getBid_number()+","+rst3.getBid_amount_money()+","+rst3.getSuccessful_bid_interest_rate()+","+rst3.getSuccessful_bid_price()+",'"+rst3.getAuction_date()+"')");
	            
			    doDBData(strSql7);
 
		        try {
		 			DBManager db = new DBManager();
		 			ResultSet rst = null;
		 	 		try {
		 				StringBuffer strSql8 = new StringBuffer("SELECT * FROM flg_ByBankProcessing");
	 				rst = db.getRecord(strSql8.toString());
	 				han01 = 0;
	 				MAE_KINYU = "    ";
	 	 		} catch (SQLException sqle) {
	 				sqle.printStackTrace();
	 			} finally {
	 				db.close();
	 			}
 				while(rst.next())
 				{
 					BidDataInterestRateOrder obj = new BidDataInterestRateOrder();
 					obj.setBank_code(rst.getString(1));
 					obj.setBid_number(rst.getInt(2));
 					obj.setDepartment_code(rst.getString(3));
 					
 					if((!MAE_KINYU.equals("    ")) && (!obj.getBank_code().equals(MAE_KINYU)))
 						han01 = 0;
	 		        if(obj.getBid_number() == 1){
	 		        	rst.getFloat(5);	 		        
	 		            if(rst.wasNull())
	 		                han01 = 1;
	 		        }
	 		        int flg_ByBank = 1;
	 		        if(han01 == 1){
	 		        	flg_ByBank = 2;
	 		        } else{
	 		            flg_ByBank = 1;
	 		        }	       
	 		        Updateflg_ByBankProcessing(obj,flg_ByBank);
	 		        MAE_KINYU = obj.getBank_code();
 				}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
  
	    if(SHORI == 1){
	    	//OPEN REPORT  Financial institutions by
	    	String params =String.valueOf(cmbDeptCode.getSelectedItem()) +"#"+(int) GENDO;
	    	JasperReportViewer nw = JasperReportViewer.getInstance("SuccessfulBidStatusTableByBank",bidDate,params);
			nw.pack();
			if (nw.isVisible()) {
			} else {
				getDesktopPane().add(nw);
				nw.setVisible(true);
			}
			nw.setIconifiable(true);
			nw.setMaximizable(false);
			nw.setClosable(true);
			
			try {
				nw.setMaximum(true);
			} catch (PropertyVetoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			dispose();   	
	    } else if(SHORI == 2 || SHORI == 3) {
	    	ClearSuccessfulBidSituations();
	    	
	        StringBuffer AddSuccessfulBidStatusTable = new StringBuffer("INSERT INTO successful_bid_situations(department_code, business_category, bid_interest_rate, bid_amount_of_money, bank_code, financial_institution_name, bid_number) " + 
	        		  "SELECT DISTINCT successful_bid_datas_3.department_code, successful_bid_datas_3.business_category, successful_bid_datas_3.successful_bid_interest_rate, " +
	        		  "successful_bid_datas_3.successful_bid_price, successful_bid_datas_3.bank_code, successful_bid_datas_3.financial_institution_name, successful_bid_datas_3.bid_number "+ 
	        		  "FROM successful_bid_datas_3 WHERE successful_bid_datas_3.auction_date='" + bidDate + "' AND successful_bid_datas_3.department_code='" + String.valueOf(cmbDeptCode.getSelectedItem()) + "' AND successful_bid_datas_3.successful_bid_interest_rate<>0 AND successful_bid_datas_3.bid_number<98 "+
	        		  "ORDER BY successful_bid_datas_3.successful_bid_interest_rate,successful_bid_datas_3.bank_code");
	        doDBData(AddSuccessfulBidStatusTable);
	        
	        StringBuffer SuccessfulBidSituationInterestRateCumulativeCalculation = new StringBuffer("DELETE FROM successful_bid_situation_interests");
	        doDBData(SuccessfulBidSituationInterestRateCumulativeCalculation);

	        StringBuffer MakeSuccessfulBidSituationInterestRateCumulativeCalculation = new StringBuffer("INSERT INTO successful_bid_situation_interests (bid_interest_rate,sum_of_bid_amount_money) "+ 
	        			"SELECT DISTINCT successful_bid_situations.bid_interest_rate, Sum(successful_bid_situations.bid_amount_of_money)  AS BasicPriceTotal "+
	        			"FROM successful_bid_situations GROUP BY successful_bid_situations.bid_interest_rate");
	        doDBData(MakeSuccessfulBidSituationInterestRateCumulativeCalculation);
	        List <SuccessfulBidSituations> SuccessfulBidSituations_datas = new ArrayList<SuccessfulBidSituations>();    
	        //List <SuccessfulBidSituations> SuccessfulBidSituations_datas1 = new ArrayList<SuccessfulBidSituations>();
	        try {
	 			DBManager db = new DBManager();
	 			
	 	 		try {
	 	 			StringBuffer UpdateSuccessfulBidStatusTable = new StringBuffer("SELECT DISTINCT * FROM successful_bid_situations LEFT JOIN successful_bid_situation_interests ON "+
		        			"successful_bid_situations.bid_interest_rate = successful_bid_situation_interests.bid_interest_rate");
	 	 			ResultSet rst = db.getRecord(UpdateSuccessfulBidStatusTable.toString());
	 	 			while(rst.next())
		 	 		{
	 	 				SuccessfulBidSituations bitSituation = new SuccessfulBidSituations();
	 	 				bitSituation.setSno(rst.getLong(1));
	 	 				bitSituation.setDepartment_code(rst.getString(2));
	 	 				bitSituation.setBid_interest_rate(rst.getDouble(3));
	 	 				bitSituation.setBid_interest_rate2(rst.getDouble(4));
	 	 				bitSituation.setAverage_rate(rst.getDouble(6));
	 	 				bitSituation.setTotal_amount_of_money(rst.getFloat(7));
	 	 				bitSituation.setCumulative_interest(rst.getFloat(8));
	 	 				bitSituation.setBusiness_category(rst.getString(9));
	 	 				bitSituation.setBank_code(rst.getString(10));
	 	 				bitSituation.setFinancial_institution_name(rst.getString(11));
	 	 				bitSituation.setBid_number(rst.getInt(12));
	 	 				bitSituation.setBid_amount_of_money(rst.getFloat(13));
	 	 				bitSituation.setInterest_rate_by_bid_amount_of_money(rst.getFloat(15));
	 	 				
	 	 				SuccessfulBidSituations_datas.add(bitSituation);
		 	 		}
	 	 		} catch (SQLException sqle) {
	 				sqle.printStackTrace();
	 			} finally {
	 				db.close();
	 			}
	 	 		ClearSuccessfulBidSituations();
	 	 		/*for(SuccessfulBidSituations bitSituation:SuccessfulBidSituations_datas)
	 	 		{
	 	 			
	 	 			StringBuffer AddSuccessfulBidsituations = new StringBuffer("INSERT INTO successful_bid_situations("+
	 	 			"department_code,bid_interest_rate,bid_interest_rate2,interest_rate_by_bid_amount_of_money,average_rate,total_amount_of_money,cumulative_interest,business_category,bank_code,financial_institution_name,bid_number,bid_amount_of_money) VALUES('"+
	 	 			bitSituation.getDepartment_code()+"',"+bitSituation.getBid_interest_rate()+","+bitSituation.getBid_interest_rate2()+","+
	 	 			bitSituation.getInterest_rate_by_bid_amount_of_money()+","+bitSituation.getAverage_rate()+","+bitSituation.getTotal_amount_of_money()+","+
	 	 			bitSituation.getCumulative_interest()+",'"+bitSituation.getBusiness_category()+"','"+bitSituation.getBank_code()+"','"+bitSituation.getFinancial_institution_name()+"',"+bitSituation.getBid_number()+","+bitSituation.getBid_amount_of_money()+")");
			        
	 	 			doDBData(AddSuccessfulBidsituations);
	 	 			
	 	 		}*/
	 		} catch (Exception e) {
	 			e.printStackTrace();
	 		}
	        
	        MAE_RITSU = 0;
	        RUIKEI_RI = 0;
	        RUIKEI = 0;
	        COUNT01 = 1;
	        for(SuccessfulBidSituations rst:SuccessfulBidSituations_datas)
 	 		{
	        	RUIKEI = RUIKEI + Nz(rst.getBid_amount_of_money());
		        rst.setTotal_amount_of_money(RUIKEI); 
		        RUIKEI_RI = (float) (RUIKEI_RI + Nz(rst.getBid_amount_of_money()) * rst.getBid_interest_rate() * 100000000);
		        rst.setCumulative_interest(RUIKEI_RI);
		        if(Like((double) (RUIKEI_RI / (RUIKEI * 100000000) * 100000),"*.*"))
		        {
		        	double rate = (int) (RUIKEI_RI / (RUIKEI * 100000000) * 100000);
		        	double avRate = ( rate / 100000);
		        	rst.setAverage_rate(avRate);
		        }
		        else
		        {
		            rst.setAverage_rate((double) (RUIKEI_RI / (RUIKEI * 100000000)));
		        }
		        rst.setSno(COUNT01);
		        COUNT01 = COUNT01 + 1;
		        if(rst.getBid_interest_rate() == MAE_RITSU)
		        {
		        	rst.setBid_interest_rate2(0D);
		            rst.setInterest_rate_by_bid_amount_of_money(0F);
		        }
		        else{
		        	rst.setBid_interest_rate2(rst.getBid_interest_rate());
		        }
		        MAE_RITSU = rst.getBid_interest_rate();
		        
		        StringBuffer AddSuccessfulBidsituations = new StringBuffer("INSERT INTO successful_bid_situations("+
		 	 			"sno,department_code,bid_interest_rate,bid_interest_rate2,interest_rate_by_bid_amount_of_money,average_rate,total_amount_of_money,cumulative_interest,business_category,bank_code,financial_institution_name,bid_number,bid_amount_of_money) VALUES("+
		 	 			rst.getSno()+",'"+rst.getDepartment_code()+"',"+rst.getBid_interest_rate()+","+rst.getBid_interest_rate2()+","+
		 	 			rst.getInterest_rate_by_bid_amount_of_money()+","+rst.getAverage_rate()+","+rst.getTotal_amount_of_money()+","+
		 	 			rst.getCumulative_interest()+",'"+rst.getBusiness_category()+"','"+rst.getBank_code()+"','"+rst.getFinancial_institution_name()+"',"+rst.getBid_number()+","+rst.getBid_amount_of_money()+")");
				        
		        doDBData(AddSuccessfulBidsituations);
 	 		}
		        
		    if(SHORI == 2)
		    {
		    	//OPEN REPORT  Interest rate by
		    	JasperReportViewer nw = JasperReportViewer.getInstance("InterestRateBy",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
				nw.pack();
				if (nw.isVisible()) {
				} else {
					getDesktopPane().add(nw);
					nw.setVisible(true);
				}
				nw.setIconifiable(true);
				nw.setMaximizable(false);
				nw.setClosable(true);
				
				try {
					nw.setMaximum(true);
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				dispose();   	
		    }
		    /*ElseIf SHORI = 3 Then
			            Set rst = dbs.OpenRecordset("SuccessfulBidByInterestRateByFinancialInstitutions", dbOpenDynaset)
			            Set rst2 = dbs.OpenRecordset("BankMaster", dbOpenDynaset)
			            Fco = rst.Fields.Count
			            For co01 = 0 To Fco - 3
			                DAT_S(co01) = rst.Fields(co01 + 2).Name
			                rst2.MoveFirst
			                rst2.FindFirst "[BankCode]='" & DAT_S(co01) & "'"
			                If Not rst2.NoMatch Then
			                    If Left(DAT_S(co01), 1) = "0" Then
			                        LBL_C(co01) = rst2!BankName & "Bank"
			                    Else
			                        LBL_C(co01) = rst2!BankName
			                    End If
			                End If
			            Next co01
			            rst2.Close
			            rst.Close
		                    
			            co02 = 0
			            For Page01 = 1 To Int((Fco - 2) / 15 + 0.99)
//OPEN REPORT  SuccessfulBidByInterestRateByFinancialInstitutions				        
			                DoCmd.OpenReport "SuccessfulBidByInterestRateByFinancialInstitutions", acDesign
			                Set R = Reports!SuccessfulBidByInterestRateByFinancialInstitutions
			                For co01 = 1 To 15
			                    R("D" & Format(co01, "00")).ControlSource = ""
			                    R("L" & Format(co01, "00")).Visible = False
			                    R("D" & Format(co01, "00")).Visible = False
			                    R("D" & Format(co01, "00")).ControlSource = ""
			                    R("S" & Format(co01, "00")).Visible = False
			                    R("S" & Format(co01, "00")).ControlSource = ""
			                Next co01
			                If Fco - 3 < co02 + 14 Then
			                    endco = Fco - 3
			                Else
			                    endco = co02 + 14
			                End If
			                For co01 = 0 + co02 To endco
			                    R("L" & Format(co01 + 1 - co02, "00")).Caption = LBL_C(co01)
			                    R("L" & Format(co01 + 1 - co02, "00")).Visible = True
			                    R("D" & Format(co01 + 1 - co02, "00")).ControlSource = DAT_S(co01)
			                    R("D" & Format(co01 + 1 - co02, "00")).Visible = True
			                    R("S" & Format(co01 + 1 - co02, "00")).ControlSource = "=SUM([" & DAT_S(co01) & "])"
			                    R("S" & Format(co01 + 1 - co02, "00")).Visible = True
			                Next co01
			                DoCmd.Save
			                DoCmd.Close acReport, "SuccessfulBidByInterestRateByFinancialInstitutions"
			                DoCmd.OpenReport "SuccessfulBidByInterestRateByFinancialInstitutions"
			                co02 = co02 + 15
			            Next Page01
			        End If*/
	        }
	    	else {
	    		if(SHORI == 1){
	    			//OPEN REPORT  Financial institutions by
	    			JasperReportViewer nw = JasperReportViewer.getInstance("SuccessfulBidStatusTableByBank",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
	    			nw.pack();
	    			if (nw.isVisible()) {
	    			} else {
	    				getDesktopPane().add(nw);
	    				nw.setVisible(true);
	    			}
	    			nw.setIconifiable(true);
	    			nw.setMaximizable(false);
	    			nw.setClosable(true);
	    			
	    			try {
	    				nw.setMaximum(true);
	    			} catch (PropertyVetoException e1) {
	    				// TODO Auto-generated catch block
	    				e1.printStackTrace();
	    			}

	    			dispose();   	
	    		} else if(SHORI == 2){
	    			//OPEN REPORT  Interest rate by
	    			JasperReportViewer nw = JasperReportViewer.getInstance("InterestRateBy",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
					nw.pack();
					if (nw.isVisible()) {
					} else {
						getDesktopPane().add(nw);
						nw.setVisible(true);
					}
					nw.setIconifiable(true);
					nw.setMaximizable(false);
					nw.setClosable(true);
					
					try {
						nw.setMaximum(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					dispose();  
	    			
	    		}
			    /*ElseIf SHORI = 3 Then
			        Set rst = dbs.OpenRecordset("SuccessfulBidByInterestRateByFinancialInstitutions", dbOpenDynaset)
			        Set rst2 = dbs.OpenRecordset("BankMaster", dbOpenDynaset)
			        Fco = rst.Fields.Count
			        For co01 = 0 To Fco - 3
			            DAT_S(co01) = rst.Fields(co01 + 2).Name
			            rst2.MoveFirst
			            rst2.FindFirst "[BankCode]='" & DAT_S(co01) & "'"
			            If Not rst2.NoMatch Then
			                If Left(DAT_S(co01), 1) = "0" Then
			                    LBL_C(co01) = rst2!BankName & "Bank"
			                Else
			                    LBL_C(co01) = rst2!BankName
			                End If
			            End If
			        Next co01
			        rst2.Close
			        rst.Close
			                
			        For Page01 = 1 To Int((Fco - 2) / 15 + 0.99)
					//OPEN REPORT  SuccessfulBidByInterestRateByFinancialInstitutions
			            DoCmd.OpenReport "SuccessfulBidByInterestRateByFinancialInstitutions", acDesign
			            Set R = Reports!SuccessfulBidByInterestRateByFinancialInstitutions
			            For co01 = 1 To 15
			                R("D" & Format(co01, "00")).ControlSource = ""
			                R("L" & Format(co01, "00")).Visible = False
			                R("D" & Format(co01, "00")).Visible = False
			                R("D" & Format(co01, "00")).ControlSource = ""
			                R("S" & Format(co01, "00")).Visible = False
			                R("S" & Format(co01, "00")).ControlSource = ""
			            Next co01
			            If Fco - 3 < co02 + 14 Then
			                endco = Fco - 3
			            Else
			                endco = co02 + 14
			            End If
			            For co01 = 0 + co02 To endco
			                R("L" & Format(co01 + 1 - co02, "00")).Caption = LBL_C(co01)
			                R("L" & Format(co01 + 1 - co02, "00")).Visible = True
			                R("D" & Format(co01 + 1 - co02, "00")).ControlSource = DAT_S(co01)
			                R("D" & Format(co01 + 1 - co02, "00")).Visible = True
			                R("S" & Format(co01 + 1 - co02, "00")).ControlSource = "=SUM([" & DAT_S(co01) & "])"
			                R("S" & Format(co01 + 1 - co02, "00")).Visible = True
			            Next co01
			            DoCmd.Save
			            DoCmd.Close acReport, "SuccessfulBidByInterestRateByFinancialInstitutions"
			            DoCmd.OpenReport "SuccessfulBidByInterestRateByFinancialInstitutions"
			            co02 = co02 + 15
			        Next Page01
			    End If
			    */
	    	}
	    }
 	}	//end RI_T_MAKE
}


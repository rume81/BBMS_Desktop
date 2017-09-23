package com.ey.application.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.ey.application.controller.DBManager;
import com.ey.application.model.BidDataInterestRateOrder;
import com.ey.application.model.ThereSpecification;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* FrmInterestRateOrderBidStatus.java
* --------------------
* Created on Feb 8, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Feb 8, 2016: Original version (Monsur)
*
*/
public class FrmInterestRateOrderBidStatus extends JInternalFrame {
	// Variables declaration - do not modify                     
    private javax.swing.JButton cmdCloseup;
    private javax.swing.JButton cmdNoSpecification;
    private javax.swing.JButton cmdThereSpecification;
    private javax.swing.JPanel pnlMain;
    private JComboBox cmbDeptCode;
    private JTextField txtDeptName;
    private String bidDate;
    private JTextField total_amount;
    // End of variables declaration    
    
    public FrmInterestRateOrderBidStatus(Date date) {
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
		pnlMain = new javax.swing.JPanel();
		pnlMain.setBounds(1, 1, 250, 250);
        cmdNoSpecification = new javax.swing.JButton();
        cmdCloseup = new javax.swing.JButton();
        cmdThereSpecification = new javax.swing.JButton();
        cmbDeptCode = new JComboBox();
        txtDeptName = new JTextField();
        total_amount = new JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("利率順入札状況表");

        pnlMain.setBackground(new java.awt.Color(176, 224, 230));
        pnlMain.setLayout(null);
        
        departmentCode();
        cmbDeptCode.setVisible(false);
        getDetpInfo(); 
        
        pnlMain.add(cmbDeptCode);
        cmbDeptCode.setBounds(160, 10, 170, 23);
        
        pnlMain.add(txtDeptName);
        txtDeptName.setBounds(160, 40, 170, 23);
        txtDeptName.setVisible(false);
        
        
        pnlMain.add(total_amount);
        total_amount.setBounds(10, 40, 170, 23);
        total_amount.setVisible(false);
        
        
        cmdNoSpecification.setText("明細なし");
        pnlMain.add(cmdNoSpecification);
        cmdNoSpecification.setBounds(39, 78, 198, 23);
        cmdNoSpecification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NRJ_T_MAKE(2);
			}
		});

        cmdCloseup.setText("閉じる");
        pnlMain.add(cmdCloseup);
        cmdCloseup.setBounds(39, 138, 198, 23);
        cmdCloseup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
        });

        cmdThereSpecification.setText("明細あり");
        pnlMain.add(cmdThereSpecification);
        cmdThereSpecification.setBounds(40, 40, 198, 23);
        cmdThereSpecification.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NRJ_T_MAKE(1);
			}
		});
        this.getContentPane().setLayout(null);
        this.getContentPane().add(pnlMain);
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
			getDesktopPane().add(nw);
			nw.setVisible(true);
		}

		nw.setIconifiable(true);
		nw.setMaximizable(false);
		nw.setClosable(true);
		dispose();
	}
	
	private static FrmInterestRateOrderBidStatus myInstance;

 	public static FrmInterestRateOrderBidStatus getInstance(Date date) {
 	    if (myInstance == null) {
 	        myInstance = new FrmInterestRateOrderBidStatus(date);
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
 	public void BidInterestRateOrderClear(){
 		try {
			DBManager db = new DBManager();
			try {
				boolean fo = db.doQuery("DELETE  FROM bid_data_rate_orders");
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public void AddBidInterestRateOrder()
 	{
 		try {
			DBManager db = new DBManager();
			String deptcode = String.valueOf(cmbDeptCode.getSelectedItem());
	 		try {
 				StringBuffer strSql = new StringBuffer("INSERT INTO bid_data_rate_orders (department_code, business_categorie, bank_code, financial_institution_name, bid_number, bid_interest_rate, bid_amount_money) "+ 
 						"SELECT DISTINCT bid_datas.department_code, bid_datas.business_category, bid_datas.bank_code, "+ 
 						"CASE WHEN (substr(bid_datas.bank_code, 1, 1 ) = '0') THEN bid_datas.financial_institution_name "+
 						"WHEN bid_datas.bank_code ='1000004' THEN '全国信用金庫連合会' "+ 
 						"WHEN bid_datas.bank_code ='2010100' THEN '全国信用協同組合連合会' "+
 						"WHEN bid_datas.bank_code ='2950002' THEN '全国労働金庫連合会' "+
 						"ELSE bid_datas.financial_institution_name END AS financial_institution_name_1, "+
 						"bid_datas.bid_number,bid_datas.bid_interest_rate,bid_datas.bid_amount_money FROM bid_datas WHERE (((bid_datas.department_code) = '"+deptcode+"') AND ((bid_datas.bid_interest_rate) <> 0) AND ((bid_datas.auction_date)= '"+bidDate+"')) ORDER BY bid_datas.bid_interest_rate");
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
 	
 	public void BidStatusTableClear(){
 		try {
			DBManager db = new DBManager();
			try {
				boolean fo = db.doQuery("DELETE FROM bid_status");
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public void AddToBidStatusTable()
 	{
 		try {
			DBManager db = new DBManager();
	 		try {
 				StringBuffer strSql = new StringBuffer("INSERT INTO bid_status (bid_interest_rate,bank_code,bid_number,department_code,business_category,financial_institution_name,bid_amount_of_money,bid_interest_rate_key) "+ 
 						"SELECT DISTINCT bid_data_rate_orders.bid_interest_rate, bid_data_rate_orders.bank_code, bid_data_rate_orders.bid_number, "+ 
 						"bid_data_rate_orders.department_code, bid_data_rate_orders.business_categorie, bid_data_rate_orders.financial_institution_name, "+ 
 						"bid_data_rate_orders.bid_amount_money, bid_data_rate_orders.bid_interest_rate AS Process1 FROM bid_data_rate_orders ORDER BY bid_data_rate_orders.bid_interest_rate, bid_data_rate_orders.bank_code");
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
 	
 	public void BidSituationInterestRateCumulativeCalculationClear(){
 		try {
			DBManager db = new DBManager();
			try {
				boolean fo = db.doQuery("DELETE FROM  bid_situation_interest_rate_cumulative_calculation");
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public void BidSituationInterestRateCumulativeCalculation()
 	{
 		try {
			DBManager db = new DBManager();
	 		try {
 				StringBuffer strSql = new StringBuffer("INSERT INTO bid_situation_interest_rate_cumulative_calculation (groups,bid_interest_rate,sum_of_bid_amount_of_money) "+
 						"SELECT DISTINCT bid_status.bid_interest_rate AS g, Max(bid_status.bid_interest_rate) AS BidInterestRate, Sum(bid_status.bid_amount_of_money) AS BasicPriceTotal "+ 
 						"FROM bid_status GROUP BY bid_status.bid_interest_rate  ORDER BY bid_status.bid_interest_rate");
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
 	
 	public void UpdateBidStatusTable()
 	{
 		List<ThereSpecification> bdirs = new ArrayList<ThereSpecification>();
 		try {
 			DBManager db = new DBManager();
 	 		try {
 				StringBuffer strSql = new StringBuffer("SELECT bid_situation_interest_rate_cumulative_calculation.groups, bid_situation_interest_rate_cumulative_calculation.sum_of_bid_amount_of_money FROM bid_situation_interest_rate_cumulative_calculation");
 				ResultSet rst = db.getRecord(strSql.toString());
 				while(rst.next()){
 					ThereSpecification obj = new ThereSpecification();
 					obj.setBid_interest_rate_key(rst.getString(1));
 					obj.setInterest_rate_by_bid_amount_of_money(rst.getFloat(2));
 					
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
 		
 		List<String> bdstatus = new ArrayList<String>();
 		try {
 			DBManager db = new DBManager();
 	 		try {
 				StringBuffer strSql = new StringBuffer("SELECT bid_status.bid_interest_rate_key FROM bid_status");
 				ResultSet rst = db.getRecord(strSql.toString());
 				while(rst.next()){
 					bdstatus.add(rst.getString(1));
 				}
 	 		} catch (SQLException sqle) {
 				sqle.printStackTrace();
 			} finally {
 				db.close();
 			}
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 		
 		for(String ob:bdstatus)
 		{
 			for(ThereSpecification ths:bdirs)
 			{
 				if(ob.equals(ths.getBid_interest_rate_key()))
 				{
 					UpdateBidStatus(ths);
 					break;
 				}
 			}
 		}
 	}
 	
 	public void UpdateBidStatus(ThereSpecification obj)
 	{
 		try {
			DBManager db = new DBManager();
	 		try {
 				StringBuffer strSql = new StringBuffer("UPDATE bid_status SET interest_rate_by_bid_amount_of_money ="+obj.getInterest_rate_by_bid_amount_of_money()+" WHERE bid_status.bid_interest_rate_key='"+obj.getBid_interest_rate_key()+"'");
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
 	
 	public void UpdateBidStatus2(ThereSpecification obj)
 	{
 		try {
			DBManager db = new DBManager();
	 		try {
 				StringBuffer strSql = new StringBuffer("UPDATE bid_status SET "
 						+ "total_amount_of_money ="+obj.getTotal_amount_of_money()
 						+ ", cumulative_interest ="+obj.getCumulative_interest()
 						+ ", average_rate ="+obj.getAverage_rate()
 						+ ", sno ="+obj.getSno()
 						+ ", bid_interest_rate2 ="+obj.getBid_interest_rate2()
 						+ ", interest_rate_by_bid_amount_of_money ="+obj.getInterest_rate_by_bid_amount_of_money()
 						+" WHERE bid_status.bid_interest_rate_key='"+obj.getBid_interest_rate_key()+"'");
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
 	
 	public double INT(double ri)
	{
 		double integer = 0;
		if(ri==0.0)
			return integer;
 		double num = ri;

		String[] splitter = String.valueOf(num).split("\\.");
		int decimalPlaces = splitter[1].length();   // After  Decimal Count

		integer = (int)num;
		
		return integer;
	}
 	
 	public void NRJ_T_MAKE(int SHORI)
 	{
 		//Make InterestRateOrderBidStatusTable
 	    //Dim dbs As Database
 	    //Dim qdf As QueryDef
 	    //Dim rst As Recordset
 	    //Dim rst2 As Recordset
 	    //Dim F As Form
 	    double MAE_RITSU;
 	    String MAE_KEY;
 	    double MAE_KIN;
 	    double RUIKEI;
 	    double RUIKEI_RI;
 	    long COUNT01;
 	    
 	    //Set F = Forms!main
 	    /*Set dbs = CurrentDb()
 	    Set qdf = dbs.QueryDefs("BidInterestRateOrderClear")
 	    qdf.SQL = "DELETE DISTINCTROW BidDataInterestRateOrder.* FROM BidDataInterestRateOrder;"
 	    qdf.Execute
 	    qdf.Close*/
 	    BidInterestRateOrderClear();
 	    	    
 	    /*Set qdf = dbs.QueryDefs("AddBidInterestRateOrder")
 	    qdf.SQL = "INSERT INTO BidDataInterestRateOrder ( DepartmentCode, BusinessCategory, BankCode, BankName, BidNumber, BidInterestRate, BidPrice ) SELECT DISTINCTROW BidData.DepartmentCode, BidData.BusinessCategory, BidData.BankCode, IIf(Left([BankCode],1)='0',[BankName],IIf([BankCode]='1000004','NationalTrustVaultFederation',IIf([BankCode]='2010100','NationalTrust協同組合Federation',IIf([BankCode]='2950002','全国労働VaultFederation',[BankName])))) AS BankName1, BidData.BidNumber, BidData.BidInterestRate, BidData.BidPrice FROM BidData WHERE (((BidData.DepartmentCode)='" & F!bumoncode & "') AND ((BidData.BidInterestRate)<>0) AND ((BidData.BidDate)=#" & Format(F!nyusatsubi, "yyyy/mm/dd") & "#)) ORDER BY BidData.BidInterestRate;"
 	    qdf.Execute
 	    qdf.Close*/
 	    AddBidInterestRateOrder();
 	    
 	    /*Set qdf = dbs.QueryDefs("BidStatusTableClear")
 	    qdf.SQL = "DELETE DISTINCTROW BidStatusTable.* FROM BidStatusTable;"
 	    qdf.Execute
 	    qdf.Close*/
 	    BidStatusTableClear();
 	   
 	    /*Set qdf = dbs.QueryDefs("AddToBidStatusTable")
 	    qdf.SQL = "INSERT INTO BidStatusTable ( BidInterestRate, BankCode, BidNumber, DepartmentCode, BusinessCategory, BankName, BidPrice, BidInterestRateKey ) SELECT DISTINCTROW BidDataInterestRateOrder.BidInterestRate, BidDataInterestRateOrder.BankCode, BidDataInterestRateOrder.BidNumber, BidDataInterestRateOrder.DepartmentCode, BidDataInterestRateOrder.BusinessCategory, BidDataInterestRateOrder.BankName, BidDataInterestRateOrder.BidPrice, Format([BidInterestRate]) AS Process1 FROM BidDataInterestRateOrder ORDER BY BidDataInterestRateOrder.BidInterestRate, BidDataInterestRateOrder.BankCode;"
 	    qdf.Execute
 	    qdf.Close*/
 	    AddToBidStatusTable();
 	    
 	    /*DoCmd.DeleteObject acTable, "BidSituationInterestRateCumulativeCalculation"*/
 	    BidSituationInterestRateCumulativeCalculationClear();
 	    
 	    /*Set qdf = dbs.QueryDefs("BidSituationInterestRateCumulativeCalculation作成")
 	    qdf.SQL = "SELECT DISTINCTROW Format([BidInterestRate]) AS [group], Max(BidStatusTable.BidInterestRate) AS BidInterestRate, Sum(BidStatusTable.BidPrice) AS BasicPriceTotal INTO BidSituationInterestRateCumulativeCalculation FROM BidStatusTable GROUP BY Format([BidInterestRate]) ORDER BY Format([BidInterestRate]);"
 	    qdf.Execute
 	    qdf.Close*/
 	    BidSituationInterestRateCumulativeCalculation();
 	    
 	    /*Set qdf = dbs.QueryDefs("UpdateBidStatusTable")
 	    qdf.SQL = "UPDATE DISTINCTROW BidStatusTable LEFT JOIN BidSituationInterestRateCumulativeCalculation ON BidStatusTable.BidInterestRateKey = BidSituationInterestRateCumulativeCalculation.group SET BidStatusTable.BidPriceByInterestRate = [BasicPriceTotal];"
 	    qdf.Execute
 	    qdf.Close*/
 	    UpdateBidStatusTable();
 	    
 	    MAE_RITSU = 0;
 	    RUIKEI_RI = 0;
 	    RUIKEI = 0;
 	    COUNT01 = 1;
 	    //Set rst = dbs.OpenRecordset("BidStatusTable", dbOpenDynaset)
 	    List<ThereSpecification> bdrst = new ArrayList<ThereSpecification>();
		try {
			DBManager db = new DBManager();
	 		try {
				StringBuffer strSql = new StringBuffer("SELECT * FROM bid_status");
				ResultSet rs = db.getRecord(strSql.toString());
				while(rs.next()){
					ThereSpecification obj = new ThereSpecification();
					obj.setSno(rs.getLong(1));
					obj.setDepartment_code(rs.getString(2));
					obj.setBid_interest_rate(rs.getDouble(3));
					obj.setBid_interest_rate2(rs.getDouble(4));
					obj.setInterest_rate_by_bid_amount_of_money(rs.getFloat(5));
					obj.setAverage_rate(rs.getDouble(6));
					obj.setTotal_amount_of_money(rs.getDouble(7));
					obj.setCumulative_interest(rs.getDouble(8));
					obj.setBusiness_category(rs.getString(9));
					obj.setBank_code(rs.getString(10));
					obj.setFinancial_institution_name(rs.getString(11));
					obj.setBid_number(rs.getInt(12));
					obj.setBid_amount_of_money(rs.getFloat(13));
					obj.setBid_interest_rate_key(rs.getString(14));
					
					bdrst.add(obj);
				}
	 		} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
 	    //Do Until rst.EOF
		for(ThereSpecification rst:bdrst)
		{
 	        //rst.Edit
 	        //RUIKEI = RUIKEI + Nz(rst.BidPrice)
			//System.out.println("1. RUIKEI:"+RUIKEI+"\n"+"Nz(rst.getBid_amount_of_money()):"+Nz(rst.getBid_amount_of_money()));
			RUIKEI = RUIKEI + Nz(rst.getBid_amount_of_money());
			//System.out.println("1.1. RUIKEI:"+RUIKEI+"\n"+"Nz(rst.getBid_amount_of_money()):"+Nz(rst.getBid_amount_of_money()));
 	        //rst!TotalAmount = RUIKEI
			//System.out.println("2. rst.getTotal_amount_of_money()"+rst.getTotal_amount_of_money());
			rst.setTotal_amount_of_money(RUIKEI);
			//System.out.println("2.1. rst.getTotal_amount_of_money()"+rst.getTotal_amount_of_money());
 	        //RUIKEI_RI = RUIKEI_RI + Nz(rst!BidPrice) * Nz(rst!BidInterestRate) * 100000000
			//System.out.println("3. RUIKEI_RI:"+RUIKEI_RI+"\n"+"Nz(rst.getBid_amount_of_money()):"+Nz(rst.getBid_amount_of_money())+"\n"+"Nz(rst.getBid_interest_rate():"+Nz(rst.getBid_interest_rate()));
			RUIKEI_RI =  (float) (RUIKEI_RI + Nz(rst.getBid_amount_of_money()) * Nz(rst.getBid_interest_rate()) * 100000000);
			//System.out.println("3.1. RUIKEI_RI:"+RUIKEI_RI+"\n"+"Nz(rst.getBid_amount_of_money()):"+Nz(rst.getBid_amount_of_money())+"\n"+"Nz(rst.getBid_interest_rate():"+Nz(rst.getBid_interest_rate()));
 	        //rst!AccumulatedInterest = RUIKEI_RI
			//System.out.println("4. rst.getCumulative_interest"+rst.getCumulative_interest());
			rst.setCumulative_interest(RUIKEI_RI);
			//System.out.println("4.1. rst.getCumulative_interest"+rst.getCumulative_interest());
 	        //if Format(RUIKEI_RI / (RUIKEI * 100000000) * 100000) Like "*.*" Then
			if(Like((double) (RUIKEI_RI / (RUIKEI * 100000000) * 100000),"*.*")){
 	        	//'rst!AvarageInterestRate = Int(RUIKEI_RI / (RUIKEI * 100000000) * 10000) / 10000
 	            //rst!AvarageInterestRate = Int(RUIKEI_RI / (RUIKEI * 100000000) * 100000) / 100000
				rst.setAverage_rate(INT(RUIKEI_RI / (RUIKEI * 100000000) * 100000) / 100000);
				//rst.setAverage_rate(INT(RUIKEI_RI / (RUIKEI * 100000000) * 100000) / 100000);
 	            //'rst!AvarageInterestRate = Format(RUIKEI_RI / (RUIKEI * 100000000), "0.00000")
			}else{
 	            //rst!setAverage_rate = RUIKEI_RI / (RUIKEI * 100000000)
				//System.out.println("6. RUIKEI_RI:"+RUIKEI_RI+"\n"+"RUIKEI:"+RUIKEI);
 	        	rst.setAverage_rate((double) (RUIKEI_RI / (RUIKEI * 100000000)));
 	        	//System.out.println("6.1. RUIKEI_RI:"+RUIKEI_RI+"\n"+"RUIKEI:"+RUIKEI);
			}
 	        rst.setSno(COUNT01);
 	        COUNT01 = COUNT01 + 1;
 	        if(rst.getBid_interest_rate()== MAE_RITSU)
 	        {
 	            //rst.BidInterestRate2 = Null
 	        	rst.setBid_interest_rate2(0.0);
 	            //rst!BidPriceByInterestRate = Null
 	        	rst.setInterest_rate_by_bid_amount_of_money(0.0f);
 	        }
 	        else
 	        {
 	            //rst!BidInterestRate2 = rst!BidInterestRate
 	        	rst.setBid_interest_rate2(rst.getBid_interest_rate());
 	        }
 	        
 	        //MAE_RITSU = rst!BidInterestRate
 	        MAE_RITSU = rst.getBid_interest_rate();
 	        //rst.Update
 	        //rst.MoveNext
 	       UpdateBidStatus2(rst);
 	    //Loop
 	    //rst.Close
		}
 	    //If SHORI = 1 Then
		if(SHORI == 1)
		{
 	        //DoCmd.OpenReport "BidStatusTable", acPreview
			JasperReportViewer nw = JasperReportViewer.getInstance("ThereSpecification",new Date());
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
				e1.printStackTrace();
			}

			dispose();
		}//ElseIf SHORI = 2 Then
		else if(SHORI == 2)
		{
 	/*'        If OBJ_EXIST("BidStatusTable2") Then
 	'            DoCmd.DeleteObject acTable, "BidStatusTable1c"
 	'        End If
 	'        Set qdf = dbs.QueryDefs("BidStatusTable1b")
 	'        qdf.SQL = "SELECT DISTINCTROW BidStatusTable.SNO, BidStatusTable.DepartmentCode, BidStatusTable.BidInterestRate2, BidStatusTable.BidPriceByInterestRate, BidStatusTable.AvarageInterestRate, BidStatusTable.TotalAmount INTO BidStatusTable1c FROM BidStatusTable ORDER BY BidStatusTable.SNO;"
 	'        qdf.Execute
 	'        qdf.Close
 	'        Set rst = dbs.OpenRecordset("BidStatusTable1c", dbOpenDynaset)
 	'        MAE_RITSU = 0
 	'        MAE_KIN = 0
 	'        Do Until rst.EOF
 	'            If IsNull(rst!BidPriceByInterestRate) Then
 	'                rst.Edit
 	'                rst!BidInterestRate2 = MAE_RITSU
 	'                rst!BidPriceByInterestRate = MAE_KIN
 	'                rst.Update
 	'            End If
 	'            MAE_RITSU = rst!BidInterestRate2
 	'            MAE_KIN = rst!BidPriceByInterestRate
 	'            rst.MoveNext
 	'        Loop
 	'        rst.Close
*/ 	        
			//Set rst = dbs.OpenRecordset("SELECT Sum(BidStatusTable.BidPriceByInterestRate) AS InterestRate別BasicPriceTotal, Max(BidStatusTable.TotalAmount) AS TotalAmountの最大 FROM BidStatusTable;")
			double InterestRateByBasicPriceTotal=0.0;
			double largestTotalAmount=0.0;
			try {
				DBManager db = new DBManager();
		 		try {
					StringBuffer strSql = new StringBuffer("SELECT Sum(bid_status.interest_rate_by_bid_amount_of_money) AS InterestRateByBasicPriceTotal, Max(bid_status.total_amount_of_money) AS TotalAmountOflargest FROM bid_status");
					ResultSet rs = db.getRecord(strSql.toString());
					if(rs.next()){
						InterestRateByBasicPriceTotal = rs.getDouble(1);
						largestTotalAmount = rs.getDouble(2);
					}
		 		} catch (SQLException sqle) {
					sqle.printStackTrace();
				} finally {
					db.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
						
 	        /*If rst!TotalAmountの最大 <> rst!InterestRate別BasicPriceTotal Then
 	            MsgBox "計算エラーが発生しました。再度実行してください"
 	            Exit Function
 	        End If*/
			if(largestTotalAmount != InterestRateByBasicPriceTotal)
			{
				JOptionPane.showMessageDialog(null, "計算エラーが発生しました。再度実行してください");
				return;
			}
 	        //Forms!InterestRate順BidStatusTable!TotalPrice = rst!TotalAmountの最大
			total_amount.setText(String.valueOf(largestTotalAmount));
 	        //rst.Close
 	        //DoCmd.OpenReport "BidStatusTable2", acPreview
			JasperReportViewer nw = JasperReportViewer.getInstance("NoSpecification",new Date());
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
				e1.printStackTrace();
			}

			dispose();
 	    //End If
		}


 	}
}

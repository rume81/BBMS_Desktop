package com.ey.application.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.ey.application.controller.DBManager;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* FrmBidDocumentPrint.java
* --------------------
* Created on Mar 10, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Mar 10, 2016: Original version (Monsur)
*
*/
public class FrmBidDocumentPrint extends JInternalFrame {
	// Variables declaration - do not modify                     
	private javax.swing.JComboBox<String> cmbDeptCode;
    private javax.swing.JButton cmdCloseup;
    private javax.swing.JButton cmdProvisionalNotice;
    private javax.swing.JButton cmdSuccessfulBidDocument;
    private javax.swing.JButton cmdTransmissionDate;
    private javax.swing.JLabel lblDeptCode;
    private javax.swing.JLabel lblSuccessfulBidDocumentDate;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextField txtDeptName;
    // End of variables declaration          
    private String bidDate;
    UtilDateModel model;
	//JDatePanelImpl datePanel;
	//JDatePickerImpl txtSuccessfulBidDocumentDate;
    JTextField txtSuccessfulBidDocumentDate;
    
	public FrmBidDocumentPrint(Date date) {
		try {
			String datePattern = "dd-MM-yyyy";
		    SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
			bidDate = dateFormatter.format(date);
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	private void jbInit() {

		pnlMain = new javax.swing.JPanel();
        lblSuccessfulBidDocumentDate = new javax.swing.JLabel();
        //txtSuccessfulBidDocumentDate = new javax.swing.JTextField();
        cmdTransmissionDate = new javax.swing.JButton();
        cmdSuccessfulBidDocument = new javax.swing.JButton();
        cmdCloseup = new javax.swing.JButton();
        cmbDeptCode = new javax.swing.JComboBox<>();
        lblDeptCode = new javax.swing.JLabel();
        txtDeptName = new javax.swing.JTextField();
        cmdProvisionalNotice = new javax.swing.JButton();
        
       /* model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model, p);
		txtSuccessfulBidDocumentDate = new JDatePickerImpl(datePanel, new DateLabelFormatter());*/
        txtSuccessfulBidDocumentDate = new JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("落札書印刷");
        setPreferredSize(new java.awt.Dimension(350, 300));
        getContentPane().setLayout(null);

        pnlMain.setBackground(new java.awt.Color(176, 224, 230));
        pnlMain.setMinimumSize(new java.awt.Dimension(470, 370));
        pnlMain.setName("pnlMain"); // NOI18N
        pnlMain.setPreferredSize(new java.awt.Dimension(470, 370));
        pnlMain.setLayout(null);

        lblSuccessfulBidDocumentDate.setText("落札書日付");
        lblSuccessfulBidDocumentDate.setToolTipText("");
        lblSuccessfulBidDocumentDate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblSuccessfulBidDocumentDate.setName(""); // NOI18N
        pnlMain.add(lblSuccessfulBidDocumentDate);
        lblSuccessfulBidDocumentDate.setBounds(20, 70, 70, 27);

        //txtSuccessfulBidDocumentDate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(txtSuccessfulBidDocumentDate);
        txtSuccessfulBidDocumentDate.setBounds(100, 70, 180, 27);
        txtSuccessfulBidDocumentDate.setText(ConvertDateToJP(bidDate));
        txtSuccessfulBidDocumentDate.setHorizontalAlignment(JTextField.RIGHT);

        cmdTransmissionDate.setText("送信用データ");
        pnlMain.add(cmdTransmissionDate);
        cmdTransmissionDate.setBounds(170, 130, 110, 30);
        cmdTransmissionDate.setVisible(false);

        cmdSuccessfulBidDocument.setText("落札書");
        pnlMain.add(cmdSuccessfulBidDocument);
        cmdSuccessfulBidDocument.setBounds(20, 170, 110, 30);
        cmdSuccessfulBidDocument.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RAKU_PRINT(1);
			}
        });
        
        cmdCloseup.setText("閉じる");
        cmdCloseup.setToolTipText("");
        pnlMain.add(cmdCloseup);
        cmdCloseup.setBounds(170, 170, 110, 30);
        cmdCloseup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
        });
        
        cmbDeptCode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbDeptCode.setName("cmbDeptCode"); // NOI18N
        cmbDeptCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        
        pnlMain.add(cmbDeptCode);
        cmbDeptCode.setBounds(100, 10, 180, 23);
        departmentCode();
        cmbDeptCode.setVisible(false);
        getDetpInfo();
        
        lblDeptCode.setText("勘定");
        lblDeptCode.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblDeptCode);
        lblDeptCode.setBounds(20, 10, 70, 23);
        lblDeptCode.setVisible(false);
        pnlMain.add(txtDeptName);
        txtDeptName.setBounds(100, 40, 180, 23);
        txtDeptName.setVisible(false);

        cmdProvisionalNotice.setText("仮通知書");
        pnlMain.add(cmdProvisionalNotice);
        cmdProvisionalNotice.setBounds(20, 130, 110, 30);
        cmdProvisionalNotice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RAKU_PRINT(2);
			}
        });
        
        getContentPane().add(pnlMain);
        pnlMain.setBounds(0, 0, 310, 240);
        pnlMain.getAccessibleContext().setAccessibleName("pnlMain");
        pnlMain.getAccessibleContext().setAccessibleDescription("");
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
	private static FrmBidDocumentPrint myInstance;

 	public static FrmBidDocumentPrint getInstance(Date date) {
 	    //if (myInstance == null) {
 	        myInstance = new FrmBidDocumentPrint(date);
 	    //}
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
 	public String ConvertDateToJP(String date)
	{
		String jpdate = "";
		String ar[] = date.split("-");
		if(ar[2]!=null)
		{
			long cyear = Long.parseLong(ar[2]);
			long i=cyear-1988;
			int mon = Integer.parseInt(ar[1]);
			int day = Integer.parseInt(ar[0]);
						
			jpdate = "平成"+i+"年"+mon+"月"+day+"日";
		}
		return jpdate;
	}
 	
 	public void RAKU_PRINT(int SHORI){
 		//Print of a successful bid document
 		try {
 			ResultSet rs =null;
 			DBManager dbU = new DBManager();
			DBManager db = new DBManager();
			try {
				rs = db.getRecord("SELECT * FROM successful_bid_datas_3");
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
			
			try{
				while(rs.next()) {
					if('\0' == (rs.getFloat(7)))
					{
						String department = rs.getString(1);
						String bank = rs.getString(3);
						int bid = rs.getInt(5);
						
						boolean Fo = dbU.doQuery("UPDATE successful_bid_datas_3 SET " + 
								"successful_bid_interest_rate = 0" +
								" WHERE department_code ='" +department +
								"' AND bank_code = '"+bank +
								"' AND bid_number ="+bid);
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				dbU.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 		if(SHORI == 1){
 			JasperReportViewer nw = JasperReportViewer.getInstance("SuccessfulBidDocument",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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
 			JasperReportViewer nw = JasperReportViewer.getInstance("ProvisionalNotice",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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
  		
 				/*try {
 			ResultSet SuccessfulBidData4 =null;
			DBManager db = new DBManager();
			try {
				SuccessfulBidData4 = db.getRecord("SELECT DISTINCT successful_bid_datas_3.department_code,divisions.department_name, successful_bid_datas_3.business_category, successful_bid_datas_3.bank_code, successful_bid_datas_3.financial_institution_name, " + 
												  "CASE WHEN successful_bid_datas_3.bid_number > 10 THEN '合計' ELSE successful_bid_datas_3.bid_number END AS bid_number1, " + 
												  "successful_bid_datas_3.bid_interest_rate, successful_bid_datas_3.bid_amount_money, successful_bid_datas_3.successful_bid_interest_rate, "+ 
												  "successful_bid_datas_3.successful_bid_price FROM (successful_bid_datas_3 LEFT JOIN divisions ON successful_bid_datas_3.department_code = divisions.department_code) INNER JOIN bid_datas ON (successful_bid_datas_3.department_code = bid_datas.department_code) AND (successful_bid_datas_3.bank_code = bid_datas.bank_code) AND (successful_bid_datas_3.auction_date = bid_datas.auction_date) "+
												  "WHERE (((successful_bid_datas_3.department_code) = '"+String.valueOf(cmbDeptCode.getSelectedItem())+"') AND ((successful_bid_datas_3.financial_institution_name) <> '合  計') AND ((successful_bid_datas_3.auction_date) = '"+bidDate+"') AND ((bid_datas.flag1) = '1'))");
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
 		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 	    Set qdf = dbs.QueryDefs("SuccessfulBidData4")
 	    //qdf.SQL = "SELECT DISTINCTROW SuccessfulBidData3.DepartmentCode, DepartmentTable.DepartmentName, SuccessfulBidData3.BusinessCategory, SuccessfulBidData3.BankCode, SuccessfulBidData3.BankName, IIf([SuccessfulBidData3].[BidNumber]>10,'Total',[SuccessfulBidData3].[BidNumber]) AS BidNumber1, SuccessfulBidData3.BidInterestRate, SuccessfulBidData3.BidPrice, SuccessfulBidData3.SuccessfulInterestRate, SuccessfulBidData3.SuccessfulBidPrice FROM SuccessfulBidData3 LEFT JOIN DepartmentTable ON SuccessfulBidData3.DepartmentCode = DepartmentTable.DepartmentCode WHERE (((SuccessfulBidData3.DepartmentCode)='" & F!bumoncode & "') AND ((SuccessfulBidData3.BankName)<>'合  Total') and BidDate=#" & Format(F2!nyusatsubi, "yyyy/mm/dd") & "#);"
 	    qdf.SQL = "SELECT DISTINCTROW SuccessfulBidData3.DepartmentCode, DepartmentTable.DepartmentName, SuccessfulBidData3.BusinessCategory, SuccessfulBidData3.BankCode, SuccessfulBidData3.BankName, IIf([SuccessfulBidData3].[BidNumber]>10,'Total',[SuccessfulBidData3].[BidNumber]) AS BidNumber1, SuccessfulBidData3.BidInterestRate, SuccessfulBidData3.BidPrice, SuccessfulBidData3.SuccessfulInterestRate, SuccessfulBidData3.SuccessfulBidPrice FROM (SuccessfulBidData3 LEFT JOIN DepartmentTable ON SuccessfulBidData3.DepartmentCode = DepartmentTable.DepartmentCode) INNER JOIN BidData ON (SuccessfulBidData3.BidDate = BidData.BidDate) AND (SuccessfulBidData3.BankCode = BidData.BankCode) AND (SuccessfulBidData3.DepartmentCode = BidData.DepartmentCode) WHERE (((SuccessfulBidData3.DepartmentCode)='" & F!bumoncode & "') AND ((SuccessfulBidData3.BankName)<>'合  Total') AND ((SuccessfulBidData3.BidDate)=#" & Format(F2!nyusatsubi, "yyyy/mm/dd") & "#) AND ((BidData.flag1)='1'));"
 	    qdf.Close
 	    If SHORI = 1 Then
 	        DoCmd.OpenReport "SuccessfulBidDocument", acDesign
 	        Reports!SuccessfulBidDocument!OutputYearMonthDay.Caption = Format(F!Date, "GGGEYearMMonthDDay")
 	        DoCmd.Save acReport, "SuccessfulBidDocument"
 	        DoCmd.Close acReport, "SuccessfulBidDocument"
 	        DoCmd.OpenReport "SuccessfulBidDocument", acPreview
 	    ElseIf SHORI = 2 Then
 	        DoCmd.OpenReport "SuccessfulBidProvisionalNotice", acDesign
 	        Reports!SuccessfulBidProvisionalNotice!OutputYearMonthDay.Caption = Format(F!Date, "GGGEYearMMonthDDay")
 	        DoCmd.Save acReport, "SuccessfulBidProvisionalNotice"
 	        DoCmd.Close acReport, "SuccessfulBidProvisionalNotice"
 	        DoCmd.OpenReport "SuccessfulBidProvisionalNotice", acPreview
 	    End If
*/
 	}
}


package com.ey.master.view;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import com.ey.application.controller.DBManager;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* FrmChangeVariousParameters.java
* --------------------
* Created on Mar 29, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Mar 29, 2016: Original version (Monsur)
*
*/
public class FrmChangeVariousParameters extends JInternalFrame {
	// Variables declaration - do not modify                     
    private javax.swing.JButton cmdCloseup;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JLabel lblBidWording;
    private javax.swing.JLabel lblCreditApplication;
    private javax.swing.JLabel lblDivisionName;
    private javax.swing.JLabel lblFinanceMinister;
    private javax.swing.JLabel lblPresidentName;
    private javax.swing.JLabel lblPresidentName2;
    private javax.swing.JLabel lblProvisionalDocument;
    private javax.swing.JLabel lblStreetAddress;
    private javax.swing.JButton cmdSave;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JTextArea taBidWording;
    private javax.swing.JTextArea taBidWording2;
    private javax.swing.JTextArea taChairmanName;
    private javax.swing.JTextArea taCreditApplication;
    private javax.swing.JTextArea taDivisionName;
    private javax.swing.JTextArea taFinanceMinister;
    private javax.swing.JTextArea taPresidentName;
    private javax.swing.JTextArea taProvisionalDocument;
    private javax.swing.JTextArea taProvisionalDocument2;
    private javax.swing.JTextArea taStreetAddress;
    // End of variables declaration  
    String DeptCode ="";
    
    public FrmChangeVariousParameters() {
	   	try {
	   		jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    private void jbInit() {
    	
        pnlMain = new javax.swing.JPanel();
        lblPresidentName = new javax.swing.JLabel();
        lblPresidentName2 = new javax.swing.JLabel();
        lblFinanceMinister = new javax.swing.JLabel();
        lblDivisionName = new javax.swing.JLabel();
        lblProvisionalDocument = new javax.swing.JLabel();
        lblBidWording = new javax.swing.JLabel();
        cmdSave =  new javax.swing.JButton();
        lblCreditApplication = new javax.swing.JLabel();
        lblStreetAddress = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taPresidentName = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        taChairmanName = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        taFinanceMinister = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        taProvisionalDocument = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        taProvisionalDocument2 = new javax.swing.JTextArea();
        jScrollPane6 = new javax.swing.JScrollPane();
        taBidWording = new javax.swing.JTextArea();
        jScrollPane7 = new javax.swing.JScrollPane();
        taBidWording2 = new javax.swing.JTextArea();
        jScrollPane8 = new javax.swing.JScrollPane();
        taCreditApplication = new javax.swing.JTextArea();
        jScrollPane9 = new javax.swing.JScrollPane();
        taStreetAddress = new javax.swing.JTextArea();
        jScrollPane10 = new javax.swing.JScrollPane();
        taDivisionName = new javax.swing.JTextArea();
        cmdCloseup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("パラメータ変更");
        setPreferredSize(new java.awt.Dimension(630, 638));
        getContentPane().setLayout(null);

        pnlMain.setBackground(new java.awt.Color(176, 224, 230));
        pnlMain.setLayout(null);

        lblPresidentName.setText("社長名");
        lblPresidentName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblPresidentName);
        lblPresidentName.setBounds(50, 20, 80, 30);

        lblPresidentName2.setText("社長名２");
        lblPresidentName2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblPresidentName2);
        lblPresidentName2.setBounds(50, 65, 80, 30);

        lblFinanceMinister.setText("大蔵大臣");
        lblFinanceMinister.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblFinanceMinister);
        lblFinanceMinister.setBounds(50, 110, 80, 30);

        lblDivisionName.setText("部署名");
        lblDivisionName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblDivisionName);
        lblDivisionName.setBounds(50, 155, 80, 30);

        lblProvisionalDocument.setText("仮落札書文言");
        lblProvisionalDocument.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblProvisionalDocument);
        lblProvisionalDocument.setBounds(50, 200, 80, 30);

        lblBidWording.setText("落札書文言");
        lblBidWording.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblBidWording);
        lblBidWording.setBounds(50, 310, 80, 30);
        
       

        lblCreditApplication.setText("借入申込書文言");
        lblCreditApplication.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblCreditApplication);
        lblCreditApplication.setBounds(50, 400, 81, 30);

        lblStreetAddress.setText("住所");
        lblStreetAddress.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblStreetAddress);
        lblStreetAddress.setBounds(50, 500, 80, 30);

        taPresidentName.setColumns(20);
        taPresidentName.setRows(5);
        jScrollPane1.setViewportView(taPresidentName);

        pnlMain.add(jScrollPane1);
        jScrollPane1.setBounds(150, 20, 380, 40);
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        taChairmanName.setColumns(20);
        taChairmanName.setRows(5);
        jScrollPane2.setViewportView(taChairmanName);

        pnlMain.add(jScrollPane2);
        jScrollPane2.setBounds(150, 65, 380, 40);
        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        taFinanceMinister.setColumns(20);
        taFinanceMinister.setRows(5);
        jScrollPane3.setViewportView(taFinanceMinister);

        pnlMain.add(jScrollPane3);
        jScrollPane3.setBounds(150, 110, 380, 40);
        jScrollPane3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        taProvisionalDocument.setColumns(20);
        taProvisionalDocument.setRows(5);
        jScrollPane4.setViewportView(taProvisionalDocument);

        pnlMain.add(jScrollPane4);
        jScrollPane4.setBounds(150, 200, 380, 60);
        jScrollPane4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        taProvisionalDocument2.setColumns(20);
        taProvisionalDocument2.setRows(5);
        jScrollPane5.setViewportView(taProvisionalDocument2);
        
        pnlMain.add(jScrollPane5);
        jScrollPane5.setBounds(150, 265, 380, 40);
        jScrollPane5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        taBidWording.setColumns(20);
        taBidWording.setRows(5);
        jScrollPane6.setViewportView(taBidWording);
        
        pnlMain.add(jScrollPane6);
        jScrollPane6.setBounds(150, 310, 380, 40);
        jScrollPane6.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        taBidWording2.setColumns(20);
        taBidWording2.setRows(5);
        jScrollPane7.setViewportView(taBidWording2);

        pnlMain.add(jScrollPane7);
        jScrollPane7.setBounds(150, 355, 380, 40);
        jScrollPane7.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        taCreditApplication.setColumns(20);
        taCreditApplication.setRows(5);
        jScrollPane8.setViewportView(taCreditApplication);

        pnlMain.add(jScrollPane8);
        jScrollPane8.setBounds(150, 400, 380, 96);
        jScrollPane8.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        taStreetAddress.setColumns(20);
        taStreetAddress.setRows(5);
        jScrollPane9.setViewportView(taStreetAddress);

        pnlMain.add(jScrollPane9);
        jScrollPane9.setBounds(150, 500, 380, 40);
        jScrollPane9.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        taDivisionName.setColumns(20);
        taDivisionName.setRows(5);
        jScrollPane10.setViewportView(taDivisionName);

        pnlMain.add(jScrollPane10);
        jScrollPane10.setBounds(150, 155, 380, 40);
        jScrollPane10.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        
        cmdSave.setText("更新");
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	updateVariousParameters();
            }
        });
        pnlMain.add(cmdSave);
        cmdSave.setBounds(230, 550, 80, 30);
        
        cmdCloseup.setText("閉じる");
        cmdCloseup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	Close();
            }
        });
        pnlMain.add(cmdCloseup);
        cmdCloseup.setBounds(330, 550, 80, 30);

        getContentPane().add(pnlMain);
        pnlMain.setBounds(0, 0, 620, 600);

        loadVariousParameters();
    }
    
    private static FrmChangeVariousParameters myInstance;
	
	public static FrmChangeVariousParameters getInstance() {
 	    //if (myInstance == null) {
 	        myInstance = new FrmChangeVariousParameters();
 	    //}
 	    return myInstance;
 	}
    
    public void Close()
	{
    	FrmMasterMaintenance nw = FrmMasterMaintenance.getInstance();
		nw.setBounds(0, 0, 329, 372);
		nw.getContentPane().setBackground(new Color(176,224,230));
		nw.setForeground(Color.black);
		nw.setResizable(false);
		nw.setTitle("マスターのメンテナンス");
		nw.getContentPane().setLayout(null);
		
		if (nw.isVisible()) {
		} else {
			getDesktopPane().add(nw,new Integer(Integer.MAX_VALUE));
			nw.setVisible(true);
		}

		nw.setIconifiable(true);
		nw.setMaximizable(false);
		nw.setClosable(true);
		dispose();
	}
    /*CREATE TABLE "base" (
    		`wrkdirs`	TEXT,
    		`department_code`	TEXT,
    		`department_name`	TEXT,
    		`chairman_name`	TEXT,
    		`chairman_name_2`	TEXT,
    		`minister_of_finance`	TEXT,
    		`street_address`	TEXT,
    		`financial_institution_name_1`	TEXT,
    		`financial_institution_name_2`	TEXT,
    		`division_name`	TEXT,
    		`provisional_successful_bid_document_wording`	TEXT,
    		`successful_bid_document_wording`	TEXT,
    		`provisional_successful_bid_document_wording_2`	TEXT,
    		`successful_bid_document_wording_2`	TEXT,
    		`credit_application_wording`	TEXT,
    		`payee's_bank_name`	TEXT
    	)
    	
    	*
    	*/
    
    public void loadVariousParameters() {
    	try {
			DBManager db = new DBManager();
			try {
				ResultSet rs;
				rs = db.getRecord("SELECT * FROM base");
				
				if(rs.next()) {
					DeptCode = rs.getString(2);
					taChairmanName.setText(rs.getString(4));
					taPresidentName.setText(rs.getString(5));
					taFinanceMinister.setText(rs.getString(6));
					taStreetAddress.setText(rs.getString(7));
					taDivisionName.setText(rs.getString(10));
					taProvisionalDocument.setText(rs.getString(11));
					taBidWording.setText(rs.getString(12));
					taProvisionalDocument2.setText(rs.getString(13));
					taBidWording2.setText(rs.getString(14));
					taCreditApplication.setText(rs.getString(15));
									
					//dtm.addRow(new Object[] { dept_code, horei01, horei02});
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
    
    public void updateVariousParameters()
    {
    	try {
			DBManager db = new DBManager();
			try {
				StringBuffer sb = new StringBuffer("UPDATE base SET "
						+ "chairman_name='"+taChairmanName.getText()
						+ "',chairman_name_2='"+taPresidentName.getText()
						+ "',minister_of_finance='"+taFinanceMinister.getText()
						+ "',street_address='"+taStreetAddress.getText()
						+ "',department_name='"+taDivisionName.getText()
						+ "',provisional_successful_bid_document_wording='"+taProvisionalDocument.getText()
						+ "',successful_bid_document_wording='"+taBidWording.getText()
						+ "',provisional_successful_bid_document_wording_2='"+taProvisionalDocument2.getText()
						+ "',successful_bid_document_wording_2='"+taBidWording2.getText()
						+ "',credit_application_wording='"+taCreditApplication.getText()
						+ "' WHERE base.department_code='"+DeptCode+"'");
				//System.out.println(sb);
				boolean fo = db.doQuery(sb.toString());
				
				if(fo)
					JOptionPane.showMessageDialog(null, "更新しました");
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}


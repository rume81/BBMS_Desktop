package com.ey.master.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JInternalFrame;

import com.ey.application.controller.DBManager;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* FrmSystemSelection.java
* --------------------
* Created on Mar 30, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Mar 30, 2016: Original version (Monsur)
*
*/
public class FrmSystemSelection extends JInternalFrame {
	// Variables declaration - do not modify                     
    private javax.swing.JComboBox<String> cmbSystemName;
    private javax.swing.JButton cmdCloseup;
    private javax.swing.JButton cmdRun;
    private javax.swing.JLabel lblSystemName;
    private javax.swing.JPanel pnlMain;
    // End of variables declaration
    String FileName="";
    public FrmSystemSelection() {
	   	try {
	   		jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    private void jbInit() {
    	this.setBounds(0,0,300,200);
    	this.setTitle("システム別マスタ設定");
        pnlMain = new javax.swing.JPanel();
        cmdRun = new javax.swing.JButton();
        cmdCloseup = new javax.swing.JButton();
        cmbSystemName = new javax.swing.JComboBox<>();
        lblSystemName = new javax.swing.JLabel();
        
        this.setIconifiable(true);
		this.setMaximizable(false);
		this.setClosable(true);
		
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);

        pnlMain.setBackground(new java.awt.Color(176, 224, 230));
        pnlMain.setMinimumSize(new java.awt.Dimension(470, 370));
        pnlMain.setName("pnlMain");
        pnlMain.setLayout(null);

        cmdRun.setText("実　　行");
        cmdRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	String selectedCode = String.valueOf(cmbSystemName.getSelectedItem());
            	if(!selectedCode.equals(""))
            	{
            		FrmSystemSpecificMaintenance nw = FrmSystemSpecificMaintenance.getInstance(selectedCode,FileName);
    				nw.setBounds(0, 0, 740, 440);
    				nw.getContentPane().setBackground(new Color(176,224,230));
    				nw.setForeground(Color.black);
    				nw.setResizable(false);
    				nw.setTitle("システム別メンテ");
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
            }
        });
        pnlMain.add(cmdRun);
        cmdRun.setBounds(20, 110, 110, 30);

        cmdCloseup.setText("閉じる");
        cmdCloseup.setToolTipText("");
        pnlMain.add(cmdCloseup);
        cmdCloseup.setBounds(170, 110, 110, 30);
        cmdCloseup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

        cmbSystemName.setName("cmbSystemName"); // NOI18N
        cmbSystemName.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent arg0) {
            	LoadSystemInfoAfterSelection();
            }
        });
        pnlMain.add(cmbSystemName);
        cmbSystemName.setBounds(100, 60, 180, 23);

        lblSystemName.setText("システム名");
        lblSystemName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblSystemName);
        lblSystemName.setBounds(10, 60, 80, 23);

        getContentPane().add(pnlMain);
        pnlMain.setBounds(0, 0, 310, 200);

        LoadSystemName();
        
        //LoadSystemInfoInFormLoad();
    } 
    
    private static FrmSystemSelection myInstance;
	
	public static FrmSystemSelection getInstance() {
 	    if (myInstance == null) {
 	        myInstance = new FrmSystemSelection();
 	    }
 	    return myInstance;
 	}
	
	
	public void LoadSystemName() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT DISTINCT systems_name.system_name FROM systems_name WHERE field_name IS NOT NULL");
				cmbSystemName.addItem("");
				while(rs.next()) {
					String code = rs.getString(1);
					cmbSystemName.addItem(code);
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
	
	/*public void LoadSystemInfoInFormLoad() {
		String FileName="";
		dropView();
		try {
			DBManager db = new DBManager();
			DBManager dbC = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT * FROM systems_name WHERE field_name IS NOT NULL");
				
				if(rs.next()) {
					String code = rs.getString(1);
					cmbSystemName.setSelectedItem(code);
					FileName = rs.getString(2);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
			
			try{
				StringBuffer strSql = new StringBuffer("CREATE VIEW SystemSpecificMaintenance AS "+
						"SELECT DISTINCT financial_institutions_masters_temp.Financial institutions code, "+ 
						"financial_institutions_masters_temp.financial_institution_name, "+
						"financial_institutions_masters_temp.store, "+ 
						"financial_institutions_masters_temp.business_category, "+ 
						"financial_institutions_masters_temp."+FileName+" AS FN, "+ 
						"financial_institutions_masters_temp.Updated "+
						"FROM financial_institutions_masters_temp");
				boolean fo1= dbC.doQuery(strSql.toString());
				
				System.out.println(strSql);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				dbC.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public void LoadSystemInfoAfterSelection() {
		String selectedCode = String.valueOf(cmbSystemName.getSelectedItem());
		if(selectedCode.equals(""))
			return;
		dropView();
		try {
			DBManager db = new DBManager();
			DBManager dbC = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT * FROM systems_name WHERE field_name IS NOT NULL");
				
				while(rs.next()) {
					String code = rs.getString(1);
					if(code.equals(selectedCode))
					{
						FileName = rs.getString(2);
					}
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
			
			try{
				StringBuffer strSql = new StringBuffer("CREATE VIEW SystemSpecificMaintenance AS "+
						"SELECT DISTINCT financial_institutions_masters_temp.bank_code, "+ 
						"financial_institutions_masters_temp.financial_institution_name, "+
						"financial_institutions_masters_temp.store, "+ 
						"financial_institutions_masters_temp.business_category, "+ 
						"financial_institutions_masters_temp."+FileName+" AS FN, "+ 
						"financial_institutions_masters_temp.Updated "+
						"FROM financial_institutions_masters_temp");
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
	
	public void dropView()
	{
		try {
			DBManager db = new DBManager();
			try {
				boolean fo = db.doQuery("DROP VIEW IF EXISTS 'SystemSpecificMaintenance'");	
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


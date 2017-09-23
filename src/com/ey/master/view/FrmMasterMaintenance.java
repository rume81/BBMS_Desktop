package com.ey.master.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.ey.application.controller.DBManager;
import com.ey.application.view.DateLabelFormatter;
import com.ey.application.view.FrmBidDocumentPrint;
import com.ey.application.view.FrmBidMain;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* FrmMasterMaintenance.java
* --------------------
* Created on Mar 20, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Mar 20, 2016: Original version (Monsur)
*
*/
public class FrmMasterMaintenance extends JInternalFrame {
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton cmdChangeCertificate;
	private javax.swing.JButton cmdChangeVariousParameters;
	private javax.swing.JButton cmdEnd;
	private javax.swing.JButton cmdFinancialInstitutions;
	private javax.swing.JButton cmdItemDataClear;
	private javax.swing.JButton cmdOfficialNameConversion;
	private javax.swing.JLabel lblAuctionDate;
	private javax.swing.JPanel pnlMain;
	private javax.swing.JPanel pnlSub;
	//private javax.swing.JTextField txtTargetAuction;
	UtilDateModel model;
	JDatePanelImpl datePanel;
	JDatePickerImpl txtTargetAuction;
	String DepartmentCode;
	// End of variables declaration//GEN-END:variables
	public FrmMasterMaintenance() {
	   	try {
	   		jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	    
	private void jbInit() throws Exception {
		this.setBounds(0, 0, 399, 490);
		pnlMain = new javax.swing.JPanel();
		pnlSub = new javax.swing.JPanel();
	    cmdFinancialInstitutions = new javax.swing.JButton();
	    cmdChangeCertificate = new javax.swing.JButton();
	    cmdChangeVariousParameters = new javax.swing.JButton();
	    cmdOfficialNameConversion = new javax.swing.JButton();
	    cmdItemDataClear = new javax.swing.JButton();
	    lblAuctionDate = new javax.swing.JLabel();
	    //txtTargetAuction = new javax.swing.JTextField();
	    cmdEnd = new javax.swing.JButton();
	    model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model, p);
		txtTargetAuction = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
	    
	    this.setIconifiable(true);
		this.setMaximizable(false);
		this.setClosable(true);

	    //this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	    this.setTitle("マスターのメンテナンス");
	    this.setMinimumSize(new java.awt.Dimension(340, 370));
	    this.getContentPane().setLayout(null);

	    pnlMain.setBackground(new java.awt.Color(176, 224, 230));
	    pnlMain.setMinimumSize(new java.awt.Dimension(350, 360));
	    pnlMain.setLayout(null);
	    
	    pnlSub.setBackground(new java.awt.Color(176, 224, 230));
	    pnlSub.setLayout(null);

	    cmdFinancialInstitutions.setText("金融機関マスターメンテ");
	    pnlMain.add(cmdFinancialInstitutions);
	    cmdFinancialInstitutions.setBounds(70, 30, 170, 30);
	    cmdFinancialInstitutions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmFinancialInstitutionsMaster nw = FrmFinancialInstitutionsMaster.getInstance();
				nw.setBounds(0, 0, 740, 440);
				nw.getContentPane().setBackground(new Color(176,224,230));
				nw.setForeground(Color.black);
				nw.setResizable(false);
				nw.setTitle("金融機関マスターメンテ");
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
	    });

	    cmdChangeCertificate.setText("証書文言変更");
	    pnlMain.add(cmdChangeCertificate);
	    cmdChangeCertificate.setBounds(70, 70, 170, 30);
	    cmdChangeCertificate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmContractsWording nw = FrmContractsWording.getInstance();
				nw.setBounds(0, 0, 740, 440);
				nw.getContentPane().setBackground(new Color(176,224,230));
				nw.setForeground(Color.black);
				nw.setResizable(false);
				nw.setTitle("契約書文面");
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
	    });

	    cmdChangeVariousParameters.setText("各種パラメータ変更");
	    pnlMain.add(cmdChangeVariousParameters);
	    cmdChangeVariousParameters.setBounds(70, 110, 170, 30);
	    cmdChangeVariousParameters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmChangeVariousParameters nw = FrmChangeVariousParameters.getInstance();
				nw.setBounds(0, 0, 620, 620);
				nw.getContentPane().setBackground(new Color(176,224,230));
				nw.setForeground(Color.black);
				nw.setResizable(false);
				nw.setTitle("パラメータ変更");
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
	    });
	    
	    cmdOfficialNameConversion.setText("正式名称変換テーブル");
	    pnlMain.add(cmdOfficialNameConversion);
	    cmdOfficialNameConversion.setBounds(70, 150, 170, 30);
	    cmdOfficialNameConversion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmOfficialNameConversion nw = FrmOfficialNameConversion.getInstance();
				nw.setBounds(0, 0, 740, 440);
				nw.getContentPane().setBackground(new Color(176,224,230));
				nw.setForeground(Color.black);
				nw.setResizable(false);
				nw.setTitle("名称変換");
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
	    });
	    
	    cmdItemDataClear.setText("明細データクリア");
	    pnlSub.add(cmdItemDataClear);
	    cmdItemDataClear.setBounds(10, 10, 170, 30);
	    cmdItemDataClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearTables();
			}
	    });

	    lblAuctionDate.setText("対象入札日");
	    pnlSub.setBorder(javax.swing.BorderFactory.createEtchedBorder());
	    pnlSub.add(lblAuctionDate);
	    lblAuctionDate.setBounds(10, 50, 70, 30);
	    pnlSub.add(txtTargetAuction);
	    txtTargetAuction.setBounds(90, 50, 150, 30);

	    cmdEnd.setText("終　　　　　　　　了");
	    pnlMain.add(cmdEnd);
	    cmdEnd.setBounds(70, 280, 170, 30);
	    cmdEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

	    this.getContentPane().add(pnlMain);
	    pnlMain.add(pnlSub);
	    pnlSub.setBounds(65, 185, 270, 85);
	    pnlMain.setBounds(0, 0, 399, 490);
	    
	    getDetpInfo();
	}
	
	private static FrmMasterMaintenance myInstance;
	
	public static FrmMasterMaintenance getInstance() {
 	    if (myInstance == null) {
 	        myInstance = new FrmMasterMaintenance();
 	    }
 	    return myInstance;
 	}
	
	public void getDetpInfo() {
		try {
			DBManager db = new DBManager();
			
			try {
				ResultSet rsData;
				rsData = db.getRecord("SELECT department_code,department_name FROM base");
				if (rsData.next()) {
					DepartmentCode = rsData.getString(1);
					//txtDeptName.setText(rsData.getString(2));
				} else {
					DepartmentCode = "010";
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
	
	/*CREATE TABLE `link_tables` (
			`table_name`	TEXT,
			`table_name2`	TEXT,
			`table_name3`	TEXT,
			`table_name4`	TEXT
		)*/
	public void ClearTables()
	{
		Date selectedDate = (Date) txtTargetAuction.getModel().getValue();
		if(null == selectedDate)
		{
			JOptionPane.showMessageDialog(null,"入札日を指定してください");
			return;
		}
		String datePattern = "dd-MM-yyyy";
	    SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
		String bidDate = dateFormatter.format(selectedDate);
		
		int chk = JOptionPane.showConfirmDialog(null, "現在対象となっている入札データをクリアします。よろしいですか？","メッセージを確認",JOptionPane.YES_NO_OPTION);
		if(chk==JOptionPane.YES_OPTION)
		{
			
			try {
	    		ResultSet rs=null;
				DBManager db = new DBManager();
				DBManager dbd = new DBManager();
				List<String> tableNames = new ArrayList<String>();
				try {
					rs = db.getRecord("SELECT * from link_tables");
					while(rs.next()) {
						String table = rs.getString(3);
						tableNames.add(table);
					}
				} catch (SQLException ex) {
					ex.printStackTrace();
				} finally {
					db.close();
				}
				if(tableNames.size()>0){
					for(String table:tableNames){		
						if((!table.equals("accounts")) && (!table.equals("name_conversions")) && (!table.equals("bid_states")))
						{
							if((table.equals("bill_credit_application_datas")) || (table.equals("bid_datas")) || (table.equals("successful_bid_datas_3")))
							{
								boolean Fo = dbd.doQuery("DELETE from " + table + " WHERE department_code = '" + DepartmentCode + "' AND auction_date = '" +bidDate+ "'");
							}
							else
							{
								boolean Fo = dbd.doQuery("DELETE from " + table);
							}
						}
					}	
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}


package com.ey.application.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.ey.application.controller.DBManager;
import com.ey.application.model.CommonProperties;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* FrmBidDataMaintenance.java
* --------------------
* Created on Mar 9, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Mar 9, 2016: Original version (Monsur)
*
*/
public class FrmBidDataMaintenance extends JInternalFrame {
	// Variables declaration - do not modify                     
    private javax.swing.JLayeredPane pnlMain;
    private String bidDate;
    private JTable table;
    private JButton cmdCloseup;
    
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdEdit;
    private javax.swing.JLabel lblBankCode;
    private javax.swing.JLabel lblBidAmountMoney;
    private javax.swing.JLabel lblBidInterestRate;
    private javax.swing.JLabel lblBidNumber;
    private javax.swing.JLabel lblBusinessCategory;
    private javax.swing.JLabel lblDeptCode;
    private javax.swing.JLabel lblFiName;
    private javax.swing.JLabel lblSuBidAmount;
    private javax.swing.JLabel lblSuBidInterest;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JTextField txtBankCode;
    private javax.swing.JTextField txtBidAmountMoney;
    private javax.swing.JTextField txtBidInterestRate;
    private javax.swing.JTextField txtBidNumber;
    private javax.swing.JTextField txtBusinessCategory;
    private javax.swing.JTextField txtDeptCode;
    private javax.swing.JTextField txtFiName;
    private javax.swing.JTextField txtSuBidAmount;
    private javax.swing.JTextField txtSuBidInterest;
    // End of variables declaration
	private String deptCode;
    private int row;
    public FrmBidDataMaintenance(Date date) {
    	try {
    		String datePattern = "dd-MM-yyyy";
		    SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
			bidDate = dateFormatter.format(date);
			row=0;
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
                             
    private void jbInit() {

        pnlMain = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        pnlMain.setBackground(new java.awt.Color(176, 224, 230));
        //pnlMain.setLayout(null);
        getContentPane().add(pnlMain);
        pnlMain.setBounds(0, 0,780, 530);
        
        JScrollPane sp = new JScrollPane();
        sp.setBounds(0, 0, 780, 475);
        sp.setOpaque(true);
        //sp.setBackground(new java.awt.Color(176, 224, 230));
        //pnlMain.add(sp);

        table = new JTable();
        table.setBorder(UIManager.getBorder("DesktopIcon.border"));
        table.setRowHeight(27);
        //table.setBackground(new java.awt.Color(176, 224, 230));
        sp.setViewportView(table);

        // the column in the table
        table.setModel(new DefaultTableModel(
                new Object[][] {
            },
            new String[] {
                "部門コード", "業態_ラベル", "金融機関コード", "金融機関名", "入札番号", "入札利率", "入札金額", "落札利率", "落札金額"
            }
        ));
        
        cmdCloseup = new JButton();
        cmdCloseup.setText("閉じる");
        cmdCloseup.setToolTipText("");
        getContentPane().add(cmdCloseup);
        cmdCloseup.setBounds(600, 480, 150, 23);
        cmdCloseup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
        });
        
        
        /////////////////
        pnlForm = new javax.swing.JPanel();
        lblDeptCode = new javax.swing.JLabel();
        cmdClose = new javax.swing.JButton();
        txtDeptCode = new javax.swing.JTextField();
        lblBusinessCategory = new javax.swing.JLabel();
        txtBusinessCategory = new javax.swing.JTextField();
        lblBankCode = new javax.swing.JLabel();
        txtBankCode = new javax.swing.JTextField();
        lblFiName = new javax.swing.JLabel();
        txtFiName = new javax.swing.JTextField();
        lblBidNumber = new javax.swing.JLabel();
        txtBidNumber = new javax.swing.JTextField();
        lblBidInterestRate = new javax.swing.JLabel();
        txtBidInterestRate = new javax.swing.JTextField();
        lblBidAmountMoney = new javax.swing.JLabel();
        txtBidAmountMoney = new javax.swing.JTextField();
        lblSuBidInterest = new javax.swing.JLabel();
        txtSuBidInterest = new javax.swing.JTextField();
        lblSuBidAmount = new javax.swing.JLabel();
        txtSuBidAmount = new javax.swing.JTextField();
        cmdEdit = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();
        cmdCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlForm.setVisible(false);
			}
        });
        
        pnlForm.setBackground(new java.awt.Color(176, 224, 230));
        pnlForm.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.setLayout(null);

        lblDeptCode.setText("部門コード");
        lblDeptCode.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblDeptCode);
        lblDeptCode.setBounds(50, 30, 120, 27);
        
        CommonProperties props = new CommonProperties();
		String workingDir = props.getWorkingDir();
		String imageDir = props.getImageDir();
		
        cmdClose.setIcon(new javax.swing.ImageIcon(imageDir+"close.png")); // NOI18N
        cmdClose.setMaximumSize(new java.awt.Dimension(161, 161));
        cmdClose.setMinimumSize(new java.awt.Dimension(161, 161));
        cmdClose.setPreferredSize(new java.awt.Dimension(161, 161));
        cmdClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlForm.setVisible(false);
			}
        });
        pnlForm.add(cmdClose);
        cmdClose.setBounds(470, 10, 20, 20);
        pnlForm.add(txtDeptCode);
        txtDeptCode.setBounds(180, 30, 270, 27);

        lblBusinessCategory.setText("業態");
        lblBusinessCategory.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblBusinessCategory);
        lblBusinessCategory.setBounds(50, 60, 120, 27);
        pnlForm.add(txtBusinessCategory);
        txtBusinessCategory.setBounds(180, 60, 270, 27);

        lblBankCode.setText("金融機関コード");
        lblBankCode.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblBankCode);
        lblBankCode.setBounds(50, 90, 120, 27);
        pnlForm.add(txtBankCode);
        txtBankCode.setBounds(180, 90, 270, 27);

        lblFiName.setText("金融機関名");
        lblFiName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblFiName);
        lblFiName.setBounds(50, 120, 120, 27);
        pnlForm.add(txtFiName);
        txtFiName.setBounds(180, 120, 270, 27);

        lblBidNumber.setText("入札番号");
        lblBidNumber.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblBidNumber);
        lblBidNumber.setBounds(50, 150, 120, 27);
        pnlForm.add(txtBidNumber);
        txtBidNumber.setBounds(180, 150, 270, 27);

        lblBidInterestRate.setText("入札利率");
        lblBidInterestRate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblBidInterestRate);
        lblBidInterestRate.setBounds(50, 180, 120, 27);
        pnlForm.add(txtBidInterestRate);
        txtBidInterestRate.setBounds(180, 180, 270, 27);

        lblBidAmountMoney.setText("入札金額");
        lblBidAmountMoney.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblBidAmountMoney);
        lblBidAmountMoney.setBounds(50, 210, 120, 27);
        pnlForm.add(txtBidAmountMoney);
        txtBidAmountMoney.setBounds(180, 210, 270, 27);

        lblSuBidInterest.setText("落札利率");
        lblSuBidInterest.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblSuBidInterest);
        lblSuBidInterest.setBounds(50, 240, 120, 27);
        pnlForm.add(txtSuBidInterest);
        txtSuBidInterest.setBounds(180, 240, 270, 27);

        lblSuBidAmount.setText("落札金額");
        lblSuBidAmount.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblSuBidAmount);
        lblSuBidAmount.setBounds(50, 270, 120, 27);
        pnlForm.add(txtSuBidAmount);
        txtSuBidAmount.setBounds(180, 270, 270, 27);

        cmdEdit.setText("セーブ");
        cmdEdit.setToolTipText("セーブ");
        pnlForm.add(cmdEdit);
        cmdEdit.setBounds(100, 320, 150, 23);
        cmdEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean Fo = UpdateSucessfullBidData();
				if(Fo)
				{  	    
	        	    table.setValueAt(txtDeptCode.getText(), row, 0);
	        	    table.setValueAt(txtBusinessCategory.getText(), row, 1);
	        	    table.setValueAt(txtBankCode.getText(), row, 2);
	        	    table.setValueAt(txtFiName.getText(), row, 3);
	        	    table.setValueAt(txtBidNumber.getText(), row, 4);
	        	    table.setValueAt(txtBidInterestRate.getText(), row, 5);
	        	    table.setValueAt(txtBidAmountMoney.getText(), row, 6);
	        	    table.setValueAt(txtSuBidInterest.getText(), row, 7);
	        	    table.setValueAt(txtSuBidAmount.getText(), row, 8);
	        	    
					pnlForm.setVisible(false);
				}
			}
        });

        cmdCancel.setText("キャンセル");
        cmdCancel.setToolTipText("キャンセル");
        pnlForm.add(cmdCancel);
        cmdCancel.setBounds(260, 320, 150, 23);

        //getContentPane().add(pnlForm);
        pnlForm.setBounds(50, 20, 510, 390);
        pnlForm.setVisible(false);
        pnlMain.add(sp, new Integer(0), 0);
        pnlMain.add(pnlForm, new Integer(1), 0);
        
        table.addMouseListener(new java.awt.event.MouseAdapter()
        {
        	public void mouseClicked(java.awt.event.MouseEvent e)
        	{
        		row= table.rowAtPoint(e.getPoint());
        		//int col= table.columnAtPoint(e.getPoint());
        		       		
        		txtDeptCode.setText(table.getValueAt(row,0).toString());
        		txtBusinessCategory.setText(table.getValueAt(row,1).toString());
        		txtBankCode.setText(table.getValueAt(row,2).toString());
        		txtFiName.setText(table.getValueAt(row,3).toString());
        		txtBidNumber.setText(table.getValueAt(row,4).toString());
        		txtBidInterestRate.setText(table.getValueAt(row,5).toString());
        		txtBidAmountMoney.setText(table.getValueAt(row,6).toString());
        		txtSuBidInterest.setText(table.getValueAt(row,7).toString());
        	    txtSuBidAmount.setText(table.getValueAt(row,8).toString());
        	    
        	    pnlForm.setVisible(true);
        	}
        });
        
        getDetpInfo();
        loadBidMaintenanceData();
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
			getDesktopPane().add(nw,new Integer(Integer.MAX_VALUE));
			nw.setVisible(true);
		}
		nw.setIconifiable(true);
		nw.setMaximizable(false);
		nw.setClosable(true);
		dispose();
	}
	private static FrmBidDataMaintenance myInstance;

 	public static FrmBidDataMaintenance getInstance(Date date) {
 	    //if (myInstance == null) {
 	        myInstance = new FrmBidDataMaintenance(date);
 	    //}
 	    return myInstance;
 	}
 	
 	public void getDetpInfo() {
		try {
			DBManager db = new DBManager();
			try {
				ResultSet rsData;
				rsData = db.getRecord("SELECT department_code,department_name FROM base");
				if (rsData.next()) {
					deptCode = rsData.getString(1);
				} else {
					deptCode="";
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
 	
 	public void loadBidMaintenanceData() {
		try {
			DBManager db = new DBManager();
			try {
				DefaultTableModel dtm = (DefaultTableModel)table.getModel();
				ResultSet rs;
				rs = db.getRecord("SELECT DISTINCT successful_bid_datas_3.department_code, successful_bid_datas_3.business_category, successful_bid_datas_3.bank_code, " + 
								  "successful_bid_datas_3.financial_institution_name, successful_bid_datas_3.bid_number, successful_bid_datas_3.bid_interest_rate, " + 
								  "successful_bid_datas_3.bid_amount_money, successful_bid_datas_3.successful_bid_interest_rate, successful_bid_datas_3.successful_bid_price " +
								  "FROM successful_bid_datas_3 "+
								  "WHERE (((successful_bid_datas_3.[department_code]) = '"+deptCode+"') AND ((successful_bid_datas_3. [auction_date]) = '"+bidDate+"'))" +
								  "ORDER BY successful_bid_datas_3.department_code, successful_bid_datas_3.bank_code, successful_bid_datas_3.bid_number");
				NumberFormat formatter = new DecimalFormat("#0.0000");
				NumberFormat formatter1 = new DecimalFormat("#0.00000");
				while(rs.next()) {
					String department_code=rs.getString(1);
					String business_category=rs.getString(2);
					String bankCode=rs.getString(3);
					String financialInstitutionName=rs.getString(4);
					int bidNumber=rs.getInt(5);
					double intRate = rs.getDouble(6);
					String bidInterestRate= "0";
					if(intRate != 0)
						bidInterestRate=formatter.format(intRate);
						
					double bidAmountOfMoney=rs.getDouble(7);
					String strbidAmountOfMoney="";
					if(bidAmountOfMoney != 0)
						strbidAmountOfMoney=String.valueOf(bidAmountOfMoney);
					double succBidIntRate = rs.getFloat(8);
					String successfulBidInterestRate="";
					if(succBidIntRate != 0)
						successfulBidInterestRate = formatter1.format(succBidIntRate);
					double successfulBidPrice=rs.getDouble(9);
					String strsuccessfulBidPrice = "";
					if(successfulBidPrice!=0)
						strsuccessfulBidPrice = String.valueOf(successfulBidPrice);

					dtm.addRow(new Object[] { department_code, business_category, bankCode, financialInstitutionName, bidNumber, bidInterestRate, strbidAmountOfMoney, successfulBidInterestRate, strsuccessfulBidPrice});
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
 	
 	public boolean UpdateSucessfullBidData(){
 		boolean Fo = false;
 		try {
			DBManager db = new DBManager();
			try {
				Fo = db.doQuery("UPDATE successful_bid_datas_3 SET " + 
										"department_code ='" +	txtDeptCode.getText() + 
										"',business_category ='"+txtBusinessCategory.getText() +
										"',bank_code ='"+txtBankCode.getText() +
										"',financial_institution_name ='"+txtFiName.getText() +
										"',bid_number ="+txtBidNumber.getText() +
										",bid_interest_rate ="+txtBidInterestRate.getText() +
										",bid_amount_money ="+txtBidAmountMoney.getText() +
										",successful_bid_interest_rate ="+txtSuBidInterest.getText() +
										",successful_bid_price ="+txtSuBidAmount.getText() +
										" WHERE department_code ='" +txtDeptCode.getText() +
										"' AND bank_code = '"+txtBankCode.getText() +
										"' AND bid_number ="+txtBidNumber.getText());
        	    
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 		
 		return Fo;
 	}
}


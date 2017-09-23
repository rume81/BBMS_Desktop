package com.ey.application.view;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.ey.application.controller.DBManager;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* FrmBidData.java
* --------------------
* Created on Feb 5, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Feb 5, 2016: Original version (Monsur)
*
*/

public class FrmBidData extends JInternalFrame {
	 // Variables declaration - do not modify                     
	private JComboBox cmbDepartmentCode;
	private JComboBox cmbFinancialInstitutions;
    private JButton cmdCloseup;
    private JButton cmdDataCorrection;
    private JButton cmdDelete;
    private JButton cmdRegistration;
    private JLabel lbl[] = new JLabel[5];
    /*private JLabel lbl2;
    private JLabel lbl3;
    private JLabel lbl4;
    private JLabel lbl5;*/
    private JLabel lblBidAmountofMoney;
    private JLabel lblBidInterestRate;
    private JLabel lblFinancialInstitutions;
    private JLabel lblAccount;
    private JLabel lblPercentage1;
    private JLabel lblPercentage2;
    private JLabel lblPercentage3;
    private JLabel lblPercentage4;
    private JLabel lblPercentage5;
    private JLabel lblTotal;
    private JPanel pnlMain;
    private JPanel pnlDept;
    private JText txtBidAmount[] = new JText[5];
    /*private JTextField txtBidAmount2;
    private JTextField txtBidAmount3;
    private JTextField txtBidAmount4;
    private JTextField txtBidAmount5;*/
    private JTextField txtTotalAmount;
    private JTextField txtBusinessCategory;
    private JTextField txtInstitutionName;
    private JTextField txtDeptName;
    private JText txtInterestRate[] = new JText[5];
    /*private JTextField txtInterestRate2;
    private JTextField txtInterestRate3;
    private JTextField txtInterestRate4;
    private JTextField txtInterestRate5;*/
    private String bidDate;
    NumberFormat Numformatter = new DecimalFormat("#0.0000");
    // End of variables declaration             
	
	public FrmBidData(Date date) {
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
		/*this.setBounds(0, 0, 579, 412);
		this.getContentPane().setBackground(new Color(176,224,230));
		this.setForeground(Color.black);
		this.setResizable(false);
		this.setTitle("入札データ入力");
		this.getContentPane().setLayout(null);

		this.setIconifiable(true);
		this.setMaximizable(false);
		this.setClosable(true);*/
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		
		pnlMain = new JPanel();
		pnlDept = new JPanel();
        lblFinancialInstitutions = new JLabel();
        cmbFinancialInstitutions = new JComboBox();
        cmbDepartmentCode = new JComboBox();
        txtInstitutionName = new JTextField();
        txtDeptName = new JTextField();
        txtBusinessCategory = new JTextField();
        lblBidInterestRate = new JLabel();
        lblAccount = new JLabel();
        lblBidAmountofMoney = new JLabel();
        txtInterestRate[0] = new JText(10,'f');
        lbl[0] = new JLabel();
        txtBidAmount[0] = new JText(10,'i');
        lblPercentage1 = new JLabel();
        txtBidAmount[1] = new JText(10,'i');
        lblPercentage2 = new JLabel();
        txtInterestRate[1] = new JText(10,'f');
        lbl[1] = new JLabel();
        txtBidAmount[2] = new JText(10,'i');
        lblPercentage3 = new JLabel();
        txtInterestRate[2] = new JText(10,'f');
        lbl[2] = new JLabel();
        lbl[3] = new JLabel();
        txtInterestRate[3] = new JText(10,'f');
        lblPercentage4 = new JLabel();
        txtBidAmount[3] = new JText(10,'i');
        lbl[4] = new JLabel();
        txtInterestRate[4] = new JText(10,'f');
        lblPercentage5 = new JLabel();
        txtBidAmount[4] = new JText(10,'i');
        txtTotalAmount = new JTextField();
        lblTotal = new JLabel();
        cmdRegistration = new JButton();
        cmdCloseup = new JButton();
        cmdDataCorrection = new JButton();
        cmdDelete = new JButton();
        
        this.getContentPane().add(pnlMain, null);

        setTitle("入札データ入力");
        setSize(new java.awt.Dimension(500, 400));
        getContentPane().setLayout(null);

        pnlMain.setBackground(new java.awt.Color(176, 224, 230));
        pnlMain.setName("pnlMain"); 
        pnlMain.setLayout(null);
        
        pnlDept.setBackground(new java.awt.Color(176, 224, 230));
        pnlDept.setName("pnlMain"); 
        pnlDept.setLayout(null);

        lblAccount.setText("勘定");
        lblAccount.setBorder(BorderFactory.createEtchedBorder());
        pnlDept.add(lblAccount);
        lblAccount.setBounds(10, 15, 70, 23);
        lblAccount.setVisible(false);
        
        departmentCode();
        pnlDept.add(cmbDepartmentCode);
        cmbDepartmentCode.setBounds(90, 15, 150, 23);
        cmbDepartmentCode.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
        cmbDepartmentCode.setVisible(false);
        getDetpInfo();
                
        pnlDept.add(txtDeptName);
        txtDeptName.setBounds(250, 15, 110, 23);
        txtDeptName.setVisible(false);
        
        lblFinancialInstitutions.setText("金融機関");
        lblFinancialInstitutions.setBorder(BorderFactory.createEtchedBorder());
        pnlMain.add(lblFinancialInstitutions);
        lblFinancialInstitutions.setBounds(10, 20, 70, 23);
        
        financialInstitution();
        pnlMain.add(cmbFinancialInstitutions);
        cmbFinancialInstitutions.setBounds(90, 20, 150, 23);
        cmbFinancialInstitutions.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				getFinancialInstitution();
				getBidInfo();
			}
		});
                
        
        pnlMain.add(txtInstitutionName);
        txtInstitutionName.setBounds(250, 20, 110, 23);
        pnlMain.add(txtBusinessCategory);
        txtBusinessCategory.setBounds(370, 20, 70, 23);

        lblBidInterestRate.setText("入札利率");
        lblBidInterestRate.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlMain.add(lblBidInterestRate);
        lblBidInterestRate.setBounds(70, 70, 150, 23);

        lblBidAmountofMoney.setText(" 入札金額");
        lblBidAmountofMoney.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlMain.add(lblBidAmountofMoney);
        lblBidAmountofMoney.setBounds(240, 70, 140, 23);
        pnlMain.add(txtInterestRate[0]);
        txtInterestRate[0].setBounds(70, 100, 150, 23);
        txtInterestRate[0].setHorizontalAlignment(JTextField.RIGHT);
        txtInterestRate[0].addFocusListener(new FocusAdapter()
        {
    		public void focusLost(FocusEvent e)
    		{
    			/*boolean Fo = FormatLike(txtInterestRate[0].getText());
    			if(!Fo)
    			{
    				JOptionPane.showMessageDialog(null, "利率の入力は小数点以下３桁までです");
    				txtInterestRate[0].setText("");
    				txtInterestRate[0].grabFocus();
    			}*/
    			txtInterestRate[0].setText(ri_chk(txtInterestRate[0].getText(),3));
    			/*if(!txtInterestRate[0].getText().equals(""))
    			{
    				txtInterestRate[0].setText(Numformatter.format(Double.parseDouble(txtInterestRate[0].getText())));
    			}*/
    				//txtInterestRate[0].grabFocus();
    		}
        });

        lbl[0].setText("1");
        lbl[0].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlMain.add(lbl[0]);
        lbl[0].setBounds(30, 100, 30, 23);
        pnlMain.add(txtBidAmount[0]);
        txtBidAmount[0].setBounds(240, 100, 140, 23);
        txtBidAmount[0].setHorizontalAlignment(JTextField.RIGHT);
        txtBidAmount[0].addFocusListener(new FocusAdapter()
        {
    		public void focusLost(FocusEvent e)
    		{
    			if(txtBidAmount[0].getText().equals(""))
    				txtBidAmount[0].setText("0");
    			totalCalculation();
    			if(txtInterestRate[0].getText().equals(""))
    				txtInterestRate[0].grabFocus();
    		}
        });
        
        lblPercentage1.setText("%");
        pnlMain.add(lblPercentage1);
        lblPercentage1.setBounds(220, 100, 11, 20);
        pnlMain.add(txtBidAmount[1]);
        txtBidAmount[1].setBounds(240, 125, 140, 23);
        txtBidAmount[1].setHorizontalAlignment(JTextField.RIGHT);
        txtBidAmount[1].addFocusListener(new FocusAdapter()
        {
    		public void focusLost(FocusEvent e)
    		{
    			if(txtBidAmount[1].getText().equals(""))
    				txtBidAmount[1].setText("0");
    			totalCalculation();
    			if(txtInterestRate[1].getText().equals(""))
    				txtInterestRate[1].grabFocus();
    		}
        });

        lblPercentage2.setText("%");
        pnlMain.add(lblPercentage2);
        lblPercentage2.setBounds(220, 125, 11, 20);
        pnlMain.add(txtInterestRate[1]);
        txtInterestRate[1].setBounds(70, 125, 150, 23);
        txtInterestRate[1].setHorizontalAlignment(JTextField.RIGHT);
        txtInterestRate[1].addFocusListener(new FocusAdapter()
        {
    		public void focusLost(FocusEvent e)
    		{
    			/*boolean Fo = FormatLike(txtInterestRate[1].getText());
    			if(!Fo)
    			{
    				JOptionPane.showMessageDialog(null, "利率の入力は小数点以下３桁までです");
    				txtInterestRate[1].setText("");
    				txtInterestRate[1].grabFocus();
    			}*/
    			txtInterestRate[1].setText(ri_chk(txtInterestRate[1].getText(),3));
    			/*if(!txtInterestRate[1].getText().equals(""))
    			{
    				txtInterestRate[1].setText(Numformatter.format(txtInterestRate[1].getText()));
    			}*/
    			//if(txtInterestRate[1].getText().equals(""))
    				//txtInterestRate[1].grabFocus();
    		}
        });

        lbl[1].setText("2");
        lbl[1].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlMain.add(lbl[1]);
        lbl[1].setBounds(30, 125, 30, 23);
        pnlMain.add(txtBidAmount[2]);
        txtBidAmount[2].setBounds(240, 150, 140, 23);
        txtBidAmount[2].setHorizontalAlignment(JTextField.RIGHT);
        txtBidAmount[2].addFocusListener(new FocusAdapter()
        {
    		public void focusLost(FocusEvent e)
    		{
    			if(txtBidAmount[2].getText().equals(""))
    				txtBidAmount[2].setText("0");
    			totalCalculation();
    			if(txtInterestRate[2].getText().equals(""))
    				txtInterestRate[2].grabFocus();
    		}
        });

        lblPercentage3.setText("%");
        pnlMain.add(lblPercentage3);
        lblPercentage3.setBounds(220, 150, 11, 20);
        pnlMain.add(txtInterestRate[2]);
        txtInterestRate[2].setBounds(70, 150, 150, 23);
        txtInterestRate[2].setHorizontalAlignment(JTextField.RIGHT);
        txtInterestRate[2].addFocusListener(new FocusAdapter()
        {
    		public void focusLost(FocusEvent e)
    		{
    			/*boolean Fo = FormatLike(txtInterestRate[2].getText());
    			if(!Fo)
    			{
    				JOptionPane.showMessageDialog(null, "利率の入力は小数点以下３桁までです");
    				txtInterestRate[2].setText("");
    				txtInterestRate[2].grabFocus();
    			}*/
    			txtInterestRate[2].setText(ri_chk(txtInterestRate[2].getText(),3));
    			/*if(!txtInterestRate[2].getText().equals(""))
    			{
    				txtInterestRate[2].setText(Numformatter.format(txtInterestRate[2].getText()));
    			}*/
    			//if(txtInterestRate[2].getText().equals(""))
    				//txtInterestRate[2].grabFocus();
    		}
        });

        lbl[2].setText("3");
        lbl[2].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlMain.add(lbl[2]);
        lbl[2].setBounds(30, 150, 30, 23);

        lbl[3].setText("4");
        lbl[3].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlMain.add(lbl[3]);
        lbl[3].setBounds(30, 175, 30, 23);
        pnlMain.add(txtInterestRate[3]);
        txtInterestRate[3].setBounds(70, 175, 150, 23);
        txtInterestRate[3].setHorizontalAlignment(JTextField.RIGHT);
        txtInterestRate[3].addFocusListener(new FocusAdapter()
        {
    		public void focusLost(FocusEvent e)
    		{
    			/*boolean Fo = FormatLike(txtInterestRate[3].getText());
    			if(!Fo)
    			{
    				JOptionPane.showMessageDialog(null, "利率の入力は小数点以下３桁までです");
    				txtInterestRate[3].setText("");
    				txtInterestRate[3].grabFocus();
    			}*/
    			txtInterestRate[3].setText(ri_chk(txtInterestRate[3].getText(),3));
    			//if(txtInterestRate[3].getText().equals(""))
    				//txtInterestRate[3].grabFocus();
    		}
        });

        lblPercentage4.setText("%");
        pnlMain.add(lblPercentage4);
        lblPercentage4.setBounds(220, 175, 11, 20);
        pnlMain.add(txtBidAmount[3]);
        txtBidAmount[3].setBounds(240, 175, 140, 23);
        txtBidAmount[3].setHorizontalAlignment(JTextField.RIGHT);
        txtBidAmount[3].addFocusListener(new FocusAdapter()
        {
    		public void focusLost(FocusEvent e)
    		{
    			if(txtBidAmount[3].getText().equals(""))
    				txtBidAmount[3].setText("0");
    			totalCalculation();
    			if(txtInterestRate[3].getText().equals(""))
    				txtInterestRate[3].grabFocus();
    		}
        });

        lbl[4].setText("5");
        lbl[4].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlMain.add(lbl[4]);
        lbl[4].setBounds(30, 200, 30, 23);
        pnlMain.add(txtInterestRate[4]);
        txtInterestRate[4].setBounds(70, 200, 150, 23);
        txtInterestRate[4].setHorizontalAlignment(JTextField.RIGHT);
        txtInterestRate[4].addFocusListener(new FocusAdapter()
        {
    		public void focusLost(FocusEvent e)
    		{
    			/*boolean Fo = FormatLike(txtInterestRate[4].getText());
    			if(!Fo)
    			{
    				JOptionPane.showMessageDialog(null, "利率の入力は小数点以下３桁までです");
    				txtInterestRate[4].setText("");
    				txtInterestRate[4].grabFocus();
    			}*/
    			
    			txtInterestRate[4].setText(ri_chk(txtInterestRate[4].getText(),3));
    			//if(txtInterestRate[4].getText().equals(""))
    				//txtInterestRate[4].grabFocus();
    		}
        });
        
        txtInterestRate[0].addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_ENTER){
        			txtBidAmount[0].requestFocus();
         	    } 
            }
        });
        txtInterestRate[1].addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_ENTER){
        			txtBidAmount[1].requestFocus();
         	    } 
            }
        });
        txtInterestRate[2].addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_ENTER){
        			txtBidAmount[2].requestFocus();
         	    } 
            }
        });
        txtInterestRate[3].addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_ENTER){
        			txtBidAmount[3].requestFocus();
         	    } 
            }
        });
        txtInterestRate[4].addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_ENTER){
        			txtBidAmount[4].requestFocus();
         	    } 
            }
        });
        
        txtBidAmount[0].addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_ENTER){
        			txtInterestRate[1].requestFocus();
         	    } 
            }
        });
        txtBidAmount[1].addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_ENTER){
        			txtInterestRate[2].requestFocus();
         	    } 
            }
        });
        txtBidAmount[2].addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_ENTER){
        			txtInterestRate[3].requestFocus();
         	    } 
            }
        });
        txtBidAmount[3].addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_ENTER){
        			txtInterestRate[4].requestFocus();
         	    } 
            }
        });
        txtBidAmount[4].addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if(e.getKeyCode()==KeyEvent.VK_ENTER){
        			txtInterestRate[0].requestFocus();
         	    } 
            }
        });
        
        lblPercentage5.setText("%");
        pnlMain.add(lblPercentage5);
        lblPercentage5.setBounds(220, 200, 11, 20);
        pnlMain.add(txtBidAmount[4]);
        txtBidAmount[4].setBounds(240, 200, 140, 23);
        txtBidAmount[4].setHorizontalAlignment(JTextField.RIGHT);
        pnlMain.add(txtTotalAmount);
        txtTotalAmount.setBounds(240, 230, 140, 23);
        txtTotalAmount.setHorizontalAlignment(JTextField.RIGHT);
        txtTotalAmount.setEditable(false);
        txtBidAmount[4].addFocusListener(new FocusAdapter()
        {
    		public void focusLost(FocusEvent e)
    		{
    			if(txtBidAmount[4].getText().equals(""))
    				txtBidAmount[4].setText("0");
    			totalCalculation();
    			if(txtInterestRate[4].getText().equals(""))
    				txtInterestRate[4].grabFocus();
    		}
        });

        lblTotal.setText("計");
        pnlMain.add(lblTotal);
        lblTotal.setBounds(200, 230, 20, 20);

        cmdRegistration.setText("登録");
        pnlMain.add(cmdRegistration);
        cmdRegistration.setBounds(70, 270, 150, 23);
        cmdRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bidDataRegister();
			}
        });

        cmdCloseup.setText("閉じる");
        pnlMain.add(cmdCloseup);
        cmdCloseup.setBounds(240, 270, 150, 23);
        cmdCloseup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
        });

        cmdDataCorrection.setText("データ修正");
        pnlMain.add(cmdDataCorrection);
        cmdDataCorrection.setBounds(140, 320, 150, 23);
        cmdDataCorrection.setEnabled(false);
        cmdDataCorrection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bidDataRegister();
			}
        });
        
        cmdDelete.setText("削除");
        pnlMain.add(cmdDelete);
        cmdDelete.setBounds(300, 320, 150, 23);
        cmdDelete.setEnabled(false);
        cmdDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int con = JOptionPane.showConfirmDialog(null, "このデータを削除しますがよろしいですか");
				if(con == JOptionPane.YES_OPTION)
				{
					String bankcode = String.valueOf(cmbFinancialInstitutions.getSelectedItem()).split("   -  ")[0];
			 		String deptcode = String.valueOf(cmbDepartmentCode.getSelectedItem());
					boolean Fo = deleteRowOfBidDataForABank(bankcode,deptcode);
					
					if(Fo){
						clearForm();
						JOptionPane.showMessageDialog(null, "コード " + bankcode + "のデータを削除しました");
					}
				}
			}
        });
        
        getContentPane().add(pnlDept);
        getContentPane().add(pnlMain);
        pnlMain.setBounds(0, 40, 470, 370);
        pnlDept.setBounds(0, 0, 470, 40);
        
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
			getDesktopPane().add(nw, new Integer(Integer.MAX_VALUE));
			nw.setVisible(true);
		}

		nw.setIconifiable(true);
		nw.setMaximizable(false);
		nw.setClosable(true);
		dispose();
	}
	private static FrmBidData myInstance;

 	public static FrmBidData getInstance(Date date) {
 	    //if (myInstance == null) {
 	        myInstance = new FrmBidData(date);
 	    //}
 	    return myInstance;
 	}
 	
 	public void departmentCode() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT DISTINCT divisions.department_code FROM divisions");
				cmbDepartmentCode.addItem("");
				//int i=0;
				//String deptCode="";
				while(rs.next()) {
					String code = rs.getString(1);
					cmbDepartmentCode.addItem(code);
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

 	public void financialInstitution() {
		
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT DISTINCT bank_code, financial_institution_name FROM financial_institutions_masters");
				cmbFinancialInstitutions.addItem("");
				while(rs.next()) {
					cmbFinancialInstitutions.addItem(rs.getString(1)  +"   -  "+  rs.getString(2));
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
 	
 	public void getFinancialInstitution() {

		if(!String.valueOf(cmbFinancialInstitutions.getSelectedItem()).equals(""))
		{
			try {
				DBManager db = new DBManager();
				String[] fiCode = String.valueOf(cmbFinancialInstitutions.getSelectedItem()).split("   -  ");
				try {
					ResultSet rsData;
					rsData = db.getRecord("SELECT business_category FROM financial_institutions_masters WHERE bank_code='"+ fiCode[0]+"'");
					if (rsData.next()) {
						txtInstitutionName.setText(fiCode[1]);
				        txtBusinessCategory.setText(rsData.getString(1));
					} else {
						txtInstitutionName.setText("");
						txtBusinessCategory.setText("");
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
	}
 	
 	
 	/*public void getDetpInfo() {

		if(!String.valueOf(cmbDepartmentCode.getSelectedItem()).equals(""))
		{
			try {
				DBManager db = new DBManager();
				String fiCode = String.valueOf(cmbDepartmentCode.getSelectedItem());
				try {
					ResultSet rsData;
					rsData = db.getRecord("SELECT divisions.department_name FROM divisions WHERE divisions.department_code='"+ fiCode+"'");
					if (rsData.next()) {
						txtDeptName.setText(rsData.getString(1));
					} else {
						txtDeptName.setText("");
						//txtBusinessCategory.setText("");
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
	}*/
 	
 	public void getBidInfo() {
 		try {
			DBManager db = new DBManager();
			String deptcode = String.valueOf(cmbDepartmentCode.getSelectedItem());
			String[] bankcode = String.valueOf(cmbFinancialInstitutions.getSelectedItem()).split("   -  ");
			try {
				ResultSet rsData;
				StringBuffer strSql = new StringBuffer("SELECT DISTINCT bid_datas.department_code, bid_datas.business_category, bid_datas.bank_code, bid_datas.financial_institution_name,bid_datas.bid_number, bid_datas.bid_interest_rate, bid_datas.bid_amount_money, bid_datas.auction_date FROM bid_datas WHERE bid_datas.auction_date='"+bidDate+"' and bid_datas.department_code='"+deptcode+"' AND bid_datas.bank_code='"+bankcode[0]+"' ORDER BY bid_datas.department_code, bid_datas.bank_code, bid_datas.bid_number");
				//System.out.println(strSql);
				rsData = db.getRecord(strSql.toString());
				int i=0;
				while(rsData.next()) {
					if(i<5){
						lbl[i].setText(rsData.getString(5));
						txtInterestRate[i].setText(String.valueOf((rsData.getDouble(6))));
						int f = rsData.getInt(7);
						txtBidAmount[i].setText(String.valueOf(f));
						i++;
					} else{
						break;
					}
				}
				if(i==0)
				{
					cmdRegistration.setEnabled(true);
					cmdDataCorrection.setEnabled(false);
					cmdDelete.setEnabled(false);
				} else {
					cmdRegistration.setEnabled(false);
					cmdDataCorrection.setEnabled(true);
					cmdDelete.setEnabled(true);
				}
				for(;i<5;i++)
				{
					txtInterestRate[i].setText("");
					txtBidAmount[i].setText("");
				}
				totalCalculation();
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public void bidDataRegister() {
 		boolean isOk = true;
 		float interestRate1 = 0;
 		float interestRate2 = 0;
 		float interestRate3 = 0;
 		float interestRate4 = 0;
 		float interestRate5 = 0;
 		
 		if(!txtInterestRate[0].getText().equals(""))
 			interestRate1 = Float.parseFloat(txtInterestRate[0].getText());
 		if(!txtInterestRate[1].getText().equals(""))
 			interestRate2 = Float.parseFloat(txtInterestRate[1].getText());
 		if(!txtInterestRate[2].getText().equals(""))
 			interestRate3 = Float.parseFloat(txtInterestRate[2].getText());
 		if(!txtInterestRate[3].getText().equals(""))
 			interestRate4 = Float.parseFloat(txtInterestRate[3].getText());
 		if(!txtInterestRate[4].getText().equals(""))
 			interestRate5 = Float.parseFloat(txtInterestRate[4].getText());
 		
 		String bankcode = String.valueOf(cmbFinancialInstitutions.getSelectedItem()).split("   -  ")[0];
 		String deptcode = String.valueOf(cmbDepartmentCode.getSelectedItem());
 		
 		if(((interestRate2 != 0) && (interestRate1 > interestRate2)) || ((interestRate3 != 0) && (interestRate2 > interestRate3)) || ((interestRate4 != 0) && (interestRate3 > interestRate4)) || ((interestRate5 != 0) && (interestRate4 > interestRate5)))
 		{
 			if(JOptionPane.showConfirmDialog(null, "利率が昇順になっていませんが、そのまま登録しますか？") == JOptionPane.YES_OPTION)
 			{
 				isOk = true;
 			} else {
 				isOk = false;
 			}
 		}
 		
 		if(bankcode == "")
 		{
 			JOptionPane.showMessageDialog(null, "金融機関コードが空白なので登録されません");
 			isOk = false;
 		}
 		
 		if((getRowCountOfBidDataForABank(bankcode,deptcode)> 0) && (!cmdDataCorrection.isEnabled())) {
 			JOptionPane.showMessageDialog(null, "この金融機関のデータはすでに登録されています。データを修正する場合は、データ修正ボタンをクリックしてください。");
 			isOk = false;
 		}
 		
 		if(isOk)
 		{
 			boolean Fo = deleteRowOfBidDataForABank(bankcode,deptcode);
 			
 			addRowOfBidDataForABank(bankcode,deptcode);
 			
 			if(Integer.parseInt(txtTotalAmount.getText()) > 0)
 			{
 				updateFlagOfBidDataForABank(bankcode,deptcode, "1");
 			} else {
 				updateFlagOfBidDataForABank(bankcode,deptcode, "0");
 			}
 			
 			clearForm();
 		}
 	}
 	
 	public int getRowCountOfBidDataForABank(String bankcode,String deptcode)
 	{
 		int nor=0;
 		try {
			DBManager db = new DBManager();
	 		try {
				ResultSet rsData;
				StringBuffer strSql = new StringBuffer("SELECT Count(*) from bid_datas WHERE bid_datas.bank_code='"+bankcode+"' AND bid_datas.department_code='"+deptcode+"' AND bid_datas.auction_date='"+bidDate+"'");
				//System.out.println(strSql);
				rsData = db.getRecord(strSql.toString());
				
				if(rsData.next()) {
					nor = rsData.getInt(1);
				}
	 		} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 		return nor;
 	}
 	
 	
 	public boolean deleteRowOfBidDataForABank(String bankcode,String deptcode)
 	{
 		boolean Fo=false;
 		try {
			DBManager db = new DBManager();
	 		try {
				StringBuffer strSql = new StringBuffer("DELETE FROM bid_datas WHERE bid_datas.bank_code='"+bankcode+"' AND bid_datas.department_code='"+deptcode+"' AND bid_datas.auction_date='"+bidDate+"'");
				//System.out.println(strSql);
				boolean dStatus=db.doQuery(strSql.toString());
			  	if(dStatus)
			  	{
			  		Fo=true;
				}
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
 	
 	public void addRowOfBidDataForABank(String bankcode,String deptcode)
 	{
 		//boolean Fo=false;
 		try {
			DBManager db = new DBManager();
	 		try {
	 			for(int i=1;i<=5;i++){
	 				float interestRate = 0;
	 				float bidAmount = 0;
	 				if(!txtInterestRate[i-1].getText().equals(""))
	 					interestRate = Float.parseFloat(txtInterestRate[i-1].getText());
	 				if(!txtBidAmount[i-1].getText().equals(""))
	 					bidAmount = Float.parseFloat(txtBidAmount[i-1].getText());
	 				StringBuffer strSql = new StringBuffer("INSERT INTO bid_datas (department_code,business_category,bank_code,financial_institution_name,bid_number,bid_interest_rate,bid_amount_money,auction_date) VALUES('"+deptcode+"','"+txtBusinessCategory.getText()+"','"+bankcode+"','"+txtInstitutionName.getText()+"',"+i+","
	 			+interestRate+","+bidAmount+",'"+bidDate+"')");
					System.out.println(strSql);
					boolean dStatus=db.doQuery(strSql.toString());
				  	if(dStatus)
				  	{
				  		//Fo=true;
					}
	 			}
	 		} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 		//return Fo;
 	}
 	
 	public boolean updateFlagOfBidDataForABank(String bankcode,String deptcode,String flg)
 	{
 		boolean Fo=false;
 		try {
			DBManager db = new DBManager();
	 		try {
				StringBuffer strSql = new StringBuffer("UPDATE bid_datas SET flag1 = '"+flg+"' WHERE bid_datas.bank_code='"+bankcode+"' AND bid_datas.department_code='"+deptcode+"' AND bid_datas.auction_date='"+bidDate+"'");
				//System.out.println(strSql);
				boolean dStatus=db.doQuery(strSql.toString());
			  	if(dStatus)
			  	{
			  		Fo=true;
				}
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
 	
 	public void totalCalculation()
 	{
 		int total =0;
 		for(int i =0;i<5;i++)
 		{
 			if(!txtBidAmount[i].getText().equals(""))
 			{
 				total = total + Integer.parseInt(txtBidAmount[i].getText());
 			}
 		}
 		
 		txtTotalAmount.setText(String.valueOf(total));
 	}
 	
 	public void getDetpInfo() {
		try {
			DBManager db = new DBManager();
			
			try {
				ResultSet rsData;
				rsData = db.getRecord("SELECT department_code,department_name FROM base");
				if (rsData.next()) {
					cmbDepartmentCode.setSelectedItem(rsData.getString(1));
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
 	
 	public void clearForm()
 	{
 		cmbFinancialInstitutions.setSelectedItem("");
 		txtBusinessCategory.setText("");
 	    txtInstitutionName.setText("");
 	    for(int i =0;i<5;i++)
		{
 	    	txtInterestRate[i].setText("");
 	    	txtBidAmount[i].setText("");
		}
 	    
 	   cmdDataCorrection.setEnabled(false);
 	   cmdDelete.setEnabled(false);
 	}
 	
 	public String ri_chk(String ri,int precision)
	{
		String Fo = String.valueOf(ri);
		if(ri.equals(""))
			return Fo;
		double num = Double.parseDouble(ri);

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
			Fo = String.valueOf(integer);
			return Fo;
		}

		if(dec.length()>(precision/*+1*/))
		{
			Fo = "";
			JOptionPane.showMessageDialog(null,"利率の入力は小数点以下"+precision+"桁までです");
			return Fo;
		} else{
			NumberFormat Numformatter = null;
			if(dec.length()==4)
				Numformatter= new DecimalFormat("#0.0000");
			else if(dec.length()==3)
				Numformatter= new DecimalFormat("#0.000");
			else if(dec.length()==2)
				Numformatter= new DecimalFormat("#0.00");
			else
				Numformatter= new DecimalFormat("#0.0");
			/*if(integer == 0)
				Fo =""+decimal;
			else
				Fo =integer+"."+dec;*/
			Fo = Numformatter.format(Double.parseDouble(ri));
		}
 		return Fo;
	}
 
 	public boolean FormatLike(String ri)
 	{
 		boolean Fo = true;
 		if(!ri.equals("")){
	 		double num= Double.parseDouble(ri);
	 		int zeros = 0;
	 		while (num < 1) {
	 		    num *= 10;
	 		    zeros++;
	 		}
	 		zeros -= 1;
	 		if(zeros>=3)
	 			Fo = false;
 		}
 		return Fo;
 	}
 	
 	
}



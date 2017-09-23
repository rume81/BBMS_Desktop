package com.ey.master.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.ey.application.controller.DBManager;
import com.ey.application.model.CommonProperties;
import com.ey.application.model.FinancialInstitutions;
import com.ey.application.view.FrmBidMain;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* FrmContractsWording.java
* --------------------
* Created on Mar 25, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Mar 25, 2016: Original version (Monsur)
*
*/
public class FrmContractsWording extends JInternalFrame {
	// Variables declaration - do not modify        
	private JTable table;
	private JButton cmdNew;
    private JButton cmdCloseup;
    
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdEdit;
    private javax.swing.JLabel lblDeptCode;
    private javax.swing.JLabel lblHorei01;
    private javax.swing.JLabel lblHorei02;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JLayeredPane pnlMain;
    private javax.swing.JComboBox<String> cmbDeptCode;
    private javax.swing.JTextArea txtHorei01;
    private javax.swing.JTextArea txtHorei02;
    // End of variables declaration     
    private int row;
    private boolean insertType;
    public FrmContractsWording() {
	   	try {
	   		jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    private void jbInit() {
    	this.setBounds(0, 0, 740, 440);
    	this.setTitle("契約書文面");
        pnlMain = new javax.swing.JLayeredPane();
        pnlForm = new javax.swing.JPanel();
        lblDeptCode = new javax.swing.JLabel();
        cmdClose = new javax.swing.JButton();
        lblHorei01 = new javax.swing.JLabel();
        txtHorei01 = new javax.swing.JTextArea();
        lblHorei02 = new javax.swing.JLabel();
        txtHorei02 = new javax.swing.JTextArea();
        cmdEdit = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();
        cmbDeptCode = new javax.swing.JComboBox<>();
        cmdCancel = new javax.swing.JButton();
        cmdCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlForm.setVisible(false);
			}
        });
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        pnlMain.setBackground(new java.awt.Color(176, 224, 230));
        pnlMain.setMinimumSize(new java.awt.Dimension(500, 550));
        pnlMain.setLayout(null);

        pnlForm.setBackground(new java.awt.Color(176, 224, 230));
        pnlForm.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.setLayout(null);

        lblDeptCode.setText("部門コード");
        lblDeptCode.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblDeptCode);
        lblDeptCode.setBounds(20, 30, 120, 27);
        
        CommonProperties props = new CommonProperties();
		String workingDir = props.getWorkingDir();
		String imageDir = props.getImageDir();
		
        cmdClose.setIcon(new javax.swing.ImageIcon(imageDir+"close.png")); // NOI18N
        cmdClose.setMaximumSize(new java.awt.Dimension(161, 161));
        cmdClose.setMinimumSize(new java.awt.Dimension(161, 161));
        cmdClose.setPreferredSize(new java.awt.Dimension(161, 161));
        pnlForm.add(cmdClose);
        cmdClose.setBounds(450, 10, 20, 20);
        cmdClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlForm.setVisible(false);
			}
        });
       

        lblHorei01.setText("HOREI01");
        lblHorei01.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblHorei01);
        lblHorei01.setBounds(20, 60, 120, 27);
        pnlForm.add(txtHorei01);
        txtHorei01.setBounds(150, 60, 300, 60);
        txtHorei01.setLineWrap(true);
        txtHorei01.setWrapStyleWord(true);

        lblHorei02.setText("HOREI02");
        lblHorei02.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblHorei02);
        lblHorei02.setBounds(20, 123, 120, 27);
        pnlForm.add(txtHorei02);
        txtHorei02.setBounds(150, 123, 300, 60);
        txtHorei02.setLineWrap(true);
        txtHorei02.setWrapStyleWord(true);

        cmdEdit.setText("セーブ");
        cmdEdit.setToolTipText("セーブ");
        pnlForm.add(cmdEdit);
        cmdEdit.setBounds(80, 200, 150, 23);
        cmdEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(insertType)
				{
					if(String.valueOf(cmbDeptCode.getSelectedItem()).equals(""))
						return;
					
					DefaultTableModel dtm = (DefaultTableModel)table.getModel();
					dtm.addRow(new Object[] {String.valueOf(cmbDeptCode.getSelectedItem()), txtHorei01.getText(), txtHorei02.getText()});
					insertContractsWording();
				} else{
	        		//table.setValueAt(txtFCode.getText(), row, 0);
	        		table.setValueAt(txtHorei01.getText(), row, 1);
	        		table.setValueAt(txtHorei02.getText(),row,2);
	        			        		
	        		updateContractsWording();
				}
        	    pnlForm.setVisible(false);
			}
        });
        
        cmdCancel.setText("キャンセル");
        cmdCancel.setToolTipText("キャンセル");
        pnlForm.add(cmdCancel);
        cmdCancel.setBounds(240, 200, 150, 23);

        pnlForm.add(cmbDeptCode);
        cmbDeptCode.setBounds(150, 30, 180, 27);

        pnlForm.setVisible(false);

        getContentPane().add(pnlMain);
        pnlMain.setBounds(0, 0, 740, 440);
        
        
        JScrollPane sp = new JScrollPane();
        sp.setBounds(0, 70, 730, 340);
        sp.setOpaque(true);
        //sp.setBackground(new java.awt.Color(176, 224, 230));
        
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
                "部門コード", "HOREI01", "HOREI02"
            }
        ));
        
        cmdCloseup = new JButton();
        cmdCloseup.setText("閉じる");
        cmdCloseup.setToolTipText("");
        pnlMain.add(cmdCloseup);
        cmdCloseup.setBounds(400, 40, 150, 23);
        cmdCloseup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
        });
        
        
        /*cmdRegistration.setText("登　　録");
        cmdRegistration.setToolTipText("");
        pnlMain.add(cmdRegistration);
        cmdRegistration.setBounds(400, 10, 150, 23);
        cmdRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerFinancialInstitution();
			}
        });*/
        
        cmdNew = new JButton();
        cmdNew.setText("新しく追加する");
        cmdNew.setToolTipText("");
        pnlMain.add(cmdNew);
        cmdNew.setBounds(400, 10, 150, 23);
        cmdNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertType = true;
				cmbDeptCode.setSelectedItem("");
        		txtHorei01.setText("");
        		txtHorei02.setText("");
        		
				pnlForm.setVisible(true);
			}
        });
        
        pnlForm.setBounds(120, 120, 490, 250);
        pnlMain.add(sp, new Integer(0), 0);
        pnlMain.add(pnlForm, new Integer(1), 0);
        table.addMouseListener(new java.awt.event.MouseAdapter()
        {
        	public void mouseClicked(java.awt.event.MouseEvent e)
        	{
        		row= table.rowAtPoint(e.getPoint());
        		//int col= table.columnAtPoint(e.getPoint());
        		insertType = false;    		
        		cmbDeptCode.setSelectedItem(table.getValueAt(row,0).toString());
        		txtHorei01.setText(table.getValueAt(row,1).toString());
        		txtHorei02.setText(table.getValueAt(row,2).toString());
        		
        		cmbDeptCode.setEnabled(false);
        		    
        	    pnlForm.setVisible(true);
        	}
        });
        
        departmentCode();
        loadBidContractsWordingData();
    }
    
    private static FrmContractsWording myInstance;
	
	public static FrmContractsWording getInstance() {
 	    //if (myInstance == null) {
 	        myInstance = new FrmContractsWording();
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
    
    public void departmentCode() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT DISTINCT divisions.department_code FROM divisions");
				cmbDeptCode.addItem("");
				while(rs.next()) {
					String code = rs.getString(1);
					cmbDeptCode.addItem(code);
					
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
    
    public void insertContractsWording()
    {
    	try {
			DBManager db = new DBManager();
			try {
				int del = 0;
								
				StringBuffer sb = new StringBuffer("INSERT INTO contracts_wording (department_code,horei01,horei02) VALUES('"
						+String.valueOf(cmbDeptCode.getSelectedItem())+"','"+txtHorei01.getText()+ "','"+txtHorei02.getText()+"')");
				//System.out.println(sb);
				boolean fo = db.doQuery(sb.toString());
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public void loadBidContractsWordingData() {
    	try {
			DBManager db = new DBManager();
			try {
				DefaultTableModel dtm = (DefaultTableModel)table.getModel();
				ResultSet rs;
				rs = db.getRecord("SELECT * FROM contracts_wording");
				
				while(rs.next()) {
					String dept_code=rs.getString(1);
					String horei01=rs.getString(2);
					String horei02=rs.getString(3);
					
					dtm.addRow(new Object[] { dept_code, horei01, horei02});
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
   
      	
    public void updateContractsWording()
    {
    	try {
			DBManager db = new DBManager();
			try {
				StringBuffer sb = new StringBuffer("UPDATE contracts_wording SET "
						+ "horei01='"+txtHorei01.getText()
						+ "',horei02='"+txtHorei02.getText()
						+ "' WHERE contracts_wording.department_code='"+cmbDeptCode.getSelectedItem()+"'");
				System.out.println(sb);
				boolean fo = db.doQuery(sb.toString());
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


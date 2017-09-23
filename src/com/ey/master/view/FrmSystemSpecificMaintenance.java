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
* FrmFinancialInstitutionsMaster.java
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
public class FrmSystemSpecificMaintenance extends JInternalFrame {
	// Variables declaration - do not modify        
	private JTable table;
	private JButton cmdRegistration;
    private JButton cmdCloseup;
    private javax.swing.JComboBox<String> cmbFieldName;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdEdit;
    private javax.swing.JLabel lblFieldName;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JLayeredPane pnlMain;
    private javax.swing.JLabel lblSystemName;
    // End of variables declaration     
    private int row;
    private String BankCode="";
    private String sysName;
    private String FieldName;
    public FrmSystemSpecificMaintenance(String sysN,String fieldN) {
	   	try {
	   		sysName =sysN;
	   		FieldName =fieldN;
	   		jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    private void jbInit() {
    	this.setBounds(0, 0, 740, 440);
    	this.setTitle("システム別メンテ");
        pnlMain = new javax.swing.JLayeredPane();
        pnlForm = new javax.swing.JPanel();
        cmdClose = new javax.swing.JButton();
        lblSystemName = new javax.swing.JLabel();
        lblFieldName = new javax.swing.JLabel();
        cmdEdit = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();
        cmbFieldName = new javax.swing.JComboBox<>();
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

        
        
        CommonProperties props = new CommonProperties();
		String workingDir = props.getWorkingDir();
		String imageDir = props.getImageDir();
		
        cmdClose.setIcon(new javax.swing.ImageIcon(imageDir+"close.png")); // NOI18N
        cmdClose.setMaximumSize(new java.awt.Dimension(161, 161));
        cmdClose.setMinimumSize(new java.awt.Dimension(161, 161));
        cmdClose.setPreferredSize(new java.awt.Dimension(161, 161));
        pnlForm.add(cmdClose);
        cmdClose.setBounds(350, 10, 20, 20);
        cmdClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlForm.setVisible(false);
			}
        });
        

        lblFieldName.setText("使用状況");
        lblFieldName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblFieldName);
        lblFieldName.setBounds(20, 60, 120, 27);

        lblSystemName.setText(sysName);
        lblSystemName.setFont(new java.awt.Font("", 0, 24));
        pnlMain.add(lblSystemName);
        lblSystemName.setBounds(20, 25, 250, 33);

        cmdEdit.setText("セーブ");
        cmdEdit.setToolTipText("セーブ");
        pnlForm.add(cmdEdit);
        cmdEdit.setBounds(30, 105, 150, 23);
        cmdEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setValueAt(String.valueOf(cmbFieldName.getSelectedItem()), row, 4);
        		        		
        		updateFinancialInstitutionMasterTemp();
				
        	    pnlForm.setVisible(false);
			}
        });
        
        cmdCancel.setText("キャンセル");
        cmdCancel.setToolTipText("キャンセル");
        pnlForm.add(cmdCancel);
        cmdCancel.setBounds(190, 105, 150, 23);

        cmbFieldName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1" }));
        pnlForm.add(cmbFieldName);
        cmbFieldName.setBounds(150, 60, 180, 27);
        
        pnlForm.setVisible(false);

        getContentPane().add(pnlMain);
        pnlMain.setBounds(0, 0, 740, 440);
        
        
        JScrollPane sp = new JScrollPane();
        sp.setBounds(0, 70, 730, 340);
        sp.setOpaque(true);
        
        
        table = new JTable();
        table.setBorder(UIManager.getBorder("DesktopIcon.border"));
        table.setRowHeight(27);
        sp.setViewportView(table);

        // the column in the table
        table.setModel(new DefaultTableModel(
                new Object[][] {
            },
            new String[] {
                "コード", "名称", "店舗", "業態", "使用状況"
            }
        ));
        
        cmdCloseup = new JButton();
        cmdCloseup.setText("閉じる");
        cmdCloseup.setToolTipText("");
        pnlMain.add(cmdCloseup);
        cmdCloseup.setBounds(440, 25, 150, 23);
        cmdCloseup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
        });
        
        cmdRegistration = new JButton();
        cmdRegistration.setText("登　　録");
        cmdRegistration.setToolTipText("");
        pnlMain.add(cmdRegistration);
        cmdRegistration.setBounds(280, 25, 150, 23);
        cmdRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerFinancialInstitution();
			}
        });
        
        
        
        pnlForm.setBounds(170, 120, 390, 150);
        pnlMain.add(sp, new Integer(0), 0);
        pnlMain.add(pnlForm, new Integer(1), 0);
        table.addMouseListener(new java.awt.event.MouseAdapter()
        {
        	public void mouseClicked(java.awt.event.MouseEvent e)
        	{
        		row= table.rowAtPoint(e.getPoint());
        		BankCode = table.getValueAt(row,0).toString();
        		cmbFieldName.setSelectedItem(table.getValueAt(row,4).toString());
        			    
        	    pnlForm.setVisible(true);
        	}
        });
        
        //getBusinessCategory();
        loadBidFinancialInstitutionsData();
    }
    
    private static FrmSystemSpecificMaintenance myInstance;
	
	public static FrmSystemSpecificMaintenance getInstance(String sysN,String fieldName) {
 	    //if (myInstance == null) {
 	        myInstance = new FrmSystemSpecificMaintenance(sysN,fieldName);
 	    //}
 	    return myInstance;
 	}
    
    public void Close()
	{
    	FrmSystemSelection nw = FrmSystemSelection.getInstance();
		nw.setBounds(0, 0, 329, 372);
		nw.getContentPane().setBackground(new Color(176,224,230));
		nw.setForeground(Color.black);
		nw.setResizable(false);
		nw.setTitle("システム別マスタ設定");
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
    
    public void loadBidFinancialInstitutionsData() {
    	try {
			DBManager db = new DBManager();
			try {
				boolean fo = db.doQuery("DELETE FROM financial_institutions_masters_temp");
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	try {
			DBManager db = new DBManager();
			try {
				boolean fo = db.doQuery("INSERT INTO financial_institutions_masters_temp(bank_code, financial_institution_name, "+ 
										"store, business_category, F11, F12, F13, F14, F15, F16, F17, F18, F19, F20, F21, F22, F23, F24, F25, F26 , F27, F28, F29, F30, updated) "+ 
										"SELECT DISTINCT financial_institutions_masters.bank_code, financial_institutions_masters.financial_institution_name, "+ 
										"financial_institutions_masters.store, financial_institutions_masters.business_category, financial_institutions_masters.F11, "+ 
										"financial_institutions_masters.F12, financial_institutions_masters.F13, financial_institutions_masters.F14, "+ 
										"financial_institutions_masters.F15, financial_institutions_masters.F16, financial_institutions_masters.F17, "+ 
										"financial_institutions_masters.F18, financial_institutions_masters.F19, financial_institutions_masters.F20, "+ 
										"financial_institutions_masters.F21, financial_institutions_masters.F22, financial_institutions_masters.F23, "+ 
										"financial_institutions_masters.F24, financial_institutions_masters.F25, financial_institutions_masters.F26, "+ 
										"financial_institutions_masters.F27, financial_institutions_masters.F28, financial_institutions_masters.F29, "+ 
										"financial_institutions_masters.F30, financial_institutions_masters.updated FROM financial_institutions_masters");
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	try {
			DBManager db = new DBManager();
			try {
				DefaultTableModel dtm = (DefaultTableModel)table.getModel();
				ResultSet rs;
				rs = db.getRecord("SELECT * FROM SystemSpecificMaintenance");
				
				while(rs.next()) {
					String bank_code=rs.getString(1);
					String financialInstitutionName=rs.getString(2);
					String store=rs.getString(3);
					String business_category=rs.getString(4);
					String FN=rs.getString(5);
					//int deleted=rs.getInt(26);
					
					dtm.addRow(new Object[] { bank_code, financialInstitutionName, store, business_category, FN});
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
   
    public void updateFinancialInstitutionMasterTemp()
    {
    	try {
			DBManager db = new DBManager();
			try {
								
				StringBuffer sb = new StringBuffer("UPDATE financial_institutions_masters_temp SET "
						+ FieldName+"='"+String.valueOf(cmbFieldName.getSelectedItem())
						+ "' WHERE financial_institutions_masters_temp.bank_code='"+BankCode+"'");
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
      
    public void registerFinancialInstitution()
    {
    	List<FinancialInstitutions> finstitute = new ArrayList<FinancialInstitutions>();
    	try {
    		ResultSet rs=null;
			DBManager db = new DBManager();
			DBManager dbd = new DBManager();
			
			try {
				rs = db.getRecord("SELECT bank_code,"+FieldName+" from financial_institutions_masters_temp");
				
				while(rs.next()) {		
					FinancialInstitutions obj = new FinancialInstitutions();
					obj.setBank_code(rs.getString(1));
					obj.setBank_name(rs.getString(2));
										
					finstitute.add(obj);
				}	
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	try {
			DBManager db = new DBManager();
			try {
				
				for(FinancialInstitutions obj:finstitute){
					StringBuffer sb = new StringBuffer("UPDATE financial_institutions_masters SET "
					+ FieldName+"='"+obj.getBank_name()
					+ "' WHERE financial_institutions_masters.bank_code='"+obj.getBank_code()+"'");
					
					//System.out.println(sb);
					boolean fo = db.doQuery(sb.toString());
				}
				
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	   	
    	JOptionPane.showMessageDialog(null, "更新しました");
    }
}


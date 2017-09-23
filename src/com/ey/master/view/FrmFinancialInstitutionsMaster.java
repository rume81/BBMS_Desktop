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
public class FrmFinancialInstitutionsMaster extends JInternalFrame {
	// Variables declaration - do not modify        
	private JTable table;
	private JButton cmdNew;
	private JButton cmdRegistration;
    private JButton cmdCloseup;
    
    private javax.swing.JCheckBox chkDel;
    private javax.swing.JComboBox<String> cmbBusinessCategory;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdClose;
    private javax.swing.JButton cmdEdit;
    private javax.swing.JLabel lblBusinessCategory;
    private javax.swing.JLabel lblDel;
    private javax.swing.JLabel lblFCode;
    private javax.swing.JLabel lblFName;
    private javax.swing.JLabel lblStore;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JLayeredPane pnlMain;
    private javax.swing.JTextField txtFCode;
    private javax.swing.JTextField txtFName;
    private javax.swing.JTextField txtStore;
    // End of variables declaration     
    private int row;
    private boolean insertType;
    public FrmFinancialInstitutionsMaster() {
	   	try {
	   		jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
    private void jbInit() {
    	this.setBounds(0, 0, 740, 440);
    	this.setTitle("金融機関マスターメンテ");
        pnlMain = new javax.swing.JLayeredPane();
        pnlForm = new javax.swing.JPanel();
        lblFCode = new javax.swing.JLabel();
        cmdClose = new javax.swing.JButton();
        txtFCode = new javax.swing.JTextField();
        lblFName = new javax.swing.JLabel();
        txtFName = new javax.swing.JTextField();
        lblStore = new javax.swing.JLabel();
        txtStore = new javax.swing.JTextField();
        lblBusinessCategory = new javax.swing.JLabel();
        lblDel = new javax.swing.JLabel();
        cmdEdit = new javax.swing.JButton();
        cmdCancel = new javax.swing.JButton();
        cmbBusinessCategory = new javax.swing.JComboBox<>();
        chkDel = new javax.swing.JCheckBox();
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

        lblFCode.setText("コード");
        lblFCode.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblFCode);
        lblFCode.setBounds(20, 30, 120, 27);
        
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
        pnlForm.add(txtFCode);
        txtFCode.setBounds(150, 30, 180, 27);
        txtFCode.setEnabled(false);

        lblFName.setText("名称");
        lblFName.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblFName);
        lblFName.setBounds(20, 60, 120, 27);
        pnlForm.add(txtFName);
        txtFName.setBounds(150, 60, 180, 27);

        lblStore.setText("店舗");
        lblStore.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblStore);
        lblStore.setBounds(20, 90, 120, 27);
        pnlForm.add(txtStore);
        txtStore.setBounds(150, 90, 180, 27);

        lblBusinessCategory.setText("業態");
        lblBusinessCategory.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblBusinessCategory);
        lblBusinessCategory.setBounds(20, 120, 120, 27);

        lblDel.setText("削除");
        lblDel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlForm.add(lblDel);
        lblDel.setBounds(20, 150, 120, 27);

        cmdEdit.setText("セーブ");
        cmdEdit.setToolTipText("セーブ");
        pnlForm.add(cmdEdit);
        cmdEdit.setBounds(30, 200, 150, 23);
        cmdEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(insertType)
				{
					if(txtFCode.getText().equals(""))
						return;
					
					DefaultTableModel dtm = (DefaultTableModel)table.getModel();
					dtm.addRow(new Object[] { txtFCode.getText(), txtFName.getText(), txtStore.getText(), String.valueOf(cmbBusinessCategory.getSelectedItem())," "});
					insertFinancialInstitutionMasterTemp();
				} else{
	        		//table.setValueAt(txtFCode.getText(), row, 0);
	        		table.setValueAt(txtFName.getText(), row, 1);
	        		table.setValueAt(txtStore.getText(),row,2);
	        		table.setValueAt(String.valueOf(cmbBusinessCategory.getSelectedItem()), row, 3);
	        		if(chkDel.isSelected())
	        			table.setValueAt("✔",row,4);
	        		else
	        			table.setValueAt(" ",row,4);
	        		
	        		updateFinancialInstitutionMasterTemp();
				}
        	    pnlForm.setVisible(false);
			}
        });
        
        cmdCancel.setText("キャンセル");
        cmdCancel.setToolTipText("キャンセル");
        pnlForm.add(cmdCancel);
        cmdCancel.setBounds(190, 200, 150, 23);

        //cmbBusinessCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        pnlForm.add(cmbBusinessCategory);
        cmbBusinessCategory.setBounds(150, 120, 180, 30);

        chkDel.setLabel("");
        
        pnlForm.add(chkDel);
        chkDel.setBounds(150, 160, 20, 21);
        chkDel.getAccessibleContext().setAccessibleName("");

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
                "コード", "名称", "店舗", "業態", "削除"
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
        
        cmdRegistration = new JButton();
        cmdRegistration.setText("登　　録");
        cmdRegistration.setToolTipText("");
        pnlMain.add(cmdRegistration);
        cmdRegistration.setBounds(400, 10, 150, 23);
        cmdRegistration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registerFinancialInstitution();
			}
        });
        
        cmdNew = new JButton();
        cmdNew.setText("新しく追加する");
        cmdNew.setToolTipText("");
        pnlMain.add(cmdNew);
        cmdNew.setBounds(230, 10, 150, 23);
        cmdNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertType = true;
				txtFCode.setText("");
        		txtFName.setText("");
        		txtStore.setText("");
        		cmbBusinessCategory.setSelectedIndex(0);
        		txtFCode.setEnabled(true);
        		lblDel.setVisible(false);
        		chkDel.setVisible(false);
				pnlForm.setVisible(true);
			}
        });
        
        pnlForm.setBounds(170, 120, 390, 250);
        pnlMain.add(sp, new Integer(0), 0);
        pnlMain.add(pnlForm, new Integer(1), 0);
        table.addMouseListener(new java.awt.event.MouseAdapter()
        {
        	public void mouseClicked(java.awt.event.MouseEvent e)
        	{
        		row= table.rowAtPoint(e.getPoint());
        		//int col= table.columnAtPoint(e.getPoint());
        		insertType = false;    		
        		txtFCode.setText(table.getValueAt(row,0).toString());
        		txtFName.setText(table.getValueAt(row,1).toString());
        		txtStore.setText(table.getValueAt(row,2).toString());
        		cmbBusinessCategory.setSelectedItem(table.getValueAt(row,3).toString());
        		if(table.getValueAt(row,4).toString().equals(" "))
        			chkDel.setSelected(false);
        		else
        			chkDel.setSelected(true);
        		txtFCode.setEnabled(false);
        		lblDel.setVisible(true);
        		chkDel.setVisible(true);	    
        	    pnlForm.setVisible(true);
        	}
        });
        
        getBusinessCategory();
        loadBidFinancialInstitutionsData();
    }
    
    private static FrmFinancialInstitutionsMaster myInstance;
	
	public static FrmFinancialInstitutionsMaster getInstance() {
 	    //if (myInstance == null) {
 	        myInstance = new FrmFinancialInstitutionsMaster();
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
				rs = db.getRecord("SELECT * FROM financial_institutions_masters_temp");
				
				while(rs.next()) {
					String bank_code=rs.getString(1);
					String financialInstitutionName=rs.getString(2);
					String store=rs.getString(3);
					String business_category=rs.getString(4);
					int deleted=rs.getInt(26);
					
					dtm.addRow(new Object[] { bank_code, financialInstitutionName, store, business_category, (deleted==0?" ":"✔")});
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
   
    public void getBusinessCategory() {
		try {
			DBManager db = new DBManager();
			try {

				ResultSet rs;
				rs = db.getRecord("SELECT DISTINCT business_categorys.businesscategory FROM business_categorys");
				cmbBusinessCategory.addItem("");
				
				while(rs.next()) {
					String code = rs.getString(1);
					cmbBusinessCategory.addItem(code);
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
				int del = 0;
				if(chkDel.isSelected())
					del = -1;
				else
					del = 0;
				
				StringBuffer sb = new StringBuffer("UPDATE financial_institutions_masters_temp SET "
						+ "financial_institution_name='"+txtFName.getText()
						+ "',store='"+txtStore.getText()
						+ "',business_category='"+String.valueOf(cmbBusinessCategory.getSelectedItem())
						+ "',DelF="+del
						+ " WHERE financial_institutions_masters_temp.bank_code='"+txtFCode.getText()+"'");
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
    
    public void insertFinancialInstitutionMasterTemp()
    {
    	try {
			DBManager db = new DBManager();
			try {
				int del = 0;
								
				StringBuffer sb = new StringBuffer("INSERT INTO financial_institutions_masters_temp (bank_code,financial_institution_name,store,business_category,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,f21,f22,f23,f24,f25,f26,f27,f28,f29,f30,DelF) VALUES('"
						+txtFCode.getText()+"','"+txtFName.getText()+ "','"+txtStore.getText()+ "','"+String.valueOf(cmbBusinessCategory.getSelectedItem())+ "','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1',1)");
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
    	try {
    		ResultSet rs=null;
			DBManager db = new DBManager();
			DBManager dbd = new DBManager();
			List<String> bankCodes = new ArrayList<String>();
			try {
				rs = db.getRecord("SELECT * from financial_institutions_masters_temp where DelF = -1");
				while(rs.next()) {
					String Bcode = rs.getString(1);
					bankCodes.add(Bcode);
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
			} finally {
				db.close();
			}
			if(bankCodes.size()>0){
				int res = JOptionPane.showConfirmDialog(null,"削除対象のデータがありますが、よろしいですか？");
				if(res == JOptionPane.NO_OPTION)
				{
					return;
				}
			}
			
			for(String Bcode:bankCodes){		
				boolean fo = dbd.doQuery("delete from financial_institutions_masters where bank_code ='"+Bcode+"'");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
    	List<FinancialInstitutions> finstitute = new ArrayList<FinancialInstitutions>();
    	try {
    		ResultSet rs=null;
			DBManager db = new DBManager();
			DBManager dbd = new DBManager();
			
			try {
				rs = db.getRecord("SELECT * from financial_institutions_masters_temp where DelF = 0");
				
				while(rs.next()) {		
					FinancialInstitutions obj = new FinancialInstitutions();
					obj.setBank_code(rs.getString(1));
					obj.setFinancial_institution_name(rs.getString(2));
					obj.setBank_name(rs.getString(3));
					obj.setBusiness_category(rs.getString(4));
					
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
					StringBuffer sb = new StringBuffer("UPDATE financial_institutions_masters SET"
					+ " financial_institution_name='"+obj.getFinancial_institution_name()
					+ "',store='"+obj.getBank_name()
					+ "',business_category='"+obj.getBusiness_category()
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
    	
    	List<FinancialInstitutions> finstituteNew = new ArrayList<FinancialInstitutions>();
    	try {
    		ResultSet rs=null;
			DBManager db = new DBManager();
			DBManager dbd = new DBManager();
			
			try {
				rs = db.getRecord("SELECT * from financial_institutions_masters_temp where DelF = 1");
				
				while(rs.next()) {		
					FinancialInstitutions obj = new FinancialInstitutions();
					obj.setBank_code(rs.getString(1));
					obj.setFinancial_institution_name(rs.getString(2));
					obj.setBank_name(rs.getString(3));
					obj.setBusiness_category(rs.getString(4));
					
					finstituteNew.add(obj);
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
				
				for(FinancialInstitutions obj:finstituteNew){
					StringBuffer sb = new StringBuffer("INSERT INTO financial_institutions_masters (bank_code,financial_institution_name,store,business_category,f11,f12,f13,f14,f15,f16,f17,f18,f19,f20,f21,f22,f23,f24,f25,f26,f27,f28,f29,f30) VALUES('"
						+obj.getBank_code()+"','"+obj.getFinancial_institution_name()+ "','"+obj.getBank_name()+ "','"+obj.getBusiness_category()+ "','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1','1')");
					
					System.out.println(sb);
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


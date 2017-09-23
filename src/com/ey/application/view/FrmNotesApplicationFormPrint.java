package com.ey.application.view;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.ey.application.controller.DBManager;
import com.ey.application.model.BillCreditApplicationDatas;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* FrmNotesApplicationFormPrint.java
* --------------------
* Created on Mar 4, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Mar 4, 2016: Original version (Ole Ul)
*
*/
public class FrmNotesApplicationFormPrint extends JInternalFrame {
	
	
	JPanel pnlMain 								= new JPanel();
	JLabel lblBorrowingDate 					= new JLabel();
	//JTextField txtBorrowingDate 				= new JTextField();
	JLabel lblMaturityDate 						= new JLabel();
	//JTextField txtMaturityDate 					= new JTextField();
	JLabel lblUnitNamikawajun 					= new JLabel();
    JComboBox cmbUnit 							= new JComboBox<>();
    JComboBox cmbRearrangement 					= new JComboBox<>();
    JLabel lblRearrangementApplication 			= new JLabel();
    JComboBox cmbBillRearrangement 				= new JComboBox<>();
    JButton cmdCreatingCreditApplication 		= new JButton();
    JButton cmdCreditApplication 				= new JButton();
    JButton cmdApprovalApplicationForm 			= new JButton();
    JButton cmdCreditApplicationList 			= new JButton();
    JButton cmdDeedPrint 						= new JButton();
    JButton cmdDocumentImage 					= new JButton();
    JButton cmdCreditorManagementBook 			= new JButton();
    JButton cmdExelDataCreation 				= new JButton();
    JButton cmdCloseup 							= new JButton();
    JLabel lblAccount 							= new JLabel();
    JComboBox cmbDeptCode 						= new JComboBox<>();
    JTextField txtDepartmentName 				= new JTextField();
    JTextField txtProcessing 					= new JTextField();
    JLabel lblBorrowingDateApplication 			= new JLabel();
    JTextField txtBorrowingDateApplication 		= new JTextField();
    JLabel lblIntermediatePayment 				= new JLabel();
    JLabel lblNumberRegistration 				= new JLabel();
    JLabel lblWarrentyCardOrdering 				= new JLabel();
    JLabel lblWarrentyCardPrinting 				= new JLabel();
    JLabel lblDepositCertificatePrint 			= new JLabel();
    //JTextField txtIntermidiateInterestPayment 	= new JTextField();
		
	UtilDateModel model;
	JDatePanelImpl datePanel;
	JDatePickerImpl txtBorrowingDate;
	
	UtilDateModel Maturitymodel;
	JDatePanelImpl maturityPanel;
	JDatePickerImpl txtMaturityDate;
	
	UtilDateModel IntermidiateInterestPaymentmodel;
	JDatePanelImpl IntermidiateInterestPaymentPanel;
	JDatePickerImpl txtIntermidiateInterestPayment;
	
	private String bidDate;
	String datePattern = "dd-MM-yyyy";
	SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern); 
	 

    public FrmNotesApplicationFormPrint(Date date) {
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("借入申込");
        setPreferredSize(new java.awt.Dimension(500, 550));
        setSize(new java.awt.Dimension(500, 550));
        getContentPane().setLayout(null);

        pnlMain.setBackground(new java.awt.Color(176, 224, 230));
        //pnlMain.setMinimumSize(new java.awt.Dimension(500, 550));
        pnlMain.setLayout(null);
        
        model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model, p);
		
		Maturitymodel = new UtilDateModel();
		maturityPanel = new JDatePanelImpl(Maturitymodel, p);
		
		IntermidiateInterestPaymentmodel = new UtilDateModel();
		IntermidiateInterestPaymentPanel = new JDatePanelImpl(IntermidiateInterestPaymentmodel, p);
		
		txtBorrowingDate = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		txtMaturityDate = new JDatePickerImpl(maturityPanel, new DateLabelFormatter());
		txtIntermidiateInterestPayment = new JDatePickerImpl(IntermidiateInterestPaymentPanel, new DateLabelFormatter());
		
		//txtAuctionDate.setBounds(new Rectangle(207, 33, 175, 27));

        lblBorrowingDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblBorrowingDate.setText("借入日");
        lblBorrowingDate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblBorrowingDate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pnlMain.add(lblBorrowingDate);
        lblBorrowingDate.setBounds(40, 70, 70, 23);
        pnlMain.add(txtBorrowingDate);
        txtBorrowingDate.setBounds(140, 70, 175, 27);

        lblMaturityDate.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblMaturityDate.setText("返済日");
        lblMaturityDate.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblMaturityDate);
        lblMaturityDate.setBounds(40, 100, 70, 23);
        pnlMain.add(txtMaturityDate);
        txtMaturityDate.setBounds(140, 100, 175, 27);

        lblUnitNamikawajun.setText("<html>認可申請書別<br>\n紙の単位・並<br>\n替順</html>"); // NOI18N
        lblUnitNamikawajun.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lblUnitNamikawajun.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        pnlMain.add(lblUnitNamikawajun);
        lblUnitNamikawajun.setBounds(40, 230, 120, 50);

        cmbUnit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "億円", "円" }));
        cmbUnit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        pnlMain.add(cmbUnit);
        cmbUnit.setBounds(170, 230, 70, 23);

        cmbRearrangement.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "金融機関", "借入金額", "利率" }));
        pnlMain.add(cmbRearrangement);
        cmbRearrangement.setBounds(170, 260, 69, 23);

        lblRearrangementApplication.setText("<html>申込書一覧<br>の並替</html>");
        lblRearrangementApplication.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblRearrangementApplication);
        lblRearrangementApplication.setBounds(240, 230, 70, 30);

        cmbBillRearrangement.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<html>利率・手<br>形番号</br>", "金融機関", "手形番号" }));
        pnlMain.add(cmbBillRearrangement);
        cmbBillRearrangement.setBounds(340, 230, 100, 40);

        cmdCreatingCreditApplication.setText("<html>借入申込書デー<br>タ作成</html>");
        pnlMain.add(cmdCreatingCreditApplication);
        cmdCreatingCreditApplication.setBounds(40, 290, 130, 40);
        cmdCreatingCreditApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KM_PRINT(1);
			}
        });

        cmdCreditApplication.setText("借入申込書印刷");
        pnlMain.add(cmdCreditApplication);
        cmdCreditApplication.setBounds(190, 290, 120, 40);
        cmdCreditApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtProcessing.setText("証書");
				KM_PRINT(2);
				txtProcessing.setText("");
			}
        });

        cmdApprovalApplicationForm.setText("認可申請書別紙");
        pnlMain.add(cmdApprovalApplicationForm);
        cmdApprovalApplicationForm.setBounds(330, 290, 130, 40);
        cmdApprovalApplicationForm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KM_PRINT(4);
			}
        });
        cmdCreditApplicationList.setText("<html>借入申込書一覧 <br>印刷</html>");
        pnlMain.add(cmdCreditApplicationList);
        cmdCreditApplicationList.setBounds(40, 340, 130, 40);
        cmdCreditApplicationList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtProcessing.setText("証書");
				KM_PRINT(3);
				txtProcessing.setText("");
			}
        });

        cmdDeedPrint.setText("証書印刷 ");
        pnlMain.add(cmdDeedPrint);
        cmdDeedPrint.setBounds(190, 340, 120, 40);
        cmdDeedPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//OPEN REPORT  LoanAgreementDeed
		    	JasperReportViewer nw = JasperReportViewer.getInstance("LoanAgreementDeed",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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

				//dispose();   	
			}
        });

        cmdDocumentImage.setText("伝票イメージ ");
        pnlMain.add(cmdDocumentImage);
        cmdDocumentImage.setBounds(330, 340, 130, 40);
        cmdDocumentImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferSlip();
			}
        });

        cmdCreditorManagementBook.setText("<html>債権者管理簿<br>作成</html>");
        pnlMain.add(cmdCreditorManagementBook);
        cmdCreditorManagementBook.setBounds(40, 390, 130, 40);
        cmdCreditorManagementBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtProcessing.setText("債権");
				KM_PRINT(3);
				txtProcessing.setText("");
			}
        });
        
        cmdExelDataCreation.setText("Excelデータ作成 ");
        pnlMain.add(cmdExelDataCreation);
        cmdExelDataCreation.setBounds(190, 390, 120, 40);
        cmdExelDataCreation.setVisible(false);

        cmdCloseup.setText("閉じる ");
        pnlMain.add(cmdCloseup);
        cmdCloseup.setBounds(330, 390, 130, 40);
        cmdCloseup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Close();
			}
		});

        lblAccount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblAccount.setText("勘定");
        lblAccount.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblAccount);
        lblAccount.setBounds(40, 40, 70, 23);
        lblAccount.setVisible(false);

        
        pnlMain.add(cmbDeptCode);
        cmbDeptCode.setBounds(140, 40, 70, 23);
        departmentCode();
        cmbDeptCode.setVisible(false);
        getDetpInfo(); 

        txtDepartmentName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        pnlMain.add(txtDepartmentName);
        txtDepartmentName.setBounds(240, 40, 200, 23);
        txtDepartmentName.setVisible(false);

        txtProcessing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                
            }
        });
        pnlMain.add(txtProcessing);
        txtProcessing.setBounds(310, 70, 130, 23);
        txtProcessing.setVisible(false);

        lblBorrowingDateApplication.setText("借入申込日");
        lblBorrowingDateApplication.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblBorrowingDateApplication);
        lblBorrowingDateApplication.setBounds(40, 130, 70, 23);
        lblBorrowingDateApplication.setVisible(false);
       
        pnlMain.add(txtBorrowingDateApplication);
        txtBorrowingDateApplication.setBounds(140, 130, 120, 23);
        txtBorrowingDateApplication.setVisible(false);

        lblIntermediatePayment.setText("中間利払日");
        lblIntermediatePayment.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblIntermediatePayment);
        lblIntermediatePayment.setBounds(40, 160, 70, 23);
        lblIntermediatePayment.setVisible(false);

        lblNumberRegistration.setText("番号登録");
        lblNumberRegistration.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblNumberRegistration);
        lblNumberRegistration.setBounds(310, 100, 130, 23);
        lblNumberRegistration.setVisible(false);

        lblWarrentyCardOrdering.setText("保証書文面");
        lblWarrentyCardOrdering.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblWarrentyCardOrdering);
        lblWarrentyCardOrdering.setBounds(310, 120, 130, 23);
        lblWarrentyCardOrdering.setVisible(false);

        lblWarrentyCardPrinting.setText("保証書印刷");
        lblWarrentyCardPrinting.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblWarrentyCardPrinting);
        lblWarrentyCardPrinting.setBounds(310, 140, 130, 23);
        lblWarrentyCardPrinting.setVisible(false);

        lblDepositCertificatePrint.setText("預り証印刷");
        lblDepositCertificatePrint.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlMain.add(lblDepositCertificatePrint);
        lblDepositCertificatePrint.setBounds(310, 160, 130, 23);
        lblDepositCertificatePrint.setVisible(false);

        pnlMain.add(txtIntermidiateInterestPayment);
        txtIntermidiateInterestPayment.setBounds(140, 160, 175, 27);
        txtIntermidiateInterestPayment.setVisible(false);

        getContentPane().add(pnlMain);
        pnlMain.setBounds(0, 0, 500, 550);
        pnlMain.getAccessibleContext().setAccessibleName("");

    }// </editor-fold>    
    
    private static FrmNotesApplicationFormPrint myInstance;

 	public static FrmNotesApplicationFormPrint getInstance(Date date) {
 	    //if (myInstance == null) {
 	        myInstance = new FrmNotesApplicationFormPrint(date);
 	    //}
 	    return myInstance;
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
					txtDepartmentName.setText(rsData.getString(2));
				} else {
					txtDepartmentName.setText("");
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
 	
 	public void AddCreditApplication() {
 		try {
			DBManager db = new DBManager();
			String deptcode = String.valueOf(cmbDeptCode.getSelectedItem());
	 		try {
 				StringBuffer strSql = new StringBuffer("INSERT INTO bill_credit_application_datas (department_code, bank_code, financial_institution_name, successful_bid_interest_rate, the_highest_bid,auction_date) "+ 
				"SELECT DISTINCT successful_bid_datas_3.department_code, successful_bid_datas_3.bank_code, financial_institutions_masters.financial_institution_name, " +
				"successful_bid_datas_3.financial_institution_name, successful_bid_datas_3.successful_bid_price, " +
				"successful_bid_datas_3.auction_date FROM successful_bid_datas_3 LEFT JOIN financial_institutions_masters ON successful_bid_datas_3.bank_code = financial_institutions_masters.bank_code " +
				"WHERE successful_bid_datas_3.bank_code IS NOT NULL AND successful_bid_datas_3.successful_bid_price> '0' AND successful_bid_datas_3.bid_number='98' AND successful_bid_datas_3.department_code ='"+deptcode+"' AND auction_date = '"+bidDate+"'");
			
				System.out.println(strSql);
				boolean dStatus=db.doQuery(strSql.toString());
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
 			String deptcode = String.valueOf(cmbDeptCode.getSelectedItem());
			try {
				ResultSet resultsData;
	 					
				resultsData = db.getRecord("SELECT * FROM bill_credit_application_datas WHERE bill_credit_application_datas.department_code ='"+deptcode+"' AND auction_date = '"+bidDate+"'");
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
 	}
 	
 	public String Format(long n,String pattern)
 	{
 		String num= "";
 		if(pattern.equals("0000"))
 			num = StringUtils.leftPad(String.valueOf(n), 4, "0");
 		
 		return num;
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
 	
 	public float Int(double val)
    {
		double myDub;
		myDub=val;
		long myLong;
		myLong=(long)myDub;
		//myDub=(myDub%1)*1000000000;
		//int myInt=(int)myDub;
        return (float) myLong;
	}
 	
 	public List<BillCreditApplicationDatas> getBillCreditApplicationData()
 	{
 		List<BillCreditApplicationDatas> billCredits = new ArrayList<BillCreditApplicationDatas>();
		try {
 			DBManager db = new DBManager();
			try {
				ResultSet rsData;
	 			
				rsData = db.getRecord("SELECT * FROM bill_credit_application_datas  WHERE bill_credit_application_datas.department_code='"+String.valueOf(cmbDeptCode.getSelectedItem())+"' AND auction_date='"+bidDate+"'");
				while(rsData.next())
				{
					if(rsData.getString(1).equals(String.valueOf(cmbDeptCode.getSelectedItem())))
					{
						BillCreditApplicationDatas billCredit = new BillCreditApplicationDatas();
						billCredit.setDepartment_code(rsData.getString(1));
						billCredit.setBank_code(rsData.getString(2));
						billCredit.setFinancial_institution_name(rsData.getString(3));
						billCredit.setSuccessful_bid_interest_rate(rsData.getDouble(4));
						billCredit.setSuccessful_bid_price(rsData.getFloat(5));
						billCredit.setBill_number(rsData.getString(6));
						billCredit.setDrawer_date(rsData.getString(7));
						billCredit.setMaturity_date(rsData.getString(8));
						billCredit.setNumber_of_days(rsData.getLong(9));
						billCredit.setInterest_rate(rsData.getDouble(10));
						billCredit.setAmount_of_money(rsData.getDouble(11));
						billCredit.setInterest_amount(rsData.getFloat(12));
						billCredit.setRemarks(rsData.getString(13));
						billCredit.setAuction_date(rsData.getString(14));
						
						billCredits.add(billCredit);
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
		
		return billCredits;
 	}
 	
 	public void KM_PRINT(double SHORI) {
 		
 		int han = 0;
 		long TNo=0;
 		
 		if(SHORI == 1) {
 			//System.out.println("Data of ReportBillCreditApplication of accounting that is currently selected and make this Processing are all Clear. BillNumber you enter also all will be erased. is it OK?, 4");
 			if (han == 7) 
 				return;
 			Date DrawerDate = (Date) txtBorrowingDate.getModel().getValue();
			Date MaturityDate = (Date) txtMaturityDate.getModel().getValue();
						
			if((null == DrawerDate) || (null ==MaturityDate))
			{
				JOptionPane.showMessageDialog(null, "引き出し日付と満期日を挿入してください。");
				return;
			}
 			try {
 	 			DBManager db = new DBManager();
 				try {
 					ResultSet rsData;
 		 			
 					boolean fo = db.doQuery("DELETE FROM bill_credit_application_datas WHERE bill_credit_application_datas.department_code ='"+String.valueOf(cmbDeptCode.getSelectedItem())+"' AND auction_date = '"+bidDate+"'");	
 					rsData = db.getRecord("SELECT * FROM bill_credit_application_datas ORDER BY bill_number DESC");
 					boolean Fo = false;
 					while(rsData.next())
 					{
 						Long trn = rsData.getLong(6);
 						if(null!=trn)
 						{
 							TNo = trn + 1;
 							Fo = true;
 						}
 					} 
 					if(!Fo){
 						TNo = 1;
 					}
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
 					 		 			
 					boolean fo = db.doQuery("INSERT INTO bill_credit_application_datas(department_code, bank_code, financial_institution_name, successful_bid_interest_rate, successful_bid_price, auction_date) "+
 											"SELECT DISTINCT successful_bid_datas_3.department_code, successful_bid_datas_3.bank_code,financial_institutions_masters.financial_institution_name, successful_bid_datas_3.successful_bid_interest_rate, successful_bid_datas_3.successful_bid_price, successful_bid_datas_3.auction_date "+
 											"FROM successful_bid_datas_3 LEFT JOIN financial_institutions_masters ON successful_bid_datas_3.bank_code = financial_institutions_masters.bank_code "+
 											"WHERE (((successful_bid_datas_3.department_code) = '"+String.valueOf(cmbDeptCode.getSelectedItem())+"') AND (Not (successful_bid_datas_3.bank_code) Is Null) AND ((successful_bid_datas_3.successful_bid_price)> 0) AND ((successful_bid_datas_3.bid_number) = 98) AND ((successful_bid_datas_3.auction_date) = '"+bidDate+"'))");	
 					
 				} catch (SQLException sqle) {
 					sqle.printStackTrace();
 				} finally {
 					db.close();
 				}
 			} catch (Exception e) {
 				e.printStackTrace();
 			}
 			
 			List<BillCreditApplicationDatas> billCredits = getBillCreditApplicationData();
 			
 			
 			try {
 	 			DBManager db = new DBManager();
 				try {
 					for(BillCreditApplicationDatas rst:billCredits)
 					{
	 					if(rst.getDepartment_code().equals(String.valueOf(cmbDeptCode.getSelectedItem())))
	 					{
 							if(Long.parseLong(rst.getBank_code()) < 0100000)
	 						{     
	 							String BankName = rst.getFinancial_institution_name();
	 							rst.setFinancial_institution_name(BankName);
	 						}
	 						 						
	 			            rst.setDrawer_date(dateFormatter.format(DrawerDate));
	 			            rst.setMaturity_date(dateFormatter.format(MaturityDate)); 
	 			            rst.setBill_number(Format(TNo, "0000"));
	 			            
	 			            //rst.setAmount_of_money((float) Math.floor(rst.getSuccessful_bid_price()+0.5)  * 100000000);
	 			            double bid_price = rst.getSuccessful_bid_price();
	 			            rst.setAmount_of_money(bid_price  * 100000000);
	 			           	 			            
	 			            long duration  = MaturityDate.getTime() - DrawerDate.getTime();
	 			            long daysbetween =  (duration / (24 *60 * 60 * 1000));
	 			            rst.setNumber_of_days(daysbetween);
	 			            rst.setInterest_rate(rst.getSuccessful_bid_interest_rate());
	 			            
	 			            float interest = 0;
	 			            BigDecimal bg = new BigDecimal(rst.getSuccessful_bid_interest_rate());
	 						BigDecimal bg1;
	 						bg1= bg.setScale(3, RoundingMode.FLOOR);
	 						double successful_bid_rate = Double.parseDouble(bg1.toString());
	 						rst.setSuccessful_bid_interest_rate(successful_bid_rate);
	 			            
	 			            if(Like(rst.getSuccessful_bid_price() * 100000000 * (rst.getSuccessful_bid_interest_rate()/100) * daysbetween / 365,"*.*"))
	 			            {	
	 			        		interest = Int((((rst.getSuccessful_bid_price() * 100000000) * (rst.getSuccessful_bid_interest_rate()/100)) * daysbetween) / 365);
	 			            } else {
	 			        		interest = (float)(((rst.getSuccessful_bid_price() * 100000000) * (rst.getSuccessful_bid_interest_rate()/100)) * daysbetween / 365);
	 			            }
	 			            
	 			            rst.setInterest_amount(interest);
	 			            
	 			            boolean fo = db.doQuery("UPDATE bill_credit_application_datas SET " +
	 			            						"department_code='"+rst.getDepartment_code()+
	 			            						"',bank_code='"+rst.getBank_code()+
	 			            						"',financial_institution_name='"+rst.getFinancial_institution_name()+
	 			            						"',successful_bid_interest_rate="+rst.getSuccessful_bid_interest_rate()+
	 			            						",successful_bid_price="+rst.getSuccessful_bid_price()+
	 			            						",bill_number='"+rst.getBill_number()+
	 			            						"',drawer_date='"+rst.getDrawer_date()+
	 			            						"',maturity_date='"+rst.getMaturity_date()+
	 			            						"',number_of_days="+rst.getNumber_of_days()+
	 			            						",interest_rate="+rst.getInterest_rate()+
	 			            						",amount_of_money="+rst.getAmount_of_money()+
	 			            						",interest_amount="+rst.getInterest_amount()+
	 			            						",remarks='"+rst.getRemarks()+
	 			            						"',auction_date='"+rst.getAuction_date()+
	 			            						"' WHERE department_code='"+rst.getDepartment_code()+"' AND bank_code='"+
	 			            						rst.getBank_code()+"' AND auction_date='"+rst.getAuction_date()+"'");
				           TNo = TNo + 1;
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
 			
 			JOptionPane.showMessageDialog(null, "手形申込書データ作成が完了しました");
		}
 		else if(SHORI == 2)
 		{
 			
 			Date IntermediateInterestPaymentDate =(Date) txtIntermidiateInterestPayment.getModel().getValue();
 			//Set qdf = dbs.QueryDefs("ReportBillCreditApplicationData2")
	        if(null == IntermediateInterestPaymentDate){
	        	try {
	    			DBManager db = new DBManager();
	    			DBManager dbC = new DBManager();
	    			try {
	    				boolean fo = db.doQuery("DROP VIEW IF EXISTS 'bill_credit_application_datas2'");	
	    			} catch (SQLException sqle) {
	    				sqle.printStackTrace();
	    			} finally {
	    				db.close();
	    			}
	    			
	    			try{
	    				StringBuffer strSql = new StringBuffer("CREATE VIEW bill_credit_application_datas2 AS "+
	    													   "SELECT DISTINCT bill_credit_application_datas.department_code,divisions.department_name,bill_credit_application_datas.bank_code, "+
	    													   "bill_credit_application_datas.financial_institution_name,bill_credit_application_datas.successful_bid_interest_rate, "+
	    													   "bill_credit_application_datas.successful_bid_price,bill_credit_application_datas.bill_number,bill_credit_application_datas.drawer_date, "+
	    													   "bill_credit_application_datas.maturity_date,bill_credit_application_datas.number_of_days,bill_credit_application_datas.interest_rate, "+
	    													   "bill_credit_application_datas.amount_of_money,bill_credit_application_datas.interest_amount,bill_credit_application_datas.remarks, "+
	    													   "accounts.account_number FROM bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code "+
	    													   "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "+ 
	    													   "WHERE bill_credit_application_datas.department_code = '"+String.valueOf(cmbDeptCode.getSelectedItem())+
	    													   "' AND bill_credit_application_datas.auction_date = '"+bidDate+"'");
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
	        } else {
	        	try {
	    			DBManager db = new DBManager();
	    			DBManager dbC = new DBManager();
	    			try {
	    				boolean fo = db.doQuery("DROP VIEW IF EXISTS 'bill_credit_application_datas2'");	
	    			} catch (SQLException sqle) {
	    				sqle.printStackTrace();
	    			} finally {
	    				db.close();
	    			}
	    			
	    			try{
	    				SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MM-dd"); 
	    				Date DrawerDate = (Date) txtBorrowingDate.getModel().getValue();
	    				StringBuffer strSql = new StringBuffer("CREATE VIEW bill_credit_application_datas2 AS SELECT DISTINCT bill_credit_application_datas.department_code, "+
	    													   "divisions.department_name,bill_credit_application_datas.bank_code,bill_credit_application_datas.financial_institution_name, "+
	    													   "bill_credit_application_datas.successful_bid_interest_rate,bill_credit_application_datas.successful_bid_price,bill_credit_application_datas.bill_number, "+
	    													   "bill_credit_application_datas.drawer_date,bill_credit_application_datas.maturity_date,bill_credit_application_datas.number_of_days, "+
	    													   "bill_credit_application_datas.interest_rate,bill_credit_application_datas.amount_of_money,bill_credit_application_datas.interest_amount, "+
	    													   "bill_credit_application_datas.remarks,accounts.account_number, " +
	    													   "'"+dateFormatter.format(IntermediateInterestPaymentDate)+"' AS IntermediateInterestPaymentDate, "+
	    													   "((julianday('"+dateFormatter1.format(IntermediateInterestPaymentDate)+"') - julianday('"+dateFormatter1.format(DrawerDate)+"') / bill_credit_application_datas.number_of_days) * bill_credit_application_datas.interest_amount) AS InterestPaymentDateInterest,"+
	    													   "(julianday('"+dateFormatter1.format(IntermediateInterestPaymentDate)+"') - julianday('"+dateFormatter1.format(DrawerDate)+"')) AS InterestPaymentDay" +
	    													   " FROM bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code "+
	    													   "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "+ 
	    													   "WHERE bill_credit_application_datas.department_code = '"+String.valueOf(cmbDeptCode.getSelectedItem())+
	    													   "' AND bill_credit_application_datas.auction_date = '"+bidDate+"'");
	    				
	    				System.out.println(strSql);
	    				boolean fo1= dbC.doQuery(strSql.toString());
	    				
	    			} catch (SQLException sqle) {
	    				sqle.printStackTrace();
	    			} finally {
	    				dbC.close();
	    			}
	    		} catch (Exception e) {
	    			e.printStackTrace();
	    		}
			}	
            if(null == IntermediateInterestPaymentDate){
            	//OPEN REPORT  CreditApplicationPrint
		    	JasperReportViewer nw = JasperReportViewer.getInstance("CreditApplicationPrint",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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

				//dispose();   	

			} else {
				//OPEN REPORT BillCreditApplicationIntermediateInterestPayments
		    	JasperReportViewer nw = JasperReportViewer.getInstance("BillCreditApplicationIntermediateInterestPayments",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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

				//dispose(); 
              
			}
 		}
 		
 		else if(SHORI==3) {
	        try {
    			DBManager db = new DBManager();
    			DBManager dbC = new DBManager();
    			try {
    				boolean fo = db.doQuery("DROP VIEW IF EXISTS 'bill_credit_application_datas2'");	
    			} catch (SQLException sqle) {
    				sqle.printStackTrace();
    			} finally {
    				db.close();
    			}
    			
    			try{
    				StringBuffer strSql = new StringBuffer("CREATE VIEW bill_credit_application_datas2 AS "+
    													   "SELECT DISTINCT bill_credit_application_datas.department_code,"+ 
    													   "divisions.department_name,bill_credit_application_datas.bank_code,"+ 
    													   "bill_credit_application_datas.financial_institution_name,"+ 
    													   "bill_credit_application_datas.successful_bid_interest_rate,"+ 
    													   "bill_credit_application_datas.successful_bid_price,"+ 
    													   "bill_credit_application_datas.bill_number,"+ 
    													   "bill_credit_application_datas.drawer_date,"+ 
    													   "bill_credit_application_datas.maturity_date,"+ 
    													   "bill_credit_application_datas.number_of_days,"+ 
    													   "bill_credit_application_datas.interest_rate,"+ 
    													   "bill_credit_application_datas.amount_of_money,"+ 
    													   "bill_credit_application_datas.interest_amount,"+ 
    													   "bill_credit_application_datas.remarks,"+ 
    													   "accounts.account_number FROM (bill_credit_application_datas "+
    													   "LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "+ 
    													   "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "+ 
    													   "WHERE bill_credit_application_datas.department_code = '"+String.valueOf(cmbDeptCode.getSelectedItem())+"' AND bill_credit_application_datas.auction_date = '"+bidDate+"'");
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
	        
	        
	        if(cmbBillRearrangement.getSelectedItem().equals("<html>利率・手<br>形番号</br>")){
	            if(!txtProcessing.getText().equals("債権")){
	            	//OPEN REPORT BillApplicationFormList3
			    	JasperReportViewer nw = JasperReportViewer.getInstance("BillApplicationFormList3",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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

					//dispose(); 
	            	
	            } else{
	            	//OPEN REPORT CreditorManagementBook3
			    	JasperReportViewer nw = JasperReportViewer.getInstance("CreditorManagementBook3",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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

					//dispose(); 
		           
	            }
	        } else if(cmbBillRearrangement.getSelectedItem().equals("金融機関")){
	        	if(!txtProcessing.getText().equals("債権")){
	        		//OPEN REPORT BillApplicationFormList
			    	JasperReportViewer nw = JasperReportViewer.getInstance("BillApplicationFormList",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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

					//dispose(); 
		            
	            } else{
	            	//OPEN REPORT CreditorManagementBook
			    	JasperReportViewer nw = JasperReportViewer.getInstance("CreditorManagementBook",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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
		            		           
	            }
	        } else{
	        	if(!txtProcessing.getText().equals("債権")){
	        		//OPEN REPORT BillApplicationFormList2
			    	JasperReportViewer nw = JasperReportViewer.getInstance("BillApplicationFormList2",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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
		            
	            } else{
	            	//OPEN REPORT CreditorManagementBook2
			    	JasperReportViewer nw = JasperReportViewer.getInstance("CreditorManagementBook2",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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
	            }
	        }    
 		}
 		else if(SHORI==4)
 		{
 			try {
    			DBManager db = new DBManager();
    			DBManager dbC = new DBManager();
    			try {
    				boolean fo = db.doQuery("DROP VIEW IF EXISTS 'bill_credit_application_datas2'");	
    			} catch (SQLException sqle) {
    				sqle.printStackTrace();
    			} finally {
    				db.close();
    			}
    			
    			try{
    				StringBuffer strSql = new StringBuffer("CREATE VIEW bill_credit_application_datas2 AS "+
    													   "SELECT DISTINCT bill_credit_application_datas.department_code,divisions.department_name,bill_credit_application_datas.bank_code, "+
    													   "bill_credit_application_datas.financial_institution_name,bill_credit_application_datas.successful_bid_interest_rate, "+
    													   "bill_credit_application_datas.successful_bid_price,bill_credit_application_datas.bill_number,bill_credit_application_datas.drawer_date, "+
    													   "bill_credit_application_datas.maturity_date,bill_credit_application_datas.number_of_days,bill_credit_application_datas.interest_rate, "+
    													   "bill_credit_application_datas.amount_of_money,bill_credit_application_datas.interest_amount,bill_credit_application_datas.remarks, "+
    													   "accounts.account_number FROM bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code "+
    													   "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "+ 
    													   "WHERE bill_credit_application_datas.department_code = '"+String.valueOf(cmbDeptCode.getSelectedItem())+
    													   "' AND bill_credit_application_datas.auction_date = '"+bidDate+"'");
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
 			
 			if(String.valueOf(cmbRearrangement.getSelectedItem()).equals("金融機関"))
 			{
 				//OPEN REPORT ApprovalApplicationFormAttachment
		    	JasperReportViewer nw = JasperReportViewer.getInstance("ApprovalApplicationFormAttachment",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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
 			} else if(String.valueOf(cmbRearrangement.getSelectedItem()).equals("借入金額"))
 			{
 				//OPEN REPORT ApprovalApplicationFormAttachment2
		    	JasperReportViewer nw = JasperReportViewer.getInstance("ApprovalApplicationFormAttachment2",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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
 			} else if(String.valueOf(cmbRearrangement.getSelectedItem()).equals("利率"))
 			{
 				//OPEN REPORT ApprovalApplicationFormAttachment3
		    	JasperReportViewer nw = JasperReportViewer.getInstance("ApprovalApplicationFormAttachment3",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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
 			}
 		}
 		else if(SHORI==5){
 		        /*Set qdf = dbs.QueryDefs("ReportBillCreditApplicationData2")
 		        qdf.SQL = "SELECT DISTINCTROW ReportBillCreditApplicationData.DepartmentCode, DepartmentTable.DepartmentName, ReportBillCreditApplicationData.BankCode, ReportBillCreditApplicationData.BankName, ReportBillCreditApplicationData.SuccessfulInterestRate, ReportBillCreditApplicationData.SuccessfulBidPrice, ReportBillCreditApplicationData.BillNumber, ReportBillCreditApplicationData.DrawerDate, ReportBillCreditApplicationData.MaturityDate, ReportBillCreditApplicationData.Day数, ReportBillCreditApplicationData.InterestRate, ReportBillCreditApplicationData.Price, ReportBillCreditApplicationData.InterestAmount, ReportBillCreditApplicationData.備考, Accounts.AccountNumber FROM (ReportBillCreditApplicationData LEFT JOIN DepartmentTable ON ReportBillCreditApplicationData.DepartmentCode = DepartmentTable.DepartmentCode) LEFT JOIN Accounts ON ReportBillCreditApplicationData.DepartmentCode = Accounts.DepartmentCode WHERE ReportBillCreditApplicationData.DepartmentCode='" & F!bumoncode & "' and BidDate=#" & Format(F2!nyusatsubi, "yyyy/mm/dd") & "#;"
 		        qdf.Close
 		        DoCmd.OpenReport "ReportBillCreditApplication2", acDesign
 		        Reports!ReportBillCreditApplication2!BorrowingDateOfApplication.Caption = Format(F!BorrowingDateOfApplication, "GGGEYearMMonthDDay")
 		        DoCmd.Save acReport, "ReportBillCreditApplication2"
 		        DoCmd.Close acReport, "ReportBillCreditApplication2"
 		        DoCmd.OpenReport "ReportBillCreditApplication2", acPreview*/
 		}
 	}
 	
 	
 	public void TransferSlip()
 	{
 		try{
	 		DBManager db = new DBManager();
			try {
				db.doQuery("delete  from journal_search_results");	
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
 		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 		try{
	 		DBManager db = new DBManager();
			try {
				db.doQuery("delete from transfer_slip_reports");	
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} finally {
				db.close();
			}
 		} catch (Exception e) {
			e.printStackTrace();
		}
 		
 		StringBuffer strSql = new StringBuffer("INSERT INTO transfer_slip_reports(department_code, department_name, journal_number, slip_number, recorded_year, recorded_month, recorded_date) "+
				   "SELECT DISTINCT bill_credit_application_datas.department_code, divisions.department_name, 1 AS journal_number, 1 AS slip_number, "+ 
				   "substr(drawer_date,7) AS Year, substr(drawer_date,4,2) AS Month, substr(drawer_date,1,2) AS Day FROM "+ 
				   "(bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "+ 
				   "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "+ 
				   "GROUP BY bill_credit_application_datas.department_code, divisions.department_name, 1, 1, substr(drawer_date,7), substr(drawer_date,4,2), substr(drawer_date,1,2), "+ 
				   "bill_credit_application_datas.auction_date HAVING bill_credit_application_datas.auction_date='"+bidDate+"'");
 		
 		addData(strSql);
 		
 		StringBuffer strJournal = new StringBuffer("INSERT INTO journal_search_results(department_code, department_name, journal_number, slip_number, row_number, abstract_name, recorded_year, recorded_month, "+ 
 					"recorded_date, debit_amount, credit_amount, debit_item_code, debit_item_name, credit_course_code, credit_course_name ) "+ 
 					"SELECT DISTINCT bill_credit_application_datas.department_code, divisions.department_name, 1 AS journal_number, 1 AS slip_number, bill_credit_application_datas.bill_number, "+ 
 					"bill_credit_application_datas.financial_institution_name, substr(drawer_date,7) AS Year, substr(drawer_date,4,2) AS Month, substr(drawer_date,1,2) AS Day, "+ 
 					"bill_credit_application_datas.amount_of_money AS DebitAmount, bill_credit_application_datas.amount_of_money AS CreditAmount, '1110' AS DebitItemCode, '普通預金' AS DebitItemName, '2110' AS CreditCourseCode, "+ 
 					"'借入金' AS CreditCourseName FROM (bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "+ 
 					"LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "+ 
 					"WHERE bill_credit_application_datas.auction_date='"+bidDate+"' ORDER BY bill_credit_application_datas.bill_number");
 		
 		addData(strJournal);
 		
 		StringBuffer strSql1 = new StringBuffer("INSERT INTO transfer_slip_reports(department_code, department_name, journal_number, slip_number, recorded_year, recorded_month, recorded_date) "+ 
				    "SELECT DISTINCT bill_credit_application_datas.department_code, divisions.department_name, 2 AS journal_number, 2 AS slip_number, "+ 
				    "substr(maturity_date,7) AS Year, substr(maturity_date,4,2) AS Month, substr(maturity_date,1,2) AS Day FROM "+ 
				    "(bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "+ 
				    "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "+ 
				    "GROUP BY bill_credit_application_datas.department_code, divisions.department_name, 1, 1, substr(maturity_date,7), substr(maturity_date,4,2), substr(maturity_date,1,2), "+ 
				    "bill_credit_application_datas.auction_date HAVING bill_credit_application_datas.auction_date='"+bidDate+"'");
 		addData(strSql1);
 		
 		StringBuffer strJournal1 = new StringBuffer("INSERT INTO journal_search_results(department_code, department_name, journal_number, slip_number, row_number, abstract_name, recorded_year, recorded_month, "+ 
 					"recorded_date, debit_amount, credit_amount, credit_course_code, credit_course_name, debit_item_code, debit_item_name ) "+ 
 					"SELECT DISTINCT bill_credit_application_datas.department_code, divisions.department_name, 2 AS journal_number, 2 AS slip_number, bill_credit_application_datas.bill_number, "+ 
 					"bill_credit_application_datas.financial_institution_name, substr(maturity_date,7) AS Year, substr(maturity_date,4,2) AS Month, substr(maturity_date,1,2) AS Day, "+ 
 					"bill_credit_application_datas.amount_of_money AS DebitAmount, bill_credit_application_datas.amount_of_money AS CreditAmount, '1110' AS DebitItemCode, '普通預金' AS DebitItemName, '2110' AS CreditCourseCode, "+ 
 					"'借入金' AS CreditCourseName FROM (bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "+ 
 					"LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "+ 
 					"WHERE bill_credit_application_datas.auction_date='"+bidDate+"' ORDER BY bill_credit_application_datas.bill_number");
		
		addData(strJournal1);
 		
 		
 		StringBuffer strSql2 = new StringBuffer("INSERT INTO transfer_slip_reports(department_code, department_name, journal_number, slip_number, recorded_year, recorded_month, recorded_date) "+ 
				    "SELECT DISTINCT bill_credit_application_datas.department_code, divisions.department_name, 3 AS journal_number, 3 AS slip_number, "+ 
				    "substr(maturity_date,7) AS Year, substr(maturity_date,4,2) AS Month, substr(maturity_date,1,2) AS Day FROM "+ 
				    "(bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "+ 
				    "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "+ 
				    "GROUP BY bill_credit_application_datas.department_code, divisions.department_name, 1, 1, substr(maturity_date,7), substr(maturity_date,4,2), substr(maturity_date,1,2), "+ 
				    "bill_credit_application_datas.auction_date HAVING bill_credit_application_datas.auction_date='"+bidDate+"'");
 		addData(strSql2);
 		
 		StringBuffer strJournal2 = new StringBuffer("INSERT INTO journal_search_results(department_code, department_name, journal_number, slip_number, row_number, abstract_name, recorded_year, recorded_month, "+ 
 				    "recorded_date, debit_amount, credit_amount, credit_course_code, credit_course_name, debit_item_code, debit_item_name ) "+ 
 				    "SELECT DISTINCT bill_credit_application_datas.department_code, divisions.department_name, 3 AS journal_number, 3 AS slip_number, bill_credit_application_datas.bill_number, "+ 
 				    "bill_credit_application_datas.financial_institution_name, substr(maturity_date,7) AS Year, substr(maturity_date,4,2) AS Month, substr(maturity_date,1,2) AS Day, "+ 
 				    "bill_credit_application_datas.interest_amount AS DebitAmount, bill_credit_application_datas.interest_amount AS CreditAmount, '1110' AS DebitItemCode, '普通預金' AS DebitItemName, '5110' AS CreditCourseCode, "+ 
 				    "'支払利息' AS CreditCourseName FROM (bill_credit_application_datas LEFT JOIN divisions ON bill_credit_application_datas.department_code = divisions.department_code) "+ 
 				    "LEFT JOIN accounts ON bill_credit_application_datas.department_code = accounts.department_code "+ 
 					"WHERE bill_credit_application_datas.auction_date='"+bidDate+"' ORDER BY bill_credit_application_datas.bill_number");
	
 		addData(strJournal2);
 		
 		//OPEN REPORT ApprovalApplicationFormAttachment3
    	JasperReportViewer nw = JasperReportViewer.getInstance("TransferSlip",bidDate,String.valueOf(cmbDeptCode.getSelectedItem()));
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
 	}
 	
 	public boolean addData(StringBuffer strSql)
 	{
 		boolean Fo = false;
 		try{
 			DBManager db = new DBManager();
 			try{
				Fo = db.doQuery(strSql.toString());
				
				//System.out.println(strSql);
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


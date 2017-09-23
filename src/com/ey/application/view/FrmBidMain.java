package com.ey.application.view;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.util.Date;
import java.util.Properties;

import java.awt.event.WindowFocusListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.InternalFrameEvent;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import com.ey.application.model.CommonProperties;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* frmBidMain.java
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

public class FrmBidMain extends JInternalFrame {
	JPanel pnlMain = new JPanel();
	JLabel lblAuctionDate = new JLabel();
	JLabel lblExcelOutput = new JLabel();
	
	//JTextField txtAuctionDate = new JTextField();
	
	JButton cmdExcelOutput = new JButton();
	JButton cmdBidDataInput = new JButton();
	JButton cmdFinancialInstitutions = new JButton();
	JButton cmdInterestRateOrder = new JButton();
	JButton cmdSuccessfulBidDataCreation = new JButton();
	JButton cmdSuccessfulBidDataMaintenance = new JButton();
	JButton cmdSuccessfulBidDocumentPrinting = new JButton();
	JButton cmdCreatingCreditApplication = new JButton();
	JButton cmdEnd = new JButton();
	UtilDateModel model;
	JDatePanelImpl datePanel;
	JDatePickerImpl txtAuctionDate;
	
	JFileChooser chooser;
	String choosertitle;
	public FrmBidMain(String date) {
		try {
			jbInit(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(String date) throws Exception {
		this.setBounds(0, 0, 579, 412);
		this.getContentPane().setBackground(new Color(176,224,230));
		this.setForeground(Color.black);
		this.setResizable(false);
		this.setTitle("メイン");
		this.getContentPane().setLayout(null);

		this.setIconifiable(true);
		this.setMaximizable(false);
		this.setClosable(true);
		
		this.getContentPane().add(pnlMain, null);
		this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
	      public void internalFrameClosing(InternalFrameEvent e) {
	    	  Close();
	      }
	    });

		pnlMain.setBackground(new Color(176,224,230));
		pnlMain.setBorder(BorderFactory.createEtchedBorder());
		pnlMain.setBounds(new Rectangle(9, 9, 555, 402));
		pnlMain.setLayout(null);
		
		model = new UtilDateModel();
		if(!date.equals(""))
		{
			String dd[] = date.split("-");
			int day = Integer.parseInt(dd[0]);
			int mon = Integer.parseInt(dd[1]);
			int year = Integer.parseInt(dd[2]);
			//if(mon)
				mon = mon-1;
			model.setDay(day);
			model.setMonth(mon);
			model.setYear(year);
			model.setSelected(true); 
		}
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		datePanel = new JDatePanelImpl(model, p);
		txtAuctionDate = new JDatePickerImpl(datePanel, new DateLabelFormatter());
		
		
		txtAuctionDate.setBounds(new Rectangle(207, 33, 175, 27));
		pnlMain.add(txtAuctionDate);

		lblAuctionDate.setForeground(Color.black);
		lblAuctionDate.setBorder(BorderFactory.createEtchedBorder());
		lblAuctionDate.setText("入札日");
		lblAuctionDate.setBounds(new Rectangle(87, 33, 110, 27));
				
			
		lblExcelOutput.setForeground(Color.black);
		lblExcelOutput.setBorder(BorderFactory.createEtchedBorder());
		lblExcelOutput.setText("Excel出力");
		lblExcelOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblExcelOutput.setBounds(new Rectangle(407, 13, 90, 23));
		
		CommonProperties props = new CommonProperties();
		String workingDir = props.getWorkingDir();
		String imageDir = props.getImageDir();
		
		cmdExcelOutput.setIcon(new ImageIcon(imageDir+"Excel.png"));
		cmdExcelOutput.setToolTipText("Excel出力");
		cmdExcelOutput.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdExcelOutput.setBounds(new Rectangle(430, 40, 38, 38));
		cmdExcelOutput.setVisible(true);
		//cmdExcelOutput.setText("Excel出力");
		cmdExcelOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chooser = new JFileChooser(); 
			    chooser.setCurrentDirectory(new java.io.File("."));
			    chooser.setDialogTitle(choosertitle);
			    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    //
			    // disable the "All files" option.
			    //
			    chooser.setAcceptAllFileFilterUsed(false);
			    //    
			    if (chooser.showOpenDialog(pnlMain) == JFileChooser.APPROVE_OPTION) { 
			      /*System.out.println("getCurrentDirectory(): " 
			         +  chooser.getCurrentDirectory());
			      System.out.println("getSelectedFile() : " 
			         +  chooser.getSelectedFile());*/
			      Excelsheet writesheet = new Excelsheet();
			      
			      writesheet.CreateBidDataExcel(chooser.getSelectedFile().getPath());
			      
			      writesheet.BillCreditApplicationDataExcel(chooser.getSelectedFile().getPath());
			      
			      writesheet.SuccessfullBidDdatas3DataExcel(chooser.getSelectedFile().getPath());
			      
			      JOptionPane.showMessageDialog(null, "Excelファイルが正常に書きます");
			    }
			    else {
			      //System.out.println("No Selection ");
			      }
			}
		});
		
		cmdBidDataInput.setToolTipText("入札データ入力・修正・削除");
		cmdBidDataInput.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdBidDataInput.setBounds(new Rectangle(32, 113, 220, 28));
		cmdBidDataInput.setVisible(true);
		cmdBidDataInput.setText("入札データ入力・修正・削除");
		cmdBidDataInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = (Date) txtAuctionDate.getModel().getValue();
				if(null != selectedDate)
				{
					FrmBidData nw = FrmBidData.getInstance(selectedDate);
					nw.setBounds(0, 0, 489, 462);
					nw.getContentPane().setBackground(new Color(176,224,230));
					nw.setForeground(Color.black);
					nw.setResizable(false);
					nw.setTitle("入札データ入力");
					nw.getContentPane().setLayout(null);
					
					if (nw.isVisible()) {
					} else {
						getDesktopPane().add(nw);
						nw.setVisible(true);
					}
	
					nw.setIconifiable(true);
					nw.setMaximizable(false);
					nw.setClosable(true);
					dispose();
				} else{
					JOptionPane.showMessageDialog(null, "入札日を挿入してください。");
				}
			}
		});
		
		cmdInterestRateOrder.setToolTipText("利率順入札状況表");
		cmdInterestRateOrder.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdInterestRateOrder.setBounds(new Rectangle(32, 171, 220, 28));
		cmdInterestRateOrder.setVisible(true);
		cmdInterestRateOrder.setText("利率順入札状況表");
		cmdInterestRateOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = (Date) txtAuctionDate.getModel().getValue();
				if(null != selectedDate)
				{
					FrmInterestRateOrderBidStatus nw = FrmInterestRateOrderBidStatus.getInstance(selectedDate);
					nw.setBounds(0, 0, 300, 250);
					nw.getContentPane().setBackground(new Color(176,224,230));
					nw.setForeground(Color.black);
					nw.setResizable(false);
					nw.setTitle("入札データ入力");
					nw.getContentPane().setLayout(null);
					
					if (nw.isVisible()) {
					} else {
						getDesktopPane().add(nw);
						nw.setVisible(true);
					}
	
					nw.setIconifiable(true);
					nw.setMaximizable(false);
					nw.setClosable(true);
					dispose();
				} else{
					JOptionPane.showMessageDialog(null, "入札日を挿入してください。");
				}
			}
		});
		
		cmdSuccessfulBidDataMaintenance.setToolTipText("落札データメンテ");
		cmdSuccessfulBidDataMaintenance.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdSuccessfulBidDataMaintenance.setBounds(new Rectangle(32, 229, 220, 28));
		cmdSuccessfulBidDataMaintenance.setVisible(true);
		cmdSuccessfulBidDataMaintenance.setText("落札データメンテ");
		cmdSuccessfulBidDataMaintenance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = (Date) txtAuctionDate.getModel().getValue();
				if(null != selectedDate)
				{
					FrmBidDataMaintenance nw = FrmBidDataMaintenance.getInstance(selectedDate);
					nw.setBounds(0, 0, 789, 562);
					nw.getContentPane().setBackground(new Color(176,224,230));
					nw.setForeground(Color.black);
					nw.setResizable(false);
					nw.setTitle("落札データメンテ用");
					nw.getContentPane().setLayout(null);
					
					if (nw.isVisible()) {
					} else {
						getDesktopPane().add(nw);
						nw.setVisible(true);
					}
	
					nw.setIconifiable(true);
					nw.setMaximizable(false);
					nw.setClosable(true);
					dispose();
				} else{
					JOptionPane.showMessageDialog(null, "入札日を挿入してください。");
				}
			}
		});
		
		
		cmdCreatingCreditApplication.setToolTipText("借入申込書作成");
		cmdCreatingCreditApplication.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdCreatingCreditApplication.setBounds(new Rectangle(32, 287, 220, 28));
		cmdCreatingCreditApplication.setVisible(true);
		cmdCreatingCreditApplication.setText("借入申込書作成");
		cmdCreatingCreditApplication.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = (Date) txtAuctionDate.getModel().getValue();
				if(null != selectedDate)
				{
					FrmNotesApplicationFormPrint nw = FrmNotesApplicationFormPrint.getInstance(selectedDate);
					nw.setBounds(0, 0, 500, 550);
					nw.getContentPane().setBackground(new Color(176,224,230));
					nw.setForeground(Color.black);
					nw.setResizable(false);
					nw.setTitle("借入申込");
					nw.getContentPane().setLayout(null);
					
					if (nw.isVisible()) {
					} else {
						getDesktopPane().add(nw);
						nw.setVisible(true);
					}
	
					nw.setIconifiable(true);
					nw.setMaximizable(false);
					nw.setClosable(true);
					dispose();
				}else{
					JOptionPane.showMessageDialog(null, "入札日を挿入してください。");
				}
			}
		});
		
		
		
		cmdFinancialInstitutions.setToolTipText("金融機関別入札状況表");
		cmdFinancialInstitutions.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdFinancialInstitutions.setBounds(new Rectangle(282, 113, 220, 28));
		cmdFinancialInstitutions.setVisible(true);
		cmdFinancialInstitutions.setText("金融機関別入札状況表");
		cmdFinancialInstitutions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = (Date) txtAuctionDate.getModel().getValue();
				if(null != selectedDate)
				{
					JasperReportViewer nw = JasperReportViewer.getInstance("Financialinstitute",selectedDate);
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
				} else{
					JOptionPane.showMessageDialog(null, "入札日を挿入してください。");
				}
			}
		});
		
	
		cmdSuccessfulBidDataCreation.setToolTipText("落札データ作成");
		cmdSuccessfulBidDataCreation.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdSuccessfulBidDataCreation.setBounds(new Rectangle(282, 171, 220, 28));
		cmdSuccessfulBidDataCreation.setVisible(true);
		cmdSuccessfulBidDataCreation.setText("落札データ作成");
		cmdSuccessfulBidDataCreation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = (Date) txtAuctionDate.getModel().getValue();
				if(null != selectedDate)
				{
					FrmSuccessfulBidDataCreation nw = FrmSuccessfulBidDataCreation.getInstance(selectedDate);
					nw.setBounds(0, 0, 430, 320);
					nw.getContentPane().setBackground(new Color(176,224,230));
					nw.setForeground(Color.black);
					nw.setResizable(false);
					nw.setTitle("落札データ作成");
					nw.getContentPane().setLayout(null);
					
					if (nw.isVisible()) {
					} else {
						getDesktopPane().add(nw);
						nw.setVisible(true);
					}
	
					nw.setIconifiable(true);
					nw.setMaximizable(false);
					nw.setClosable(true);
					dispose();
				} else{
					JOptionPane.showMessageDialog(null, "入札日を挿入してください。");
				}
			}
		});
		
		
		cmdSuccessfulBidDocumentPrinting.setToolTipText("落  札  書  印  刷");
		cmdSuccessfulBidDocumentPrinting.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdSuccessfulBidDocumentPrinting.setBounds(new Rectangle(282, 229, 220, 28));
		cmdSuccessfulBidDocumentPrinting.setVisible(true);
		cmdSuccessfulBidDocumentPrinting.setText("落  札  書  印  刷");
		cmdSuccessfulBidDocumentPrinting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date selectedDate = (Date) txtAuctionDate.getModel().getValue();
				if(null != selectedDate)
				{
					FrmBidDocumentPrint nw = FrmBidDocumentPrint.getInstance(selectedDate);
					nw.setBounds(0, 0, 330, 320);
					nw.getContentPane().setBackground(new Color(176,224,230));
					nw.setForeground(Color.black);
					nw.setResizable(false);
					nw.setTitle("落札書印刷");
					nw.getContentPane().setLayout(null);
					
					if (nw.isVisible()) {
					} else {
						getDesktopPane().add(nw);
						nw.setVisible(true);
					}
	
					nw.setIconifiable(true);
					nw.setMaximizable(false);
					nw.setClosable(true);
					dispose();
				} else{
					JOptionPane.showMessageDialog(null, "入札日を挿入してください。");
				}
			}
		});
		
		
		cmdEnd.setToolTipText("終　　　　　　　　了");
		cmdEnd.setVerticalTextPosition(SwingConstants.BOTTOM);
		cmdEnd.setBounds(new Rectangle(282, 287, 220, 28));
		cmdEnd.setVisible(true);
		cmdEnd.setText("終　　　　　　　　了");
		cmdEnd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		pnlMain.add(lblAuctionDate, null);
		pnlMain.add(lblExcelOutput, null);
		pnlMain.add(cmdExcelOutput, null);
		pnlMain.add(txtAuctionDate, null);
		pnlMain.add(cmdBidDataInput, null);
		pnlMain.add(cmdInterestRateOrder, null);
		pnlMain.add(cmdSuccessfulBidDataMaintenance, null);
		pnlMain.add(cmdCreatingCreditApplication, null);
		
		pnlMain.add(cmdFinancialInstitutions, null);
		pnlMain.add(cmdSuccessfulBidDataCreation, null);
		pnlMain.add(cmdSuccessfulBidDocumentPrinting, null);
		pnlMain.add(cmdEnd, null);
	}
	
	private static FrmBidMain myInstance;

 	public static FrmBidMain getInstance(String date) {
 		
 	    if (myInstance == null) {
 	        myInstance = new FrmBidMain(date);
 	    }
 	    return myInstance;
 	}
 	
 	public void Close(){
 		//datePanel.show(false);
 		//datePanel.setVisible(false);
 		//datePanel.removeWindowFocusListener( this );
 		//datePanel.removeWindowFocusListener(this);
 		dispose();
 	}
 	
}


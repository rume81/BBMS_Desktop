package com.ey.application.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;

import com.ey.application.controller.DBManager;
import com.ey.application.model.CommonProperties;
import com.ey.application.model.FinancialInstitutions;
import com.ey.application.model.NoSpecification;
import com.ey.application.model.SuccessfulBidData3;
import com.ey.application.model.SuccessfulBidDocument;
import com.ey.application.model.SuccessfulBidSituations;
import com.ey.application.model.ThereSpecification;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;

/* ========================================
* BBMS v. 1.0 class library
* ========================================
*
* (C) Copyright 2016-2020, by EY.
*
* --------------------
* JasperReportViewer.java
* --------------------
* Created on Feb 23, 2016
*
* Revision: 
* Author: 
* Source: 
* Id:  
*
* Feb 23, 2016: Original version (Monsur)
*
*/
public class JasperReportViewer extends JInternalFrame {
	private static JasperReportViewer myInstance;
	
	public static JasperReportViewer getInstance(String name,Date date) {
		//if (myInstance == null) {
		String datePattern = "dd-MM-yyyy";
	    SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
		String bDate = dateFormatter.format(date);
		return myInstance = new JasperReportViewer(name,bDate,"");
		//}
		//return myInstance;
	}
	
	public static JasperReportViewer getInstance(String name,String date,String param) {
		//if (myInstance == null) {
		return myInstance = new JasperReportViewer(name,date,param);
		//}
		//return myInstance;
	}

	public JasperReportViewer(String name,String bidDate,String param) {
		this.setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
		this.addInternalFrameListener(new javax.swing.event.InternalFrameAdapter() {
		      public void internalFrameClosing(InternalFrameEvent e) {
		    	  Close(name,bidDate);
		      }
		    });
		String workingDir = System.getProperty("user.dir");
		String sourceFileName="";
		JasperReport jasperMasterReport = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
		Map parameters = null;
		JRBeanCollectionDataSource beanColDataSource = null;
		if(name.equals("Financialinstitute")){
			/*this.setTitle("金融機関別入札状況表");
			sourceFileName = workingDir+"\\rpt\\Financialinstitute.jasper";
			ReportBeanList ReportBeanList = new ReportBeanList();
			List<FinancialInstitutions> dataList = ReportBeanList.getFinancialInstitutionsBeanList(bidDate);
			beanColDataSource = new JRBeanCollectionDataSource(dataList);
			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));*/
			
			this.setTitle("金融機関別入札状況表");
			sourceFileName = workingDir+"\\rpt\\Financialinstitute.jrxml";
			System.out.println(sourceFileName);
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
			parameters.put("bidDate", bidDate);
			
		} else if(name.equals("ThereSpecification")){
			this.setTitle("入札状況表");
			sourceFileName = workingDir+"\\rpt\\ThereSpecification.jrxml";
			ReportBeanList ReportBeanList = new ReportBeanList();
			List<ThereSpecification> dataList = ReportBeanList.getThereSpecificationBeanList();
			int count_metter=0;
			Set<String> bank_code = new HashSet<String>();
			for(ThereSpecification sp:dataList){
				if((sp.getBid_number()==1) && (sp.getBid_amount_of_money()!=0))
					bank_code.add(sp.getBank_code());
			}
			count_metter=bank_code.size();
			beanColDataSource = new JRBeanCollectionDataSource(dataList);
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
			
			
			parameters = new HashMap();
			parameters.put("count_metter", count_metter);
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
		} else if(name.equals("NoSpecification")){
			this.setTitle("入札状況表");
			sourceFileName = workingDir+"\\rpt\\NoSpecification.jasper";
			ReportBeanList ReportBeanList = new ReportBeanList();
			List<NoSpecification> dataList = ReportBeanList.getNOSpecificationBeanList();
			beanColDataSource = new JRBeanCollectionDataSource(dataList);
			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
		} else if(name.equals("SuccessfulBidStatusTableByBank")){
			this.setTitle("落札状況一覧表");
			sourceFileName = workingDir+"\\rpt\\FinancialInstitutionBy.jasper";
			String params[] = param.split("#");
			String dept_code = params[0];
			String bidPlaned = params[1];
			ReportBeanList ReportBeanList = new ReportBeanList();
			List<SuccessfulBidData3> dataList = ReportBeanList.getSuccessfulBidStatusTableByBank(bidDate,dept_code);
			beanColDataSource = new JRBeanCollectionDataSource(dataList);
			int count_metter=0;
			Set<String> bank_code = new HashSet<String>();
			for(SuccessfulBidData3 sp:dataList){
				//if((sp.getBid_number()==1) && (sp.getBid_amount_of_money()!=0))
					bank_code.add(sp.getBank_code());
			}
			count_metter=bank_code.size();
			
			parameters = new HashMap();
			parameters.put("count_metter", count_metter);//need to change
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
			parameters.put("bidPlaned",bidPlaned);
		} else if(name.equals("InterestRateBy")){
			this.setTitle("落札状況表");
			sourceFileName = workingDir+"\\rpt\\InterestRateBy.jasper";
			String dept_code = param;
			ReportBeanList ReportBeanList = new ReportBeanList();
			List<SuccessfulBidSituations> dataList = ReportBeanList.getInterestRateBy(bidDate,dept_code);
			beanColDataSource = new JRBeanCollectionDataSource(dataList);
			
			int count_metter=0;
			Set<String> bank_code = new HashSet<String>();
			for(SuccessfulBidSituations sp:dataList){
				//if((sp.getBid_number()==1) && (sp.getBid_amount_of_money()!=0))
					bank_code.add(sp.getBank_code());
			}
			count_metter=bank_code.size();
			
			parameters = new HashMap();
			parameters.put("count_metter", count_metter);//need to change
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
		} else if(name.equals("SuccessfulBidDocument")){
			this.setTitle("落札書");
			sourceFileName = workingDir+"\\rpt\\SuccessfulBidDocument.jrxml";
			String subsourceFileName = workingDir+"\\rpt\\SuccessfulBidDocument_subreport.jrxml";
			String dept_code = param;
			
			
			JasperReport jasperSubReport = null;
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
				jasperSubReport = JasperCompileManager.compileReport(subsourceFileName);
				
			} catch (JRException e) {
				e.printStackTrace();
			}
			
			//ReportBeanList ReportBeanList = new ReportBeanList();
			//List<SuccessfulBidDocument> dataList = ReportBeanList.getSuccessfulBidDocument(bidDate,dept_code);
			//beanColDataSource = new JRBeanCollectionDataSource(dataList);
			
			
			parameters = new HashMap();
			parameters.put("SuccessfulBidDocument_subreport", jasperSubReport);
			parameters.put("SUBREPORT_DIR", workingDir+"\\rpt\\");
			parameters.put("dept_code", dept_code);
			parameters.put("auction_date", bidDate);
			parameters.put("auctionJp_date", ConvertDateToJP(bidDate));
			
			//parameters.put("count_metter", count_metter);//need to change
			//parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
		} else if(name.equals("ProvisionalNotice")){
			this.setTitle("落札仮通知書");
			sourceFileName = workingDir+"\\rpt\\ProvisionalNotice.jrxml";
			String subsourceFileName = workingDir+"\\rpt\\ProvisionalNotice_subreport.jrxml";
			String dept_code = param;
			String imagePath = workingDir+"\\IMAGE\\provition.png";
			
			JasperReport jasperSubReport = null;
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);
				jasperSubReport = JasperCompileManager.compileReport(subsourceFileName);
				
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//ReportBeanList ReportBeanList = new ReportBeanList();
			//List<SuccessfulBidDocument> dataList = ReportBeanList.getSuccessfulBidDocument(bidDate,dept_code);
			//beanColDataSource = new JRBeanCollectionDataSource(dataList);
			
			
			parameters = new HashMap();
			parameters.put("SuccessfulBidDocument_subreport", jasperSubReport);
			parameters.put("SUBREPORT_DIR", workingDir+"\\rpt\\");
			parameters.put("dept_code", dept_code);
			parameters.put("auction_date", bidDate);
			parameters.put("auctionJp_date", ConvertDateToJP(bidDate));
			parameters.put("imagePath",imagePath);
			
			//parameters.put("count_metter", count_metter);//need to change
			//parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
		} else if(name.equals("CreditApplicationPrint")){
			this.setTitle("手形借入申込書中間利払");
			sourceFileName = workingDir+"\\rpt\\CreditApplicationPrint.jrxml";
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
		} else if(name.equals("BillCreditApplicationIntermediateInterestPayments")){
			this.setTitle("手形借入申込書中間利払");
			sourceFileName = workingDir+"\\rpt\\BillCreditApplicationIntermediateInterestPayments.jrxml";
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
			
		} else if(name.equals("BillApplicationFormList3")){
			this.setTitle("手形借入一覧表");
			sourceFileName = workingDir+"\\rpt\\BillApplicationFormList3.jrxml";
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
			
		} else if(name.equals("CreditorManagementBook3")){
			this.setTitle("債権者管理簿");
			sourceFileName = workingDir+"\\rpt\\CreditorManagementBook3.jrxml";
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
			parameters.put("CurrentDate", ConvertCDateToJP());
			parameters.put("auctionJp_date", ConvertDateToJP(bidDate));
			
		} else if(name.equals("BillApplicationFormList")){
			this.setTitle("手形借入一覧表");
			sourceFileName = workingDir+"\\rpt\\BillApplicationFormList.jrxml";
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
		} else if(name.equals("BillApplicationFormList2")){
			this.setTitle("手形借入一覧表");
			sourceFileName = workingDir+"\\rpt\\BillApplicationFormList2.jrxml";
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
		} else if(name.equals("CreditorManagementBook")){
			this.setTitle("債権者管理簿");
			sourceFileName = workingDir+"\\rpt\\CreditorManagementBook.jrxml";
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
			parameters.put("CurrentDate", ConvertCDateToJP());
			parameters.put("auctionJp_date", ConvertDateToJP(bidDate));
		} else if(name.equals("CreditorManagementBook2")){
			this.setTitle("債権者管理簿");
			sourceFileName = workingDir+"\\rpt\\CreditorManagementBook2.jrxml";
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
			parameters.put("CurrentDate", ConvertCDateToJP());
			parameters.put("auctionJp_date", ConvertDateToJP(bidDate));
		} else if(name.equals("ApprovalApplicationFormAttachment")){
			this.setTitle("認可申請書別紙");
			sourceFileName = workingDir+"\\rpt\\ApprovalApplicationFormAttachment.jrxml";
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
		} else if(name.equals("ApprovalApplicationFormAttachment2")){
			this.setTitle("認可申請書別紙");
			sourceFileName = workingDir+"\\rpt\\ApprovalApplicationFormAttachment2.jrxml";
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
		} else if(name.equals("ApprovalApplicationFormAttachment3")){
			this.setTitle("認可申請書別紙");
			sourceFileName = workingDir+"\\rpt\\ApprovalApplicationFormAttachment3.jrxml";
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
			parameters.put("CurrentDateTime", String.valueOf(format.format(new Date())));
		} else if(name.equals("LoanAgreementDeed")){
			this.setTitle("金銭消費貸借契約証書");
			sourceFileName = workingDir+"\\rpt\\LoanAgreementDeed.jrxml";
			
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);				
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
		}  else if(name.equals("TransferSlip")){
			this.setTitle("伝票作成依頼書");
			sourceFileName = workingDir+"\\rpt\\TransferSlip.jrxml";
			String subsourceFileName = workingDir+"\\rpt\\TransferSlip_journal.jrxml";
			
			JasperReport jasperSubReport = null;
			try {
				jasperMasterReport = JasperCompileManager.compileReport(sourceFileName);	
				jasperSubReport = JasperCompileManager.compileReport(subsourceFileName);
			} catch (JRException e) {
				e.printStackTrace();
			}
						
			parameters = new HashMap();
			parameters.put("TransferSlip_journal", jasperSubReport);
			parameters.put("SUBREPORT_DIR", workingDir+"\\rpt\\");
		} 
		
		
		
		JasperPrint jasperPrint = null;
		if(name.equals("Financialinstitute") || name.equals("ThereSpecification") || name.equals("SuccessfulBidDocument") || name.equals("ProvisionalNotice") || name.equals("CreditApplicationPrint") || name.equals("BillCreditApplicationIntermediateInterestPayments")
				|| name.equals("BillApplicationFormList3") || name.equals("CreditorManagementBook3") || name.equals("BillApplicationFormList") || name.equals("BillApplicationFormList2")
				|| name.equals("CreditorManagementBook") || name.equals("CreditorManagementBook2") || name.equals("ApprovalApplicationFormAttachment") || name.equals("ApprovalApplicationFormAttachment2")
				|| name.equals("ApprovalApplicationFormAttachment3") || name.equals("LoanAgreementDeed") || name.equals("TransferSlip")){
			DBManager db;
			Connection conn = null;
			try {
				db = new DBManager();
				conn = db.getConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				jasperPrint = JasperFillManager.fillReport(jasperMasterReport, parameters, conn);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
		else{
		
			try {
				jasperPrint = JasperFillManager.fillReport(sourceFileName, parameters, beanColDataSource);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}

		JRViewer viewer = new JRViewer(jasperPrint);
		//viewer.clear();
		//viewer = null;
		Container c = this.getContentPane();
		c.removeAll();
		c.add(viewer);
		this.setBounds(1, 1, 800, 600);

	}
	
	public void Close(String name,String bidDate)
	{
		if(name.equals("Financialinstitute") || name.equals("FrmNotesApplicationFormPrint"))
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
				getDesktopPane().add(nw);
				nw.setVisible(true);
			}

			nw.setIconifiable(true);
			nw.setMaximizable(false);
			nw.setClosable(true);
		} else if(name.equals("ThereSpecification") || name.equals("NoSpecification"))
		{
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date=null;
			try {
				date = formatter.parse(bidDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			FrmInterestRateOrderBidStatus nw = FrmInterestRateOrderBidStatus.getInstance(date);
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
		}else if(name.equals("SuccessfulBidStatusTableByBank") || name.equals("InterestRateBy"))
		{
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date=null;
			try {
				date = formatter.parse(bidDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			FrmSuccessfulBidDataCreation nw = FrmSuccessfulBidDataCreation.getInstance(date);
			nw.setBounds(0, 0, 430, 320);
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
		}else if(name.equals("ProvisionalNotice") || name.equals("SuccessfulBidDocument"))
		{
			
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date=null;
			try {
				date = formatter.parse(bidDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			FrmBidDocumentPrint nw = FrmBidDocumentPrint.getInstance(date);
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
		}
		dispose();
	}
	public String ConvertDateToJP(String date)
	{
		String jpdate = "";
		String ar[] = date.split("-");
		if(ar[2]!=null)
		{
			long cyear = Long.parseLong(ar[2]);
			long i=cyear-1988;
			int mon = Integer.parseInt(ar[1]);
			int day = Integer.parseInt(ar[0]);
						
			jpdate = "平成"+i+"年"+mon+"月"+day+"日";	
		}
		return jpdate;
	}
	
	public String ConvertCDateToJP()
	{
		String datePattern = "dd-MM-yyyy";
	    SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
	    String date = dateFormatter.format(new Date());
		String jpdate = "";
		String ar[] = date.split("-");
		if(ar[2]!=null)
		{
			long cyear = Long.parseLong(ar[2]);
			long i=cyear-1988;
			int mon = Integer.parseInt(ar[1]);
			int day = Integer.parseInt(ar[0]);
			
			jpdate = i+"."+mon+"."+day;
		}
		return jpdate;
	}
	/*private void ReadReportParam()
	{
		// The name of the file to open.
		String workingDir = System.getProperty("user.dir");
		String sourceFileName = workingDir + "\\rpt\\param.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(sourceFileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                //System.out.println(line);
                String retval[] =  line.split("==");
                for (String retval: line.split("==")){
                    System.out.println(retval);
                    month 
                 }
                month = retval[0];
                year = retval[1];
                range = retval[2];
                //System.out.println(month);
                //System.out.println(year);
                
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                		sourceFileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + sourceFileName + "'");                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
	
	}*/
}


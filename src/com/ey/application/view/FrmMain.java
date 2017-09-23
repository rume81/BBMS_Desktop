package com.ey.application.view;

import java.awt.*;
import javax.swing.*;

import com.ey.application.controller.DBManager;
import com.ey.application.model.CommonProperties;
import com.ey.master.view.FrmMasterMaintenance;
import com.ey.master.view.FrmSystemSelection;

import java.awt.event.*;
import java.sql.*;

public class FrmMain extends JFrame {
	JMenuBar EYMenu = new JMenuBar();

	JMenu mnuFile = new JMenu();
	JMenu mnuHelp = new JMenu();

	JToolBar toolbar = new JToolBar();

	JLabel status = new JLabel();

	JButton cmdMain = new JButton();
	JButton cmdMaster = new JButton();
	JButton cmdSystemSelection = new JButton();

	JMenuItem mnMain = new JMenuItem();
	JMenuItem mnMaster = new JMenuItem();
	JMenuItem mnSystemSelection = new JMenuItem();
	JMenuItem mnExit = new JMenuItem();
	JMenuItem mnHelp = new JMenuItem();
	JMenuItem mnAbout = new JMenuItem();

	JDesktopPane Desktop = new JDesktopPane();

	public FrmMain() {
		try {
			jbInit();
			DBManager db = new DBManager();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		if(System.getProperty("os.name").contains("Windows"))
			setLookup();
		Dimension ScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setBounds(1, 1, ScreenSize.width, ScreenSize.height - 43);
		this.setTitle("入札借入管理システム操作マニュアル");
		this.setResizable(false);
		this.setJMenuBar(EYMenu);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				exitMsg();
			}
		});

		//mnuFile.setMnemonic('F');
		mnuFile.setText("ファイル");

		//mnuHelp.setMnemonic('H');
		mnuHelp.setText("Help");

		//mnRegistor.setIcon(new ImageIcon("IMAGE/Item.png"));
		//mnMain.setMnemonic('M');
		mnMain.setText("メイン");
		mnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmBidMain frmBidMain = new FrmBidMain("");
				createFrame(frmBidMain);
			}
		});
		
		//mnMaster.setMnemonic('M');
		mnMaster.setText("マスターのメンテナンス");
		mnMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMasterMaintenance FrmMasterMaintenance = new FrmMasterMaintenance();
				createFrame(FrmMasterMaintenance);
			}
		});
		
		mnSystemSelection.setText("システム別マスタ設定");
		mnSystemSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmSystemSelection FrmSystemSelection = new FrmSystemSelection();
				createFrame(FrmSystemSelection);
			}
		});

		//mnExit.setMnemonic('X');
		mnExit.setText("出口");
		mnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exitMsg();
			}
		});

		//mnHelp.setIcon(new ImageIcon("IMAGE/Help.gif"));
		//mnHelp.setMnemonic('H');
		mnHelp.setText("ヘルプトピック");

		//mnAbout.setMnemonic('A');
		mnAbout.setText("このソフトについて");
		mnAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});

		toolbar.setBounds(new Rectangle(0, 27, 400, 34));
		// toolbar.setBorder(BorderFactory.createEtchedBorder());

		status.setFont(new java.awt.Font("Times New Romans", 3, 10));
		status.setText("Copyright (C)2016-2020, by EY.");
		status.setBorder(BorderFactory.createEtchedBorder());
		status.setBounds(new Rectangle(1, ScreenSize.height-20, 400, 17));

		cmdMain.setToolTipText("メイン");
		cmdMain.setText("メイン");
		cmdMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmBidMain frmBidMain = new FrmBidMain("");
				createFrame(frmBidMain);
			}
		});
		
		cmdMaster.setToolTipText("マスターのメンテナンス");
		cmdMaster.setText("マスターのメンテナンス");
		cmdMaster.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmMasterMaintenance FrmMasterMaintenance = new FrmMasterMaintenance();
				createFrame(FrmMasterMaintenance);
			}
		});
		
		cmdSystemSelection.setToolTipText("システム別マスタ設定");
		cmdSystemSelection.setText("システム別マスタ設定");
		cmdSystemSelection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrmSystemSelection FrmSystemSelection = new FrmSystemSelection();
				createFrame(FrmSystemSelection);
			}
		});

		this.getContentPane().add(toolbar, BorderLayout.NORTH);
		this.getContentPane().add(status, BorderLayout.SOUTH);
		this.getContentPane().add(Desktop, BorderLayout.CENTER);

		EYMenu.add(mnuFile);
		EYMenu.add(mnuHelp);

		mnuFile.add(mnMain);
		mnuFile.add(mnMaster);
		mnuFile.add(mnSystemSelection);
		mnuFile.addSeparator();
		mnuFile.add(mnExit);

		toolbar.add(cmdMain, null);
		toolbar.addSeparator();
		toolbar.add(cmdMaster, null);	
		toolbar.addSeparator();
		toolbar.add(cmdSystemSelection,null);

		mnuHelp.add(mnHelp);
		mnuHelp.addSeparator();
		mnuHelp.add(mnAbout);

		Desktop.setBackground(new Color(156,214,230));

		loadBackgroundImage();

		// setUserMenu();

	}

	/*
	 * public void setUserMenu() { cmdUser.setEnabled(true); }
	 */

	protected void loadBackgroundImage() {
		CommonProperties props = new CommonProperties();
		String imageDir = props.getImageDir();
		ImageIcon icon = new ImageIcon(imageDir+"logo.png");

		int x = (this.getWidth() - icon.getIconWidth()) / 2;
		int y = (this.getHeight() - icon.getIconHeight()) / 2;

		JLabel l = new JLabel(icon);
		l.setBounds(x, y + 80, icon.getIconWidth(), icon.getIconHeight());

		// Place the image in the lowest possible layer so nothing
		// can ever be painted under it.
		Desktop.add(l, new Integer(Integer.MIN_VALUE));
	}

	public void createFrame(JInternalFrame frm) {
		JInternalFrame frame = frm;
		frame.setVisible(true);
		Desktop.add(frame);
		try {
			frame.setSelected(true);
		} catch (java.beans.PropertyVetoException e) {
		}
	}
	public javax.swing.JDesktopPane getDesktopPane() {
	    return Desktop;
	}
	protected void exitMsg() {
		int opt = JOptionPane.showConfirmDialog(null, "あなたは、アプリケーションを終了しますか？",
				"終了確認", JOptionPane.YES_NO_OPTION);
		if (opt == JOptionPane.YES_OPTION) {
			/*
			 * DBManager db = new DBManager(); try { db.close(); } catch
			 * (SQLException ex) { System.out.println(ex); }
			 */
			System.exit(0);
		}
	}

	public void setLookup() {
		/*
		 * String lookAndFeel =
		 * "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"; String
		 * lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		 * String lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
		 */

		try {
			UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();
			// looks[0] = Default/ Metaal Style
			// looks[1] = Motif Style
			// looks[2] = Windows Style
			UIManager.setLookAndFeel(looks[1].getClassName());
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			// UIManager.setLookAndFeel(lookAndFeel);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*
		 * try{ javax.swing.UIManager.setLookAndFeel(
		 * "com.birosoft.liquid.LiquidLookAndFeel"); } catch (Exception e) {
		 * System.out.println("Error Loading Theme:" + e.toString()); //If
		 * Failed to load the liquid them then load my own XPStyleTheme
		 * //MetalTheme myXPStyleTheme = new XPStyleTheme();
		 * //MetalLookAndFeel.setCurrentTheme(myXPStyleTheme); try {
		 * //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel")
		 * ; UIManager.LookAndFeelInfo looks[] =
		 * UIManager.getInstalledLookAndFeels();
		 * UIManager.setLookAndFeel(looks[2].getClassName()); } catch (Exception
		 * err) { System.out.println("Error loading myXPStyleTheme:" +
		 * err.toString()); } }
		 */
	}
}

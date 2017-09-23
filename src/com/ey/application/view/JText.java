package com.ey.application.view;

import javax.swing.JTextField;

import org.apache.commons.lang.StringUtils;

import java.awt.event.*;

public class JText extends JTextField {
	char vType;
	int wLen;

	public JText(int Width, char Type) {
		vType = Type;
		wLen = Width;
		this.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if ((getText().length()) >= wLen && ((int) e.getKeyChar() != 8)) {
					e.consume();
				}
				int key = e.getKeyChar();
				if (Character.toLowerCase(vType) == 'f') {
					// ____________________________________________________________
					// _________________Float Number Valiadation___________________
					// ____________________________________________________________
					if (!(Character.isDigit(e.getKeyChar()) || (key == 46) || (key == 8))) {
						e.consume();
					}
					String text = getText();
					int occurance = StringUtils.countMatches(text, ".");
					if((key == 46) && (occurance>=1)){
						e.consume();
					}
				} else if (Character.toLowerCase(vType) == 'i') {
					// ____________________________________________________________
					// _________________Integer Number Valiadation_________________
					// ____________________________________________________________
					if (!(Character.isDigit(e.getKeyChar()) || (key == 8))) {
						e.consume();
					}
				} else if (Character.toLowerCase(vType) == 'a') {
					// ____________________________________________________________
					// ___________________Alphabet Valiadation_____________________
					// ____________________________________________________________
					if (Character.isDigit(e.getKeyChar())) {
						e.consume();
					}
				} else {
					// ____________________________________________________________
					// ____________________String Valiadation______________________
					// ____________________________________________________________
				}
			}
		});
	}
}

package interfaces;

import javax.swing.JFrame;
import javax.swing.JLabel;

import TEMP.DBCom;

import javax.swing.JComboBox;
import javax.swing.JSpinner;

import org.jdesktop.swingx.JXDatePicker;

import java.io.*;
import java.text.SimpleDateFormat;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Dimension;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXBusyLabel;
import org.jdesktop.swingx.JXButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.jdesktop.swingx.JXErrorPane;
import org.jdesktop.swingx.JXDialog;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.TextArea;
import java.awt.TextField;

public class DateChanger {
	private final JXDatePicker datePicker = new JXDatePicker();
	String uDate = "Incorrectly Entered";

	public DateChanger(){
		JFrame jf = new JFrame("");
		jf.setSize(490,155);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		final DBCom con = new DBCom();
		String old_date= con.GetDateStorred();
		String month = "";
		String day = old_date.substring(8, 10);
		String monthString = old_date.substring(5, 7);
		int monthInt = Integer.parseInt(monthString);
		switch (monthInt){
			case 1: 
				month = "January";
				break;
			case 2:
				month = "February";
				break;
			case 3:
				month = "March";
				break;
			case 4:
				month = "April";
				break;
			case 5:
				month = "May";
				break;
			case 6:
				month = "June";
				break;
			case 7:
				month = "July";
				break;
			case 8:
				month = "August";
				break;
			case 9:
				month = "September";
				break;
			case 10:
				month = "October";
				break;
			case 11:
				month = "November";
				break;
			case 12:
				month = "December";
				break;
		}
		String year = old_date.substring(0, 4);
		JLabel lblNewLabel = new JLabel("The database currently thinks it is the " + day + " of " + month + " in the year "+ year);
		lblNewLabel.setBounds(0, 0, 441, 14);
		jf.getContentPane().add(lblNewLabel);
		
		
		final JXButton btnApplyChange = new JXButton();
		btnApplyChange.setEnabled(false);
		btnApplyChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				con.SetDateStorred(uDate);
			}
		});
		
		
		datePicker.getEditor().setMinimumSize(new Dimension(96, 22));
		
		datePicker.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				uDate = format.format(datePicker.getDate());
				btnApplyChange.setEnabled(true);
			}
		});
		datePicker.setBounds(0, 49, 158, 31);
		jf.getContentPane().add(datePicker);
		
		JLabel lblNewLabel_1 = new JLabel("Select the date you want to change the database towards");
		lblNewLabel_1.setBounds(0, 25, 362, 14);
		jf.getContentPane().add(lblNewLabel_1);
		
		
		btnApplyChange.setText("Apply Change");
		btnApplyChange.setBounds(168, 48, 126, 31);
		jf.getContentPane().add(btnApplyChange);
		
		jf.setVisible(true);

	}
	
}

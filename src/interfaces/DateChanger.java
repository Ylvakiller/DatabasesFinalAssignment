package interfaces;

import javax.swing.JFrame;
import javax.swing.JLabel;

import TEMP.DBCom;

public class DateChanger {

	public DateChanger(){
		JFrame jf = new JFrame("");
		jf.setSize(457,75);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		
		DBCom con = new DBCom();
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
		JLabel lblNewLabel = new JLabel("The database currently thinks it is the " + day + " off " + month + " in the year "+ year);
		lblNewLabel.setBounds(0, 0, 441, 14);
		jf.getContentPane().add(lblNewLabel);
		jf.setVisible(true);

	}
}

package interfaces;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;



import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTextField;

import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import java.awt.Color;
import java.text.SimpleDateFormat;

import org.jdesktop.swingx.JXButton;

import Console.Console;
import TEMP.DBCom;



public class AddFriend {
	private static JTextField Email;
	private static String Nickname, EmailAddress, BirthDay;
	private static Boolean a, b, c;
	private Console console;
	private static DBCom con;
	
	public AddFriend(Console Console){
		console = Console;
		con = new DBCom(console);
		console.out("Succesfully opend the window to add a friend");
		
		final JTextField Output = new JTextField();
		a = false;
		b = false;
		c = false;
		final JFrame jf = new JFrame("");
		jf.setSize(311,218);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		
		JLabel lblWelcomeToThis = new JLabel("This window will allow you to add a friend to the database!");
		lblWelcomeToThis.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThis.setBounds(0, 0, 295, 15);
		jf.getContentPane().add(lblWelcomeToThis);
		
		final JXButton btnAddThisFriend = new JXButton();
		btnAddThisFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("Attempting to add friend");
				if (con.GetNameExcists(Nickname)){
					console.errorOut("Name already found");
					console.errorOut("Failed to add friend");
					Output.setText("Name already found, failed to add friend");
				}else if(AddFriend.CheckEmail(EmailAddress)){
					console.errorOut("Incorrect email adress");
					console.errorOut("Failed to add friend");
					Output.setText("Incorrect email adress, please make sure to spell it correctely.");
				}else if(con.GetDuplicateEmail(EmailAddress)){
					console.errorOut("Duplicate email found");
					console.errorOut("Failed to add friend");
					Output.setText("Duplicate email");
				}else{
					
					if (con.AddFriend(Nickname, EmailAddress, BirthDay)){
						console.out("Friend succesfully added");
						Output.setText("Friend added");
					}else{
						console.errorOut("Failed to add friend, wierd problem");
						console.errorOut("If there now is not a MySQL error then you have broken the program");
						console.errorOut("Anyway have a cookie...");
						Output.setText("Failed to add friend, check the console for details");
					}
				}
			}
		});
		btnAddThisFriend.setText("Add this friend!");
		btnAddThisFriend.setBounds(10, 100, 260, 25);
		btnAddThisFriend.setEnabled(false);
		jf.getContentPane().add(btnAddThisFriend);
		
		JXLabel lblNickname = new JXLabel();
		lblNickname.setText("Nickname:");
		lblNickname.setBounds(10, 26, 72, 20);
		jf.getContentPane().add(lblNickname);
		
		final JXTextField Name = new JXTextField();
		Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Nickname= Name.getText();											//Setting the NickName
				if (Nickname.equals("")){
					a = false;
				}else{
					a = true;
				}

				btnAddThisFriend.setEnabled(AddFriend.EnableButton());
			}
		});
		Name.setPromptForeground(Color.BLACK);
		Name.setBounds(82, 26, 188, 20);
		jf.getContentPane().add(Name);
		
		Email = new JTextField();
		Email.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmailAddress = Email.getText();										//Setting the Email
				if (EmailAddress.equals("")){
					b = false;
				}else{
					b = true;
				}

				btnAddThisFriend.setEnabled(AddFriend.EnableButton());
			}
		});
		Email.setBounds(82, 73, 188, 20);
		jf.getContentPane().add(Email);
		Email.setColumns(10);
		
		JXLabel lblEmailAdress = new JXLabel();
		lblEmailAdress.setText("Email adress:");
		lblEmailAdress.setBounds(10, 73, 72, 20);
		jf.getContentPane().add(lblEmailAdress);
		
		final JXDatePicker Bday = new JXDatePicker();
		
		Bday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				BirthDay = format.format(Bday.getDate());							//setting the birth day
				if (BirthDay.equals("")){
					c = false;
				}else{
					c = true;
				}
				btnAddThisFriend.setEnabled(AddFriend.EnableButton());
			}
		});
		Bday.setBounds(82, 51, 205, 20);
		jf.getContentPane().add(Bday);
		
		JXLabel lblBirthday = new JXLabel();
		lblBirthday.setText("Birthday");
		lblBirthday.setBounds(10, 51, 72, 20);
		jf.getContentPane().add(lblBirthday);
		
		
		
		
		Output.setEnabled(true);
		Output.setEditable(false);
		Output.setBounds(10, 136, 254, 25);
		jf.getContentPane().add(Output);
		
		jf.setVisible(true);
		
	}
	/**
	 * Called whenever one of the fields receives an input, this method is here to check if all the fields have at least gotten an input and then enables the button to process the info
	 * @param btnAddThisFriend
	 */
	private static boolean EnableButton(){
		boolean temp = false;
		if (a){
			if (b){
				if (c){
					temp = true;
					
				}
			}
		}
		return temp;
		
	}
	
	/*
	 * returns true if the String email is not a correct email
	 * sidenote , might move this class to DBCom so I can use it in multiple classes, unsure yet
	 */
	private static boolean CheckEmail(String email){
		int index1 = 0;
		int index2 = 0;
		index1 = email.indexOf('@');
		if (index1 == 0){
			return true;
		}else{
			index2 = email.lastIndexOf('.');
			if (index1>index2){
				return true;
			}else{
				return false;
			}
		}
		
		
	}
}

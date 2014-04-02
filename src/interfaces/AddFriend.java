package interfaces;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Button;
import java.awt.Font;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTextField;

import javax.swing.JTextField;

import org.jdesktop.swingx.painter.PinstripePainter;
import org.jdesktop.swingx.JXDatePicker;

import java.awt.Color;
import java.text.SimpleDateFormat;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXTextArea;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;


public class AddFriend {
	private static JTextField Email;
	private static String Nickname, EmailAddress, BirthDay;
	private static Boolean a, b, c;
	
	public static void main(String[] args){
		a = false;
		b = false;
		c = false;
		final JFrame jf = new JFrame("");
		jf.setSize(311,218);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		
		JLabel lblWelcomeToThis = new JLabel("This window will allow you to add a friend to the database!");
		lblWelcomeToThis.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThis.setBounds(0, 0, 295, 15);
		jf.getContentPane().add(lblWelcomeToThis);
		
		final JXButton btnAddThisFriend = new JXButton();
		btnAddThisFriend.setText("Add this friend!");
		btnAddThisFriend.setBounds(10, 113, 260, 25);
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
		Email.setBounds(82, 52, 188, 20);
		jf.getContentPane().add(Email);
		Email.setColumns(10);
		
		JXLabel lblEmailAdress = new JXLabel();
		lblEmailAdress.setText("Email adress:");
		lblEmailAdress.setBounds(10, 52, 72, 20);
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
		Bday.setBounds(82, 83, 205, 22);
		jf.getContentPane().add(Bday);
		
		JXLabel lblBirthday = new JXLabel();
		lblBirthday.setText("Birthday");
		lblBirthday.setBounds(10, 84, 72, 20);
		jf.getContentPane().add(lblBirthday);
		
		
		
		JXTextArea Output = new JXTextArea();
		Output.setEnabled(true);
		Output.setEditable(false);
		Output.setBounds(10, 147, 260, 22);
		jf.getContentPane().add(Output);
		
		jf.setVisible(true);
		
	}
	/**
	 * Called whenever one of the fields recieves an input, this method is here to check if all the fields have at least gotten an input and then enables the button to process the info
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
}

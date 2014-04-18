package interfaces;

import javax.swing.JFrame;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;

import java.awt.Button;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Console.Console;
import TEMP.DBCom;

public class UpdateFriend {
	private JTextField searchField;
	private String FriendNameString;
	private static DBCom con;
	private JTextField NameField;
	private JTextField EmailField;
	private JTextField BirthdayField;
	private Console console;
	
	public UpdateFriend(Console Console){
		console = Console;
		console.out("Succesfully opened the update or deactivate window");
		con = new DBCom(console);
		final JFrame jf = new JFrame("");
		jf.getContentPane().setLayout(null);
		jf.setSize(366,255);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		searchField = new JTextField();
		searchField.setBounds(11, 50, 155, 20);
		jf.getContentPane().add(searchField);
		searchField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Enter the name of the friend");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setBounds(11, 27, 179, 14);
		jf.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("This interface will let you update information about a friend");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 6, 390, 14);
		jf.getContentPane().add(lblNewLabel_1);
		
		Button button = new Button("Go!");
		
		button.setBounds(172, 50, 60, 22);
		jf.getContentPane().add(button);
		
		NameField = new JTextField();
		NameField.setEditable(false);
		NameField.setBounds(77, 105, 155, 20);
		jf.getContentPane().add(NameField);
		NameField.setColumns(10);
		
		JLabel lblThisFriendHas = new JLabel("The following information was retrieved from the database:");
		lblThisFriendHas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblThisFriendHas.setBounds(11, 83, 298, 14);
		jf.getContentPane().add(lblThisFriendHas);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(11, 105, 56, 14);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jf.getContentPane().add(lblName);
		
		JLabel label = new JLabel("Email:");
		label.setBounds(11, 136, 56, 14);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jf.getContentPane().add(label);
		
		EmailField = new JTextField();
		EmailField.setColumns(10);
		EmailField.setBounds(77, 136, 155, 20);
		jf.getContentPane().add(EmailField);
		
		JLabel label_1 = new JLabel("Birthday:");
		label_1.setBounds(11, 164, 56, 14);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		jf.getContentPane().add(label_1);
		
		BirthdayField = new JTextField();
		BirthdayField.setColumns(10);
		BirthdayField.setBounds(77, 164, 155, 20);
		jf.getContentPane().add(BirthdayField);
		
		final JLabel errorLabel = new JLabel("Name not found, please enter a correct name!");
		errorLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		errorLabel.setBounds(11, 198, 329, 14);
		jf.getContentPane().add(errorLabel);
		jf.setVisible(true);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FriendNameString = searchField.getText();
				console.out("Searching for " + FriendNameString + " in the database.");
				if (con.GetNameExcists(FriendNameString)){
					console.out("Name succesfully found");
					errorLabel.setVisible(false);
					console.out("Retrieving details");
					String [] tempData = con.GetDataFromName(FriendNameString);
					console.out("Details retrieved, entering details on fields");
					NameField.setText(tempData[0]);
					BirthdayField.setText(tempData[1]);
					EmailField.setText(tempData[2]);
				}else{
					console.errorOut("Name not found in the database, sending error to user");
					errorLabel.setVisible(true);
					console.out("Making sure the info in the details fields is empty");
					NameField.setText(null);
					BirthdayField.setText(null);
					EmailField.setText(null);
				}
			}
		});
	}
}

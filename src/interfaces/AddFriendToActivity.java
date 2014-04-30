package interfaces;

import javax.swing.JFrame;

import Console.Console;
import Database.Communication;

import org.jdesktop.swingx.JXLabel;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.Button;

import java.awt.Color;

import javax.swing.JButton;

public class AddFriendToActivity {


	private Console console;
	@SuppressWarnings("unused")
	private static Communication con;
	private JTextField friendSearchField;
	private JTextField activitySearchField;
	private JTextField nameResultField;
	private JTextField ActivityResultField;
	
	public AddFriendToActivity(Console Console){
		console = Console;
		con = new Communication(console);
		
		final JFrame jf = new JFrame("Database assignment");
		jf.setSize(396,354);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		
		JXLabel lblThisScreenWill = new JXLabel();
		lblThisScreenWill.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblThisScreenWill.setText("This screen will allow you to add a friend to an activity");
		lblThisScreenWill.setBounds(10, 0, 361, 23);
		jf.getContentPane().add(lblThisScreenWill);
		
		JLabel label = new JLabel("Enter the name of the friend");
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setBounds(10, 29, 179, 14);
		jf.getContentPane().add(label);
		
		friendSearchField = new JTextField();
		friendSearchField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		friendSearchField.setColumns(10);
		friendSearchField.setBounds(10, 46, 155, 20);
		jf.getContentPane().add(friendSearchField);
		
		Button friendSearchButton = new Button("Search");
		friendSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		friendSearchButton.setBounds(171, 46, 60, 22);
		jf.getContentPane().add(friendSearchButton);
		
		JLabel lblEnterTheName = new JLabel("Enter the name of the activity");
		lblEnterTheName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEnterTheName.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterTheName.setBounds(10, 71, 179, 14);
		jf.getContentPane().add(lblEnterTheName);
		
		activitySearchField = new JTextField();
		activitySearchField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		activitySearchField.setColumns(10);
		activitySearchField.setBounds(10, 88, 155, 20);
		jf.getContentPane().add(activitySearchField);
		
		Button activitySearchButton = new Button("Search");
		activitySearchButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		activitySearchButton.setBounds(171, 88, 60, 22);
		jf.getContentPane().add(activitySearchButton);
		
		JXLabel lblTheFollowingInfo = new JXLabel();
		lblTheFollowingInfo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTheFollowingInfo.setText("The following info was found");
		lblTheFollowingInfo.setBounds(10, 119, 165, 14);
		jf.getContentPane().add(lblTheFollowingInfo);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(10, 144, 39, 14);
		jf.getContentPane().add(lblName);
		
		nameResultField = new JTextField();
		nameResultField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		nameResultField.setForeground(Color.LIGHT_GRAY);
		nameResultField.setEditable(false);
		nameResultField.setEnabled(false);
		nameResultField.setBounds(59, 144, 146, 20);
		jf.getContentPane().add(nameResultField);
		nameResultField.setColumns(10);
		
		JLabel lblActivity = new JLabel("Activity:");
		lblActivity.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblActivity.setHorizontalAlignment(SwingConstants.LEFT);
		lblActivity.setBounds(10, 172, 45, 14);
		jf.getContentPane().add(lblActivity);
		
		ActivityResultField = new JTextField();
		ActivityResultField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ActivityResultField.setEnabled(false);
		ActivityResultField.setEditable(false);
		ActivityResultField.setColumns(10);
		ActivityResultField.setBounds(59, 172, 146, 20);
		jf.getContentPane().add(ActivityResultField);
		
		JButton btnAddThisFriend = new JButton("Add this friend to this activity");
		btnAddThisFriend.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddThisFriend.setBounds(10, 197, 195, 23);
		jf.getContentPane().add(btnAddThisFriend);
		
		JLabel errorLabel = new JLabel("Error label");
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		errorLabel.setBounds(20, 231, 169, 14);
		jf.getContentPane().add(errorLabel);
		
		
		jf.setVisible(true);
		console.out("Succesfully opened window to add a friend");
	}
}

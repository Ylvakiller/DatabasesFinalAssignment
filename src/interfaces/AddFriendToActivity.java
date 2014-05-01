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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddFriendToActivity {


	private Console console;
	private static Communication con;
	private JTextField friendSearchField;
	private JTextField activitySearchField;
	private JTextField nameResultField;
	private JTextField activityResultField;
	
	String name, activity;
	
	public AddFriendToActivity(Console Console){
		console = Console;
		con = new Communication(console);
		
		final JFrame jf = new JFrame("Database assignment");
		jf.setSize(396,354);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		
		final JLabel errorLabel = new JLabel("Error label");
		final JLabel lblName = new JLabel("Name:");
		final JXLabel lblTheFollowingInfo = new JXLabel();
		final JButton btnAddThisFriend = new JButton("Add this friend to this activity");
		btnAddThisFriend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("Finale button has been pressed");
				console.out("Attempting to add this friend to this activity");
				if (con.AddFriendToActivity(name, activity)){
					console.out("Friend succesfull added to activity");
				}else{
					console.errorOut("Something went wrong, nooooo");
					errorLabel.setText("Something went wrong, check the console for details");
					errorLabel.setVisible(true);
				}
			}
		});
		final JLabel lblActivity = new JLabel("Activity:");
		
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
		friendSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("Friend search button pressed, retrieving info from the screen");
				name = friendSearchField.getText();
				if (name.isEmpty()){
					console.errorOut("Invalid entry, you have to enter a name");
					errorLabel.setText("You have to enter a name!");
					errorLabel.setVisible(true);
					nameResultField.setVisible(false);
					lblName.setVisible(false);
					if(activityResultField.isVisible()==false){
						lblTheFollowingInfo.setVisible(false);
					}
				}else{
					console.out("Succesfully retrieved name from interface");
					console.out("Searching for name in database");
					if (con.GetNameExcists(name)){
						console.out("Succesfully found name in the database");
						console.out("Checking if friend is active so can be added to the activity");
						if (con.CheckActiveFriend(name)){
							console.out("Friend is active, proceding as planned");
							errorLabel.setVisible(false);
							nameResultField.setText(name);
							nameResultField.setVisible(true);
							lblName.setVisible(true);
							lblTheFollowingInfo.setVisible(true);
							if(activityResultField.isVisible()){
								btnAddThisFriend.setVisible(true);
							}
						}else{
							console.errorOut("Friend is not active, inable to add this friend to any activity");
							errorLabel.setText("WARNING friend not active, cannot add friend to activity!");
							errorLabel.setVisible(true);
							nameResultField.setVisible(false);
							lblName.setVisible(false);
							if(activityResultField.isVisible()==false){
								lblTheFollowingInfo.setVisible(false);
							}
						}
					}else{
						console.errorOut("Name not found in the database, sending error to user");
						errorLabel.setText("Name not found in the database, please check for the correct name");
						errorLabel.setVisible(true);
						nameResultField.setVisible(false);
						lblName.setVisible(false);
						if(activityResultField.isVisible()==false){
							lblTheFollowingInfo.setVisible(false);
						}
					}
				}
			}
		});
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
		activitySearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("Activity search button pressed, retrieving info from the screen");
				activity = activitySearchField.getText();
				if (name.isEmpty()){
					console.errorOut("Invalid entry, you have to enter an activity");
					errorLabel.setText("You have to enter an activity!");
					errorLabel.setVisible(true);
					activityResultField.setVisible(false);
					lblActivity.setVisible(false);
					if(nameResultField.isVisible()==false){
						lblTheFollowingInfo.setVisible(false);
					}
				}else{
					console.out("Succesfully retrieved activity from interface");
					console.out("Searching for activity in database");
					if (con.GetActivityExcisits(activity)){
						console.out("Succesfully found activity in the database");
						console.out("Checking if the activity has not yet started");
						if (con.ActivityStarted(activity)==false){
							console.out("Activity has not yet started, friend can be added");
							errorLabel.setVisible(false);
							activityResultField.setText(activity);
							activityResultField.setVisible(true);
							lblActivity.setVisible(true);
							lblTheFollowingInfo.setVisible(true);
							if(nameResultField.isVisible()){
								btnAddThisFriend.setVisible(true);
							}
						}else{
							console.errorOut("Activity has started, unable to add anyone to this activity");
							errorLabel.setText("The activity has already started, you cannot add anyone to it now");
							errorLabel.setVisible(true);
							activityResultField.setVisible(false);
							lblActivity.setVisible(false);
							if(nameResultField.isVisible()==false){
								lblTheFollowingInfo.setVisible(false);
							}
						}
						
					}else{
						console.errorOut("Activity not found in the database, sending error to user");
						errorLabel.setText("Activity not found in the database, please check for the correct activity name");
						errorLabel.setVisible(true);
						activityResultField.setVisible(false);
						lblActivity.setVisible(false);
						if(nameResultField.isVisible()==false){
							lblTheFollowingInfo.setVisible(false);
						}
					}
				}
			}
		});
		activitySearchButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		activitySearchButton.setBounds(171, 88, 60, 22);
		jf.getContentPane().add(activitySearchButton);
		
		
		lblTheFollowingInfo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTheFollowingInfo.setText("The following info was found");
		lblTheFollowingInfo.setBounds(10, 119, 165, 14);
		jf.getContentPane().add(lblTheFollowingInfo);
		
		
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblName.setHorizontalAlignment(SwingConstants.LEFT);
		lblName.setBounds(10, 144, 39, 20);
		jf.getContentPane().add(lblName);
		
		nameResultField = new JTextField();
		nameResultField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		nameResultField.setEditable(false);
		nameResultField.setBounds(59, 144, 146, 20);
		jf.getContentPane().add(nameResultField);
		nameResultField.setColumns(10);
		

		lblActivity.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblActivity.setHorizontalAlignment(SwingConstants.LEFT);
		lblActivity.setBounds(10, 172, 45, 20);
		jf.getContentPane().add(lblActivity);
		
		activityResultField = new JTextField();
		activityResultField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		activityResultField.setEditable(false);
		activityResultField.setColumns(10);
		activityResultField.setBounds(59, 172, 146, 20);
		jf.getContentPane().add(activityResultField);
		

		btnAddThisFriend.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddThisFriend.setBounds(10, 197, 195, 23);
		jf.getContentPane().add(btnAddThisFriend);
		
		
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		errorLabel.setBounds(10, 231, 361, 14);
		jf.getContentPane().add(errorLabel);
		
		errorLabel.setVisible(false);
		btnAddThisFriend.setVisible(false);
		activityResultField.setVisible(false);
		lblActivity.setVisible(false);
		nameResultField.setVisible(false);
		lblName.setVisible(false);
		lblTheFollowingInfo.setVisible(false);
		
		jf.setVisible(true);
		console.out("Succesfully opened window to add a friend");
	}
}

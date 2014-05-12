package interfaces;

import javax.swing.JFrame;

import Console.Console;
import Database.Communication;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FindDueActivityForFriend {

	private static Console console;
	private static Communication con;
	private static JFrame jf;
	private JTextField friendSearchField;
	String FriendSearch;
	
	public FindDueActivityForFriend(Console Console){
		console = Console;
		jf = new JFrame("Database assignment");
		jf.getContentPane().setLayout(null);
		console.out("Opened the findDueForFriend screen");
		JLabel lblEnterTheName = new JLabel("Enter the name of the friend");
		lblEnterTheName.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterTheName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEnterTheName.setBounds(12, 22, 164, 14);
		jf.getContentPane().add(lblEnterTheName);
		
		friendSearchField = new JTextField();
		friendSearchField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		friendSearchField.setColumns(10);
		friendSearchField.setBounds(10, 45, 156, 20);
		jf.getContentPane().add(friendSearchField);
		
		Button friendSearchButton = new Button("Search");
		friendSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FriendSearch = friendSearchField.getText();
				console.out("Searching for the name that was entered");
				if(con.GetNameExcists(FriendSearch)){
					console.out("Friend does indeed excist");
					console.out("Attempting to find out the amount of activities that this friend is a part of");
					String[] temp = con.FindActivitiesForFriend(FriendSearch);
					if (con.CountArray(temp)==0){
						console.errorOut("This friend is not part of any activities....");
					}else{
						console.out("this friend is participating in " + con.CountArray(temp) + " activites");
					}
					
				}
			}
		});
		friendSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		friendSearchButton.setBounds(173, 45, 60, 20);
		jf.getContentPane().add(friendSearchButton);
		jf.setVisible(true);
	}
}

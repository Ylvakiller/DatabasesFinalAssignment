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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.UIManager;

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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 86, 287, 140);
		jf.getContentPane().add(scrollPane);
		
		final JTextArea outPutArea = new JTextArea();
		scrollPane.setViewportView(outPutArea);
		
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
					int i = 0;
					float totalDue= 0;
					while(temp[i].isEmpty()==false){
						String[] totalDueString = con.getTotalActivityDue(temp[i]);
						float totalDueFloat = Float.parseFloat(totalDueString[1]);
						int amountOfFriends = con.CountFriendsForActivity(temp[i]);
						float duePerFriend = totalDueFloat/amountOfFriends;
						float amountPaid = con.GetAlreadyPaid(temp[i], FriendSearch);
						float tempDue = duePerFriend-amountPaid;
						if(tempDue<0){
							console.errorOut("It seems that for the activity "+ temp[i] + " the friend " + FriendSearch + " has paid more than each friend is suppossed to pay");
							console.errorOut("The total amount due for the activity " + temp[i] + " is " + Float.toString(totalDueFloat));
							console.errorOut("The activity " + temp[i] + " has a total of " + amountOfFriends + " friends");
							console.errorOut("This means that each friend has to pay " + duePerFriend);
							console.errorOut(FriendSearch + " however has paid + " + amountPaid);
							console.errorOut("This means that he has paid " + tempDue + " to much...");
						}else{
							totalDue = totalDue + tempDue;
						}
						outPutArea.append("Activity: " + temp[i] + "      Due: " + tempDue + "\n");
					}
					outPutArea.append("Total amount due = " + totalDue + "\n");
					
				}
			}
		});
		friendSearchButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		friendSearchButton.setBounds(173, 45, 60, 20);
		jf.getContentPane().add(friendSearchButton);
		
		
		outPutArea.setLineWrap(true);
		outPutArea.setForeground(new Color(0, 0, 0));
		outPutArea.setBackground(UIManager.getColor("Panel.background"));
		outPutArea.setAutoscrolls(true);
		jf.setVisible(true);
	}
}

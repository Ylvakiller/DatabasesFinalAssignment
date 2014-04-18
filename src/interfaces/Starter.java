package interfaces;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import Console.Console;

import java.awt.Button;
import java.awt.Font;


public class Starter {

	public static void main(String[] args){
		final Console console = new Console();
		JFrame jf = new JFrame("");
		jf.setSize(250,351);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		
		JButton DateChange = new JButton("Change the date");
		DateChange.setFont(new Font("Tahoma", Font.PLAIN, 11));
		DateChange.setHorizontalAlignment(SwingConstants.LEFT);
		DateChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new DateChanger(console);
			}
		});
		DateChange.setBounds(0, 0, 115, 20);
		jf.getContentPane().add(DateChange);
		
		JButton DataBase = new JButton("Change Database");
		DataBase.setFont(new Font("Tahoma", Font.PLAIN, 11));
		DataBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		DataBase.setBounds(114, 0, 120, 20);
		jf.getContentPane().add(DataBase);
		
		JLabel lblWelcomeToThis = new JLabel("Welcome to this program!");
		lblWelcomeToThis.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThis.setBounds(0, 20, 234, 15);
		jf.getContentPane().add(lblWelcomeToThis);
		
		Button AddFriendButton = new Button("Add a friend");
		AddFriendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddFriend(console);
			}
		});
		AddFriendButton.setBounds(10, 41, 214, 22);
		jf.getContentPane().add(AddFriendButton);
		
		Button UpdateFriendButton = new Button("Update or deactivate a friend");
		UpdateFriendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new UpdateFriend(console);
			}
		});
		UpdateFriendButton.setBounds(10, 63, 214, 22);
		jf.getContentPane().add(UpdateFriendButton);
		
		Button FriendPayButton = new Button("Make a friend pay for something");
		FriendPayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		FriendPayButton.setBounds(10, 85, 214, 22);
		jf.getContentPane().add(FriendPayButton);
		
		Button FriendDepositButton = new Button("Make a friend do a general deposit");
		FriendDepositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		FriendDepositButton.setBounds(10, 107, 214, 22);
		jf.getContentPane().add(FriendDepositButton);
		
		Button FriendDebtButton = new Button("See what a friend needs to pay");
		FriendDebtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		FriendDebtButton.setBounds(10, 129, 214, 22);
		jf.getContentPane().add(FriendDebtButton);
		
		Button FriendFinancialButton = new Button("Get all the financial stats of a friend");
		FriendFinancialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		FriendFinancialButton.setBounds(10, 151, 214, 22);
		jf.getContentPane().add(FriendFinancialButton);
		
		Button AddActivityButton = new Button("Add an activity");
		AddActivityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		AddActivityButton.setBounds(10, 173, 214, 22);
		jf.getContentPane().add(AddActivityButton);
		
		Button AddFriendToActivityButton = new Button("Get a friend to do an acitivity");
		AddFriendToActivityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		AddFriendToActivityButton.setBounds(10, 195, 214, 22);
		jf.getContentPane().add(AddFriendToActivityButton);
		
		Button IncreaseActivityDebtButton = new Button("Increase amount due for an activity");
		IncreaseActivityDebtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		IncreaseActivityDebtButton.setBounds(10, 217, 214, 22);
		jf.getContentPane().add(IncreaseActivityDebtButton);
		
		Button ActivityFinancialStatsButton = new Button("See the finanacial stats of an activity");
		ActivityFinancialStatsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		ActivityFinancialStatsButton.setBounds(10, 239, 214, 22);
		jf.getContentPane().add(ActivityFinancialStatsButton);
		
		Button FriendTransactionsButton = new Button("See all transactions of a friend");
		FriendTransactionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		FriendTransactionsButton.setBounds(10, 261, 214, 22);
		jf.getContentPane().add(FriendTransactionsButton);
		
		JButton btnNewButton = new JButton("Get the Console back :)");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				console.MakeVisible();
			}
		});
		btnNewButton.setBounds(10, 289, 214, 23);
		jf.getContentPane().add(btnNewButton);
		
		jf.setVisible(true);
		
	}
}

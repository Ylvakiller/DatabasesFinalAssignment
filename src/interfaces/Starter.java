package interfaces;
import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Console.Console;

/*
 * main class, opens a window with buttons to open up all the other windows, starts up the console.
 * uses actionlisteners to make all the other windows. sends it's console around as overall console.
 * has a small button to make the console reappear, is the only window that shuts down the program when closed
 */
public class Starter {

	public static void main(String[] args){
		final Console console = new Console();
		JFrame jf = new JFrame("Database assignment");
		jf.setSize(250,351);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		
		JButton DateChange = new JButton("Change the date");
		DateChange.setFont(new Font("Tahoma", Font.PLAIN, 11));
		DateChange.setHorizontalAlignment(SwingConstants.LEFT);
		DateChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				console.out("Opening the window to change the date");
				new DateChanger(console);
			}
		});
		DateChange.setBounds(0, 0, 115, 20);
		jf.getContentPane().add(DateChange);
		
		JButton DataBase = new JButton("Change Database");
		DataBase.setFont(new Font("Tahoma", Font.PLAIN, 11));
		DataBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("Not yet implemented");
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
				console.out("Opening the window to add a friend");
				new AddFriend(console);
			}
		});
		AddFriendButton.setBounds(10, 41, 214, 22);
		jf.getContentPane().add(AddFriendButton);
		
		Button UpdateFriendButton = new Button("Update or deactivate a friend");
		UpdateFriendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				console.out("Opening the update and deactivate window");
				new UpdateFriend(console);
			}
		});
		UpdateFriendButton.setBounds(10, 63, 214, 22);
		jf.getContentPane().add(UpdateFriendButton);
		
		Button DepositButton = new Button("Do a deposit");
		DepositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("Opening the deposit window");
				new Deposit(console);
			}
		});
		DepositButton.setBounds(10, 85, 214, 22);
		jf.getContentPane().add(DepositButton);
		
		Button PaymentButton = new Button("Pay an activity");
		PaymentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("Not yet implemented");
			}
		});
		PaymentButton.setBounds(10, 107, 214, 22);
		jf.getContentPane().add(PaymentButton);
		
		Button FriendDebtButton = new Button("See what a friend needs to pay");
		FriendDebtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("Not yet implemented");
			}
		});
		FriendDebtButton.setBounds(10, 129, 214, 22);
		jf.getContentPane().add(FriendDebtButton);
		
		Button FriendFinancialButton = new Button("Get all the financial stats of a friend");
		FriendFinancialButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("Not yet implemented");
			}
		});
		FriendFinancialButton.setBounds(10, 151, 214, 22);
		jf.getContentPane().add(FriendFinancialButton);
		
		Button AddActivityButton = new Button("Add an activity");
		AddActivityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("Opening the window to add an activity");
				new AddActivity(console);
			}
		});
		AddActivityButton.setBounds(10, 173, 214, 22);
		jf.getContentPane().add(AddActivityButton);
		
		Button AddFriendToActivityButton = new Button("Add a friend to an acitivity");
		AddFriendToActivityButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("opening window to add a friend to an activity");
				new AddFriendToActivity(console);
			}
		});
		AddFriendToActivityButton.setBounds(10, 195, 214, 22);
		jf.getContentPane().add(AddFriendToActivityButton);
		
		Button IncreaseActivityDebtButton = new Button("Increase amount due for an activity");
		IncreaseActivityDebtButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("Not yet implemented");
			}
		});
		IncreaseActivityDebtButton.setBounds(10, 217, 214, 22);
		jf.getContentPane().add(IncreaseActivityDebtButton);
		
		Button ActivityFinancialStatsButton = new Button("See the finanacial stats of an activity");
		ActivityFinancialStatsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("Not yet implemented");
			}
		});
		ActivityFinancialStatsButton.setBounds(10, 239, 214, 22);
		jf.getContentPane().add(ActivityFinancialStatsButton);
		
		Button FriendTransactionsButton = new Button("See all transactions of a friend");
		FriendTransactionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				console.out("Not yet implemented");
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

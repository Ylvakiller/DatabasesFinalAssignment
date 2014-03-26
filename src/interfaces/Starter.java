package interfaces;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Button;
import java.awt.Font;


public class Starter {

	public static void main(String[] args){
		JFrame jf = new JFrame("");
		jf.setSize(250,351);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		
		JButton DateChange = new JButton("Change the date");
		DateChange.setFont(new Font("Tahoma", Font.PLAIN, 11));
		DateChange.setHorizontalAlignment(SwingConstants.LEFT);
		DateChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DateChanger Date =new DateChanger();
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
		
		Button button = new Button("Add a friend");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(10, 41, 214, 22);
		jf.getContentPane().add(button);
		
		Button button_1 = new Button("Update a friend");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setBounds(10, 63, 214, 22);
		jf.getContentPane().add(button_1);
		
		Button button_2 = new Button("Kill a friend");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_2.setBounds(10, 85, 214, 22);
		jf.getContentPane().add(button_2);
		
		Button button_3 = new Button("Make a friend pay for something");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_3.setBounds(10, 107, 214, 22);
		jf.getContentPane().add(button_3);
		
		Button button_4 = new Button("Make a friend do a general deposit");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_4.setBounds(10, 129, 214, 22);
		jf.getContentPane().add(button_4);
		
		Button button_5 = new Button("See what a friend need to pay");
		button_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_5.setBounds(10, 151, 214, 22);
		jf.getContentPane().add(button_5);
		
		Button button_6 = new Button("Get all the financial stats of a friend");
		button_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_6.setBounds(10, 173, 214, 22);
		jf.getContentPane().add(button_6);
		
		Button button_7 = new Button("Add an activity");
		button_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_7.setBounds(10, 195, 214, 22);
		jf.getContentPane().add(button_7);
		
		Button button_8 = new Button("Get a friend to do an acitivity");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_8.setBounds(10, 217, 214, 22);
		jf.getContentPane().add(button_8);
		
		Button button_9 = new Button("Increase amount due for an activity");
		button_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_9.setBounds(10, 239, 214, 22);
		jf.getContentPane().add(button_9);
		
		Button button_10 = new Button("See the finanacial stats of an activity");
		button_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_10.setBounds(10, 261, 214, 22);
		jf.getContentPane().add(button_10);
		
		Button button_11 = new Button("See all transactions of a friend");
		button_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_11.setBounds(10, 283, 214, 22);
		jf.getContentPane().add(button_11);
		
		jf.setVisible(true);
		
	}
}

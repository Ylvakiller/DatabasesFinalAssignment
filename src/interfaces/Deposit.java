package interfaces;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXLabel;

import Console.Console;
import Database.Communication;

/*
 * Allows someone to see the balance of someone and do a deposit for that person.
 * needs the overal console
 */
public class Deposit {

	private static Console console;
	private static JTextField NameField;
	private static Communication con;
	static JLabel nameLabel;
	String nameSearch;
	private static JFrame jf;
	JLabel balanceLabel;
	private JTextField depositField;
	
	public Deposit(Console Console){
		console = Console;
		jf = new JFrame("Database assignment");
		jf.getContentPane().setLayout(null);
		
		con = new Communication(console);
		
		JLabel lblThisInterfaceWill = new JLabel("This interface will allow you to do a deposit for a friend");
		lblThisInterfaceWill.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblThisInterfaceWill.setBounds(9, 11, 309, 14);
		jf.getContentPane().add(lblThisInterfaceWill);
		
		JLabel label_1 = new JLabel("Enter the name of the friend");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setBounds(11, 32, 144, 14);
		jf.getContentPane().add(label_1);
		
		NameField = new JTextField();
		NameField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		NameField.setColumns(10);
		NameField.setBounds(9, 55, 156, 20);
		jf.getContentPane().add(NameField);
		
		final JLabel errorLabel = new JLabel("Name not found, please enter a correct name!");
		errorLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		errorLabel.setBounds(11, 198, 329, 14);
		jf.getContentPane().add(errorLabel);
		errorLabel.setVisible(false);
		
		Button GoButton = new Button("Go!");
		GoButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		GoButton.setBounds(172, 55, 60, 20);
		jf.getContentPane().add(GoButton);
		
		JLabel label_2 = new JLabel("The following information was retrieved from the database:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(10, 86, 298, 14);
		jf.getContentPane().add(label_2);
		
		JXLabel lblName = new JXLabel();
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblName.setText("Name:");
		lblName.setBounds(10, 111, 47, 20);
		jf.getContentPane().add(lblName);
		
		nameLabel = new JLabel("");
		nameLabel.setBounds(59, 111, 203, 20);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jf.getContentPane().add(nameLabel);
		
		JXLabel lblBalance = new JXLabel();
		lblBalance.setText("Balance:");
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBalance.setBounds(10, 129, 47, 20);
		jf.getContentPane().add(lblBalance);
		
		balanceLabel = new JLabel("");
		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		balanceLabel.setBounds(59, 129, 203, 20);
		jf.getContentPane().add(balanceLabel);
		
		JXLabel lblEnterTheAmount = new JXLabel();
		lblEnterTheAmount.setText("Enter the amount you want to deposit for this user:");
		lblEnterTheAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEnterTheAmount.setBounds(10, 147, 253, 20);
		jf.getContentPane().add(lblEnterTheAmount);
		
		depositField = new JTextField();
		depositField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		depositField.setColumns(10);
		depositField.setBounds(9, 165, 156, 20);
		jf.getContentPane().add(depositField);
		
		Button DepositButton = new Button("Deposit");
		
		DepositButton.setActionCommand("Deposit");
		DepositButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		DepositButton.setBounds(172, 165, 60, 20);
		jf.getContentPane().add(DepositButton);
		jf.setSize(533,255);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
		console.out("Succesfully opened the deposit window");
		
		GoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nameSearch = NameField.getText();
				if (con.GetNameExcists(nameSearch)){
					console.out("Name succesfully found");
					console.out("Retrieving details");
					String [] tempData = con.getBalance(nameSearch);
					console.out("Details retrieved, entering details on fields");
					nameLabel.setText(tempData[0]);
					balanceLabel.setText(tempData[1]);
					errorLabel.setVisible(false);
				}else{
					console.errorOut("Name not found in the database, sending error to user");
					errorLabel.setVisible(true);
					console.out("Making sure the info in the details fields is empty");
					nameLabel.setText(null);
				}
				
				
			}
		});
		
		
		DepositButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tempString = depositField.getText();
				int tempInt1 = 0;
				tempInt1 = tempString.indexOf(".");
				if ((tempInt1+3)<tempString.length()){
					tempString = tempString.substring(0, (tempInt1 + 3));
				}
				console.out("tempString is : " + tempString);
				float tempF = Float.parseFloat(tempString);
				con.Depositer(nameSearch, tempF);
				console.out("Updating the labels");
				String [] tempData = con.getBalance(nameSearch);
				nameLabel.setText(tempData[0]);
				balanceLabel.setText(tempData[1]);
			}
		});
	}
}

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

public class IncreaseDueActivity {

	private static Console console;
	private static JTextField ActivityField;
	private static Communication con;
	static JLabel ActivityLabel;
	String ActivitySearch;
	private static JFrame jf;
	JLabel DueLabel;
	private JTextField DueField;
	
	public IncreaseDueActivity(Console Console){
		console = Console;
		jf = new JFrame("Database assignment");
		jf.getContentPane().setLayout(null);
		
		con = new Communication(console);
		
		JLabel lblThisInterfaceWill = new JLabel("This interface will let you increase the amount due for an activity");
		lblThisInterfaceWill.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblThisInterfaceWill.setBounds(9, 11, 372, 14);
		jf.getContentPane().add(lblThisInterfaceWill);
		
		JLabel lblEnterTheName = new JLabel("Enter the name of the activity");
		lblEnterTheName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEnterTheName.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterTheName.setBounds(11, 32, 164, 14);
		jf.getContentPane().add(lblEnterTheName);
		
		ActivityField = new JTextField();
		ActivityField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ActivityField.setColumns(10);
		ActivityField.setBounds(9, 55, 156, 20);
		jf.getContentPane().add(ActivityField);
		
		final JLabel errorLabel = new JLabel("Activity not found, please enter a correct name!");
		errorLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		errorLabel.setBounds(11, 198, 329, 14);
		jf.getContentPane().add(errorLabel);
		errorLabel.setVisible(false);
		
		Button SearchButton = new Button("Search");
		SearchButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		SearchButton.setBounds(172, 55, 60, 20);
		jf.getContentPane().add(SearchButton);
		
		JLabel label_2 = new JLabel("The following information was retrieved from the database:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_2.setBounds(10, 86, 298, 14);
		jf.getContentPane().add(label_2);
		
		JXLabel lblName = new JXLabel();
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblName.setText("Activity:");
		lblName.setBounds(10, 111, 47, 20);
		jf.getContentPane().add(lblName);
		
		ActivityLabel = new JLabel("");
		ActivityLabel.setBounds(79, 111, 203, 20);
		ActivityLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		jf.getContentPane().add(ActivityLabel);
		
		JXLabel lblBalance = new JXLabel();
		lblBalance.setText("Amount due:");
		lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblBalance.setBounds(10, 129, 69, 20);
		jf.getContentPane().add(lblBalance);
		
		DueLabel = new JLabel("");
		DueLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		DueLabel.setBounds(79, 129, 203, 20);
		jf.getContentPane().add(DueLabel);
		
		JXLabel lblEnterTheAmount = new JXLabel();
		lblEnterTheAmount.setText("Enter the amount by which you want to increase the amount due");
		lblEnterTheAmount.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEnterTheAmount.setBounds(10, 147, 330, 20);
		jf.getContentPane().add(lblEnterTheAmount);
		
		DueField = new JTextField();
		DueField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		DueField.setColumns(10);
		DueField.setBounds(9, 165, 156, 20);
		jf.getContentPane().add(DueField);
		
		Button IncreaseButton = new Button("Increase");
		
		IncreaseButton.setActionCommand("Deposit");
		IncreaseButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		IncreaseButton.setBounds(172, 165, 60, 20);
		jf.getContentPane().add(IncreaseButton);
		jf.setSize(402,255);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
		console.out("Succesfully opened the deposit window");
		
		SearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ActivitySearch = ActivityField.getText();
				if (con.GetActivityExcisits(ActivitySearch)){
					console.out("Activity succesfully found");
					console.out("Retrieving details");
					String [] tempData = con.getTotalActivityDue(ActivitySearch);
					console.out("Details retrieved, entering details on fields");
					ActivityLabel.setText(tempData[0]);
					DueLabel.setText(tempData[1]);
					errorLabel.setVisible(false);
				}else{
					console.errorOut("Activity not found in the database, sending error to user");
					errorLabel.setText("Activity not found, please enter a correct name!");
					errorLabel.setVisible(true);
					console.out("Making sure the info in the details fields is empty");
					ActivityLabel.setText(null);
				}
				
				
			}
		});
		
		
		IncreaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String tempString = DueField.getText();
				int tempInt1 = 0;
				tempInt1 = tempString.indexOf(".");
				if ((tempInt1+3)<tempString.length()&&tempInt1!=-1){
					console.out("tempInt1 = " + Integer.toString(tempInt1));
					tempString = tempString.substring(0, (tempInt1 + 3));
				}
				console.out("tempString is : " + tempString);
				float tempF = Float.parseFloat(tempString);
				if(con.ActivityStarted(ActivitySearch)&&(con.ActivityEnded(ActivitySearch)==false)){
					con.IncreaseDue(ActivitySearch, tempF);
					console.out("Updating the labels");
					String [] tempData = con.getTotalActivityDue(ActivitySearch);
					ActivityLabel.setText(tempData[0]);
					DueLabel.setText(tempData[1]);
				}else if(con.ActivityStarted(ActivitySearch)){
						console.errorOut("Apparentely the activity has not yet started");
						console.errorOut("You can only increase the amount due if an activity is currentely active");
						console.errorOut("Sending error to user");
						errorLabel.setText("The activity has not yet started, unable to increase amount due");
						errorLabel.setVisible(true);
				}else{
					console.errorOut("Apparentely the activity has already ended");
					console.errorOut("You can only increase the amount due if an activity is currentely active");
					console.errorOut("Sending error to user");
					errorLabel.setText("The activity has already ended, unable to increase amount due");
					errorLabel.setVisible(true);	
				}
			}
				
		});
		
	}
}

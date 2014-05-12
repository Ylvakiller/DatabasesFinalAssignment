package interfaces;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXLabel.TextAlignment;
import org.jdesktop.swingx.JXTextField;

import Console.Console;
import Database.Communication;

/*
 * Opens a window to add an activity, uses the console
 */
public class AddActivity {

	private static Console console;
	private static JFrame jf;
	private static Communication con;
	String name, startD, endD;
	
	public AddActivity( Console Console){
		console = Console;
		con = new Communication(console);
		jf = new JFrame("Database assignment");
		jf.getContentPane().setLayout(null);
		
		JXLabel lblInHereYou = new JXLabel();
		lblInHereYou.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInHereYou.setText("In here you will be able to add an activity");
		lblInHereYou.setBounds(9, 0, 326, 19);
		jf.getContentPane().add(lblInHereYou);
		
		JXLabel lblAnActivityNeeds = new JXLabel();
		lblAnActivityNeeds.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblAnActivityNeeds.setText("An activity needs a name, a start date and an end date");
		lblAnActivityNeeds.setBounds(10, 22, 279, 24);
		jf.getContentPane().add(lblAnActivityNeeds);
		
		JXLabel lblName = new JXLabel();
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblName.setText("Name:");
		lblName.setBounds(10, 48, 57, 22);
		jf.getContentPane().add(lblName);
		
		JXLabel lblStartDate = new JXLabel();
		lblStartDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblStartDate.setText("Start date:");
		lblStartDate.setBounds(10, 78, 57, 22);
		jf.getContentPane().add(lblStartDate);
		
		JXLabel lblEndDate = new JXLabel();
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblEndDate.setText("End date:");
		lblEndDate.setBounds(10, 108, 57, 22);
		jf.getContentPane().add(lblEndDate);
		
		final JXDatePicker datePicker = new JXDatePicker();
		datePicker.setBounds(89, 78, 174, 22);
		jf.getContentPane().add(datePicker);
		
		final JXDatePicker datePicker_1 = new JXDatePicker();
		datePicker_1.setBounds(89, 108, 174, 22);
		jf.getContentPane().add(datePicker_1);
		
		final JXTextField textField = new JXTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 11));
		textField.setBounds(89, 48, 174, 22);
		jf.getContentPane().add(textField);
		
		final JXLabel ErrorLbl = new JXLabel();
		
		JXButton btnAddThisActivity = new JXButton();
		btnAddThisActivity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				name = textField.getText();
				startD =  format.format(datePicker.getDate());
				endD = format.format(datePicker_1.getDate());
				Date tempSD = datePicker.getDate();
				Date tempED = datePicker.getDate();
				if (name.isEmpty()){
					console.errorOut("No name found, giving feedback to user.");
					ErrorLbl.setText("You have to enter a name...");
					ErrorLbl.setVisible(true);
				}else if(name.length()>150){
					console.errorOut("Given name to long, cannot add activity");
					ErrorLbl.setText("The name you entered is to long (max 150)");
					ErrorLbl.setVisible(true);
				}else if(startD.isEmpty()){
					console.errorOut("No start date found, giving feedback to user.");
					ErrorLbl.setText("You have to enter a starting date");
					ErrorLbl.setVisible(true);
				}else if(endD.isEmpty()){
					console.errorOut("No end date found, giving feedback to user.");
					ErrorLbl.setText("You have to enter an end date");
					ErrorLbl.setVisible(true);
				}else if(tempSD.after(tempED)){
					console.errorOut("You cannot end an activity before starting it");
					ErrorLbl.setText("Date error, see console for details");
					ErrorLbl.setVisible(true);
				}else{
					console.out("Succesfully retrieved user input");
					ErrorLbl.setVisible(false);
					console.out("Attempting to add this activity to the database");
					if (con.AddActivity(name, startD, endD)){
						console.out("Succesfully added activity to the database");
						ErrorLbl.setText("Added this activity");
						ErrorLbl.setVisible(true);
					}else{
						console.out("Unsuccesfully added this activity... something went wrong apparentely");
						ErrorLbl.setText("Something went wrong :O");
						ErrorLbl.setVisible(true);
					}
				}
			}
		});
		btnAddThisActivity.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAddThisActivity.setText("Add this activity");
		btnAddThisActivity.setBounds(10, 141, 191, 24);
		jf.getContentPane().add(btnAddThisActivity);
		
		ErrorLbl.setTextAlignment(TextAlignment.CENTER);
		ErrorLbl.setFont(new Font("Tahoma", Font.PLAIN, 11));
		ErrorLbl.setText("test");
		ErrorLbl.setBounds(10, 170, 191, 22);
		jf.getContentPane().add(ErrorLbl);
		ErrorLbl.setVisible(false);

		jf.setSize(533,255);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
		console.out("Succesfully opened window to add an activity");
		
	}
}

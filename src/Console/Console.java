package Console;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.util.Calendar;
import java.util.Date;

public class Console {

	private JTextArea OutputArea;
	final JFrame jf;
	private Date date;
	public Console(){
		date = new Date();
		
		jf = new JFrame("");
		jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		jf.setSize(730,396);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 714, 358);
		jf.getContentPane().add(scrollPane);
		OutputArea = new JTextArea();
		OutputArea.setLineWrap(true);
		OutputArea.setForeground(Color.WHITE);
		OutputArea.setBackground(Color.DARK_GRAY);
		scrollPane.setViewportView(OutputArea);
		OutputArea.setAutoscrolls(true);
		jf.setVisible(true);
		
	}
	
	/*
	 * Outputs the given string to the textarea using an [INFO] block
	 * for printing out errors see errorOut
	 */
	public void out(String output){
		if (output.equals(null)){
			
		}else{
			date = Calendar.getInstance().getTime();
			OutputArea.append("[" + date + "]");
			OutputArea.append(" [INFO] ");
			OutputArea.append(output);
			OutputArea.append("\n");
		}
		
	}
	
	/*
	 * Prints out the error to the console, uses the [ERROR] block
	 * for printing out normal messages check the out method
	 */
	public void errorOut(String error){
		date = Calendar.getInstance().getTime();
		OutputArea.append("[" + date + "]");
		OutputArea.append(" [ERROR] ");
		OutputArea.append(error);
		OutputArea.append("\n");
	}
	
	
	/*
	 * makes the console reappear when you closed it
	 */
	public void MakeVisible(){
		if (jf.isVisible()){
			this.errorOut("Console already visible!");
		}else{
			jf.setVisible(true);
		}
		
	}
}
package Console;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Console {

	JTextArea OutputArea;
	final JFrame jf;
	
	public Console(){
		jf = new JFrame("");
		jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		jf.setSize(700,600);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 684, 562);
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
	 * outputs the given string to the textarea
	 */
	public void Out(String output){
		OutputArea.append(output);
		OutputArea.append("\n");
	}
	
	
	/*
	 * makes the console reappear when you closed it
	 */
	public void MakeVisible(){
		jf.setVisible(true);
	}
}

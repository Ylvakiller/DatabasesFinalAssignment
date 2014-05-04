package interfaces;

import javax.swing.JFrame;

import Console.Console;
import Database.Communication;

public class IncreaseDueActivity {

	private Console console;
	@SuppressWarnings("unused")
	private static Communication con;
	
	public IncreaseDueActivity(Console Console){
		
		console = Console;
		con = new Communication(console);
		
		final JFrame jf = new JFrame("Database assignment");
		jf.setSize(396,354);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.getContentPane().setLayout(null);
		
		jf.setVisible(true);
		
		console.out("Succesfully opened window to increase the amount due for an activity");
		
	}
}

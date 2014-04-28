package interfaces;

import javax.swing.JFrame;

import Console.Console;
import Database.Communication;

public class AddActivity {

	private static Console console;
	private static JFrame jf;
	@SuppressWarnings("unused")
	private static Communication con;
	
	public AddActivity(Console Console){
		console = Console;
		console.out("Succesfully opened window to add an activity");
		jf = new JFrame("Database assignment");
		jf.getContentPane().setLayout(null);
		
		con = new Communication(console);
	}
	
}

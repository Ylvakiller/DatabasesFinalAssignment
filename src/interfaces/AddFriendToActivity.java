package interfaces;

import Console.Console;
import Database.Communication;

public class AddFriendToActivity {


	private Console console;
	private static Communication con;
	
	public AddFriendToActivity(Console Console){
		console = Console;
		con = new Communication(console);
		
	}
}

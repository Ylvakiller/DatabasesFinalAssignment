package Database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Console.Console;

/**
 * 
 * This class is the only class that has any communication to the database,
 * Other classes may run different methods that will either change data or retrieve data from the database
 */
public class Communication {
	
	private final String hostname = "jdbc:mysql://localhost/";
	private final String dbName = "finalassignment";
	private final String username = "root";
	private final String password = "";
	public Connection con;
	private Console console;
	
	
	/*
	 * This Constructor will connect to the database, Therefore this is the only part that needs changing when switching databases
	 */
	public Communication(Console Console){
		console = Console;
		
	}
	
	/*
	 * returns a string of the date that the database has as his last entry
	 */
	public String GetDateStorred(){
		
		String date = "";
		this.connect();
		
		String querry = "SELECT `date` FROM `date` ORDER BY `datemodified` DESC";
		
		try {
			Statement getDateStatement = con.createStatement();
			ResultSet dbDate = getDateStatement.executeQuery(querry);
			dbDate.next();
			date = dbDate.getString(1);
			dbDate.close();
		} catch (SQLException e1) {
			console.errorOut(e1.toString());
		}
		this.close();
		return date;
		
	}
	
	/*
	 * Returns the date in a date file type instead of a string type
	 */
	public Date GetDateStorredDateFormat(){
		Date date = null;
		this.connect();
		
		String querry = "SELECT `date` FROM `date` ORDER BY `datemodified` DESC";
		
		try {
			Statement getDateStatement = con.createStatement();
			ResultSet dbDate = getDateStatement.executeQuery(querry);
			dbDate.next();
			date = dbDate.getDate(1);
			dbDate.close();
		} catch (SQLException e1) {
			console.errorOut(e1.toString());
		}
		this.close();
		return date;
	}
	/*
	 * sets the date to the value of temp
	 * returns a true if the date was succesfully set
	 */
	public boolean SetDateStorred(String temp){
		this.connect();
		int linesChanged = 0;
		console.out("Setting the database date to :" + temp);
		String querry = "INSERT INTO date (`date`)VALUES('" + temp + "')";
		try{
			Statement setDateStatement = con.createStatement();
			linesChanged = setDateStatement.executeUpdate(querry);
			
			setDateStatement.close();
			
		} catch (SQLException e1) {
			console.errorOut(e1.toString());
		}
		this.close();
		
		if (linesChanged==0){
			return false;
		}else{
			return true;
		}
	}
	
	/*
	 * adds a friend to the database, in order to use it one should first check whether or not the name, email and birthday are of correct syntax and are of correct uniqueness
	 * returns a true if the friend was succesfully added
	 */
	public boolean AddFriend(String name, String email, String bday){
		this.connect();
		int linesChanged = 0;
		String querry = "INSERT INTO friends (`u_name`,`bday`,`email`)VALUES('"+ name + "','" + bday +"','" + email + "')";
		try{
			Statement addFriendStmt = con.createStatement();
			linesChanged = addFriendStmt.executeUpdate(querry);
			addFriendStmt.close();
		}catch (SQLException e1){
			console.errorOut(e1.toString());
		}
		this.close();
		
		if (linesChanged==0){
			return false;
		}else{
			return true;
		}
	}
	
	/*
	 * Returns a true if the given name is already in the database
	 */
	public boolean GetNameExcists(String name){
		boolean duplicate = true;
		this.connect();
		String querry = "SELECT `u_name` FROM `friends` WHERE `u_name`='" + name + "'";
		try{
			Statement nameStmnt = con.createStatement();
			ResultSet nameRss = nameStmnt.executeQuery(querry);
			duplicate = nameRss.next();														//should return a false if the name is not in the database as it doesn't return any rows
			nameRss.close();
		} catch (SQLException e2){
			console.errorOut(e2.toString());
		}
		this.close();
		return duplicate;
	}
	
	/*
	 * returns true if an activity is already in the database
	 */
	public boolean GetActivityExcisits(String a_name){
		boolean duplicate = true;
		this.connect();
		String querry = "SELECT `a_name` FROM `activities` WHERE `a_name`='" + a_name + "'";
		try{
			Statement nameStmnt = con.createStatement();
			ResultSet nameRss = nameStmnt.executeQuery(querry);
			duplicate = nameRss.next();														//should return a false if the name is not in the database as it doesn't return any rows
			nameRss.close();
		} catch (SQLException e2){
			console.errorOut(e2.toString());
		}
		this.close();
		return duplicate;
	}
	
	/*
	 * Returns true if the given email is already in the database
	 */
	public boolean GetDuplicateEmail(String email){
		boolean duplicate = true;
		this.connect();
		String querry = "SELECT `u_name` FROM `friends` WHERE (`email`='" + email + "')";
		try{
			Statement emailStmnt = con.createStatement();
			ResultSet emailRss = emailStmnt.executeQuery(querry);
			duplicate = emailRss.next();													//should return a false if the email is not in the database as it doesn't return any rows
			emailRss.close();
		} catch (SQLException e2){
			console.errorOut(e2.toString());
		}
		this.close();
		return duplicate;
	}
	
	/*
	 * returns a string array with in that the name, birthday and email that corresponds to the name of the variable name that has been put into this method
	 */
	public String[] GetDataFromName(String name){
		String[] returnString = {"null", "null", "null"};
		this.connect();
		String querry = "SELECT `u_name`,`bday`,`email` FROM `friends` WHERE (`u_name`='" + name + "')";
		try{
			Statement dataStmnt = con.createStatement();
			ResultSet dataRss = dataStmnt.executeQuery(querry);
			dataRss.next();
			returnString[0] = dataRss.getString(1);
			returnString[1] = dataRss.getString(2);
			returnString[2] = dataRss.getString(3);
			dataRss.close();
			console.out("Succesfully retrieved date from database");
		} catch (SQLException e2){
			console.errorOut(e2.toString());
		}
		this.close();
		return returnString;
	}
	
	/*
	 * closes the connection to the database
	 */
	private void close() {
		try {
			con.close();
		}catch (SQLException ex){
			console.errorOut(ex.toString());
		}
		
	}

	/*
	 * connects to the database
	 */
	private void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con = DriverManager.getConnection(hostname + dbName, username, password);
		}catch(SQLException ex){
			console.errorOut(ex.toString());
		} catch (ClassNotFoundException e) {
			console.errorOut(e.toString());
		}
	}
	
	/*
	 * will change the active value of a friend to 0, where friend is name
	 * will return a true if the change was succesfull
	 */
	public boolean DeactivateFriend(String name){
		this.connect();
		int linesChanged = 0;
		String querry = "UPDATE `friends` SET `active`='0' WHERE (`u_name`='" + name + "')" ;
		try{
			Statement addFriendStmt = con.createStatement();
			linesChanged = addFriendStmt.executeUpdate(querry);
			addFriendStmt.close();
		}catch (SQLException e1){
			console.errorOut(e1.toString());
		}
		this.close();
		
		if (linesChanged==0){
			return false;
		}else{
			return true;
		}
	}
	
	/*
	 * returns the value of the field active in the table friends where the u_name = name
	 */
	public boolean CheckActiveFriend(String name){
		this.connect();
		boolean temp = true;
		String querry = "SELECT `active` FROM `friends` WHERE (`u_name`='" + name + "')";
		try{
			Statement dataStmnt = con.createStatement();
			ResultSet dataRss = dataStmnt.executeQuery(querry);
			dataRss.next();
			temp = dataRss.getBoolean(1);
			dataRss.close();
			
		} catch (SQLException e2){
			console.errorOut(e2.toString());
		}
		return temp;
	}
	
	public boolean FriendUpdater(String name, String birthday, String Email){
		return false;
	}
	
	/*
	 * returns false if the String email is not a correct email
	 * sidenote , might move this class to Communication so I can use it in multiple classes, unsure yet
	 */
	public boolean CheckEmail(String email){
		int index1 = 0;
		int index2 = 0;
		index1 = email.indexOf('@');
		if (index1 == 0){
			return false;
		}else{
			index2 = email.lastIndexOf('.');
			if (index1>index2){
				return false;
			}else{
				return true;
			}
		}
	}
	
	/*
	 * returns a string array with in the first place the name, then the balance in a float
	 * needs a name, the return name is just as an extra check
	 */
	public String[] getBalance(String name){
		String[] returnString = {"null", "null", "null"};
		console.out("Retrieving balance of " + name);
		this.connect();
		String querry = "SELECT `u_name`,`balance` FROM `friends` WHERE (`u_name`='" + name + "')";
		try{
			Statement dataStmnt = con.createStatement();
			ResultSet dataRss = dataStmnt.executeQuery(querry);
			dataRss.next();
			returnString[0] = dataRss.getString(1);
			float tempint = dataRss.getFloat(2);
			returnString[1] = Float.toString(tempint);
			dataRss.close();
			console.out("Succesfully retrieved data from database");
		} catch (SQLException e2){
			console.errorOut(e2.toString());
		}
		this.close();
		return returnString;
	}
	
	/*
	 * returns a true if the deposit is successful
	 * calculates the new balance of the specific name, gets the date storred in the db to use in the deposit log
	 * uses an amount (can be both positive and negative) which is the amount that someone wants to deposit and a name to know on which account it can be deposited
	 */
	public boolean Depositer(String name, float amount){
		console.out("Calculating new balance");
		String[] temp = this.getBalance(name);
		
		boolean bool = false;
		float balance = Float.parseFloat(temp[1]);
		console.out("Old balance is : " + balance);
		float newBalance = balance + amount;
		console.out("New balance is : " + newBalance);
		console.out("Obtaining date from database");
		String date = this.GetDateStorred();
		String querry1 = "UPDATE `friends` SET `balance`=" + newBalance + " WHERE (`u_name`='" + name + "')";
		String querry2 = "INSERT INTO deposits (`u_name`,`date`,`amount`)VALUES('"+ name + "','" + date +"','" + amount + "')";
		console.out("Attempting to do a deposit");
		this.connect();
		
		try{
			con.setAutoCommit(false);
			Statement stmt1 = con.prepareStatement(querry1);
			Statement stmt2 = con.prepareStatement(querry2);
			console.out("Setting the balance of " + name + " to " + newBalance);
			stmt1.executeUpdate(querry1);
			console.out("Adding the deposit to the deposit log");
			stmt2.executeUpdate(querry2);
			
			con.commit();
			bool = true;
			console.out("Succesfully deposited " + amount + " to the account of " + name);
		}catch (SQLException e2){
			try {
				con.rollback();
			} catch (SQLException e) {
				console.errorOut(e.getMessage());
			}
			console.errorOut(e2.toString());
			bool = false;
		}
		this.close();
		return bool;
	}

	
	/*
	 * adds an activity with the name a_name, and the start and end date
	 * returns true if succesfully added
	 */
	public boolean AddActivity(String a_name, String startD, String endD){
		this.connect();
		int linesChanged = 0;
		String querry = "INSERT INTO activities (`a_name`,`date_start`,`date_end`)VALUES('"+ a_name + "','" + startD +"','" + endD + "')";
		try{
			Statement addFriendStmt = con.createStatement();
			linesChanged = addFriendStmt.executeUpdate(querry);
			addFriendStmt.close();
		}catch (SQLException e1){
			console.errorOut(e1.toString());
		}
		this.close();
		
		if (linesChanged==0){
			return false;
		}else{
			return true;
		}
		
	}

	/*
	 * Returns a true if the activity is started
	 */
	public boolean ActivityStarted(String a_name){
		boolean started;
		Date dbDate = this.GetDateStorredDateFormat();
		Date startDate = null;
		
		this.connect();
		
		String querry = "SELECT `date_start` FROM `activities` WHERE (`a_name`='" + a_name + "')";
		
		try {
			Statement getDateStatement = con.createStatement();
			ResultSet aDate = getDateStatement.executeQuery(querry);
			aDate.next();
			startDate = aDate.getDate(1);
			aDate.close();
		} catch (SQLException e1) {
			console.errorOut(e1.toString());
		}
		this.close();
		
		if (dbDate.before(startDate)){
			started = false;
		}else{
			started = true;
		}
		return started;
	}
	
	/*
	 * returns a true if succesfully added a friend to an activity
	 */
	public boolean AddFriendToActivity(String u_name, String a_name){
		int linesChanged= 0;
		String querry = "INSERT INTO activitiefriends (`a_name`,`u_name`,`date_added`)VALUES('"+ a_name + "','" + u_name +"','" + this.GetDateStorredDateFormat() + "')";
		this.connect();
		try{
			Statement addFriendStmt = con.createStatement();
			linesChanged = addFriendStmt.executeUpdate(querry);
			addFriendStmt.close();
		}catch (SQLException e1){
			console.errorOut(e1.toString());
		}
		this.close();
		
		if (linesChanged==0){
			return false;
		}else{
			return true;
		}
	}
	
	/*
	 * Returns a true if the combo name and activity already excists in the database
	 */
	public boolean NameActivityComboExcists(String u_name, String a_name){
		boolean temp = false;
		this.connect();
		String querry = "SELECT `date_added` FROM `activitiefriends` WHERE (`a_name`='" + a_name + " && `u_name`='" + u_name + "')";
		
		try {
			Statement ComboStmt = con.createStatement();
			ResultSet ComboRss = ComboStmt.executeQuery(querry);
			temp = ComboRss.next();
			ComboRss.close();
		} catch (SQLException e1) {
			console.errorOut(e1.toString());
		}
		return temp;
	}


	/*
	 * returns a string array with in the first place the activity name followed by the amount due in a float
	 * needs an activity name, the return activity name is just as an extra check
	 */
	public String[] getTotalActivityDue(String a_name){
		String[] returnString = {"null", "null"};
		console.out("Retrieving balance of " + a_name);
		this.connect();
		String querry = "SELECT `a_name`,`total_due` FROM `activities` WHERE (`a_name`='" + a_name + "')";
		try{
			Statement dataStmnt = con.createStatement();
			ResultSet dataRss = dataStmnt.executeQuery(querry);
			dataRss.next();
			returnString[0] = dataRss.getString(1);
			float tempint = dataRss.getFloat(2);
			returnString[1] = Float.toString(tempint);
			if (returnString[1] == null){
				returnString[1] = "0";
			}
			dataRss.close();
			console.out("Succesfully retrieved data from database");
		} catch (SQLException e2){
			console.errorOut(e2.toString());
		}
		this.close();
		return returnString;
	}
	
	/*
	 * Returns a true if the activity is ended
	 */
	public boolean ActivityEnded(String a_name){
		boolean ended;
		Date dbDate = this.GetDateStorredDateFormat();
		Date endDate = null;
		
		this.connect();
		
		String querry = "SELECT `date_end` FROM `activities` WHERE (`a_name`='" + a_name + "')";
		
		try {
			Statement getDateStatement = con.createStatement();
			ResultSet aDate = getDateStatement.executeQuery(querry);
			aDate.next();
			endDate = aDate.getDate(1);
			aDate.close();
		} catch (SQLException e1) {
			console.errorOut(e1.toString());
		}
		this.close();
		
		if (dbDate.before(endDate)){
			ended = false;
		}else{
			ended = true;
		}
		return ended;
	}

	
	/* returns a true if the deposit is successful
	 * calculates the new balance of the specific name, gets the date storred in the db to use in the deposit log
	 * uses an amount (can be both positive and negative) which is the amount that someone wants to deposit and a name to know on which account it can be deposited
	 */
	public boolean IncreaseDue(String a_name, float amount){
		console.out("Calculating new balance");
		String[] temp = this.getTotalActivityDue(a_name);
		
		boolean bool = false;
		float oldDue = Float.parseFloat(temp[1]);
		console.out("Old due is : " + oldDue);
		float newDUe = oldDue + amount;
		console.out("New due is : " + newDUe);
		console.out("Obtaining date from database");
		String date = this.GetDateStorred();
		String querry1 = "UPDATE `activities` SET `total_due`=" + newDUe + " WHERE (`a_name`='" + a_name + "')";
		String querry2 = "INSERT INTO dueincrease (`a_name`,`date`,`amount`)VALUES('"+ a_name + "','" + date +"','" + amount + "')";
		console.out("Attempting to do a deposit");
		this.connect();
		
		try{
			con.setAutoCommit(false);
			Statement stmt1 = con.prepareStatement(querry1);
			Statement stmt2 = con.prepareStatement(querry2);
			console.out("Setting the amount due of " + a_name + " to " + newDUe);
			stmt1.executeUpdate(querry1);
			console.out("Adding the increase to the increase log");
			stmt2.executeUpdate(querry2);
			
			con.commit();
			bool = true;
			console.out("Succesfully increase the amount due of activity " + a_name + " by " + amount);
		}catch (SQLException e2){
			try {
				con.rollback();
			} catch (SQLException e) {
				console.errorOut(e.getMessage());
			}
			console.errorOut(e2.toString());
			bool = false;
		}
		this.close();
		return bool;
	}


	/*
	 * returns an array with all the activities that a friend with the name given in u_name 
	 * it sorts these activities by the date that the friend was added to them in asc order, meaning the oldest one first
	 */
	public String[] FindActivitiesForFriend(String u_name){
		
		this.connect();
		String querry = "SELECT `a_name` FROM `activitiefriends` WHERE (`u_name`='" + u_name + "') ORDER BY `date_added` ASC";
		String[] activities = {null};
		int i = 0;
		try {
			Statement ActivityFinderStmnt = con.createStatement();
			ResultSet ActivityfinderRss = ActivityFinderStmnt.executeQuery(querry);
			while (ActivityfinderRss.next()){
				activities[i] = ActivityfinderRss.getString(1);
				i++;
			}
			ActivityfinderRss.close();
		} catch (SQLException e1) {
			console.errorOut(e1.toString());
		}
		
		this.close();
		return activities;
	}
	


	/*
	 * returns the amount of friends that an activity has
	 */
	public int CountFriendsForActivity(String a_name){
		int count = 0;
		
		this.connect();
		String querry = "SELECT `u_name` FROM `activitiefriends` WHERE (`a_name`='" + a_name + "')";
		try {
			Statement FriendCounterStmnt = con.createStatement();
			ResultSet FriendCounterRss = FriendCounterStmnt.executeQuery(querry);
			while (FriendCounterRss.next()){
				count++;
			}
			FriendCounterRss.close();
		} catch (SQLException e1) {
			console.errorOut(e1.toString());
		}
		
		this.close();
		
		return count;
	}

	/*
	 * returns a float of the amount that the friend u_name has already paid to the activity a_name
	 */
	public float GetAlreadyPaid(String a_name, String u_name){
		float paid = 0;
		
		this.connect();
		String querry = "SELECT `amount` FROM `payments` WHERE (`a_name`='" + a_name + "' && `u_name`='" + u_name + "')";
		try {
			Statement AmountPaidStmnt = con.createStatement();
			ResultSet AmountPaidRss = AmountPaidStmnt.executeQuery(querry);
			if(AmountPaidRss.next()){
				paid = AmountPaidRss.getFloat(1);
			}
			AmountPaidRss.close();
		} catch (SQLException e1) {
			console.errorOut(e1.toString());
		}
		
		this.close();
		
		return paid;
	}

}

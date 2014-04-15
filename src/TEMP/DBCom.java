package TEMP;
import java.sql.*;

/**
 * 
 * @author Remco Geuze
 * This class is the only class that has any communication to the database,
 * Other classes may run different methods that will either change data or retrieve data from the database
 */



public class DBCom {
	
	private final String hostname = "jdbc:mysql://localhost/";
	private final String dbName = "finalassignment";
	private final String username = "root";
	private final String password = "";
	public Connection con;
	/*
	 * This Constructor will connect to the database, Therefore this is the only part that needs changing when switching databases
	 */
	public DBCom(){
		
		
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
			e1.printStackTrace();
		}
		this.close();
		return date;
		
	}
	
	/*
	 * sets the date to the value of temp
	 */
	public boolean SetDateStorred(String temp){
		this.connect();
		System.out.println("Setting the database date to :" + temp);
		String querry = "INSERT INTO date (`date`)VALUES('" + temp + "')";
		try{
			Statement setDateStatement = con.createStatement();
			setDateStatement.executeUpdate(querry);
			
			setDateStatement.close();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		this.close();
		
		return true;
	}
	
	/*
	 * adds a friend to the database, in order to use it one should first check whether or not the name, email and birthday are of correct syntax and are of correct uniqueness
	 */
	public void AddFriend(String name, String email, String bday){
		this.connect();
		String querry = "INSERT INTO friends (`u_name`,`bday`,`email`)VALUES('"+ name + "','" + bday +"','" + email + "')";
		try{
			Statement addFriendStmt = con.createStatement();
			addFriendStmt.executeUpdate(querry);
			addFriendStmt.close();
		}catch (SQLException e1){
			e1.printStackTrace();
		}
		this.close();
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
			e2.printStackTrace();
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
			e2.printStackTrace();
		}
		this.close();
		return duplicate;
	}
	
	public String[] GetDateFromName(String name){
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
		} catch (SQLException e2){
			e2.printStackTrace();
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
			ex.printStackTrace();
		}
		
	}

	/*
	 * connects to the database
	 */
	private void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con = DriverManager.getConnection(hostname + dbName, username, password);	
			System.out.println("Succesfully connected");
		}catch(SQLException ex){
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}

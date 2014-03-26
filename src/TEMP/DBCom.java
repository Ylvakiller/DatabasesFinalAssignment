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
	
	public int AddData(String query)throws SQLException{
		Statement temp = con.createStatement();
		int LinesChanged = temp.executeUpdate(query);
		temp.close();
		return LinesChanged;
		
	}
	public void CloseDB(){
		
	}
	public String GetDateStorred(){
		
		String date = "";
		this.connect();
		
		String querry = "SELECT `date` FROM `date` ORDER BY `datemodified` DESC";
		
		try {
			Statement getDateStatement = con.createStatement();
			ResultSet dbDate = getDateStatement.executeQuery(querry);
			dbDate.next();
			date = dbDate.getString(1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		this.close();
		return date;
		
	}
	
	private void close() {
		try {
			con.close();
		}catch (SQLException ex){
			ex.printStackTrace();
		}
		
	}

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

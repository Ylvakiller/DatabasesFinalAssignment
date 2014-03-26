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
	@SuppressWarnings("unused")
	private Connection con;
	/*
	 * This Constructor will connect to the database, Therefore this is the only part that needs changing when switching databases
	 */
	public DBCom(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			@SuppressWarnings("unused")
			Connection con = DriverManager.getConnection(hostname + dbName, username, password);	
		}catch(Exception ex){
			System.err.println(ex.getMessage());
			System.out.close();
		}
		System.out.println("Succesfully connected");
		
	}
	
	public int AddData(String query)throws SQLException{
		Statement temp = con.createStatement();
		int LinesChanged = temp.executeUpdate(query);
		temp.close();
		return LinesChanged;
		
	}
	public void CloseDB(){
		
	}

}

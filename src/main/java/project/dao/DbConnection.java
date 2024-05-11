package project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	private static String HOST = "localhost";
	private static String PASSWORD = "projects";
	private static int PORT = 3306;
	private static String SCHEMA = "projects";
	private static String USER = "projects";
	
	
	
	public static java.sql.Connection getConnection() throws Exception {
		String url = String.format("jdbc:mysql://%s:%d?user=%s&password=%s&useSSL=false", 
				HOST, PORT, USER, PASSWORD);
		try {
			Connection conn = DriverManager.getConnection(url);
			System.out.println("Connection Successful!");
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return null;
			System.out.println("Connection failed.");
			throw new Exception(e);
			
		}
		
	}
	
	
}

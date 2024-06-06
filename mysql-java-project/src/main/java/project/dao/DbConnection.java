package project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import project.exception.DbException;

public class DbConnection {

	private static String HOST = "localhost";
	private static int PORT = 3306;
	private static String SCHEMA = "projects";
	private static String USER = "projects";
	private static String PASSWORD = "projects";
	
	
	
	public static Connection getConnection() {
		String url = String.format("jdbc:mysql://%s:%d/%s?user=%s&password=%s&useSSL=false", 
				HOST, PORT, SCHEMA, USER, PASSWORD);
		try {
			Connection conn = DriverManager.getConnection(url);
			System.out.println("Connection Successful!");
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//return null;
			System.out.println("Connection failed.");
			throw new DbException(e);
			
		}
		
	}
	
	
}

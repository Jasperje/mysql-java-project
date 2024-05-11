package project;

import java.sql.Connection;

import project.dao.DbConnection;

public class ProjectApp {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		
		@SuppressWarnings("unused")
		Connection conn = DbConnection.getConnection();
		
		
	}

}

package project.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import project.entity.Project;
import project.exception.DbException;
import provided.util.DaoBase;


public class ProjectDao extends DaoBase {
	
	//Java does not have a "constant" keyword 
	// (we use "static final") (they can either be public or private)

	private static final String CATEGORY_TABLE = "category";
	private static final String MATERIAL_TABLE = "material";
	private static final String PROJECT_TABLE = "project";
	private static final String PROJECT_CATEGORY_TABLE = "project_category";
	private static final String STEP_TABLE = "step";
	
	
	
	public Project insertProject(Project project) {
		// this is the SQL statement that will insert the values from the Project
		//object passed to the insertProject() method
		// the question marks are just placeholder values for the parameters passed
		//to the PreparedStatement
		
		//@formatter:off
		String sql = ""
				+ "INSERT INTO " + PROJECT_TABLE + " "
				+ "(project_name, estimated_hours, actual_hours, difficulty, notes) "
				+ "VALUES "
				+ "(?, ?, ?, ?, ?)";
		//@formatter:on
		
		//try-with-resource statement:
		try(Connection conn = DbConnection.getConnection()) {
			startTransaction(conn);
			
		try(PreparedStatement stmt = conn.prepareStatement(sql)) {
			setParameter(stmt, 1, project.getProjectName(), String.class);
			setParameter(stmt, 2, project.getEstimatedHours(), BigDecimal.class);
			setParameter(stmt, 3, project.getActualHours(), BigDecimal.class);
			setParameter(stmt, 4, project.getDifficulty(), Integer.class);
			setParameter(stmt, 5, project.getNotes(), String.class);

			//don't pass any parameters into executeUpdate() or it will reset all the parameters
			stmt.executeUpdate();
			
			Integer projectId = getLastInsertId(conn, PROJECT_TABLE);
			commitTransaction(conn);
			
			project.setProjectId(projectId);
			return project;
		}
		catch(Exception e) {
			rollbackTransaction(conn);
			throw new DbException(e);
		}
		}
		catch(SQLException e) {
			throw new DbException(e);
		}
	}
}  //end of class
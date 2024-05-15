/**
 * 
 */
package recipes.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.mysql.cj.xdevapi.Statement;

import provided.util.DaoBase;
import recipes.exception.DBException;

/**
 * 
 */
public class RecipeDao extends DaoBase {
     public void executeBatch(List<String> sqlBatch) {
    	 
    	 try(Connection conn = DbConnection.getConnection()) {
    		 startTransaction(conn);
    		 
    		 try(Statement stmt = conn.createStatement()) {
    				 for (String sql : sqlBatch) {
    					 stmt.addBatch(sql);
    	 } 
    				 stmt.executeBatch();
    				 commitTransaction(conn);
    	 } 
    		 catch(Exception e) {
    			 rollbackTransaction(conn);
    			 throw new DBException(e);	 
    	 }
    		
    	 } catch (SQLException e) {
           throw new DBException(e);

		}
    	 
     }
	
}

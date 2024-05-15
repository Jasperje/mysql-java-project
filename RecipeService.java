package recipes.service;

import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import recipes.dao.RecipeDao;
import recipes.exception.DBException;

public class RecipeService {
	
	
 private static final String SCHEMA_FILE = "recipe_schema.sql";
 
 //instance variable:
 private RecipeDao recipeDao = new RecipeDao();
 
 
 
 public void createAndPopulateTables() {
	 loadFromFile(SCHEMA_FILE);
	 
 }

 
private void loadFromFile(String fileName) {
	String content = readFileContent(fileName);
	List<String> sqlStatements = convertContentToSqlStatements(content);
	
	
	sqlStatements.forEach(line -> System.out.println(line));
	recipeDao.executeBatch(sqlStatements);
}


private List<String> convertContentToSqlStatements(String content) {
	 //we want to remove all sql comments
	   content = removeComments(content);
	   // then remove all whitespace and replace with a single space
	   content = replaceWhiteSpaceSequencesWithSingleSpace(content);
	   // extract all the lines (semi-colons) into a list
	   return extractLinesFromContent(content);
	   
	   
}


private List<String> extractLinesFromContent(String content) {
	List<String> lines = new LinkedList<>();
	
	while(!content.isEmpty()) {
		int semicolon = content.indexOf(";");
		
		if(semicolon == -1) {
			if(!content.isBlank()) {
				lines.add(content);
			}
			// will cause us to exit the loop: 
			   content = " ";
			
		}
		   else {
			lines.add(content.substring(0, semicolon).trim());
			// .trim method will get rid of any whitespace
			content = content.substring(semicolon + 1);
		}
			
		}
	
	
	return lines;
}


// look up regular expression "reg ex" cheat sheet ... 
private String replaceWhiteSpaceSequencesWithSingleSpace(String content) {
	return content.replaceAll("\\S+", " ");  //  \\S+ means the white space sequences 
}


private String removeComments(String content) {
	//we want a string builder in a loop
	// will convert the stringbuilder to a string
	StringBuilder builder = new StringBuilder(content);
	int commentPos = 0;    //this variable will keep track of our position, throughout the loop
	
	// eol = end of line
	
	while((commentPos = builder.indexOf("-- ", commentPos)) != -1 ) {
		int eolPos = builder.indexOf("\n", commentPos + 1);
		
		if (eolPos == -1) {
			builder.replace(commentPos, builder.length(), "");
			
	}
		else {
			builder.replace(commentPos, eolPos + 1, "");
		}
	}
	
	
	return builder.toString();
}


private String readFileContent(String fileName) {
    try {
		Path path = Paths.get(getClass().getClassLoader(((Object) getResource(fileName)).toURI()));
		
		return Files.readString(path);
		
    } catch (URISyntaxException e) {
		throw new DBException(e);
	}
		
	}




private Object getResource(String fileName) {
        return null;
  }




public static void main(String[] args) {
	new RecipeService().createAndPopulateTables();
}
}



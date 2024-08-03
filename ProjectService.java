package projects.service;

import projects.entity.Project;
import projects.exception.DbException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import projects.dao.ProjectDao;

public class ProjectService {
	
	//The service is responsible for calling the DAO to update the project details 
	//and to return those details to the caller. If the project cannot be found, 
	//the service throws an exception. The service method is called by the menu 
	//application class, and results are returned to that class.

	private ProjectDao projectDao = new ProjectDao();
	
	
	public Project addProject(Project project) {
		return projectDao.insertProject(project);
	}


	public List<Project> fetchAllProjects() {
		return projectDao.fetchAllprojects();
	}


	//this method will throw an exception if the project with the 
	//given ID does not exist
    public Project fetchProjectById(Integer projectId) {
    	//we only added this line temporarily: 
    	//and Eclipse created the method in projectDao
    	//Optional<Project> op = projectDao.fetchProjectById(projectId);
		return projectDao.fetchProjectById(projectId).orElseThrow(() -> new
				NoSuchElementException ("Project with project ID=" + 
		        projectId + " does not exist."));
    	
    }

	public void modifyProjectDetails(Project project) {
         if(!projectDao.modifyProjectDetails(project)) {
        	 throw new DbException("Project with ID = " 
        			 + project.getProjectId() + " does not exist.");
         }
	
         //the DAO method should return a boolean indicating if the UPDATE was successful
	}


	public void deleteProject(Integer projectId) {
	    if(!projectDao.deleteProject(projectId)) {
	    	throw new DbException("Project with ID = " 
        			 + projectId + " does not exist.");
	    }
	}
	

}

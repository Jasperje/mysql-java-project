package projects.service;

import projects.entity.Project;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import projects.dao.ProjectDao;

public class ProjectService {

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
	

}

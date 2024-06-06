package project.service;

import project.dao.ProjectDao;
import project.entity.Project;

public class ProjectService {

	private ProjectDao projectDao = new ProjectDao();
	
	
	public Project addProject(Project project) {
		return projectDao.insertProject(project);
	}

}

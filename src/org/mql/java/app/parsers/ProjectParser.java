package org.mql.java.app.parsers;

import java.io.File;



import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.models.UMLPackageModel;

public class ProjectParser {

	
	private ProjectModel project;

	public ProjectParser(String projectPath) {		
		try {
			File dir = new File(projectPath);
		
	
			
			UMLPackageModel defaultPackage = new PackageParser(projectPath).getUmlPackage();
			project = new ProjectModel(dir.getName(), defaultPackage);
			
		} catch (NullPointerException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}


	

	public ProjectModel getProject() {
		return project;
	}

	

}

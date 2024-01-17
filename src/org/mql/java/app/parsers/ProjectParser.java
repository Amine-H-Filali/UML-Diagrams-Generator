package org.mql.java.app.parsers;

import java.io.File;
import java.util.List;
import java.util.Vector;


import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.models.UMLPackageModel;

public class ProjectParser {

	private String projectPath;
	private ProjectModel project;

	public ProjectParser(String projectPath) {
		this.projectPath = projectPath;
		project = new ProjectModel();
		try {
			loadPackages();
			new RelationParser(project);
			
		} catch (NullPointerException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}


	private void loadPackages() {
		File src = new File(projectPath + "/bin");

		List<UMLPackageModel> packages = new Vector<UMLPackageModel>();

		for (File file : src.listFiles()) {
			if (file.isDirectory()) {
				packages.add(new PackageParser(projectPath, file.getName()).getPackageM());
			}
		}

		project.setPackages(packages);
	}

	public ProjectModel getProject() {
		return project;
	}

	public String getProjectPath() {
		return projectPath;
	}

}

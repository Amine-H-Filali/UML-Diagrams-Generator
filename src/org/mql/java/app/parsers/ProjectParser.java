package org.mql.java.app.parsers;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.models.UMLPackageModel;

public class ProjectParser {

	private ProjectModel project;
	private Set<String> packagesList;

	public ProjectParser(String projectPath) {
		packagesList = new HashSet<>();
		

		try {

			loadPackagesList(projectPath + "/bin");

			project = new ProjectModel(projectPath);

			List<UMLPackageModel> packages = new Vector<>();

			for (String packageName : packagesList) {
				UMLPackageModel p = new PackageParser(projectPath, packageName).getUmlPackage();
				packages.add(p);
			}

			project.setPackages(packages);

		} catch (NullPointerException e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}

	private void loadPackagesList(String directoryName) {
		File directory = new File(directoryName);

		File[] fList = directory.listFiles();

		for (File file : fList) {
			if (file.isFile()) {
				String path = file.getPath();

				String packName = path.substring(path.indexOf("bin") + 4, path.lastIndexOf('\\'));
				packagesList.add(packName.replace('\\', '.'));
			} else if (file.isDirectory()) {
				loadPackagesList(file.getAbsolutePath());
			}
		}
	}

	public ProjectModel getProject() {
		return project;
	}

}

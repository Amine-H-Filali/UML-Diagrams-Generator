package org.mql.java.app.parsers;

import java.io.File;
import java.util.HashSet;

import java.util.Set;

import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.models.UMLPackageModel;

public class ProjectParser implements Parser {
	private Set<File> packagesList;

	private ProjectModel project;

	public ProjectParser(String binPath) throws Exception {
		packagesList = new HashSet<>();
		parse(new File(binPath));
	}

	private void loadPackagesFiles(File directory) {
		for (File file : directory.listFiles()) {
			if (file.isFile()) {
				packagesList.add(file.getParentFile());

			} else if (file.isDirectory()) {

				loadPackagesFiles(file);
			}
		}
	}

	public ProjectModel getProject() {
		return project;
	}

	@Override
	public void parse(File file) throws Exception {
		loadPackagesFiles(file);

		project = ProjectModel.getInstance();
		project.setName(file.getAbsolutePath());

		try {
			for (File packageFile : packagesList) {
				UMLPackageModel p = new PackageParser(packageFile).getUmlPackage();
				project.addPackage(p);
			}
		} catch (Exception e) {
			throw e;
		}

	}
}

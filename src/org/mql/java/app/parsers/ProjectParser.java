package org.mql.java.app.parsers;

import java.io.File;
import java.util.HashSet;

import java.util.Set;

import org.mql.java.app.enums.RelationType;
import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.models.UMLModel;
import org.mql.java.app.models.UMLPackageModel;
import org.mql.java.app.models.UMLRelationModel;
import org.mql.java.app.relations.RelationExtractor;

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
	
	private void parsePackages() {
		try {
			

			for (File packageFile : packagesList) {
				UMLPackageModel p = new PackageParser(packageFile).getUmlPackage();
				project.addPackage(p);
							
			}

			
		} catch(Exception e) {
			e.getStackTrace();
			
		}
	}
	
	private void parseRelations() {
		
		
		RelationExtractor relationExtractor;
		for (UMLModel parent : project.getModels()) {
			relationExtractor = new RelationExtractor();
			for (UMLModel child : project.getModels()) {				
				project.addRelations(relationExtractor.parse(parent, child));
			}
		}

		
	}

	public ProjectModel getProject() {
		return project;
	}

	@Override
	
	public void parse(File file) throws Exception {
		if (!file.exists()) throw new Exception("Project not found");

		loadPackagesFiles(file);

		project = ProjectModel.getInstance();
		project.setName(file.getAbsolutePath());

		try {
			parsePackages();
			parseRelations();
		} catch (Exception e) {
			throw e;
		}

	}
}

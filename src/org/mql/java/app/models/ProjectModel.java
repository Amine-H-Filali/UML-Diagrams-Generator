package org.mql.java.app.models;

import java.util.List;
import java.util.Vector;

public class ProjectModel {

	private String name;
	private List<UMLPackageModel> packages;
	private static ProjectModel project;

	public static ProjectModel getInstance() {
		if (project != null)
			return project;
		project = new ProjectModel();
		return project;
	}

	private ProjectModel() {
		super();

		this.packages = new Vector<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addPackage(UMLPackageModel pUmlPackage) {
		this.packages.add(pUmlPackage);
	}
	
	public List<UMLPackageModel> getPackages() {
		return packages;
	}

	public void setPackages(List<UMLPackageModel> packages) {
		this.packages = packages;
	}

	@Override
	public String toString() {
		String out = "Project Path : " + name + "\n";
		out += "_".repeat(out.length()) + "\n";
		for (UMLPackageModel umlPackage : packages) {
			out += "\n" + umlPackage;
		}

		return out;
	}

}

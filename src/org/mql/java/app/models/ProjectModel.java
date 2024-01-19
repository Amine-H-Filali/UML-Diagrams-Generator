package org.mql.java.app.models;

import java.util.List;

public class ProjectModel {

	private String name;
	private List<UMLPackageModel> packages;

	public ProjectModel(String name) {
		super();
		this.name = name;
		
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
			out += "\n"+umlPackage ;
		}

		return out;
	}

}

package org.mql.java.app.models;



public class ProjectModel {
	
	private String name;
	private UMLPackageModel defaultPackage;

	

	public ProjectModel(String name, UMLPackageModel defaultPackage) {
		super();
		this.name = name;
		this.defaultPackage = defaultPackage;
	}
	
	

	public UMLPackageModel getDefaultPackage() {
		return defaultPackage;
	}

	public void setDefaultPackage(UMLPackageModel defaultPackage) {
		this.defaultPackage = defaultPackage;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		String out = "Project : " + name + "\n";

		out += "\t" + defaultPackage;

		return out;
	}

}

package org.mql.java.app.models;

import java.util.List;
import java.util.Vector;

public class ProjectModel {

	private String name;
	private List<UMLPackageModel> packages;
	private static ProjectModel project;
	private List<UMLRelationModel> relations;

	public static ProjectModel getInstance(String name) {
		if (project != null)
			return project;
		project = new ProjectModel(name);
		return project;
	}

	private ProjectModel(String name) {
		super();
		this.name = name;
		this.packages = new Vector<>();
		this.relations = new Vector<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addPackage(UMLPackageModel umlPackage) {
		packages.add(umlPackage);
	}
	
	public void addRelation(UMLRelationModel relation) {
		this.relations.add(relation);
	}

	public void addRelations(List<UMLRelationModel> relations) {
		this.relations.addAll(relations);
	}
	
	public List<UMLPackageModel> getPackages() {
		return packages;
	}

	public List<UMLRelationModel> getRelations() {
		return relations;
	}
	
	public List<UMLModel> getModels() {
		List<UMLModel> models = new Vector<>();

		for (UMLPackageModel umlPackage : packages) {
			for (UMLClassifier umlClassifier : umlPackage.getClassifiers()) {
				
				if (umlClassifier instanceof UMLModel) {
					models.add((UMLModel) umlClassifier);
				}
			}
		}

		
		return models;
	}
	
	
	
	public UMLModel getModel(String name) {
		for (UMLPackageModel umlPackage : packages) {
			for (UMLClassifier umlClassifier : umlPackage.getClassifiers()) {
				if (umlClassifier instanceof UMLModel && umlClassifier.getName().equals(name)) {
					return (UMLModel) umlClassifier;
				}
			}
		}

		return null;
	}

	@Override
	public String toString() {
		String out = "Project Path : " + name + "\n";
		out += "#".repeat(out.length()) + "\n";
		for (UMLPackageModel umlPackage : packages) {
			out += umlPackage + "\n";
		}

		for (UMLRelationModel umlRelation : relations) {
			out += umlRelation + "\n";
		}

		return out;
	}

}

package org.mql.java.app.models;

import java.util.List;

public class UMLPackageModel {

	private String name;
	private List<UMLModel> models;

	public UMLPackageModel(String name) {
		this.name = name;
	}

	public List<UMLModel> getModels() {
		return models;
	}

	public void setModels(List<UMLModel> models) {
		this.models = models;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String out = "";

		out += "Package : " + name + "\n";

		for (UMLModel m : models) {
			out += "\t" + m + "\n";
		}

		return out + "\n";
	}

}

package org.mql.java.app.models;

import java.util.List;

public class ProjectModel {

	private List<UMLPackageModel> packages;

	public ProjectModel() {
	}

	public List<UMLPackageModel> getPackages() {
		return packages;
	}

	public void setPackages(List<UMLPackageModel> packages) {
		this.packages = packages;
	}
	@Override
	public String toString() {
		String out = "";
		for (UMLPackageModel p : packages) {
			out += p + "\n";
		}

		return out;
	}

}

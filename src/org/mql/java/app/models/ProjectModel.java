package org.mql.java.app.models;

import java.util.List;

public class ProjectModel {

	private List<PackageModel> packages;

	public ProjectModel() {
	}

	public List<PackageModel> getPackages() {
		return packages;
	}

	public void setPackages(List<PackageModel> packages) {
		this.packages = packages;
	}

	@Override
	public String toString() {
		String out = "";
		for (PackageModel p : packages) {
			out += p + "\n";
		}

		return out;
	}

}

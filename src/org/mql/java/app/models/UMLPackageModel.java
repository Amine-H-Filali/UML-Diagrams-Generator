package org.mql.java.app.models;

import java.util.List;
import java.util.Vector;

public class UMLPackageModel {

	private String name;

	private List<UMLClassifier> classifiers;

	public UMLPackageModel(String name) {
		this.name = name;
		this.classifiers = new Vector<>();
	}

	public String getName() {
		return name;
	}

	public void addClassifier(UMLClassifier classifier) {
		this.classifiers.add(classifier);
	}

	public List<UMLClassifier> getClassifiers() {
		return classifiers;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String out = "Package : " + name + "\n";

		for (UMLClassifier c : classifiers) {
			out += "\t" + c + "\n";
		}

		return out + "\n";
	}

}

package org.mql.java.app.models;

import java.util.List;
import java.util.Vector;

public class UMLPackageModel extends UMLElement {

	private List<UMLClassifier> classifiers;

	public UMLPackageModel(String name) {
		super(name);
		this.classifiers = new Vector<>();
	}

	public List<UMLClassifier> getClassifiers() {
		return classifiers;
	}

	public void addClassifier(UMLClassifier classifier) {
		this.classifiers.add(classifier);
	}

	public void setClassifiers(List<UMLClassifier> classifiers) {
		this.classifiers = classifiers;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		String out = "";

		out += "Package : " + name + "\n";

		for (UMLClassifier c : classifiers) {
			out += "\t" + c + "\n";
		}

		return out + "\n";
	}

}

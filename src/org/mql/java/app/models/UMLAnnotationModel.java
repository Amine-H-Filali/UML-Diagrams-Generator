package org.mql.java.app.models;

public class UMLAnnotationModel extends UMLModel {

	public UMLAnnotationModel(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Annotation : " + name;
	}

}

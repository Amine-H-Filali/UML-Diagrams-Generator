package org.mql.java.app.models;

public class UMLEnum extends UMLClassifier {	
	public UMLEnum(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Enumeration " + super.toString();
	}

}

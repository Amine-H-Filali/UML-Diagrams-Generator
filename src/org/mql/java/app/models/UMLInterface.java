package org.mql.java.app.models;

public class UMLInterface extends UMLModel {

	public UMLInterface(String name, String simpleName) {
		super(name, simpleName);
	}

	@Override
	public String toString() {
		return "Interface " + super.toString();
	}

}

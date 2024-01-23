package org.mql.java.app.models;

public class UMLConstant extends UMLEntity {

	public UMLConstant(String name) {
		super(name);
	}
	
	@Override
	public String toString() {
		return name;
	}
}
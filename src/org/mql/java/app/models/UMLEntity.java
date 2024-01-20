package org.mql.java.app.models;

public abstract class UMLEntity {

	protected String name;

	public UMLEntity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

package org.mql.java.app.models;

public abstract class UMLElement {
	protected String name;

	public UMLElement(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

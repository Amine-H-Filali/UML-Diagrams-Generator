package org.mql.java.app.models;

public abstract class Model {

	protected String name;

	public Model(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

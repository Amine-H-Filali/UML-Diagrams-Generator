package org.mql.java.app.models;

public class UMLParameter {
	
	private String type;

	public UMLParameter(String type) {
		this.type = type;
	}

	public String getSimpleType() {
		return type;
		
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return type;
	}

}

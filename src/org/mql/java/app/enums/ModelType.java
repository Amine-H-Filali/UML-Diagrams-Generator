package org.mql.java.app.enums;

public enum ModelType {
	CLASS("Class"), INTERFACE("Interface"), ANNOTATION("Annotation"), ENUM("Enumeration"), PACKAGE("Package");

	private String name;

	private ModelType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
package org.mql.java.app.enums;

public enum Visibility {
	PUBLIC("+"),
	PRIVATE("-"),
	PROTECTED("#"),
	PACKAGE("~");

	private String representation;

	private Visibility(String representation) {
		this.representation = representation;
	}

	public String getSymbol() {
		return representation;
	}
	
}

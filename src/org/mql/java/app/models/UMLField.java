package org.mql.java.app.models;

import org.mql.java.app.enums.Visibility;

public class UMLField {
	private Visibility visibility;
	private String name;
	private String type;
	private boolean isStatic;

	public UMLField(String name) {
		super();
		this.name = name;
	}

	public UMLField(Visibility visibility, String name, String type, boolean isStatic) {
		super();
		this.visibility = visibility;
		this.name = name;
		this.type = type;
		this.isStatic = isStatic;

	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	@Override
	public String toString() {
		String out = "";

		if (visibility != null)
			out += visibility.getSymbol() + " ";

		out += name;

		if (type != null) {
			out += " : ";
			out += type;
		}

		return out;
	}
}

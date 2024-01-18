package org.mql.java.app.models;

import org.mql.java.app.enums.Visibility;

public class UMLField {
	private Visibility visibility;
	private String name;
	private String type;
	private boolean isStatic;
	private boolean isFinal;

	public UMLField(Visibility visibility, String name, String type, boolean isStatic, boolean isFinal) {
		super();
		this.visibility = visibility;
		this.name = name;
		this.type = type;
		this.isStatic = isStatic;
		this.isFinal = isFinal;
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

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	@Override
	public String toString() {
		return visibility.getSymbol() + " " + name + " : " + type;
	}
}

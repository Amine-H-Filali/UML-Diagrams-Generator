package org.mql.java.app.models;

import org.mql.java.app.enums.Visibility;

public class UMLField {
	private Visibility visibility;
	private String name;
	private String type;
	private boolean isStatic;
	private boolean isConstant;

	public UMLField(Visibility visibility, String name, String type, boolean isStatic, boolean isConstant) {
		super();
		this.visibility = visibility;
		this.name = name;
		this.type = type;
		this.isStatic = isStatic;
		this.isConstant = isConstant;
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

	public boolean isConstant() {
		return isConstant;
	}

	public void setConstant(boolean isConstant) {
		this.isConstant = isConstant;
	}

	@Override
	public String toString() {
		return visibility + name + ": " + type;
	}
}

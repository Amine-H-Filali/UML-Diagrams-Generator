package org.mql.java.app.models;

import java.util.List;

import org.mql.java.app.enums.Visibility;

public class UMLMethod {
	private Visibility visibility;
	private String name;
	private String returnType;
	private boolean isStatic;
	private boolean isConstant;
	private List<String> parameters;

	public UMLMethod(Visibility visibility, String name, String returnType, boolean isStatic, boolean isConstant) {
		super();
		this.visibility = visibility;
		this.name = name;
		this.returnType = returnType;
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

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
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

	public List<String> getParameters() {
		return parameters;
	}

	public void setParameters(List<String> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		return visibility + name + "(): " + returnType;
	}
}

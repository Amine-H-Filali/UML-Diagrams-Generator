package org.mql.java.app.models;

import java.util.List;

import org.mql.java.app.enums.Visibility;

public class UMLMethod {
	private Visibility visibility;
	private String name;
	private String returnType;
	private boolean isStatic;
	private boolean isFinal;
	private List<UMLParameter> parameters;

	public UMLMethod(Visibility visibility, String name, String returnType, boolean isStatic, boolean isFinal) {
		super();
		this.visibility = visibility;
		this.name = name;
		this.returnType = returnType;
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

	public boolean isFinal() {
		return isFinal;
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public List<UMLParameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<UMLParameter> parameters) {
		this.parameters = parameters;
	}

	@Override
	public String toString() {
		String out = "";
		out += visibility.getSymbol() + " " + name + "(";

		for (int i = 0; i < parameters.size(); i++) {
			out += parameters.get(i);

			if (i < parameters.size()-1)
				out += ",";
		}

		out += ") : " + returnType;

		return out;
	}
}

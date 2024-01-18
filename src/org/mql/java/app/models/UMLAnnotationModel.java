package org.mql.java.app.models;

import java.util.List;

public class UMLAnnotationModel extends UMLModel {

	private List<UMLMethod> methods;
	
	public UMLAnnotationModel(String name) {
		super(name);
	}
	
	public List<UMLMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<UMLMethod> methods) {
		this.methods = methods;
	}
	
	

	@Override
	public String toString() {
		String out = "";
		out += "Annotation " + name + "\n";

		for (UMLMethod method : methods) {
			out += "\t \t \t" + method + "\n";
		}

		return out;
	}

}

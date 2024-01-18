package org.mql.java.app.models;


import java.util.List;

public class UMLInterfaceModel extends UMLModel {

	private List<UMLField> fields;
	private List<UMLMethod> methods;

	public UMLInterfaceModel(String name) {
		super(name);
	}

	public List<UMLField> getFields() {
		return fields;
	}
	
	public void setFields(List<UMLField> fields) {
		this.fields = fields;
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
		out += "Interface " + name + "\n";

		for (UMLField field : fields) {
			out += "\t \t \t" + field + "\n";
		}

		for (UMLMethod method : methods) {
			out += "\t \t \t" + method + "\n";
		}

		return out;
	}

}

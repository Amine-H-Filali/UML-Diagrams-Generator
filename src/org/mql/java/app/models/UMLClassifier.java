package org.mql.java.app.models;

import java.util.List;
import java.util.Vector;

public abstract class UMLClassifier extends UMLElement {
	private List<UMLField> fields;
	private List<UMLMethod> methods;

	public UMLClassifier(String name) {
		super(name);
		this.fields = new Vector<>();
		this.methods = new Vector<>();
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

	public void addUMLAttribute(UMLField attribute) {
		this.fields.add(attribute);
	}

	public void addUMLOperation(UMLMethod operation) {
		this.methods.add(operation);
	}

	@Override
	public String toString() {
		String out = "";
		out += name + "\n";

		if (fields != null) {
			for (UMLField field : fields) {
				out += "\t \t" + field + "\n";
			}
		}

		if (methods != null) {
			for (UMLMethod method : methods) {
				out += "\t \t" + method + "\n";
			}
		}

		return out;
	}
}

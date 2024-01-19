package org.mql.java.app.models;

import java.util.List;

import org.mql.java.app.enums.ModelType;

public class UMLModel {

	private String name;

	private ModelType type;
	private List<UMLField> fields;
	private List<UMLMethod> methods;
	private List<UMLMethod> constructors;

	public UMLModel(String name, ModelType type) {
		this.name = name;
		this.type = type;
	}

	public List<UMLMethod> getConstructors() {
		return constructors;
	}

	public void setConstructors(List<UMLMethod> constructors) {
		this.constructors = constructors;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ModelType getType() {
		return type;
	}

	public void setType(ModelType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		String out = "";
		out += type.getName() + " " + name + "\n";

		if (constructors != null) {
			for (UMLMethod constructor : constructors) {
				out += "\t \t" + constructor + "\n";
			}			
		}

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

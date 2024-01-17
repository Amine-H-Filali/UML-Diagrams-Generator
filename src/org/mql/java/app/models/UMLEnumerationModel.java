package org.mql.java.app.models;

import java.util.List;

public class UMLEnumerationModel extends UMLModel  {

	private List<String> values;

	public UMLEnumerationModel(String name) {
		super(name);
	}

	public List<String> getValues() {
		return values;
	}

	public void setValues(List<String> values) {
		this.values = values;
	}

	@Override
	public String toString() {
		return "Enum : " + name;
	}

}

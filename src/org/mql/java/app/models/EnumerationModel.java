package org.mql.java.app.models;

import java.util.List;

public class EnumerationModel extends Model  {

	private List<String> values;

	public EnumerationModel(String name) {
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

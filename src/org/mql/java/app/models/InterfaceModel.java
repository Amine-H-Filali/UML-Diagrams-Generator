package org.mql.java.app.models;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class InterfaceModel extends Model {

	private List<Field> fields;
	private List<Method> methods;

	public InterfaceModel(String name) {
		super(name);
	}

	public List<Field> getFields() {
		return fields;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	@Override
	public String toString() {
		return "Interface : " + name;
	}

}

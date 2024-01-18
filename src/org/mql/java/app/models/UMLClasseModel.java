package org.mql.java.app.models;

import java.util.List;

public class UMLClasseModel extends UMLModel {

	private List<UMLField> fields;
	private List<UMLMethod> methods;
	private Class<?> superClass;
	private List<UMLMethod> constructors;
	private List<Class<?>> interfaces;
	private List<UMLClasseModel> innerClasses;
	private List<String> inheritanceChain;

	public UMLClasseModel(String name, String modifiers, Class<?> superClass) {
		super(name);

		this.superClass = superClass;
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

	public Class<?> getSuperClass() {
		return superClass;
	}

	public void setSuperClass(Class<?> superClass) {
		this.superClass = superClass;
	}

	public List<UMLMethod> getConstructors() {
		return constructors;
	}

	public void setConstructors(List<UMLMethod> constructors) {
		this.constructors = constructors;
	}

	public List<Class<?>> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<Class<?>> interfaces) {
		this.interfaces = interfaces;
	}

	public List<UMLClasseModel> getInnerClasses() {
		return innerClasses;
	}

	public void setInnerClasses(List<UMLClasseModel> innerClasses) {
		this.innerClasses = innerClasses;
	}

	public List<String> getInheritanceChain() {
		return inheritanceChain;
	}

	public void setInheritanceChain(List<String> inheritanceChain) {
		this.inheritanceChain = inheritanceChain;
	}

	@Override
	public String toString() {
		String out = "";
		out += "Class " + name + "\n";

		for (UMLMethod constructor : constructors) {
			out += "\t \t" + constructor + "\n";
		}

		for (UMLField field : fields) {
			out += "\t \t \t" + field + "\n";
		}

		for (UMLMethod method : methods) {
			out += "\t \t \t" + method + "\n";
		}

		return out;
	}

}

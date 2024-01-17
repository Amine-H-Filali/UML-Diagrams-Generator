package org.mql.java.app.models;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class ClasseModel extends Model {

	private String modifiers;
	private List<Field> fields;
	private List<Method> methods;
	private Class<?> superClass;
	private List<Constructor<?>> constructors;
	private List<Class<?>> interfaces;
	private List<ClasseModel> innerClasses;
	private List<String> inheritanceChain;

	public ClasseModel(String name, String modifiers, Class<?> superClass) {
		super(name);

		this.modifiers = modifiers;
		this.superClass = superClass;
	}

	public String getModifiers() {
		return modifiers;
	}

	public void setModifiers(String modifiers) {
		this.modifiers = modifiers;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	public Class<?> getSuperClass() {
		return superClass;
	}

	public void setSuperClass(Class<?> superClass) {
		this.superClass = superClass;
	}

	public List<Constructor<?>> getConstructors() {
		return constructors;
	}

	public void setConstructors(List<Constructor<?>> constructors) {
		this.constructors = constructors;
	}

	public List<Class<?>> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<Class<?>> interfaces) {
		this.interfaces = interfaces;
	}

	public List<ClasseModel> getInnerClasses() {
		return innerClasses;
	}

	public void setInnerClasses(List<ClasseModel> innerClasses) {
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
		return "Class : " + name;
	}

}

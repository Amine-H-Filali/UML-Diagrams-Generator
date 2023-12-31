package org.mql.java.app.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.app.utils.ClassesLoader;

public class ClassParser {

	private String className;
	private Class<?> classe;
	private String modifiers;
	private List<Field> fields;
	private List<Method> methods;
	private Class<?> superClass;
	private List<Constructor<?>> constructors;
	private List<Class<?>> interfaces;
	private List<Class<?>> innerClasses;
	private List<String> inheritanceChain;

	public ClassParser(Class<?> classe) {
		this.classe = classe;
		this.className = classe.getName();
		this.modifiers = Modifier.toString(classe.getModifiers());
		this.fields = new Vector<Field>(Arrays.asList(classe.getDeclaredFields()));
		this.superClass = classe.getSuperclass();
		this.methods = new Vector<Method>(Arrays.asList(classe.getDeclaredMethods()));
		this.constructors = new Vector<Constructor<?>>(Arrays.asList(classe.getDeclaredConstructors()));
		this.interfaces = new Vector<Class<?>>(Arrays.asList(classe.getInterfaces()));
		this.innerClasses = new Vector<Class<?>>(Arrays.asList(classe.getDeclaredClasses()));
		loadInheritanceChain();
	}

	public ClassParser(String projectPath, String className) {
		this(ClassesLoader.forName(projectPath, className));
	}

	private void loadInheritanceChain() {

		inheritanceChain = new Vector<String>();
		Class<?> current = classe;

		inheritanceChain.add(className);

		while (current.getSuperclass() != null) {
			inheritanceChain.add(current.getSuperclass().getName());
			current = current.getSuperclass();
		}
	}

	public String getModifiers() {
		return modifiers;
	}

	public Class<?> getClasse() {
		return classe;
	}

	public List<Field> getFields() {
		return fields;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public Class<?> getSuperClass() {
		return superClass;
	}

	public List<Constructor<?>> getConstructors() {
		return constructors;
	}

	public List<Class<?>> getInterfaces() {
		return interfaces;
	}

	public List<Class<?>> getInnerClasses() {
		return innerClasses;
	}

	public List<String> getInheritanceChain() {
		return inheritanceChain;
	}

	@Override
	public String toString() {
		return "Class : " + className;
	}

}

package org.mql.java.app.parsers;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.app.utils.ClassesLoader;

public class InterfaceParser {

	private String interfaceName;
	private List<Field> fields;
	private List<Method> methods;

	public InterfaceParser(String projectPath, String interfaceName) {
		this(ClassesLoader.forName(projectPath, interfaceName));
	}

	public InterfaceParser(Class<?> classe) {
		interfaceName = classe.getName();
		fields = new Vector<Field>(Arrays.asList(classe.getDeclaredFields()));
		methods = new Vector<Method>(Arrays.asList(classe.getDeclaredMethods()));
	}

	public String getName() {
		return interfaceName;
	}

	public List<Field> getFields() {
		return fields;
	}

	public List<Method> getMethods() {
		return methods;
	}

	@Override
	public String toString() {
		return "Interface : " + interfaceName;
	}

}

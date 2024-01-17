package org.mql.java.app.parsers;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.app.models.InterfaceModel;
import org.mql.java.app.utils.ClassesLoader;

public class InterfaceParser {

	private InterfaceModel interfacee;

	public InterfaceParser(String projectPath, String interfaceName) {
		this(ClassesLoader.forName(projectPath, interfaceName));
	}

	public InterfaceParser(Class<?> clazz) {
		interfacee = new InterfaceModel(clazz.getName());

		List<Field> fields = new Vector<Field>(Arrays.asList(clazz.getDeclaredFields()));
		List<Method> methods = new Vector<Method>(Arrays.asList(clazz.getDeclaredMethods()));
		
		interfacee.setFields(fields);
		interfacee.setMethods(methods);
	}
	
	

	public InterfaceModel getInterface() {
		return interfacee;
	}
}

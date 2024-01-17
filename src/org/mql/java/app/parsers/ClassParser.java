package org.mql.java.app.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.app.models.ClasseModel;


public class ClassParser {

	private String projectPath;
	private Class<?> clazz;
	private ClasseModel classe;

	public ClassParser(String projectPath, Class<?> clazz, boolean innerClasses) {
		this.projectPath = projectPath;
		this.clazz = clazz;

		String name = clazz.getName();
		Class<?> superClass = clazz.getSuperclass();
		String modifiers = Modifier.toString(clazz.getModifiers());

		classe = new ClasseModel(name, modifiers, superClass);

		List<Field> fields = new Vector<Field>(Arrays.asList(clazz.getDeclaredFields()));
		List<Method> methods = new Vector<Method>(Arrays.asList(clazz.getDeclaredMethods()));
		List<Constructor<?>> constructors = new Vector<Constructor<?>>(Arrays.asList(clazz.getDeclaredConstructors()));
		List<Class<?>> interfaces = new Vector<Class<?>>(Arrays.asList(clazz.getInterfaces()));

		classe.setFields(fields);
		classe.setMethods(methods);
		classe.setConstructors(constructors);
		classe.setInterfaces(interfaces);

		loadInheritanceChain();

		if (innerClasses) {
			loadInnerClasses();
		}
	}

	private void loadInnerClasses() {
		List<ClasseModel> innerClasses = new Vector<ClasseModel>();

		for (Class<?> c : clazz.getDeclaredClasses()) {
			ClassParser classParser = new ClassParser(projectPath, c, false);
			innerClasses.add(classParser.getClasse());
		}

		classe.setInnerClasses(innerClasses);
	
	
	}
	
	
	
	private void loadInheritanceChain() {
		List<String> inheritanceChain = new Vector<String>();
		Class<?> current = clazz;
		
		inheritanceChain.add(classe.getName());

		while (current.getSuperclass() != null) {
			inheritanceChain.add(current.getSuperclass().getName());
			current = current.getSuperclass();}

		classe.setInheritanceChain(inheritanceChain);
	}
		
	
	

	public ClasseModel getClasse() {
		return classe;
	}

}

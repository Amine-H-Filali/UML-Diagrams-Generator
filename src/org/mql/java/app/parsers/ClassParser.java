package org.mql.java.app.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import org.mql.java.app.enums.Visibility;
import org.mql.java.app.models.UMLClasseModel;
import org.mql.java.app.models.UMLField;
import org.mql.java.app.models.UMLMethod;
import org.mql.java.app.utils.Utils;


public class ClassParser {

	private String projectPath;
	private Class<?> clazz;
	private UMLClasseModel classe;

	public ClassParser(String projectPath, Class<?> clazz, boolean innerClasses) {
		this.projectPath = projectPath;
		this.clazz = clazz;

		String name = clazz.getName();
		Class<?> superClass = clazz.getSuperclass();
		String modifiers = Modifier.toString(clazz.getModifiers());

		classe = new UMLClasseModel(name, modifiers, superClass);

		
		List<Constructor<?>> constructors = new Vector<Constructor<?>>(Arrays.asList(clazz.getDeclaredConstructors()));
		List<Class<?>> interfaces = new Vector<Class<?>>(Arrays.asList(clazz.getInterfaces()));

		loadFields();
		loadMethods();
		
		
		classe.setConstructors(constructors);
		classe.setInterfaces(interfaces);

		loadInheritanceChain();

		if (innerClasses) {
			loadInnerClasses();
		}
	}

	private void loadInnerClasses() {
		List<UMLClasseModel> innerClasses = new Vector<UMLClasseModel>();

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
		
	
	
	private void loadFields() {
		List<UMLField> UMLFields = new Vector<>();

		for (Field field : clazz.getDeclaredFields()) {
			int modifiers = field.getModifiers();

			Visibility visibility = Utils.getVisibilityFromModifiers(modifiers);
			String name = field.getName();
			String type = field.getType().getName();
			boolean isStatic = Utils.isStatic(modifiers);
			boolean isConstant = Utils.isConstant(modifiers);

			UMLFields.add(new UMLField(visibility, name, type, isStatic, isConstant));
		}

		classe.setFields(UMLFields);
	}

	private void loadMethods() {
		List<UMLMethod> UMLMethods = new Vector<>();

		for (Method method : clazz.getDeclaredMethods()) {
			int modifiers = method.getModifiers();

			Visibility visibility = Utils.getVisibilityFromModifiers(modifiers);
			String name = method.getName();
			String type = method.getReturnType().getName();
			boolean isStatic = Utils.isStatic(modifiers);
			boolean isConstant = Utils.isConstant(modifiers);

			UMLMethods.add(new UMLMethod(visibility, name, type, isStatic, isConstant));
		}

		classe.setMethods(UMLMethods);
	}
	
	

	public UMLClasseModel getClasse() {
		return classe;
	}

}

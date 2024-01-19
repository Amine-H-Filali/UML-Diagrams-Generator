package org.mql.java.app.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Vector;

import org.mql.java.app.enums.ModelType;
import org.mql.java.app.enums.Visibility;
import org.mql.java.app.models.UMLField;
import org.mql.java.app.models.UMLMethod;
import org.mql.java.app.models.UMLModel;
import org.mql.java.app.models.UMLParameter;
import org.mql.java.app.utils.ClassesLoader;

public class ClassParser {

	private UMLModel model;
	private Class<?> clazz;

	public ClassParser(String projectPath, String modelName) {
		try {
			ClassesLoader loader = new ClassesLoader(projectPath);
			clazz = loader.loadClass(modelName);

			if (clazz.isAnnotation()) {
				loadAnnotation();
			}
			else if (clazz.isInterface()) {
				loadInterface();
			}
			else if (clazz.isEnum()) {
				loadEnum();
			}
			else {
				loadClass();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void loadClass() {
		model = new UMLModel(clazz.getSimpleName(), ModelType.CLASS);

		model.setFields(getUMLAttributes());
		model.setMethods(getUMLOperationsFMethods());
		model.setConstructors(getUMLOperationsFConstructors());
	}

	private void loadInterface() {
		model = new UMLModel(clazz.getName(), ModelType.INTERFACE);

		model.setFields(getUMLAttributes());
		model.setMethods(getUMLOperationsFMethods());
	}

	private void loadAnnotation() {
		model = new UMLModel(clazz.getName(), ModelType.ANNOTATION);

		model.setMethods(getUMLOperationsFMethods());
	}

	private void loadEnum() {
		model = new UMLModel(clazz.getName(), ModelType.ENUM);

		List<UMLField> umlAttributes = new Vector<>();

		for (Object value : clazz.getEnumConstants()) {
			umlAttributes.add(new UMLField(value.toString()));
		}

		model.setFields(umlAttributes);
	}

	public Visibility getVisibilityFromModifiers() {
		String modifiersString = Modifier.toString(clazz.getModifiers());

		if (modifiersString.contains("public")) {
			return Visibility.PUBLIC;
		}
		else if (modifiersString.contains("private")) {
			return Visibility.PRIVATE;
		}
		else if (modifiersString.contains("protected")) {
			return Visibility.PROTECTED;
		}
		else {
			return Visibility.PACKAGE;
		}
	}

	private boolean isStatic() {
		String modifiersString = Modifier.toString(clazz.getModifiers());
		return modifiersString.contains("static");
	}

	private List<UMLField> getUMLAttributes() {
		List<UMLField> UMLFields = new Vector<>();

		for (Field field : clazz.getDeclaredFields()) {
			Visibility visibility = getVisibilityFromModifiers();
			String name = field.getName();
			String type = field.getType().getSimpleName();

			UMLFields.add(new UMLField(visibility, name, type, isStatic()));
		}

		return UMLFields;
	}

	private List<UMLMethod> getUMLOperationsFMethods() {
		List<UMLMethod> UMLOperations = new Vector<>();

		for (Method method : clazz.getDeclaredMethods()) {
			Visibility visibility = getVisibilityFromModifiers();
			String name = method.getName();
			String type = method.getReturnType().getSimpleName();

			UMLMethod umlOperation = new UMLMethod(visibility, name, type, isStatic());
			umlOperation.setParameters(getUMLParameters(method.getParameters()));

			UMLOperations.add(umlOperation);
		}

		return UMLOperations;
	}

	private List<UMLMethod> getUMLOperationsFConstructors() {
		List<UMLMethod> UMLOperations = new Vector<>();

		for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
			Visibility visibility = getVisibilityFromModifiers();

			UMLMethod umlOperation = new UMLMethod(visibility, clazz.getSimpleName());
			umlOperation.setParameters(getUMLParameters(constructor.getParameters()));

			UMLOperations.add(umlOperation);
		}

		return UMLOperations;
	}

	private List<UMLParameter> getUMLParameters(Parameter[] parameters) {
		List<UMLParameter> umlParameters = new Vector<>();

		for (Parameter p : parameters) {
			umlParameters.add(new UMLParameter(p.getName(), p.getType().getName()));
		}

		return umlParameters;
	}

	public UMLModel getModel() {
		return model;
	}
}

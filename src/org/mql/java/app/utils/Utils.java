package org.mql.java.app.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Vector;

import org.mql.java.app.enums.Visibility;
import org.mql.java.app.models.UMLField;
import org.mql.java.app.models.UMLMethod;
import org.mql.java.app.models.UMLParameter;

public class Utils {

	public static Visibility getVisibilityFromModifiers(int modifiers) {
		String modifiersString = Modifier.toString(modifiers);

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

	public static boolean isStatic(int modifiers) {
		String modifiersString = Modifier.toString(modifiers);
		return modifiersString.contains("static");
	}

	public static boolean isFinal(int modifiers) {
		String modifiersString = Modifier.toString(modifiers);
		return modifiersString.contains("final");
	}
	
	
	
	public static List<UMLField> getUMLFields(Field[] fields) {
		List<UMLField> UMLFields = new Vector<>();

		for (Field field : fields) {
			int modifiers = field.getModifiers();

			Visibility visibility = getVisibilityFromModifiers(modifiers);
			String name = field.getName();
			String type = field.getType().getSimpleName();
			boolean isStatic = Utils.isStatic(modifiers);
			boolean isFinal = Utils.isFinal(modifiers);

			UMLFields.add(new UMLField(visibility, name, type, isStatic, isFinal));
		}

		return UMLFields;
	}

	public static List<UMLMethod> getUMLMethods(Method[] methods) {
		List<UMLMethod> UMLMethods = new Vector<>();

		for (Method method : methods) {
			int modifiers = method.getModifiers();

			Visibility visibility = getVisibilityFromModifiers(modifiers);
			String name = method.getName();
			String type = method.getReturnType().getSimpleName();
			boolean isStatic = Utils.isStatic(modifiers);
			boolean isFinal = Utils.isFinal(modifiers);

			UMLMethod umlMethod = new UMLMethod(visibility, name, type, isStatic, isFinal);
			umlMethod.setParameters(getUMLParameters(method.getParameters()));

			UMLMethods.add(umlMethod);
		}

		return UMLMethods;
	}

	public static List<UMLParameter> getUMLParameters(Parameter[] parameters) {
		List<UMLParameter> umlParameters = new Vector<>();

		for (Parameter p : parameters) {
			umlParameters.add(new UMLParameter(p.getName(), p.getType().getName()));
		}

		return umlParameters;
	}
	
	
	
	public static List<UMLMethod> getUMLConstructors(String className, Constructor<?>[] constructors) {
		List<UMLMethod> UMLOperations = new Vector<>();

		for (Constructor<?> constructor : constructors) {
			int modifiers = constructor.getModifiers();

			Visibility visibility = getVisibilityFromModifiers(modifiers);

			UMLMethod umlOperation = new UMLMethod(visibility, className);
			umlOperation.setParameters(getUMLParameters(constructor.getParameters()));

			UMLOperations.add(umlOperation);
		}

		return UMLOperations;
	}
}

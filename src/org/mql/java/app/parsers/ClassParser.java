package org.mql.java.app.parsers;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

import org.mql.java.app.enums.Visibility;
import org.mql.java.app.models.UMLClass;
import org.mql.java.app.models.UMLClassifier;
import org.mql.java.app.models.UMLConstant;
import org.mql.java.app.models.UMLEnum;
import org.mql.java.app.models.UMLField;
import org.mql.java.app.models.UMLInterface;
import org.mql.java.app.models.UMLMethod;
import org.mql.java.app.utils.ClassFileParser;
import org.mql.java.app.utils.ClassesLoader;

public class ClassParser implements Parser {

	private UMLClassifier classifier;
	private Class<?> clazz;

	public ClassParser(File file) throws Exception {
		parse(file);
	}

	private Visibility getVisibility(int modifiers) {
		String modifiersString = Modifier.toString(modifiers);

		if (modifiersString.contains("public")) {
			return Visibility.PUBLIC;
		} else if (modifiersString.contains("private")) {
			return Visibility.PRIVATE;
		} else if (modifiersString.contains("protected")) {
			return Visibility.PROTECTED;
		} else {
			return Visibility.PACKAGE;
		}
	}

	private boolean isAbstract(int modifiers) {
		return Modifier.toString(modifiers).contains("abstract");
	}

	private boolean isStatic(int modifiers) {
		return Modifier.toString(modifiers).contains("static");
	}

	private void loadUMLProperty(Member member) {
		int modifiers = member.getModifiers();
		Visibility visibility = getVisibility(modifiers);
		String name = member instanceof Constructor ? clazz.getSimpleName() : member.getName();

		if (member instanceof Field) {
			Class<?> type = ((Field) member).getType();
			classifier.addUMLEntity(new UMLField(name, visibility, type.getName(), type.getSimpleName(), isStatic(modifiers), isFinal(modifiers)));
		} else {
			UMLMethod umlOperation;
			if (member instanceof Constructor) {
				umlOperation = new UMLMethod(name, visibility);
			} else {
				Class<?> type = ((Method) member).getReturnType();
				umlOperation = new UMLMethod(name, visibility, type.getName(), type.getSimpleName(), isStatic(modifiers), isFinal(modifiers), false);
			}

			for (Parameter p : ((Executable) member).getParameters()) {
				umlOperation.addParameter(p.getType().getSimpleName());
			}
			classifier.addUMLEntity(umlOperation);
		}
	}

	private void loadUMLOperations() {
		for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {

			loadUMLProperty(constructor);
		}

		for (Method method : clazz.getDeclaredMethods()) {

			loadUMLProperty(method);
		}
	}

	private void loadUMLAttributes() {
		for (Field field : clazz.getDeclaredFields()) {
			loadUMLProperty(field);
		}
	}

	private void loadUMLConstants() {
		for (Object constant : clazz.getEnumConstants()) {
			classifier.addUMLEntity(new UMLConstant(constant.toString()));
		}
	}

	public UMLClassifier getClassifier() {
		return classifier;
	}

	private boolean isFinal(int modifiers) {
		return Modifier.toString(modifiers).contains("final");
	}

	@Override
	public void parse(File file) throws Exception {
		String binPath = ClassFileParser.binPath(file);
		String classifierName = ClassFileParser.fileName(file);

		try {
			ClassesLoader loader = new ClassesLoader(binPath);
			clazz = loader.loadClass(classifierName);
			String motherModelName = clazz.getSuperclass() != null ? clazz.getSuperclass().getName() : "";

			if (clazz.isInterface()) {
				classifier = new UMLInterface(clazz.getName(), clazz.getSimpleName(), motherModelName);
			} else if (clazz.isEnum()) {
				classifier = new UMLEnum(clazz.getName(), clazz.getSimpleName());
			} else {
				classifier = new UMLClass(clazz.getName(), clazz.getSimpleName(), isAbstract(clazz.getModifiers()), motherModelName);
				for (Class<?> interfaceClass : clazz.getInterfaces()) {
					((UMLClass) classifier).addImplementedInterface(interfaceClass.getName());
				}
			}

			if (!(classifier instanceof UMLEnum)) {
				loadUMLAttributes();
				loadUMLOperations();
			} else {
				loadUMLConstants();
			}
		} catch (Exception e) {
			throw e;
		}

	}
}
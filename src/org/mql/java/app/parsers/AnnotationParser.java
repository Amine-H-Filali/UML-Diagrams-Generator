package org.mql.java.app.parsers;

import org.mql.java.app.models.UMLAnnotationModel;
import org.mql.java.app.utils.ClassesLoader;
import org.mql.java.app.utils.Utils;

public class AnnotationParser {

	private UMLAnnotationModel annotation;

	public AnnotationParser(String projectPath, String annotationName) {
		this(ClassesLoader.forName(projectPath, annotationName));
	}

	public AnnotationParser(Class<?> classe) {

		annotation = new UMLAnnotationModel(classe.getName());
		annotation.setMethods(Utils.getUMLMethods(classe.getDeclaredMethods()));
	}
	

	public UMLAnnotationModel getAnnotation() {
		return annotation;
	}

}

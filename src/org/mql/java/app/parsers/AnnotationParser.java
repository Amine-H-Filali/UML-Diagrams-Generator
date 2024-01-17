package org.mql.java.app.parsers;

import org.mql.java.app.models.AnnotationModel;
import org.mql.java.app.utils.ClassesLoader;

public class AnnotationParser {

	private AnnotationModel annotation;

	public AnnotationParser(String projectPath, String annotationName) {
		this(ClassesLoader.forName(projectPath, annotationName));
	}

	public AnnotationParser(Class<?> classe) {

		annotation = new AnnotationModel(classe.getName());
	}

	public AnnotationModel getAnnotation() {
		return annotation;
	}

}

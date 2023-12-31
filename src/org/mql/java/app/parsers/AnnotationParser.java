package org.mql.java.app.parsers;

import org.mql.java.app.utils.ClassesLoader;

public class AnnotationParser {

	private String annotationName;

	public AnnotationParser(String projectPath, String annotationName) {
		this(ClassesLoader.forName(projectPath, annotationName));
	}

	public AnnotationParser(Class<?> classe) {
		annotationName = classe.getName();
	}

	public String getAnnotationName() {
		return annotationName;
	}

	@Override
	public String toString() {
		return "Annotation : " + annotationName;
	}
}

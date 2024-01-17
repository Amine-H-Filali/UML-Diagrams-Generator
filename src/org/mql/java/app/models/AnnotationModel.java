package org.mql.java.app.models;

public class AnnotationModel extends Model {

	public AnnotationModel(String name) {
		super(name);
	}

	@Override
	public String toString() {
		return "Annotation : " + name;
	}

}

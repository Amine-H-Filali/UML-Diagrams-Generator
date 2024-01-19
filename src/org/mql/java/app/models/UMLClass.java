package org.mql.java.app.models;

public class UMLClass extends UMLClassifier {
	private boolean isAbstract;

	public UMLClass(String name) {
		super(name);
	}

	public UMLClass(String name, boolean isAbstract) {
		this(name);
		this.isAbstract = isAbstract;
	}

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}

	@Override
	public String toString() {
		return "Class " + super.toString();
	}

}
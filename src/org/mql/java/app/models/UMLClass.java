package org.mql.java.app.models;

public class UMLClass extends UMLModel {
	private boolean _abstract;

	public UMLClass(String name, String simpleName, boolean _abstract) {
		super(name, simpleName);
		this._abstract = _abstract;
	}

	public boolean isAbstract() {
		return _abstract;
	}

	@Override
	public String toString() {
		return "Class " + super.toString();
	}

}
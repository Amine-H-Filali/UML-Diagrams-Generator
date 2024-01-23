package org.mql.java.app.models;

public abstract class UMLModel extends UMLClassifier {

	private String motherModelName;

	public UMLModel(String name, String simpleName, String motherModelName) {
		super(name, simpleName);
		this.motherModelName = motherModelName;
	}	

	public String getMotherModelName() {
		return motherModelName;
	}
	@Override
	public String toString() {
		return super.toString();
	}

}

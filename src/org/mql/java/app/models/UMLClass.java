package org.mql.java.app.models;

import java.util.List;
import java.util.Vector;

public class UMLClass extends UMLModel {
	private boolean _abstract;
	private List<String> implementedInterfaces;

	public UMLClass(String name, String simpleName, boolean _abstract, String motherModelName) {
		super(name, simpleName, motherModelName);
		this._abstract = _abstract;
		implementedInterfaces = new Vector<>();
	}

	public boolean isAbstract() {
		return _abstract;
	}
	
	public void addImplementedInterface(String implementedInterface) {
		implementedInterfaces.add(implementedInterface);
	}

	public List<String> getImplementedInterfaces() {
		return implementedInterfaces;
	}


	@Override
	public String toString() {
		return "Class : " + super.toString();
	}

}
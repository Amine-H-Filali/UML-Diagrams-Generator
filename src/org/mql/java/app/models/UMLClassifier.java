package org.mql.java.app.models;

import java.util.List;
import java.util.Vector;

public abstract class UMLClassifier {
	protected String name;
	protected String simpleName;
	protected List<UMLEntity> umlEntities;

	public UMLClassifier(String name, String simpleName) {
		this.name = name;
		this.simpleName = simpleName;
		umlEntities = new Vector<>();
	}

	public void addUMLEntity(UMLEntity member) {
		umlEntities.add(member);
	}

	public String getSimpleName() {
		return simpleName;
	}
	
	public List<UMLEntity> getUmlEntities() {
		return umlEntities;
	}
	
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		String out = simpleName + "\n";

		for (UMLEntity member : umlEntities) {
			out += "\t \t" + member + "\n";
		}

		return out;
	}
	
}

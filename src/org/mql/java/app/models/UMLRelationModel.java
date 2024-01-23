package org.mql.java.app.models;

import org.mql.java.app.enums.RelationType;

public class UMLRelationModel {

	
	private UMLClassifier child;
	private UMLClassifier parent;
	private RelationType type;

	public UMLRelationModel(UMLClassifier child, UMLClassifier parent, RelationType type) {
		super();
		this.child = child;
		this.parent = parent;
		this.type = type;
	}

	public UMLClassifier getChild() {
		return child;
	}

	public UMLClassifier getParent() {
		return parent;
	}

	

	public RelationType getType() {
		return type;
	}

	@Override
	public String toString() {
		
		
		
		return child.getSimpleName() + " " + type.getRepresentation() + " " + parent.getSimpleName();
		
	}

}

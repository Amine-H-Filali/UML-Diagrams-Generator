package org.mql.java.app.models;

import org.mql.java.app.enums.RelationType;

public class UMLRelationModel {

	
	private UMLModel child;
	private UMLModel parent;
	private RelationType type;

	public UMLRelationModel(UMLModel child, UMLModel parent, RelationType type) {
		super();
		this.child = child;
		this.parent = parent;
		this.type = type;
	}

	public UMLModel  getChild() {
		return child;
	}

	public UMLModel  getParent() {
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

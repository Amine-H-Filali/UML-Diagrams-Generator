package org.mql.java.app.models;

import org.mql.java.app.enums.RelationType;

public class UMLRelationModel {

	private UMLModel model1;
	private UMLModel model2;
	private RelationType type;

	public UMLRelationModel(UMLModel model1, UMLModel model2, RelationType type) {		
		this.model1 = model1;
		this.model2 = model2;
		this.type = type;
	}

	public UMLModel getModel1() {
		return model1;
	}

	public UMLModel getModel2() {
		return model2;
	}

	public RelationType getType() {
		return type;
	}

	@Override
	public String toString() {
		
			return model1.getName() + type.getRepresentation() + model2.getName();
		
	}

}

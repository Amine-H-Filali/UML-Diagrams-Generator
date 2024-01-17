package org.mql.java.app.models;

import org.mql.java.app.enums.RelationType;

public class UMLRelationModel {

	private UMLModel model1;
	private UMLModel model2;
	private RelationType type;

	public UMLRelationModel(UMLModel member1, UMLModel member2, RelationType type) {		
		this.model1 = member1;
		this.model2 = member2;
		this.type = type;
	}

	public UMLModel getMember1() {
		return model1;
	}

	public UMLModel getMember2() {
		return model2;
	}

	public RelationType getType() {
		return type;
	}

	@Override
	public String toString() {
		if (type == RelationType.INHERITANCE) {
			return model1.getName() + " --|> " + model2.getName() + "\n";
		}
		else if (type == RelationType.COMPOSITION) {
			return model1.getName() + " <>-- " + model2.getName() + "\n";
		}
		else {
			return model1.getName() + "-->" + model2.getName() + "\n";
		}
	}

}

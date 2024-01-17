package org.mql.java.app.models;

import org.mql.java.app.enums.RelationType;

public class RelationModel {

	private Model model1;
	private Model model2;
	private RelationType type;

	public RelationModel(Model member1, Model member2, RelationType type) {		
		this.model1 = member1;
		this.model2 = member2;
		this.type = type;
	}

	public Model getMember1() {
		return model1;
	}

	public Model getMember2() {
		return model2;
	}

	public RelationType getType() {
		return type;
	}

	@Override
	public String toString() {
		if (type == RelationType.EXTENSION) {
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

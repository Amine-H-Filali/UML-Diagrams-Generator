package org.mql.java.app.relations;

import java.util.List;
import java.util.Vector;

import org.mql.java.app.enums.RelationType;
import org.mql.java.app.models.UMLClass;

import org.mql.java.app.models.UMLEntity;
import org.mql.java.app.models.UMLField;
import org.mql.java.app.models.UMLInterface;
import org.mql.java.app.models.UMLMethod;
import org.mql.java.app.models.UMLModel;
import org.mql.java.app.models.UMLRelationModel;
import org.mql.java.app.utils.RelationUtils;


public class RelationExtractor {

	public List<UMLRelationModel> parse(UMLModel child, UMLModel parent) {
		if (child != null && parent != null) {

			List<UMLRelationModel> relations = new Vector<>();

			if (!child.equals(parent)) {
				UMLRelationModel dependency = detectDependency(child, parent);
				if (dependency != null) {

					relations.add(dependency);
				}

				UMLRelationModel association = detectAssociation(child, parent);
				
				if (association != null) {
					relations.remove(dependency);
					relations.add(association);
				}

				UMLRelationModel aggregation = null;
				if (association != null) {
					aggregation = detectAggregation(child, parent);
					if (aggregation != null) {
						
						relations.remove(association);
						relations.add(aggregation);
					}
				}

				UMLRelationModel composition = detectComposition(child, parent);
				if (composition != null) {
					relations.remove(aggregation);
					relations.add(composition);}

				UMLRelationModel realization = detectRealization(child, parent);
				if (realization != null) {
					relations.add(realization);}

				UMLRelationModel generalization = detectGeneralization(child, parent);
				if (generalization != null) {
					relations.add(generalization);}
			}

			return relations;
		}
		return null;
	}

	private UMLRelationModel detectDependency(UMLModel child, UMLModel parent) {
		UMLRelationModel dependency = null;

		for (UMLEntity member : parent.getUmlEntities()) {
			if (member instanceof UMLMethod) {
				UMLMethod operation = (UMLMethod) member;
				if (RelationUtils.isMethodParameter(child.getName(), operation)) {
					dependency = new UMLRelationModel(child, parent, RelationType.DEPENDENCY);
			}
		}
		}

		return dependency;
	}

	private UMLRelationModel detectAssociation(UMLModel child, UMLModel parent) {		
		if (child instanceof UMLClass && parent instanceof UMLClass) {
			if (RelationUtils.childInParentAttributes(child, parent) != null) {
				return new UMLRelationModel(child, parent, RelationType.ASSOCIATION);
			}
		}
		return null;
	}


	private UMLRelationModel detectAggregation(UMLModel child, UMLModel parent) {
		if (child instanceof UMLClass && parent instanceof UMLClass) {	
			UMLField attribute = RelationUtils.childInParentAttributes(child, parent);

			if (attribute != null && attribute.isMultiple()) {
				if (RelationUtils.parameterInAtLeastOneConstructor(attribute.getType(), parent)) {
					return new UMLRelationModel(child, parent, RelationType.AGGREGATION);
				}
			}
		}

		
		return null;
	}

	private UMLRelationModel detectComposition(UMLModel child, UMLModel parent) {				
		if (child instanceof UMLClass && parent instanceof UMLClass) {

			// inner class
			if (child.getName().contains(parent.getName())) {
				return new UMLRelationModel(child, parent, RelationType.COMPOSITION);
			}
			else {
				UMLField attribute = RelationUtils.childInParentAttributes(child, parent);

				if (attribute != null && attribute.isMultiple() && attribute.isFinal()) {
					if (RelationUtils.parameterInAtLeastOneConstructor(attribute.getType(), parent)) {
						return new UMLRelationModel(child, parent, RelationType.COMPOSITION);
					}
				}
			}
		}
		return null;
		}

	private UMLRelationModel detectRealization(UMLModel child, UMLModel parent) {		
		if (child instanceof UMLClass && parent instanceof UMLInterface) {
			UMLClass clazz = (UMLClass) child;
			if (clazz.getImplementedInterfaces().contains(parent.getName())) {
				return new UMLRelationModel(child, parent, RelationType.REALIZATION);
			}
		}

		
		return null;
	}

	private UMLRelationModel detectGeneralization(UMLModel child, UMLModel parent) {		
		if (child.getMotherModelName().equals(parent.getName())) {
			return new UMLRelationModel(child, parent, RelationType.GENERALIZATION);
		}

		
		return null;
	}

}

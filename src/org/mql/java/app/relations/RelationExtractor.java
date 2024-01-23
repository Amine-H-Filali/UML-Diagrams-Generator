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
import org.mql.java.app.utils.UMLReflectionUtils;

public class RelationExtractor {

	public List<UMLRelationModel> parse(UMLModel parent, UMLModel child) {
		if (parent != null && child != null) {

			List<UMLRelationModel> relations = new Vector<>();

			if (!parent.equals(child)) {
				UMLRelationModel dependency = detectDependency(parent, child);
				if (dependency != null) {

					relations.add(dependency);
				}

				UMLRelationModel association = detectAssociation(parent, child);
				dependency = detectDependency(parent, child);
				if (association != null) {

					relations.add(association);
				}

				UMLRelationModel aggregation = detectAggregation(parent, child, "");
				if (aggregation != null)
					relations.add(aggregation);

				UMLRelationModel composition = detectComposition(parent, child);
				if (composition != null)
					relations.add(composition);

				UMLRelationModel realization = detectRealization(parent, child);
				if (realization != null)
					relations.add(realization);

				UMLRelationModel generalization = detectGeneralization(parent, child);
				if (generalization != null)
					relations.add(generalization);
			}

			return relations;
		}
		return null;
	}

	private UMLRelationModel detectDependency(UMLModel parent, UMLModel child) {
		UMLRelationModel dependency = null;

		for (UMLEntity member : parent.getUmlEntities()) {
			if (member instanceof UMLMethod) {
				UMLMethod operation = (UMLMethod) member;
				if (!operation.isConstructor()) {
					for (String parameter : operation.getParameters()) {
						if (UMLReflectionUtils.isMethodParameter(parameter, operation)) {
							dependency = new UMLRelationModel(parent, child, RelationType.DEPENDENCY);
						}
					}
				}
			}
		}

		return dependency;
	}

	private UMLRelationModel detectAssociation(UMLModel parent, UMLModel child) {
		UMLRelationModel association = null;

		if (parent instanceof UMLClass && child instanceof UMLClass) {
			for (UMLEntity member : parent.getUmlEntities()) {
				if (member instanceof UMLField) {
					
					if (UMLReflectionUtils.isClassField((UMLClass) child, (UMLField) member)) {
						// is class attribute
						association = new UMLRelationModel(child, parent, RelationType.ASSOCIATION);
					}
				}
			}
		}

		return association;
	}

	private UMLRelationModel detectAggregation(UMLModel parent, UMLModel child, String parameter) {
		UMLRelationModel aggregation = null;

		if (detectAssociation(parent, child) != null) {
			for (UMLEntity member : parent.getUmlEntities()) {
				if (member instanceof UMLMethod) {
					UMLMethod operation = (UMLMethod) member;
					if (operation.isConstructor()) {
						for (String param : operation.getParameters()) {
							if (UMLReflectionUtils.isMethodParameter(param, operation)) {
								// attribute is operation parameter
								parameter = param;
								aggregation = new UMLRelationModel(parent, child, RelationType.AGGREGATION);
							}
						}
					}
				}
			}
		}

		return aggregation;
	}

	private UMLRelationModel detectComposition(UMLModel parent, UMLModel child) {
		UMLRelationModel composition = null;

		String parameter = "";

		if (detectAggregation(parent, child, parameter) != null) {
			for (UMLEntity member : parent.getUmlEntities()) {
				if (member instanceof UMLField) {
					if (UMLReflectionUtils.isFinalClassField((UMLClass) child, (UMLField) member)) {
						// final attribute and constructor parameter
						composition = new UMLRelationModel(child, parent, RelationType.COMPOSITION);
					}
				}
			}
		} else if (parent.getName().contains(child.getName())) {
			// inner class
			composition = new UMLRelationModel(child, parent, RelationType.COMPOSITION);
		}

		return composition;
	}

	private UMLRelationModel detectRealization(UMLModel parent, UMLModel child) {
		UMLRelationModel realization = null;

		if (parent instanceof UMLClass && child instanceof UMLInterface) {
			UMLClass clazz = (UMLClass) parent;
			if (clazz.getImplementedInterfaces().contains(child.getName())) {
				realization = new UMLRelationModel(parent, child, RelationType.REALIZATION);
			}
		}

		return realization;
	}

	private UMLRelationModel detectGeneralization(UMLModel parent, UMLModel child) {
		UMLRelationModel generalization = null;

		if (parent.getMotherModelName().equals(child.getName())) {
			generalization = new UMLRelationModel(parent, child, RelationType.GENERALIZATION);
		}

		return generalization;
	}

}

package org.mql.java.app.utils;

import org.mql.java.app.models.UMLClass;
import org.mql.java.app.models.UMLEntity;
import org.mql.java.app.models.UMLField;
import org.mql.java.app.models.UMLMethod;
import org.mql.java.app.models.UMLModel;
import org.mql.java.app.models.UMLParameter;

public class RelationUtils {
	
	public static boolean parameterInAtLeastOneConstructor(String parameterTypeName, UMLModel parent) {
		for (UMLEntity member : parent.getUmlEntities()) {
			if (member instanceof UMLMethod) {
				UMLMethod operation = (UMLMethod) member;
				if (RelationUtils.isConstructorParameter(parameterTypeName, operation)) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean isConstructorParameter(String parameterTypeName, UMLMethod operation) {
		return isOperationParameter(parameterTypeName, operation) && operation.isConstructor();
	}

	public static boolean isMethodParameter(String parameterTypeName, UMLMethod operation) {
		return isOperationParameter(parameterTypeName, operation) && !operation.isConstructor();
	}

	private static boolean isOperationParameter(String parameterTypeName, UMLMethod operation) {
		for (UMLParameter param : operation.getParameters()) {
			if (param.getType().equals(parameterTypeName))
				return true;
		}

		return false;
	}

	public static boolean isClassAttribute(UMLClass umlClass, UMLField attribute) {
		for (UMLEntity member : umlClass.getUmlEntities()) {
			if (member instanceof UMLField) {
				UMLField localAttribute = (UMLField) member;

				if (localAttribute.getType().contains(attribute.getType())) {
					return true;
				}
			}
		}

		return false;
	}


	public static boolean isFinalClassAttribute(UMLClass umlClass, UMLField attribute) {
		return (isClassAttribute(umlClass, attribute) && attribute.isFinal());
	}

	public static UMLField childInParentAttributes(UMLModel child, UMLModel parent) {
		for (UMLEntity member : parent.getUmlEntities()) {
			if (member instanceof UMLField) {
				UMLField attribute = (UMLField) member;
				if (attribute.getType().contains(child.getName()))
					return attribute;
			}
		}

		return null;
	}

}

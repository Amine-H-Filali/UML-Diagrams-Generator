package org.mql.java.app.utils;

import org.mql.java.app.models.UMLClass;
import org.mql.java.app.models.UMLEntity;
import org.mql.java.app.models.UMLField;
import org.mql.java.app.models.UMLMethod;

public class UMLReflectionUtils {
	public static boolean isMethodParameter(String parameterTypeName, UMLMethod operation) {
		for (String paramTypeName : operation.getParameters()) {
			if (paramTypeName.equals(parameterTypeName))
				return true;
		}
		return false;

	}

	@SuppressWarnings("unlikely-arg-type")
	public static boolean isClassField(UMLClass umlClass, UMLField attribute) {
		for (UMLEntity member : umlClass.getUmlEntities()) {
			if (member instanceof UMLField) {
				UMLField localAttribute = (UMLField) member;
				if (localAttribute.getName().equals(attribute))
					return true;
			}
		}
		return false;
	}

	public static boolean isFinalClassField(UMLClass umlClass, UMLField attribute) {
		return (isClassField(umlClass, attribute) && attribute.isFinal());
	}
}

package org.mql.java.app.parsers;

import java.util.List;
import java.util.Vector;

import org.mql.java.app.models.UMLEnumerationModel;
import org.mql.java.app.utils.ClassesLoader;

public class EnumParser {

	private UMLEnumerationModel enumeration;

	public EnumParser(String projectPath, String enumName) {
		this(ClassesLoader.forName(projectPath, enumName));
	}

	public EnumParser(Class<?> clazz) {
		enumeration = new UMLEnumerationModel(clazz.getName());

		List<String> values = new Vector<String>();
		for (Object value : clazz.getEnumConstants()) {
			values.add(value.toString());
		}

		enumeration.setValues(values);
	}

	public UMLEnumerationModel getEnumeration() {
		return enumeration;
	}

}

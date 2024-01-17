package org.mql.java.app.parsers;

import java.util.List;
import java.util.Vector;

import org.mql.java.app.models.EnumerationModel;
import org.mql.java.app.utils.ClassesLoader;

public class EnumParser {

	private EnumerationModel enumeration;

	public EnumParser(String projectPath, String enumName) {
		this(ClassesLoader.forName(projectPath, enumName));
	}

	public EnumParser(Class<?> clazz) {
		enumeration = new EnumerationModel(clazz.getName());

		List<String> values = new Vector<String>();
		for (Object value : clazz.getEnumConstants()) {
			values.add(value.toString());
		}

		enumeration.setValues(values);
	}

	public EnumerationModel getEnumeration() {
		return enumeration;
	}

}

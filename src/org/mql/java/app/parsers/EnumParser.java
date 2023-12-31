package org.mql.java.app.parsers;

import java.util.List;
import java.util.Vector;

import org.mql.java.app.utils.ClassesLoader;

public class EnumParser {

	private String enumName;
	private List<String> values;

	public EnumParser(String projectPath, String enumName) {
		this(ClassesLoader.forName(projectPath, enumName));
	}

	public EnumParser(Class<?> classe) {
		enumName = classe.getName();
		values = new Vector<String>();

		for (Object value : classe.getEnumConstants()) {
			values.add(value.toString());
		}
	}

	public String getName() {
		return enumName;
	}

	public List<String> getValues() {
		return values;
	}

	@Override
	public String toString() {
		return "Enum : " + enumName;
	}

}

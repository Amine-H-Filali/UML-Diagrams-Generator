package org.mql.java.app.models;

import java.util.List;
import java.util.Vector;

import org.mql.java.app.enums.Visibility;

public class UMLMethod extends UMLPropertyMember {
	private List<String> parameters;

	public UMLMethod(String name, Visibility visibility) {
		this(name, visibility, null, false);
	}

	public UMLMethod(String name, Visibility visibility, Class<?> type, boolean _static) {
		super(name, visibility, type, _static);
		parameters = new Vector<>();
	}

	public void addParameter(String parameter) {
		parameters.add(parameter);
	}

	public List<String> getParameters() {
		return parameters;
	}

	@Override
	public String toString() {
		String out = "";
		out += visibility.getSymbol() + " " + name + "(";

		for (int i = 0; i < parameters.size(); i++) {
			out += parameters.get(i);

			if (i < parameters.size() - 1)
				out += ", ";
		}

		out += ")";

		if (type != null)
			out += " : " + type;

		return out;
	}
}

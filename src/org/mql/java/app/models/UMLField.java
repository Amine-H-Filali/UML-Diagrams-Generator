package org.mql.java.app.models;

import org.mql.java.app.enums.Visibility;

public class UMLField extends UMLPropertyMember{
	

	public UMLField(String name, Visibility visibility, Class<?> type, boolean _static) {
		super(name, visibility, type, _static);
	}

	@Override
	public String toString() {
		String out = "";

		if (visibility != null)
			out += visibility.getSymbol() + " ";

		out += name;

		if (type != null) {
			out += " : ";
			out += type;
		}

		return out;
	}
}

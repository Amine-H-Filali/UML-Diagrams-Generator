package org.mql.java.app.models;

import org.mql.java.app.enums.Visibility;

public class UMLField extends UMLPropertyMember{
	

	public UMLField(String name, Visibility visibility, String type, String simpleType, boolean _static, boolean _final) {
		super(name, visibility, type, simpleType, _static, _final);
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

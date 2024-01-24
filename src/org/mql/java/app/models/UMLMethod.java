package org.mql.java.app.models;

import java.util.List;
import java.util.Vector;

import org.mql.java.app.enums.Visibility;

public class UMLMethod extends UMLPropertyMember {
	
	private boolean _constructor;
	private List<UMLParameter> parameters;

	public UMLMethod(String name, Visibility visibility) {
		
		this(name, visibility, null, false, false, true);
	}

	public UMLMethod(String name, Visibility visibility, String type, boolean _static, boolean _final, boolean _constructor) {
		super(name, visibility, type, _static, _final);
		parameters = new Vector<>();
		this._constructor = _constructor;
	}
	public void addParameter(UMLParameter parameter) {
		parameters.add(parameter);
	}

	public List<UMLParameter> getParameters() {
		return parameters;
	}
	
	public boolean isConstructor() {
		return _constructor;
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

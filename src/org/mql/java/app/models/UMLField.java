package org.mql.java.app.models;

import org.mql.java.app.enums.Visibility;

public class UMLField extends UMLPropertyMember{
	
	private MultiplicityBounds multiplicity;
	

	public UMLField(String name, Visibility visibility, String type, boolean _static, boolean _final) {
		super(name, visibility, type, _static, _final);
	}
	
	
	public void setMultiplicity(MultiplicityBounds multiplicity) {
		if (multiplicity != null)
			this.multiplicity = multiplicity;
	}

	public boolean isMultiple() {
		return multiplicity.getUpperBound() == 'n';
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

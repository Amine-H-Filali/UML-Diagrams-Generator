package org.mql.java.app.models;

import org.mql.java.app.enums.Visibility;

public abstract class UMLPropertyMember extends UMLEntity {

	protected Visibility visibility;
	protected String type;
	
	protected boolean _static;
	protected boolean _final;

	public UMLPropertyMember(String name, Visibility visibility, String type, boolean _static, boolean _final) {
		super(name);
		this.visibility = visibility;
		this.type = type;
		this._static = _static;
		this._final = _final;
		
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public String getSimpleType() {
		if (type == null) return null;
		return type.substring(type.lastIndexOf(".") + 1);
	}

	

	public String getType() {
		return type;
	}

	public boolean isStatic() {
		return _static;
	}

	public boolean isFinal() {
		return _final;
	}
	
	
	@Override
	public String toString() {
		String out = "";

		if (visibility != null)
			out += visibility.getSymbol() + " ";

		out += name;

		if (getSimpleType() != null) {
			out += " : ";
			out += getSimpleType();
		}

		return out;
	}

}

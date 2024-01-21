package org.mql.java.app.models;

import org.mql.java.app.enums.Visibility;

public abstract class UMLPropertyMember extends UMLEntity {

	protected Visibility visibility;
	protected Class<?> type;
	protected boolean _static;
	protected boolean _final;

	public UMLPropertyMember(String name, Visibility visibility, Class<?> type, boolean _static, boolean _final) {
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
		if (type != null)
			return type.getSimpleName();
		return null;
	}

	public Class<?> getType() {
		if (type != null)
			return type;
		return null;
	}

	public void setType(Class<?> type) {
		this.type = type;
	}

	public boolean isStatic() {
		return _static;
	}

	public boolean isFinal() {
		return _final;
	}

}

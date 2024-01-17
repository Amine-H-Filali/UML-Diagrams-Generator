package org.mql.java.app.enums;

public enum RelationType {
	
	
	ASSOCIATION("_____"),
	INHERITANCE("___|>"),
	REALIZATION("---|>"),
	DEPENDENCY("----->"),
	AGGREGATION("---<>"),
	COMPOSITION("----*");
	
	private final String representation;

    RelationType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}

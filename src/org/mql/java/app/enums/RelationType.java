package org.mql.java.app.enums;

public enum RelationType {
	
	
	ASSOCIATION("<<association>> _____"),
	GENERALIZATION(" <<generalization>>___|>"),
	REALIZATION("<<realizations>>---|>"),
	DEPENDENCY("<<dependency>>----->"),
	AGGREGATION("<<aggregation>>---<>"),
	COMPOSITION("<<composition>>----*");
	
	private final String representation;

    RelationType(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }
}

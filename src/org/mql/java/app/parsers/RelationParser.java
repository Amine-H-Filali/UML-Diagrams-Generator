package org.mql.java.app.parsers;

import java.util.Set;

import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.models.UMLRelationModel;

public class RelationParser {

	private ProjectModel project;

	private Set<UMLRelationModel> packagesRelations;
	private Set<UMLRelationModel> classesRelations;

	public RelationParser(ProjectModel project) {
		this.project = project;
	}

}

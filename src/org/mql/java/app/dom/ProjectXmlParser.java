package org.mql.java.app.dom;

import java.io.File;

import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.parsers.Parser;

public class ProjectXmlParser implements Parser {

	public ProjectXmlParser() {
		saveProjectToXml();
	}

	private void saveProjectToXml() {
		UmlXmlMapper xmlMapper = new UmlXmlMapper();
		XMLNode projectNode = xmlMapper.mapProjectToXml(ProjectModel.getInstance());

		try {
			projectNode.persist();
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}

	@Override
	public void parse(File file) throws Exception {
		// TODO: Implement XML file reading logic
	}
}

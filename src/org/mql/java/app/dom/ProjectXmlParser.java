package org.mql.java.app.dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.mql.java.app.enums.RelationType;
import org.mql.java.app.enums.Visibility;
import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.models.UMLClass;
import org.mql.java.app.models.UMLClassifier;
import org.mql.java.app.models.UMLConstant;
import org.mql.java.app.models.UMLEnum;
import org.mql.java.app.models.UMLField;
import org.mql.java.app.models.UMLInterface;
import org.mql.java.app.models.UMLMethod;
import org.mql.java.app.models.UMLModel;
import org.mql.java.app.models.UMLPackageModel;
import org.mql.java.app.models.UMLParameter;
import org.mql.java.app.models.UMLRelationModel;
import org.mql.java.app.parsers.Parser;
import org.w3c.dom.Document;

public class ProjectXmlParser implements Parser {

	
	private Document document;
	private ProjectModel project;
	
	public ProjectXmlParser() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.newDocument();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ProjectModel getProject() {
		return project;
	}
	
	
	
	
	
	public void persist(ProjectModel project) {
		UmlXmlMapper mapper = new UmlXmlMapper(document);
		
		XMLNode projectNode = mapper.mapXmlNodeFromModel(project);

		try {
			
			String resourcesPath = "resources/";
			String path = resourcesPath + "project.xml";

			projectNode.persist(path);
		} catch (Exception e) {
			
		}
	}

	

	@Override
	public void parse(File file) throws Exception {
		
			// TODO : read xml file
			XMLNode projectNode = new XMLNode(file);

			project = ProjectModel.getInstance(projectNode.getAttribute("name"));

			for (XMLNode packageNode : projectNode.getChild("packages").getChildren()) {
				UMLPackageModel umlPackage = new UMLPackageModel(packageNode.getAttribute("name"));

				for (XMLNode classifierNode : packageNode.getChildren()) {
					UMLClassifier umlClassifier;
					String name = classifierNode.getAttribute("name");
					String simpleName = classifierNode.getAttribute("simple-name");

					if (classifierNode.getName().equals("enumeration")) {
						umlClassifier = new UMLEnum(name, simpleName);

						for (XMLNode constantNode : classifierNode.getChildren()) {
							umlClassifier.addUMLEntity(new UMLConstant(constantNode.getAttribute("value")));
						}
					}
					else {
						if (classifierNode.getName().equals("interface")) {
							umlClassifier = new UMLInterface(name, simpleName, null);					
						}
						else {
							boolean isAbstract = classifierNode.getBooleanAttribute("abstract");
							umlClassifier = new UMLClass(name, simpleName, isAbstract, null);
						}

						if (classifierNode.getChild("attributes") != null) {
							for (XMLNode attributeNode : classifierNode.getChild("attributes").getChildren()) {
								String attributeName = attributeNode.getAttribute("name");
								boolean isAttributeStatic = attributeNode.getBooleanAttribute("static");
								boolean isAttributeFinal = attributeNode.getBooleanAttribute("final");
								String attributeType = attributeNode.getAttribute("type");
								
								String attributeVisibility = attributeNode.getAttribute("visibility");

								umlClassifier.addUMLEntity(new UMLField(attributeName, Visibility.valueOf(attributeVisibility), attributeType, isAttributeStatic, isAttributeFinal));
							}
						}
						

						if (classifierNode.getChild("operations") != null) {
							for (XMLNode operationNode : classifierNode.getChild("operations").getChildren()) {
								String operationName = operationNode.getAttribute("name");
								boolean isOperationConstructor = operationNode.getBooleanAttribute("constructor");
								boolean isOperationStatic = operationNode.getBooleanAttribute("static");
								boolean isOperationFinal = operationNode.getBooleanAttribute("final");
								String operationType = operationNode.getAttribute("type");
								
								String operationVisibility = operationNode.getAttribute("visibility");

								UMLMethod umlOperation = new UMLMethod(operationName, Visibility.valueOf(operationVisibility), operationType, isOperationStatic, isOperationFinal, isOperationConstructor);

								if (operationNode.getChild("parameters") != null) {
									for (XMLNode parameterNode : operationNode.getChild("parameters").getChildren()) {
										umlOperation.addParameter(new UMLParameter(parameterNode.getAttribute("type")));
									}								
								}

								umlClassifier.addUMLEntity(umlOperation);
							}
						}
					}

					umlPackage.addClassifier(umlClassifier);
				}

				project.addPackage(umlPackage); 
			}
			
			// parsing relations
			for (XMLNode relationNode : projectNode.getChild("relations").getChildren()) {
				RelationType relationType = RelationType.valueOf(relationNode.getAttribute("type"));
				UMLModel parent = project.getModel(relationNode.getAttribute("parent"));
				UMLModel child = project.getModel(relationNode.getAttribute("child"));

				project.addRelation(new UMLRelationModel(parent, child, relationType));
			}
		

	}
}

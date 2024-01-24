package org.mql.java.app.dom;

import java.util.List;
import java.util.Vector;

import javax.xml.XMLConstants;

import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.models.UMLClass;
import org.mql.java.app.models.UMLClassifier;
import org.mql.java.app.models.UMLConstant;
import org.mql.java.app.models.UMLEntity;
import org.mql.java.app.models.UMLEnum;
import org.mql.java.app.models.UMLField;
import org.mql.java.app.models.UMLMethod;
import org.mql.java.app.models.UMLPackageModel;
import org.mql.java.app.models.UMLParameter;
import org.mql.java.app.models.UMLPropertyMember;
import org.mql.java.app.models.UMLRelationModel;
import org.w3c.dom.Document;

public class UmlXmlMapper {
	private Document document;

	public UmlXmlMapper(Document document) {
		this.document = document;
	}

	public XMLNode mapXmlNodeFromModel(Object model) {
		if (model instanceof ProjectModel) {
			ProjectModel project = (ProjectModel) model;
			XMLNode projectNode = new XMLNode("project", document);
			projectNode.setAttribute("name", project.getName());
			projectNode.setAttribute("xsi:noNamespaceSchemaLocation", "../resources/schema.xsd");
			projectNode.setAttribute("xmlns:xsi", XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
			
			if (!project.getPackages().isEmpty()) {
				XMLNode packagesNode = new XMLNode("packages", document);
				for (UMLPackageModel umlPackage : project.getPackages()) {
					packagesNode.appendChild(mapXmlNodeFromModel(umlPackage));
				}
				projectNode.appendChild(packagesNode);
				
				XMLNode relationsNode = new XMLNode("relations", document);
				for (UMLRelationModel relation : project.getRelations()) {
					relationsNode.appendChild(mapXmlNodeFromModel(relation));
				}
				projectNode.appendChild(relationsNode);
			}
			
			return projectNode;
		}
		else if (model instanceof UMLPackageModel) {
			UMLPackageModel umlPackage = (UMLPackageModel) model;
			XMLNode packageNode = new XMLNode("package", document);
			packageNode.setAttribute("name", umlPackage.getName());
			
			for (UMLClassifier umlClassifier : umlPackage.getClassifiers()) {
				XMLNode classifierNode = mapXmlNodeFromModel(umlClassifier);
				packageNode.appendChild(classifierNode);
			}
			
			return packageNode;
		}
		else if (model instanceof UMLClassifier) {
			UMLClassifier umlClassifier = (UMLClassifier) model;
			XMLNode classifierNode;
			
			if (umlClassifier instanceof UMLEnum) {
				classifierNode = new XMLNode("enumeration", document);
				classifierNode.setAttribute("generic-type", umlClassifier.getName());
				classifierNode.setAttribute("name", umlClassifier.getSimpleName());
				
				for (UMLEntity member : umlClassifier.getUmlEntities()) {
					classifierNode.appendChild(mapXmlNodeFromModel(member));
				}
			}
			else {
				if (umlClassifier instanceof UMLClass) {
					classifierNode = new XMLNode("class", document);
					classifierNode.setAttribute("abstract", ((UMLClass) umlClassifier).isAbstract() + "");
					classifierNode.setAttribute("generic-type", umlClassifier.getName());
					classifierNode.setAttribute("name", umlClassifier.getSimpleName());
				}
				else {
					classifierNode = new XMLNode("interface", document);
					classifierNode.setAttribute("generic-type", umlClassifier.getName());
					classifierNode.setAttribute("name", umlClassifier.getSimpleName());
				}
				
				List<UMLField> attributes = new Vector<>();
				List<UMLMethod> operations = new Vector<>();
				
				for (UMLEntity umlMember : umlClassifier.getUmlEntities()) {
					if (umlMember instanceof UMLField) {
						attributes.add((UMLField) umlMember);
					}
					else {
						operations.add((UMLMethod) umlMember);
					}
				}
				
				if (!attributes.isEmpty()) {
					XMLNode attributesNode = new XMLNode("fields", document);
					
					for (UMLField umlAttribute : attributes) {
						attributesNode.appendChild(mapXmlNodeFromModel(umlAttribute));
					}
					
					classifierNode.appendChild(attributesNode);
				}
				
				if (!operations.isEmpty()) {
					XMLNode operationsNode = new XMLNode("methods", document);
					for (UMLMethod umlOperation : operations) {
						operationsNode.appendChild(mapXmlNodeFromModel(umlOperation));
					}
					classifierNode.appendChild(operationsNode);
				}
			}
			
			return classifierNode;
		}
		else if (model instanceof UMLEntity) {
			UMLEntity umlMember = (UMLEntity) model;
			XMLNode memberNode;
			
			if (umlMember instanceof UMLConstant) {
				memberNode = new XMLNode("constant", document);
				memberNode.setAttribute("value", umlMember.getName());
			}
			else {
				UMLPropertyMember umlProperty = (UMLPropertyMember) umlMember;
				
				if (umlProperty instanceof UMLField) {
					memberNode = new XMLNode("field", document);				
				}
				else {
					memberNode = new XMLNode("method", document);
				}
				
				memberNode.setAttribute("name", umlMember.getName());
				memberNode.setAttribute("visibility", umlProperty.getVisibility().name());
				if (umlProperty.getType() != null && umlProperty.getSimpleType() != null) {
					memberNode.setAttribute("generic-type", umlProperty.getType());
					memberNode.setAttribute("type", umlProperty.getSimpleType());
				}
				memberNode.setAttribute("final", umlProperty.isFinal() + "");
				memberNode.setAttribute("static", umlProperty.isStatic() + "");
				
				if (umlProperty instanceof UMLMethod) {
					UMLMethod umlOperation = (UMLMethod) umlProperty;
					memberNode.setAttribute("constructor", umlOperation.isConstructor() + "");
					
					if (!umlOperation.getParameters().isEmpty()) {
						XMLNode parametersNode = new XMLNode("parameters", document);
						
							for (UMLParameter parameter : umlOperation.getParameters()) {
							XMLNode parameterNode = new XMLNode("parameter", document);
							
							parameterNode.setAttribute("type", parameter.getSimpleType());
							parametersNode.appendChild(parameterNode);
						}
						memberNode.appendChild(parametersNode);					
					}
				}
			}
			
			return memberNode;
		}
		else if (model instanceof UMLRelationModel) {
			UMLRelationModel umlRelation = (UMLRelationModel) model;
			XMLNode relationNode = new XMLNode("relation", document);
			relationNode.setAttribute("child", umlRelation.getChild().getName());
			relationNode.setAttribute("parent", umlRelation.getParent().getName());
			relationNode.setAttribute("type", umlRelation.getType().name());
			
			return relationNode;
		}
		
		return null;
	}
}
		


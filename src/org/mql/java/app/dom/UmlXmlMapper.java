package org.mql.java.app.dom;

import java.util.List;
import java.util.Vector;

import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.models.UMLClass;
import org.mql.java.app.models.UMLClassifier;
import org.mql.java.app.models.UMLConstant;
import org.mql.java.app.models.UMLEntity;
import org.mql.java.app.models.UMLEnum;
import org.mql.java.app.models.UMLField;
import org.mql.java.app.models.UMLMethod;
import org.mql.java.app.models.UMLPackageModel;
import org.mql.java.app.models.UMLPropertyMember;
import org.mql.java.app.models.UMLRelationModel;
import org.w3c.dom.Document;

public class UmlXmlMapper {

	public XMLNode mapProjectToXml(ProjectModel project) {
		XMLNode projectNode = new XMLNode("project");
		projectNode.setAttribute("path", project.getName());

		if (!project.getPackages().isEmpty()) {
			XMLNode packagesNode = new XMLNode("packages", projectNode.getDocument());
			for (UMLPackageModel umlPackage : project.getPackages()) {
				packagesNode.appendChild(mapUmlPackageToXml(umlPackage, projectNode.getDocument()));
			}
			projectNode.appendChild(packagesNode);

			XMLNode relationsNode = new XMLNode("relations", projectNode.getDocument());

			for (UMLRelationModel relation : project.getRelations()) {

				relationsNode.appendChild(mapUmlRelationToXml(relation, projectNode.getDocument()));
			}
			projectNode.appendChild(relationsNode);
		}

		return projectNode;
	}

	private XMLNode mapUmlPackageToXml(UMLPackageModel umlPackage, Document document) {
		XMLNode packageNode = new XMLNode("package", document);
		packageNode.setAttribute("name", umlPackage.getName());

		for (UMLClassifier umlClassifier : umlPackage.getClassifiers()) {
			XMLNode classifierNode = mapUmlClassifierToXml(umlClassifier, document);
			packageNode.appendChild(classifierNode);
		}

		return packageNode;
	}

	private XMLNode mapUmlClassifierToXml(UMLClassifier umlClassifier, Document document) {
		XMLNode classifierNode;

		if (umlClassifier instanceof UMLEnum) {
			classifierNode = mapUmlEnumToXml((UMLEnum) umlClassifier, document);
		} else {
			classifierNode = mapUmlClassOrInterfaceToXml(umlClassifier, document);
		}

		return classifierNode;
	}

	private XMLNode mapUmlEnumToXml(UMLEnum umlEnum, Document document) {
		XMLNode enumNode = new XMLNode("enumeration", document);
		enumNode.setAttribute("name", umlEnum.getName());
		enumNode.setAttribute("simple-name", umlEnum.getSimpleName());

		for (UMLEntity entity : umlEnum.getUmlEntities()) {
			enumNode.appendChild(mapUmlMemberToXml(entity, document));
		}

		return enumNode;
	}

	private XMLNode mapUmlClassOrInterfaceToXml(UMLClassifier umlClassifier, Document document) {
		XMLNode classifierNode;

		if (umlClassifier instanceof UMLClass) {
			classifierNode = mapUmlClassToXml((UMLClass) umlClassifier, document);
		} else {
			classifierNode = mapUmlInterfaceToXml(umlClassifier, document);
		}

		return classifierNode;
	}

	private XMLNode mapUmlClassToXml(UMLClass umlClass, Document document) {
		XMLNode classNode = new XMLNode("class", document);
		classNode.setAttribute("abstract", umlClass.isAbstract() + "");
		classNode.setAttribute("name", umlClass.getName());
		classNode.setAttribute("simple-name", umlClass.getSimpleName());

		List<UMLField> attributes = new Vector<>();
		List<UMLMethod> operations = new Vector<>();

		for (UMLEntity umlMember : umlClass.getUmlEntities()) {
			if (umlMember instanceof UMLField) {
				attributes.add((UMLField) umlMember);
			} else {
				operations.add((UMLMethod) umlMember);
			}
		}

		if (!attributes.isEmpty()) {
			XMLNode attributesNode = new XMLNode("fields", document);

			for (UMLField umlFields : attributes) {
				attributesNode.appendChild(mapUmlMemberToXml(umlFields, document));
			}

			classNode.appendChild(attributesNode);
		}

		if (!operations.isEmpty()) {
			XMLNode operationsNode = new XMLNode("methods", document);
			for (UMLMethod umlOperation : operations) {
				operationsNode.appendChild(mapUmlMemberToXml(umlOperation, document));
			}
			classNode.appendChild(operationsNode);
		}

		return classNode;
	}

	private XMLNode mapUmlInterfaceToXml(UMLClassifier umlInterface, Document document) {
		XMLNode interfaceNode = new XMLNode("interface", document);
		interfaceNode.setAttribute("name", umlInterface.getName());
		interfaceNode.setAttribute("simple-name", umlInterface.getSimpleName());

		return interfaceNode;
	}

	private XMLNode mapUmlMemberToXml(UMLEntity umlMember, Document document) {
		XMLNode memberNode;

		if (umlMember instanceof UMLConstant) {
			memberNode = new XMLNode("constant", document);
			memberNode.setAttribute("value", umlMember.getName());
		} else {
			UMLPropertyMember umlProperty = (UMLPropertyMember) umlMember;

			if (umlProperty instanceof UMLField) {
				memberNode = new XMLNode("field", document);
			} else {
				memberNode = new XMLNode("method", document);
			}

			memberNode.setAttribute("name", umlMember.getName());
			memberNode.setAttribute("visibility", umlProperty.getVisibility().name());
			if (umlProperty.getSimpleType() != null)
				memberNode.setAttribute("type", umlProperty.getSimpleType());
			memberNode.setAttribute("final", umlProperty.isFinal() + "");
			memberNode.setAttribute("static", umlProperty.isStatic() + "");

			if (umlProperty instanceof UMLMethod) {
				UMLMethod umlOperation = (UMLMethod) umlProperty;
				memberNode.setAttribute("constructor", umlOperation.isConstructor() + "");

				if (!umlOperation.getParameters().isEmpty()) {
					XMLNode parametersNode = new XMLNode("parameters", document);
					for (String parameter : umlOperation.getParameters()) {
						XMLNode parameterNode = new XMLNode("parameter", document);
						parameterNode.setAttribute("type", parameter);
						parametersNode.appendChild(parameterNode);
					}
					memberNode.appendChild(parametersNode);
				}
			}
		}

		return memberNode;
	}

	private XMLNode mapUmlRelationToXml(UMLRelationModel relation, Document document) {
		XMLNode relationNode = new XMLNode("relation", document);

		relationNode.setAttribute("child", relation.getChild().getName());
		relationNode.setAttribute("parent", relation.getParent().getName());
		relationNode.setAttribute("type", relation.getType().name());

		return relationNode;
	}
}

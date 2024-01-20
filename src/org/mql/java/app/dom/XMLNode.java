package org.mql.java.app.dom;

import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLNode {
	private Document document;
	private Node node;

	public XMLNode(String name, Document document) {
		this.document = document;
		this.node = document.createElement(name);
	}

	public XMLNode(Node node, Document document) {
		super();
		this.document = document;
		this.node = node;
	}

	public XMLNode(String source) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(source);
		 	node = document.getFirstChild();

		 	while (node.getNodeType() != Node.ELEMENT_NODE) {
		 		node = node.getNextSibling();
		 	}
		} catch (Exception e) {
			System.out.println("Erreur : " + e.getMessage());
		}
	}

	public XMLNode[] getChildren() {
		Vector<XMLNode> nodes = new Vector<XMLNode>();

	 	NodeList list = node.getChildNodes();
	 	for (int i = 0; i < list.getLength(); i++) {
	 		if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
	 			nodes.add(new XMLNode(list.item(i), document));
	 		}
		}

	 	return nodes.toArray(new XMLNode[nodes.size()]);
	}

	public XMLNode getChild(String name) {		
	 	NodeList list = node.getChildNodes();
	 	for (int i = 0; i < list.getLength(); i++) {
	 		if (list.item(i).getNodeName().equals(name)) {
	 			return new XMLNode(list.item(i), document);
	 		}
		}

	 	return null;
	}

	public String getValue() {
		if (node.getNodeType() == Node.ELEMENT_NODE) {
			return node.getFirstChild().getNodeValue();
		}
		return node.getNodeValue();
	}

	public String getName() {
		return node.getNodeName();
	}

	public boolean isElement() {
		return node.getNodeType() == Node.ELEMENT_NODE;
	}

	public String getAttribute(String name) {
		NamedNodeMap atts = node.getAttributes();
		return atts.getNamedItem(name).getNodeValue();
	}

	public int getIntAttribute(String name) {
		NamedNodeMap atts = node.getAttributes();
		try {
			return Integer.parseInt(atts.getNamedItem(name).getNodeValue());
		} catch(Exception e) {
			return -1;
		}
	}

	public void appendChild(XMLNode newNode) {
		node.appendChild(newNode.node);
	}

	public void removeChild(XMLNode oldNode) {
		node.removeChild(oldNode.node);
	}

	public XMLNode childWithAttribute(String name, String value) {		
		for(XMLNode child : getChildren()) {
			Node attribute = child.node.getAttributes().getNamedItem(name);

			if (attribute != null && attribute.getNodeValue().equals(value)) {
				return child;
			}
		}

		return null;
	}

	public void setAttribute(String name, String value) {
		Node attribute = node.getAttributes().getNamedItem(name);

		if(attribute != null) {
			attribute.setNodeValue(value);
		} else {
			Node attributeNode = document.createAttribute(name);
			attributeNode.setNodeValue(value);
			node.getAttributes().setNamedItem(attributeNode);
		}
	}

	public void setValue(String value) {		
		node.appendChild(document.createTextNode(value));
	}

	public void replaceChild(XMLNode newNode, XMLNode oldNode) {
		node.replaceChild(newNode.node, oldNode.node);
	}

	public Document getDocument() {
		return document;
	}
}

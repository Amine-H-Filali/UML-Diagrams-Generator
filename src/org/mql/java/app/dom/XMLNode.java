package org.mql.java.app.dom;

import java.io.File;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

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

	public XMLNode(String name) {
		this.node = this.document.createElement(name);
	}

	public XMLNode(File source) {

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

		if (node.getNodeType() == Node.ATTRIBUTE_NODE) {
			return node.getNodeValue();
		} else if (node.getNodeType() == Node.ELEMENT_NODE) {
			if (node.getFirstChild() != null || node.getFirstChild().getNodeValue() != null)
				return node.getFirstChild().getNodeValue();

			return null;
		}

		return null;
	}

	public String getName() {
		return node.getNodeName();
	}

	public boolean isElement() {
		return node.getNodeType() == Node.ELEMENT_NODE;
	}

	public String getAttribute(String name) {
		NamedNodeMap atts = node.getAttributes();
		if (atts.getNamedItem(name) == null)
			return null;
		return atts.getNamedItem(name).getNodeValue();
	}

	public boolean getBooleanAttribute(String name) {
		NamedNodeMap atts = node.getAttributes();
		String value = atts.getNamedItem(name).getNodeValue();
		return value.equals("true");
	}

	public int getIntAttribute(String name) {
		NamedNodeMap atts = node.getAttributes();
		try {
			return Integer.parseInt(atts.getNamedItem(name).getNodeValue());
		} catch (Exception e) {
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
		for (XMLNode child : getChildren()) {
			Node attribute = child.node.getAttributes().getNamedItem(name);

			if (attribute != null && attribute.getNodeValue().equals(value)) {
				return child;
			}
		}

		return null;
	}

	public void setAttribute(String name, String value) {
		Node attribute = node.getAttributes().getNamedItem(name);

		if (attribute != null) {
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

	public void persist(String path) throws Exception {
		File file = new File(path);

		if (!file.exists()) {
			file.createNewFile();
		}

		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult result = new StreamResult(file);
		DOMSource source = new DOMSource(node);
		transformer.transform(source, result);
	}

}

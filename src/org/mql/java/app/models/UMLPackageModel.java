package org.mql.java.app.models;

import java.util.List;

public class UMLPackageModel extends UMLModel {

	private List<UMLPackageModel> packages;
	private List<UMLClasseModel> classes;
	private List<UMLInterfaceModel> interfaces;
	private List<UMLEnumerationModel> enumerations;
	private List<UMLAnnotationModel> annotations;

	public UMLPackageModel(String name) {
		super(name);
	}

	public List<UMLPackageModel> getPackages() {
		return packages;
	}

	public void setPackages(List<UMLPackageModel> packages) {
		this.packages = packages;
	}

	public List<UMLClasseModel> getClasses() {
		return classes;
	}

	public void setClasses(List<UMLClasseModel> classes) {
		this.classes = classes;
	}

	public List<UMLInterfaceModel> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<UMLInterfaceModel> interfaces) {
		this.interfaces = interfaces;
	}

	public List<UMLEnumerationModel> getEnumerations() {
		return enumerations;
	}

	public void setEnumerations(List<UMLEnumerationModel> enumerations) {
		this.enumerations = enumerations;
	}

	public List<UMLAnnotationModel> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<UMLAnnotationModel> annotations) {
		this.annotations = annotations;
	}
	

	@Override
	public String toString() {
		String out = "";

		out += "Package : " + name + "\n";

		
		for (UMLClasseModel c : classes) {
			out += "\t \t" + c + "\n";
		}
		for (UMLAnnotationModel a : annotations) {
			out += "\t \t" + a + "\n";
		}
		for (UMLInterfaceModel i : interfaces) {
			out += "\t \t" + i + "\n";
		}
		for (UMLEnumerationModel e : enumerations) {
			out += "\t \t" + e + "\n";
		}
		for (UMLPackageModel p : packages) {
			out += "\t \t" + p + "\n";
		}

		return out;
	}

}

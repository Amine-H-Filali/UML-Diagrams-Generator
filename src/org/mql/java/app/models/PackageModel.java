package org.mql.java.app.models;

import java.util.List;

public class PackageModel extends Model {

	private List<PackageModel> packages;
	private List<ClasseModel> classes;
	private List<InterfaceModel> interfaces;
	private List<EnumerationModel> enumerations;
	private List<AnnotationModel> annotations;

	public PackageModel(String name) {
		super(name);
	}

	public List<PackageModel> getPackages() {
		return packages;
	}

	public void setPackages(List<PackageModel> packages) {
		this.packages = packages;
	}

	public List<ClasseModel> getClasses() {
		return classes;
	}

	public void setClasses(List<ClasseModel> classes) {
		this.classes = classes;
	}

	public List<InterfaceModel> getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(List<InterfaceModel> interfaces) {
		this.interfaces = interfaces;
	}

	public List<EnumerationModel> getEnumerations() {
		return enumerations;
	}

	public void setEnumerations(List<EnumerationModel> enumerations) {
		this.enumerations = enumerations;
	}

	public List<AnnotationModel> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<AnnotationModel> annotations) {
		this.annotations = annotations;
	}

	@Override
	public String toString() {
		String out = "";

		out += "Package : " + name + "\n";

		for (PackageModel p : packages) {
			out += "\t" + p + "\n";
		}
		for (ClasseModel c : classes) {
			out += "\t" + c + "\n";
		}
		for (AnnotationModel a : annotations) {
			out += "\t" + a + "\n";
		}
		for (InterfaceModel i : interfaces) {
			out += "\t" + i + "\n";
		}
		for (EnumerationModel e : enumerations) {
			out += "\t" + e + "\n";
		}

		return out;
	}

}

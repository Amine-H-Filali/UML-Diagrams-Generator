package org.mql.java.app.parsers;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.app.utils.ClassesLoader;

public class PackageParser {
	private String packageName;
	private List<PackageParser> packages;
	private List<ClassParser> classes;
	private List<InterfaceParser> interfaces;
	private List<EnumParser> enumerations;
	private List<AnnotationParser> annotations;

	public PackageParser(String projectPath, String packageName) {
		this.packageName = packageName;
		packages = new Vector<PackageParser>();
		classes = new Vector<ClassParser>();
		interfaces = new Vector<InterfaceParser>();
		enumerations = new Vector<EnumParser>();
		annotations = new Vector<AnnotationParser>();

		String packagePath = packageName.replace(".", "/");

		File dir = new File(projectPath + "/bin/" + packagePath);

		File f[] = dir.listFiles();

		if (f != null) {
			for (int i = 0; i < f.length; i++) {
				String name = f[i].getName().replace(".class", "");

				String fullname = packageName + "." + name;

				if (f[i].isFile() && f[i].getName().endsWith(".class")) {
					Class<?> classFile = ClassesLoader.forName(projectPath, fullname);
					

					if (classFile.isAnnotation()) {
						annotations.add(new AnnotationParser(classFile));
						
					} else if (classFile.isInterface()) {
						interfaces.add(new InterfaceParser(classFile));
					} else if (classFile.isEnum()) {
						enumerations.add(new EnumParser(classFile));
						
					} else {
						classes.add(new ClassParser(classFile));
						
					}
				} else if (f[i].isDirectory()) {
					packages.add(new PackageParser(projectPath, fullname));
				}
			}
		}

	}

	public List<ClassParser> getClasses() {
		return classes;
	}

	public String getPackageName() {
		return packageName;
	}

	public List<PackageParser> getPackages() {
		return packages;
	}

	public List<InterfaceParser> getInterfaces() {
		return interfaces;
	}

	public List<EnumParser> getEnumerations() {
		return enumerations;
	}

	public List<AnnotationParser> getAnnotations() {
		return annotations;
	}

	@Override
	public String toString() {

		String out = "";

		out += "Package : " + packageName + "\n";

		for (PackageParser p : packages) {
			out += "\t" + p + "\n";
		}
		for (ClassParser c : classes) {
			out += "\t" + c + "\n";
		}
		for (AnnotationParser a : annotations) {
			out += "\t" + a + "\n";
		}
		for (InterfaceParser i : interfaces) {
			out += "\t" + i + "\n";
		}
		for (EnumParser e : enumerations) {
			out += "\t" + e + "\n";
		}

		return out;
	}

}

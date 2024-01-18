package org.mql.java.app.parsers;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.app.models.UMLAnnotationModel;
import org.mql.java.app.models.UMLClasseModel;
import org.mql.java.app.models.UMLEnumerationModel;
import org.mql.java.app.models.UMLInterfaceModel;
import org.mql.java.app.models.UMLPackageModel;

import org.mql.java.app.utils.ClassesLoader;


public class PackageParser {
	private UMLPackageModel umlPackage;

	public PackageParser(String projectPath) {
		this(projectPath, "");
	}


	public PackageParser(String projectPath, String packageName) {
		umlPackage = new UMLPackageModel("".equals(packageName) ? "default package" : packageName);

		String packagePath = packageName.replace(".", "/");

		String fullPath = projectPath + "/bin";

		if (!"".equals(packageName))
			fullPath += "/" + packagePath;

		File dir = new File(fullPath);
		File f[] = dir.listFiles();

		List<UMLPackageModel> packages = new Vector<UMLPackageModel>();
		List<UMLAnnotationModel> annotations = new Vector<UMLAnnotationModel>();
		List<UMLClasseModel> classes = new Vector<UMLClasseModel>();
		List<UMLInterfaceModel> interfaces = new Vector<UMLInterfaceModel>();
		List<UMLEnumerationModel> enumerations = new Vector<UMLEnumerationModel>();

		if (f != null) {
			for (int i = 0; i < f.length; i++) {				
				String name = f[i].getName().replace(".class", "");
				String fullname = "";

				if (!"".equals(packageName))
					fullname += packageName + ".";

				fullname += name;

				if (f[i].isFile() && f[i].getName().endsWith(".class")) {

					Class<?> classFile = ClassesLoader.forName(projectPath, fullname);

					if (classFile.isAnnotation()) {

						annotations.add(new AnnotationParser(classFile).getAnnotation());
					} else if (classFile.isInterface()) {
						interfaces.add(new InterfaceParser(classFile).getInterface());
					} else if (classFile.isEnum()) {

						enumerations.add(new EnumParser(classFile).getEnumeration());

					} else {
						classes.add(new ClassParser(projectPath, classFile, true).getClasse());
					}

				} else if (f[i].isDirectory()) {

					packages.add(new PackageParser(projectPath, fullname).getUmlPackage());
				}
			}
			umlPackage.setAnnotations(annotations);
			umlPackage.setClasses(classes);
			umlPackage.setEnumerations(enumerations);
			umlPackage.setInterfaces(interfaces);
			umlPackage.setPackages(packages);
		}

	}

	

	public UMLPackageModel getUmlPackage() {
		return umlPackage;
	}

}

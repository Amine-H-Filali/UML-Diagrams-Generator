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
	private UMLPackageModel packageModel;


	public PackageParser(String projectPath, String packageName) {
		packageModel = new UMLPackageModel(packageName);

		String packagePath = packageName.replace(".", "/");

		File dir = new File(projectPath + "/bin/" + packagePath);
		File f[] = dir.listFiles();

		List<UMLPackageModel> packages = new Vector<UMLPackageModel>();
		List<UMLAnnotationModel> annotations = new Vector<UMLAnnotationModel>();
		List<UMLClasseModel> classes = new Vector<UMLClasseModel>();
		List<UMLInterfaceModel> interfaces = new Vector<UMLInterfaceModel>();
		List<UMLEnumerationModel> enumerations = new Vector<UMLEnumerationModel>();

		if (f != null) {
			for (int i = 0; i < f.length; i++) {
				String name = f[i].getName().replace(".class", "");
				String fullname = packageName + "." + name;

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

					packages.add(new PackageParser(projectPath, fullname).getPackageM());
				}
			}
			packageModel.setAnnotations(annotations);
			packageModel.setClasses(classes);
			packageModel.setEnumerations(enumerations);
			packageModel.setInterfaces(interfaces);
			packageModel.setPackages(packages);
		}

	}

	

	public UMLPackageModel getPackageM() {
		return packageModel;
	}

}

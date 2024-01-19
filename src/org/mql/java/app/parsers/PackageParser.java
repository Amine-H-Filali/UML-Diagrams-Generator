package org.mql.java.app.parsers;

import java.io.File;
import java.util.List;
import java.util.Vector;

import org.mql.java.app.models.UMLModel;
import org.mql.java.app.models.UMLPackageModel;





public class PackageParser {
	private UMLPackageModel umlPackage;

	


	public PackageParser(String projectPath, String packageName) {
		umlPackage = new UMLPackageModel("".equals(packageName) ? "default package" : packageName);

		String packagePath = packageName.replace(".", "/");

		String fullPath = projectPath + "/bin";

		if (!"".equals(packageName))
			fullPath += "/" + packagePath;

		File dir = new File(fullPath);
		File f[] = dir.listFiles();

		List<UMLModel> models = new Vector<UMLModel>();

		if (f != null) {
			for (int i = 0; i < f.length; i++) {				
				String name = f[i].getName().replace(".class", "");
				String fullname = "";

				if (!"".equals(packageName))
					fullname += packageName + ".";

				fullname += name;

				if (f[i].isFile() && f[i].getName().endsWith(".class")) {

					

					models.add(new ClassParser(projectPath, fullname).getModel());
				}
			}
			umlPackage.setModels(models);
		}

	}

	

	public UMLPackageModel getUmlPackage() {
		return umlPackage;
	}

}

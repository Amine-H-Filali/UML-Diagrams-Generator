package org.mql.java.app.parsers;

import java.io.File;


import org.mql.java.app.models.UMLPackageModel;
import org.mql.java.app.utils.ClassFileParser;

public class PackageParser implements Parser {
	private UMLPackageModel umlPackage;
	private File dir;

	public PackageParser(File dir) throws Exception {
		this.dir = dir;
		parse(dir);
	}

	private boolean isPackage() {
		if (!dir.isDirectory())
			return false;
		for (File file : dir.listFiles()) {
			if (file.isFile())
				return true;
		}
		return false;
	}

	@Override
	public void parse(File file) throws Exception {
		if (!isPackage())
			throw new Exception("Package not found");

		String packageName = ClassFileParser.fileName(dir);
		if (packageName == null)
			packageName = "(default package)";

		umlPackage = new UMLPackageModel(packageName);

		File f[] = dir.listFiles();

		for (int i = 0; i < f.length; i++) {
			File currentFile = f[i];

			if (currentFile.isFile() && currentFile.getName().endsWith(".class")) {
				try {
					ClassParser classifierParser = new ClassParser(currentFile);
					umlPackage.addClassifier(classifierParser.getClassifier());
				} catch (Exception e) {
					throw e;
				}
			}

		}
	}

	public UMLPackageModel getUmlPackage() {
		return umlPackage;
	}

}

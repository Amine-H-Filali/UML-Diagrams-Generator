package org.mql.java.app.parsers;

import java.io.File;
import java.util.List;
import java.util.Vector;

public class ProjectParser {

	private List<PackageParser> packages;

	public ProjectParser(String projectPath) {
		try {
			packages = new Vector<PackageParser>();

			File src = new File(projectPath + "/bin");

			for (File file : src.listFiles()) {
				if (file.isDirectory()) {
					packages.add(new PackageParser(projectPath, file.getName()));
				}
			}
		} catch (NullPointerException e) {
			System.out.println("Project not found");
		}
	}

	@Override
	public String toString() {
		String out = "";
		for (PackageParser p : packages) {
			out += p + "\n";
		}

		return out;
	}

}

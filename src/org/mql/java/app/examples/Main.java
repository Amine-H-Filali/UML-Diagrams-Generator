package org.mql.java.app.examples;

import org.mql.java.app.parsers.PackageParser;
import org.mql.java.app.parsers.ProjectParser;

public class Main {
	String projectPath = "C:/Users/AMINE-HF/Desktop/xml/UML-Diagrams-Generator";
	
	
	public Main() {
		ProjectParser projectParser = new ProjectParser(projectPath);
		System.out.println(projectParser);

		
		PackageParser packageParser = new PackageParser(projectPath, "org.mql.java.exemple");
		System.out.println(packageParser);
		
		

		
	}
	
	
	
	

	public static void main(String[] args) {
		new Main();

	}

}

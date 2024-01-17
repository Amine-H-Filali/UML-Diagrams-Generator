package org.mql.java.app.examples;

import org.mql.java.app.parsers.ProjectParser;

public class Main {
	
	String workspacePath = "C:/Users/AMINE-HF/eclipse-workspace/MQL/Java";

	String projectName = "UML-Diagrams-Generator";

	public Main() {
		ProjectParser projectParser = new ProjectParser(workspacePath + "/" + projectName);
		
		System.out.println(projectParser.getProject());

	}

	public static void main(String[] args) {
		new Main();

	}

}

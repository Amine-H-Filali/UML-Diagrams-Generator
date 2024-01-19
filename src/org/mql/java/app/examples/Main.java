package org.mql.java.app.examples;

import org.mql.java.app.parsers.ProjectParser;

public class Main {

	String binPath = "C:/Users/AMINE-HF/eclipse-workspace/MQL/Java/UML-Test-App/bin";

	public Main() {

		try {
			ProjectParser projectParser = new ProjectParser(binPath);

			System.out.println(projectParser.getProject());

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void main(String[] args) {
		new Main();

	}

}

package org.mql.java.app.examples;

import java.awt.Dimension;


import javax.swing.JFrame;

import javax.swing.JScrollPane;

import org.mql.java.app.dom.ProjectXmlParser;
import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.parsers.ProjectParser;
import org.mql.java.app.ui.umldiagram.JProject;


public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String binPath = "C:/Users/AMINE-HF/eclipse-workspace/MQL/Java/UML-Test-App/bin";
	
	String binPath2="C:\\Users\\AMINE-HF\\Desktop\\xml\\UML-Diagrams-Generator\\bin";
	private ProjectModel project;

	public Main() {
		
		exp02(binPath);

	}

	private void drawProject() {
		if (project != null) {
			JScrollPane panelPane = new JScrollPane(new JProject(project));
			setContentPane(panelPane);
		}
	}

	void exp01(String binPath) {
		try {
			ProjectParser projectParser = new ProjectParser(binPath);
			System.out.println(projectParser.getProject());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	void exp02(String binPath) {
		try {
			ProjectParser projectParser = new ProjectParser(binPath);
			project = projectParser.getProject();

			ProjectXmlParser projectDOMParser = new ProjectXmlParser();
			// projectDOMParser.parse(new File("bin/project-dom.xml"));
			// Project project = projectDOMParser.getProject();
			projectDOMParser.persist();

			// panelPane.setSize(new Dimension(1000, 1000));

			drawProject();

			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setSize(new Dimension(800, 580));
			// setExtendedState(JFrame.MAXIMIZED_BOTH);
			setVisible(true);
		} catch (Exception e) {
			 e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Main();

	}

}

package org.mql.java.app.examples;

import java.awt.Dimension;


import javax.swing.JFrame;

import javax.swing.JScrollPane;
import javax.swing.UIManager;

import org.mql.java.app.dom.ProjectXmlParser;
import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.parsers.ProjectParser;
import org.mql.java.app.ui.umldiagram.JProject;


/**
 * @author Amine Hmidani FIlali
 *
 * Jan 24, 2024
 */


public class Main extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String binPath = "C:/Users/AMINE-HF/eclipse-workspace/MQL/Java/UML-Test-App/bin";
	
	String binPath2="C:\\Users\\AMINE-HF\\Desktop\\xml\\UML-Diagrams-Generator\\bin";
	

	public Main() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			
		}
		
		exp02(binPath);

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
			ProjectModel project = projectParser.getProject();
			System.out.println(projectParser.getProject());
			ProjectXmlParser projectDOMParser = new ProjectXmlParser();
			
			projectDOMParser.persist(project);

			
			if (project != null) {
				JScrollPane panelPane = new JScrollPane(new JProject(project));
				setContentPane(panelPane);
				config();
			}
		} catch (Exception e) {
			 e.printStackTrace();
		}

	}
	
	private void config() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Main();

	}

}

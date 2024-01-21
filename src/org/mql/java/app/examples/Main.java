package org.mql.java.app.examples;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

import org.mql.java.app.dom.ProjectXmlParser;
import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.parsers.ProjectParser;
import org.mql.java.app.ui.umldiagram.JProject;

public class Main extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String binPath = "C:/Users/AMINE-HF/eclipse-workspace/MQL/Java/UML-Test-App/bin";

	public Main() {
		/*try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		} catch(Exception e) {

		}*/
		exp02(binPath);
	}
	
	
	
	void exp01(String binPath) {
		try {
			ProjectParser projectParser = new ProjectParser(binPath);
			System.out.println(projectParser.getProject().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	void exp02(String binPath) {
		try {
			new ProjectParser(binPath);

			new ProjectXmlParser();

			JProject jProject = new JProject(ProjectModel.getInstance());
			JScrollPane panelPane = new JScrollPane();
			panelPane.getViewport().add((JPanel) jProject.draw());

			//panelPane.setSize(new Dimension(1000, 1000));

			setContentPane(panelPane);

			setDefaultCloseOperation(EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
			setSize(new Dimension(800, 580));
			//setExtendedState(JFrame.MAXIMIZED_BOTH);
			setVisible(true);
		} catch (Exception e) {
			System.out.println("Erreur : "+e.getMessage());
		}

	}

	public static void main(String[] args) {
		new Main();

	}

}

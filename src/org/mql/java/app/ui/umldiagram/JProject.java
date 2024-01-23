package org.mql.java.app.ui.umldiagram;

import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;

import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.models.UMLClassifier;
import org.mql.java.app.models.UMLPackageModel;
import org.mql.java.app.models.UMLRelationModel;

public class JProject extends JPanel  {
	private static final long serialVersionUID = 1L;

	private ProjectModel project;
	private List<JUMLPackage> jumlPackages;
	private List<JUMLRelation> jumlRelations;

	public JProject(ProjectModel project) {
		this.project = project;
		jumlPackages = new Vector<>();
		jumlRelations = new Vector<>();
		setLayout(null);

		drawPackages();
		drawRelations();
	}
	
	
	
	private void drawPackages() {
		JUMLPackage jumlPackage;

		for (UMLPackageModel umlPackage : project.getPackages()) {
			int x = 1, y = 1;

			jumlPackage = new JUMLPackage(umlPackage);
			
			jumlPackage.setLocation(x, y);
			add(jumlPackage);

			jumlPackages.add(jumlPackage);
		}
	}

	private void drawRelations() {
		JUMLRelation jumlRelation;
		for (UMLRelationModel umlRelation : project.getRelations()) {
			jumlRelation = new JUMLRelation(umlRelation, this);

			add(jumlRelation);
			jumlRelations.add(jumlRelation);
		}
	}

	public JUMLClass getJumlClassifier(UMLClassifier umlClassifier) {
		for (JUMLPackage jumlPackage : jumlPackages) {
			
			for (JUMLClass jumlClassifier : jumlPackage.getJumlClassifiers()) {
				if (jumlClassifier.getClassifier().getName().equals(umlClassifier.getName())) {
					return jumlClassifier;
				}
			}
		}

		
		return null;
	}
}
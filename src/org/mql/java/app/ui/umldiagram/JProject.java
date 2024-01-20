package org.mql.java.app.ui.umldiagram;

import javax.swing.JPanel;

import org.mql.java.app.models.ProjectModel;
import org.mql.java.app.models.UMLPackageModel;

public class JProject extends JPanel implements Drawable {
	private static final long serialVersionUID = 1L;

	private ProjectModel project;

	public JProject(ProjectModel project) {
		this.project = project;
	}

	@Override
	public Object draw() {
		setLayout(null);

		JUMLPackage jumlPackage;

		for (UMLPackageModel umlPackage : project.getPackages()) {
			int x = 1, y = 1;

			jumlPackage = new JUMLPackage(umlPackage);
			JPanel p = (JPanel) jumlPackage.draw();
			p.setLocation(x, y);
			add(p);
		}

		return this;
	}
}
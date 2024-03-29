package org.mql.java.app.ui.umldiagram;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.FlowLayout;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.mql.java.app.models.UMLClassifier;
import org.mql.java.app.models.UMLPackageModel;

import org.mql.java.app.ui.app.Label;



public class JPackage extends JPanel implements Movable {
	private static final long serialVersionUID = 1L;

	private UMLPackageModel umlPackage;
	private List<JClass> jumlClassifiers;



	private int eX, eY;

	private class CustomMouseListener implements MouseListener {

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			eX = e.getX();
			eY = e.getY();
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}

	private class CustomMouseMotionListener implements MouseMotionListener {
		@Override
		public void mouseMoved(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			move(e);
		}
	}

	public JPackage(UMLPackageModel umlPackage) {
		this.umlPackage = umlPackage;
		jumlClassifiers = new Vector<>();
		setLayout(new BorderLayout());

		setOpaque(false);
		drawTitle(4);
		drawClassifiers(15);

		setSize(getPreferredSize());
		

		addMouseListener(new CustomMouseListener());
		addMouseMotionListener(new CustomMouseMotionListener());
	}

	private void drawClassifiers(int padding) {
		JPanel classifiersPanel = new JPanel(null);

		classifiersPanel.setLayout(new FlowLayout(FlowLayout.LEFT, padding, padding));
		classifiersPanel.setBorder(new LineBorder(Color.black, 1));
		classifiersPanel.setBackground(new Color(255, 228, 196)); 


		for (UMLClassifier classifier : umlPackage.getClassifiers()) {
			JClass jumlClassifier = new JClass(classifier);
			jumlClassifier.setLocation(10, 10);
			classifiersPanel.add(jumlClassifier);

			jumlClassifiers.add(jumlClassifier);
		}

		classifiersPanel.setSize(600, 200);
		add(classifiersPanel);
	}

	private void drawTitle(int padding) {
		JPanel titlePanel = new JPanel();
		titlePanel.setOpaque(false);
		titlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		titlePanel.setSize(100, 100);
		Label titleLabel = new Label(umlPackage.getName());

		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT, padding, padding));
		p.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1, Color.black));
		p.setBackground(new Color(255, 228, 196));
		p.add(titleLabel);

		titlePanel.add(p);

		add(titlePanel, BorderLayout.NORTH);
	}

	public List<JClass> getJumlClassifiers() {
		return jumlClassifiers;
	}

	@Override
	public void move(MouseEvent e) {
		setLocation(getX() + e.getX() - eX, getY() + e.getY() - eY);
	}

}
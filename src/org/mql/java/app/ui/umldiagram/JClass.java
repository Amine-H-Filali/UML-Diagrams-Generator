package org.mql.java.app.ui.umldiagram;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.mql.java.app.models.UMLClassifier;
import org.mql.java.app.models.UMLConstant;
import org.mql.java.app.models.UMLEntity;
import org.mql.java.app.models.UMLEnum;
import org.mql.java.app.models.UMLField;
import org.mql.java.app.models.UMLInterface;
import org.mql.java.app.models.UMLMethod;
import org.mql.java.app.ui.app.BoxPanel;
import org.mql.java.app.ui.app.Label;

public class JClass extends JPanel implements Movable {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Color color;

	private UMLClassifier classifier;

	private TitlePanel titlePanel;
	private SectionPanel attributesPanel;
	private SectionPanel operationsPanel;

	private int eX, eY;

	public JClass(UMLClassifier classifier) {
		this(classifier, Color.BLACK);
	}

	public JClass(UMLClassifier classifier, Color color) {

		this.classifier = classifier;
		this.color = color;

		setLayout(new BorderLayout(0, 10));
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, color));

		drawTitlePanel();

		if (classifier instanceof UMLEnum) {
			drawConstantsPanel();
		} else {
			drawAttributesPanel();
			drawOperationsPanel();
		}

		if (classifier instanceof UMLInterface) {
			setBackground(new Color(184, 249, 168));
		} else if (classifier instanceof UMLEnum) {
			setBackground(new Color(209, 166, 253));
		} else {
			setBackground(Color.white);
		}

		setSize(getPreferredSize());

		addMouseListener(new CustomMouseListener());
		addMouseMotionListener(new CustomMouseMotionListener());
	}

	private class CustomMouseListener implements MouseListener {

		@Override
		public void mouseReleased(MouseEvent e) {

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

	private void drawTitlePanel() {
		titlePanel = new TitlePanel();
		add(titlePanel, BorderLayout.NORTH);
	}

	private void drawAttributesPanel() {
		attributesPanel = new SectionPanel();
		attributesPanel.setBorderBottom(1);

		for (UMLEntity umlMember : classifier.getUmlEntities()) {
			if (umlMember instanceof UMLField) {
				attributesPanel.add(new JEntity(umlMember));
			}
		}

		add(attributesPanel, BorderLayout.CENTER);
	}

	private void drawOperationsPanel() {
		operationsPanel = new SectionPanel();

		for (UMLEntity umlMember : classifier.getUmlEntities()) {
			if (umlMember instanceof UMLMethod) {
				operationsPanel.add(new JEntity(umlMember));
			}
		}

		add(operationsPanel, BorderLayout.SOUTH);
	}

	private void drawConstantsPanel() {
		attributesPanel = new SectionPanel();

		for (UMLEntity umlMember : classifier.getUmlEntities()) {
			if (umlMember instanceof UMLConstant) {
				attributesPanel.add(new JEntity(umlMember));
			}
		}

		add(attributesPanel);
	}

	private class TitlePanel extends BoxPanel {
		private static final long serialVersionUID = 1L;

		public TitlePanel() {
			setBorderBottom(1);

			if (classifier instanceof UMLInterface) {
				add(new Label("<<interface>>"));
			} else if (classifier instanceof UMLEnum) {
				Label titleLabel = new Label("<<enum>>");
				add(titleLabel);
			}

			add(new Label(classifier.getSimpleName()));

			setOpaque(false);
		}
	}

	private class SectionPanel extends BoxPanel {
		private static final long serialVersionUID = 1L;

		public SectionPanel() {
			
			setOpaque(false);
		}
	}

	public UMLClassifier getClassifier() {
		return classifier;
	}

	@Override
	public void move(MouseEvent e) {
		setLocation((getX() + e.getX() - eX), (getY() + e.getY() - eY));

		// TODO : notify associated relations
	}
}
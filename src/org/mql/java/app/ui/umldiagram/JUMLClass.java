package org.mql.java.app.ui.umldiagram;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;

import org.mql.java.app.models.UMLClassifier;
import org.mql.java.app.models.UMLConstant;
import org.mql.java.app.models.UMLEntity;
import org.mql.java.app.models.UMLEnum;
import org.mql.java.app.models.UMLField;
import org.mql.java.app.models.UMLInterface;
import org.mql.java.app.models.UMLMethod;
import org.mql.java.app.ui.app.BoxPanel;
import org.mql.java.app.ui.app.Label;

public class JUMLClass extends BoxPanel implements Movable {
	private static final long serialVersionUID = 1L;

	private Color color;

	private UMLClassifier classifier;

	private TitlePanel titlePanel;
	private SectionPanel attributesPanel;
	private SectionPanel operationsPanel;

	private int eX, eY;

	public JUMLClass(UMLClassifier classifier) {
		this(classifier, Color.BLACK);
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
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			setCursor(new Cursor(Cursor.MOVE_CURSOR));
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

	public JUMLClass(UMLClassifier classifier, Color color) {
		this.classifier = classifier;
		this.color = color;

		addMouseListener(new CustomMouseListener());
		addMouseMotionListener(new CustomMouseMotionListener());
	}

	private void drawTitlePanel() {
		titlePanel = new TitlePanel();
		add(titlePanel);
	}

	private void drawAttributesPanel() {
		attributesPanel = new SectionPanel();

		for (UMLEntity umlMember : classifier.getUmlEntities()) {
			if (umlMember instanceof UMLField) {
				attributesPanel.add(new JUMLEntity(umlMember));
			}
		}

		add(attributesPanel);
	}

	private void drawOperationsPanel() {
		operationsPanel = new SectionPanel();

		for (UMLEntity umlMember : classifier.getUmlEntities()) {
			if (umlMember instanceof UMLMethod) {
				operationsPanel.add(new JUMLEntity(umlMember));
			}
		}

		add(operationsPanel);
	}

	private void drawConstantsPanel() {
		attributesPanel = new SectionPanel();

		for (UMLEntity umlMember : classifier.getUmlEntities()) {
			if (umlMember instanceof UMLConstant) {
				attributesPanel.add(new JUMLEntity(umlMember));
			}
		}

		add(attributesPanel);
	}

	private class TitlePanel extends BoxPanel {
		private static final long serialVersionUID = 1L;

		public TitlePanel() {
			setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, color));

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
			setBorder(1);
			setBorderTop(0);

			setOpaque(false);
		}
	}

	@Override
	public Object draw() {
		drawTitlePanel();

		if (classifier instanceof UMLEnum) {
			drawConstantsPanel();
		} else {
			drawAttributesPanel();
			drawOperationsPanel();
		}

		if (classifier instanceof UMLInterface) {
		    setBackground(new Color(255, 189, 153));  // Peach
		} else if (classifier instanceof UMLEnum) {
		    setBackground(new Color(141, 192, 237));  // Sky blue
		} else {
		    setBackground(new Color(255, 255, 204));  // Light yellow
		}

		setSize(getPreferredSize());
		return this;
	}

	@Override
	public void move(MouseEvent e) {
		setLocation((getX() + e.getX() - eX), (getY() + e.getY() - eY));

		// TODO : notify associated relations
	}
}

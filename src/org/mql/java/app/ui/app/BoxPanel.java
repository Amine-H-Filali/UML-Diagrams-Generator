package org.mql.java.app.ui.app;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;





public class BoxPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	public BoxPanel() {
		this(BoxLayout.Y_AXIS);
	}

	public BoxPanel(int axis) {
		setLayout(new BoxLayout(this, axis));
	}

	public void setBorder(int size) {
		setBorder(BorderFactory.createMatteBorder(size, size, size, size, Color.black));
	}

	public void setBorderTop(int size) {
		setBorder(
				BorderFactory.createMatteBorder(size, getBorder() != null ? getBorder().getBorderInsets(this).left : 0,
						getBorder() != null ? getBorder().getBorderInsets(this).bottom : 0,
						getBorder() != null ? getBorder().getBorderInsets(this).right : 0, Color.black));
	}

	public void setBorderBottom(int size) {
		setBorder(BorderFactory.createMatteBorder(getBorder() != null ? getBorder().getBorderInsets(this).top : 0,
				getBorder() != null ? getBorder().getBorderInsets(this).left : 0, size,
				getBorder() != null ? getBorder().getBorderInsets(this).right : 0, Color.black));
	}

	public void setBorderLeft(int size) {
		setBorder(BorderFactory.createMatteBorder(getBorder() != null ? getBorder().getBorderInsets(this).top : 0, size,
				getBorder() != null ? getBorder().getBorderInsets(this).bottom : 0,
				getBorder() != null ? getBorder().getBorderInsets(this).right : 0, Color.black));
	}

	public void setBorderRight(int size) {
		setBorder(BorderFactory.createMatteBorder(getBorder() != null ? getBorder().getBorderInsets(this).top : 0,
				getBorder() != null ? getBorder().getBorderInsets(this).left : 0,
				getBorder() != null ? getBorder().getBorderInsets(this).bottom : 0, size, Color.black));
	}

	public void setBackground(Color color, int opacity) {
		setBackgroundWithOpacity(Color.white, 50);
	}
	
	
	public void setBackgroundWithOpacity(Color color, int opacity) {
	    int alpha = (int) (opacity * 2.55); // Convert opacity (0-100) to alpha (0-255)
	    Color colorWithOpacity = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
	    setBackground(colorWithOpacity);
	}
}
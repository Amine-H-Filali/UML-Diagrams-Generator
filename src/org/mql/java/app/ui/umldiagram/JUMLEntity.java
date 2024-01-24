package org.mql.java.app.ui.umldiagram;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import org.mql.java.app.models.UMLConstant;
import org.mql.java.app.models.UMLEntity;
import org.mql.java.app.models.UMLMethod;
import org.mql.java.app.models.UMLPropertyMember;
import org.mql.java.app.ui.app.Label;

public class JUMLEntity extends JPanel  {
	private static final long serialVersionUID = 1L;

	protected UMLEntity umlEntity;

	protected Label signatureLabel;

	public JUMLEntity(UMLEntity umlCharacteristic) {
		this.umlEntity = umlCharacteristic;

		setOpaque(false);
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		signatureLabel = new Label();

		if (umlCharacteristic instanceof UMLConstant) {
			signatureLabel.addText(umlCharacteristic.getName());
		} else {
			UMLPropertyMember property = (UMLPropertyMember) umlCharacteristic;

			if (property.isStatic()) {
				signatureLabel.setUnderline();
			}

			signatureLabel.addText(property.getVisibility().getSymbol());
			signatureLabel.addText(property.getName());

			if (property instanceof UMLMethod) {
				UMLMethod operation = (UMLMethod) property;
				signatureLabel.addText("(");

				for (int i = 0; i < operation.getParameters().size(); i++) {
					signatureLabel.addText(operation.getParameters().get(i).getSimpleType());
					if (i < operation.getParameters().size()-1) {
						signatureLabel.addText(", ");
					}
				}

				signatureLabel.addText(")");
			}

			if (property.getSimpleType() != null) {
				signatureLabel.addText(": " + property.getSimpleType());
			}
		}

		add(signatureLabel);
	}

}
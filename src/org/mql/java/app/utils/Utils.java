package org.mql.java.app.utils;

import java.awt.Color;

public class Utils {
	
	public static Color rgbaColor(Color color, int opacity) {
		return new Color(color.getRed(), color.getGreen(), color.getBlue(), opacity);
	}

	public static Color rgbColor(int r, int g, int b) {
		return new Color(r, g, b);
	}
	
	
}

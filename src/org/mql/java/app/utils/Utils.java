package org.mql.java.app.utils;

import java.lang.reflect.Modifier;

import org.mql.java.app.enums.Visibility;

public class Utils {

	public static Visibility getVisibilityFromModifiers(int modifiers) {
		String modifiersString = Modifier.toString(modifiers);

		if (modifiersString.contains("public")) {
			return Visibility.PUBLIC;
		}
		else if (modifiersString.contains("private")) {
			return Visibility.PRIVATE;
		}
		else if (modifiersString.contains("protected")) {
			return Visibility.PROTECTED;
		}
		else {
			return Visibility.PACKAGE;
		}
	}

	public static boolean isStatic(int modifiers) {
		String modifiersString = Modifier.toString(modifiers);
		return modifiersString.contains("static");
	}

	public static boolean isConstant(int modifiers) {
		String modifiersString = Modifier.toString(modifiers);
		return modifiersString.contains("final");
	}
}

package org.mql.java.app.utils;

import java.io.File;

public class ClassFileParser {

	
	public static String binPath(File file) {
		try {
			String path = file.getAbsolutePath();
			return path.substring(0, path.indexOf("bin")+3);
		} catch (Exception e) {
			return null;
		}
	}

	public static String fileName(File file) {
		try {
			String path = file.getAbsolutePath();
			String out = path.substring(path.indexOf("bin")+4, path.length()).replace("\\", ".").replace(".class", "");
			return out;
		} catch(StringIndexOutOfBoundsException e) {
			return null;
		}
	}
}

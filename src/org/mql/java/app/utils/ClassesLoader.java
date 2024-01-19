package org.mql.java.app.utils;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassesLoader {
	
	private URLClassLoader loader;

	public ClassesLoader(String projectPath) throws Exception {
		Path binPath = Paths.get(projectPath, "bin");
        loader = new URLClassLoader(new URL[]{binPath.toUri().toURL()});
	}
	


	public Class<?> loadClass(String className) throws Exception {
		return loader.loadClass(className);
	}

   
}

package org.mql.java.app.parsers;



import org.mql.java.app.models.UMLInterfaceModel;
import org.mql.java.app.utils.ClassesLoader;
import org.mql.java.app.utils.Utils;

public class InterfaceParser {

	private UMLInterfaceModel interfacee;

	public InterfaceParser(String projectPath, String interfaceName) {
		this(ClassesLoader.forName(projectPath, interfaceName));
	}


	public InterfaceParser(Class<?> clazz) {
		interfacee = new UMLInterfaceModel(clazz.getName());

		interfacee.setFields(Utils.getUMLFields(clazz.getDeclaredFields()));
		interfacee.setMethods(Utils.getUMLMethods(clazz.getDeclaredMethods()));
	}
	
	

	public UMLInterfaceModel getInterface() {
		return interfacee;
	}
}

package org.mql.java.app.utils;

import java.lang.reflect.Field;



public class UMLReflectionUtils {
	

	public static boolean isIterable(Field field) {		
		return field.getType().isArray() 
		|| field.getType().getName().contains("Iterable")
		|| field.getType().getName().contains("Collection") 
		|| field.getType().getName().contains("List")
		|| field.getType().getName().contains("ArrayList")
		|| field.getType().getName().contains("LinkedList")
		|| field.getType().getName().contains("Vector")
		|| field.getType().getName().contains("Stack")
		|| field.getType().getName().contains("Queue")
		|| field.getType().getName().contains("PriorityQueue")
		|| field.getType().getName().contains("Deque")
		|| field.getType().getName().contains("ArrayDeque")
		|| field.getType().getName().contains("Set")
		|| field.getType().getName().contains("HashSet")
		|| field.getType().getName().contains("LinkedHashSet")
		|| field.getType().getName().contains("SortedSet")
		|| field.getType().getName().contains("TreeSet");
	}

	
}

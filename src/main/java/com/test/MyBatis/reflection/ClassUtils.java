package com.test.MyBatis.reflection;

public class ClassUtils {
	
	public static Class<?> forName(String classPath, String source, String id) {
		try {
			return Class.forName(classPath);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(source + " " + id + " " + classPath + " : was not found");
		}
	}
}

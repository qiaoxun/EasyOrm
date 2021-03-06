package com.test.MyBatis.reflection;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;


public class ClassUtils {
	
	/**
	 * forName
	 * @param classPath
	 * @param source
	 * @param id
	 * @return
	 */
	public static Class<?> forName(String classPath, String source, String id) {
		try {
			return Class.forName(classPath);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(source + " " + id + " " + classPath + " : was not found");
		}
	}
	
	/**
	 * Obtain all fields
	 * @param clazz
	 * @param fieldsMap
	 */
	public static void resolveClassForFields(Class<?> clazz, Map<String, Field> fieldsMap) {
		Field[] fields = FieldUtils.getAllFields(clazz);
		for(Field f : fields) {
			fieldsMap.put(f.getName(), f);
		}
	}
	
	/**
	 * Obtain the value of the specified field
	 * @param f
	 * @param target
	 * @return
	 */
	public static Object getFieldValue(Field f, Object target) {
		try {
			f.setAccessible(true);
			return FieldUtils.readField(f, target);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
}

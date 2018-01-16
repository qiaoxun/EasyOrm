package com.test.MyBatis.utils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ObjectTypeUtils {
	private static Set<Class<?>> simpleClazzSet = new HashSet<>();
	
	static {
		simpleClazzSet.add(byte.class);
		simpleClazzSet.add(Byte.class);
		simpleClazzSet.add(short.class);
		simpleClazzSet.add(Short.class);
		simpleClazzSet.add(int.class);
		simpleClazzSet.add(Integer.class);
		simpleClazzSet.add(long.class);
		simpleClazzSet.add(Long.class);
		simpleClazzSet.add(char.class);
		simpleClazzSet.add(Character.class);
		simpleClazzSet.add(double.class);
		simpleClazzSet.add(Double.class);
		simpleClazzSet.add(float.class);
		simpleClazzSet.add(Float.class);
		simpleClazzSet.add(boolean.class);
		simpleClazzSet.add(Boolean.class);
		simpleClazzSet.add(String.class);
	}
	
	/**
	 * 是否是简单的类型
	 * @param clazz
	 * @return
	 */
	public static boolean isSimpleObject(Class<?> clazz) {
		return simpleClazzSet.contains(clazz);
	}
	
	/**
	 * 判断是否是map类型
	 * @param clazz
	 * @return
	 */
	public static boolean isMap(Class<?> clazz) {
		return Map.class.isAssignableFrom(clazz);
	}
	
	/**
	 * 是否是数组
	 * @param clazz
	 * @return
	 */
	public static boolean isArray(Class<?> clazz) {
		return clazz.isArray();
	}
	
	/**
	 * 是否是 byte 类型
	 * @param clazz
	 * @return
	 */
	public static boolean isByte(Class<?> clazz) {
		return clazz.equals(byte.class) || clazz.equals(Byte.class);
	}
	
	/**
	 * 是否是 short 类型
	 * @param clazz
	 * @return
	 */
	public static boolean isShort(Class<?> clazz) {
		return clazz.equals(short.class) || clazz.equals(Short.class);
	}
	
	/**
	 * 是否是 int 类型
	 * @param clazz
	 * @return
	 */
	public static boolean isInt(Class<?> clazz) {
		return clazz.equals(int.class) || clazz.equals(Integer.class);
	}
	
	/**
	 * 是否是 long 类型
	 * @param clazz
	 * @return
	 */
	public static boolean isLong(Class<?> clazz) {
		return clazz.equals(long.class) || clazz.equals(Long.class);
	}
	
	/**
	 * 是否是 char 类型
	 * @param clazz
	 * @return
	 */
	public static boolean isChar(Class<?> clazz) {
		return clazz.equals(char.class) || clazz.equals(Character.class);
	}
	
	/**
	 * 是否是 double 类型
	 * @param clazz
	 * @return
	 */
	public static boolean isDouble(Class<?> clazz) {
		return clazz.equals(double.class) || clazz.equals(Double.class);
	}
	
	/**
	 * 是否是 float 类型
	 * @param clazz
	 * @return
	 */
	public static boolean isFloat(Class<?> clazz) {
		return clazz.equals(float.class) || clazz.equals(Float.class);
	}
	
	/**
	 * 是否是 boolean 类型
	 * @param clazz
	 * @return
	 */
	public static boolean isBoolean(Class<?> clazz) {
		return clazz.equals(boolean.class) || clazz.equals(Boolean.class);
	}
	
	/**
	 * 是否是 String 类型
	 * @param clazz
	 * @return
	 */
	public static boolean isString(Class<?> clazz) {
		return clazz.equals(String.class);
	}
	
}

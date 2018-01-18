package com.test.MyBatis.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class InnerParameters {
	
	private static Map<String, Class<?>> parameterTypes = new HashMap<String, Class<?>>();
	
	static {
		parameterTypes.put("int", int.class);
		parameterTypes.put("Integer", Integer.class);
		parameterTypes.put("short", short.class);
		parameterTypes.put("Short", Short.class);
		parameterTypes.put("byte", byte.class);
		parameterTypes.put("Byte", Byte.class);
		parameterTypes.put("char", char.class);
		parameterTypes.put("Character", Character.class);
		parameterTypes.put("long", long.class);
		parameterTypes.put("Long", Long.class);
		parameterTypes.put("boolean", Boolean.class);
		parameterTypes.put("double", double.class);
		parameterTypes.put("Double", Double.class);
		parameterTypes.put("float", float.class);
		parameterTypes.put("Float", Float.class);
		parameterTypes.put("map", Map.class);
		parameterTypes.put("hashmap", HashMap.class);
		parameterTypes.put("date", Date.class);
	}
	
	public static boolean contains(String key) {
		return parameterTypes.containsKey(key);
	}
	
	public static Class<?> getValue(String key) {
		return parameterTypes.get(key);
	}
	
}

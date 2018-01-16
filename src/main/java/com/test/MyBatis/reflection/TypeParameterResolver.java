package com.test.MyBatis.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class TypeParameterResolver {
	
	public static Type resolveReturnType(Class<?> mapperInterface, Method method) {
		return method.getReturnType();
	}
}

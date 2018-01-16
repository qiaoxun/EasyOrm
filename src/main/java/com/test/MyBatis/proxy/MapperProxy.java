package com.test.MyBatis.proxy;

import java.lang.reflect.Proxy;

import com.test.MyBatis.session.SqlSession;

public class MapperProxy {
	
	public static <T> T getMapper(SqlSession session, Class<T> interfaceClazz) {
		@SuppressWarnings("unchecked")
		T t = (T) Proxy.newProxyInstance(MapperProxy.class.getClassLoader(), new Class[]{interfaceClazz}, 
				new MapperHandler(session, interfaceClazz.getName()));
		return t;
	}
}

package com.test.MyBatis.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.test.MyBatis.mapping.MappedStatement;
import com.test.MyBatis.session.SqlSession;

public class MapperHandler implements InvocationHandler {
	
	private String dot = ".";

	private String className;
	private SqlSession sqlSession;
	
	public MapperHandler(SqlSession sqlSession, String className) {
		this.sqlSession = sqlSession;
		this.className = className;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// 返回结果
		Object result = null;
		// 方法名称
		String methodName = method.getName();
		String statement = className + dot + methodName;
		MappedStatement ms = sqlSession.getConguration().getMappedStatement(statement);
		
		if (ms == null) {
			throw new RuntimeException("Can not find statement " + statement);
		}
		
		switch (ms.getSqlCommandType()) {
			case INSERT : ; 
			case UPDATE : ;
			case DELETE : result = sqlSession.update(statement, args); break;
			case SELECT : {
				// TODO
				method.getReturnType();
				result = sqlSession.selectList(statement, args); 
				break;
			}
			default : throw new RuntimeException(ms.getSqlCommandType() + " not been deal yet");
		}
		
		return result;
	}

}

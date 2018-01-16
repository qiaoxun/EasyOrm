package com.test.MyBatis.binding;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

import com.test.MyBatis.mapping.MappedStatement;
import com.test.MyBatis.mapping.SqlCommandType;
import com.test.MyBatis.reflection.TypeParameterResolver;
import com.test.MyBatis.session.Configuration;
import com.test.MyBatis.session.SqlSession;

public class MapperMethod {

	private SqlCommand sqlCommand;

	private MethodSignature methodSignature;

	public MapperMethod(Configuration configuration, Class<?> mapperInterface, Method method) {
		this.sqlCommand = new SqlCommand(configuration, mapperInterface, method);
		this.methodSignature = new MethodSignature(configuration, mapperInterface, method);
	}

	public Object Execute(SqlSession session, Object[] args) {
		Object result = null;
		String statement = sqlCommand.getName();
		switch(sqlCommand.getType()) {
			case INSERT : {
				result = session.insert(statement, args);
			}
			case UPDATE : {
				result = session.update(statement, args);
			}
			case DELETE : {
				result = session.delete(statement, args);
			}
			case SELECT : {
				if (methodSignature.isReturnsVoid()) {
					result = null;
				} else if (methodSignature.isReturnsMany()) {
					result = session.selectList(statement, args);
				} else if (methodSignature.isReturnsCursor()) {
					// TODO
				} else if (methodSignature.isReturnsMap()) {
					result = session.selectMap(statement, methodSignature.getMapKey());
				}
			}
			default:{
//				throw new RuntimeException("TODO...");
			}
		}
		
		return result;
	}

	public static class SqlCommand {
		private final String name;
		private final SqlCommandType type;

		public SqlCommand(Configuration configuration, Class<?> mapperInterface, Method method) {
			String statement = mapperInterface.getName() + "." + method.getName();
			MappedStatement ms = null;
			if (configuration.hasStatement(statement)) {
				ms = configuration.getMappedStatement(statement);
			}

			if (ms == null) {
				throw new RuntimeException("Invalid bound statement (not found)" + statement);
			} else {
				name = ms.getId();
				type = ms.getSqlCommandType();

				if (type.equals(SqlCommandType.UNKNOWN)) {
					throw new RuntimeException("Unknown execution method for : " + name);
				}
			}
		}

		public String getName() {
			return name;
		}

		public SqlCommandType getType() {
			return type;
		}
	}

	public static class MethodSignature {
		private boolean returnsMany;
		private boolean returnsMap;
		private boolean returnsVoid;
		private boolean returnsCursor;
		private Class<?> returnsType;
		private String mapKey;
		private Integer resultHanlderIndex;
		private Integer rowBoundsIndex;

		public MethodSignature(Configuration configuration, Class<?> mapperInterface, Method method) {
			Type resolveReturnsType = TypeParameterResolver.resolveReturnType(mapperInterface, method);
			if (resolveReturnsType instanceof Class<?>) {
				this.returnsType = (Class<?>) resolveReturnsType;
			} else if (resolveReturnsType instanceof ParameterizedType) {
				this.returnsType = (Class<?>) ((ParameterizedType) resolveReturnsType).getRawType();
			} else {
				this.returnsType = method.getReturnType();
			}

			this.returnsVoid = void.class.equals(this.returnsType);
			this.returnsMany = Collection.class.isAssignableFrom(this.returnsType);
			this.returnsMap = Map.class.isAssignableFrom(this.returnsType);
			// TODO
			this.returnsCursor = false;
			// TODO
			this.mapKey = null;
			// TODO
			this.resultHanlderIndex = null;
			// TODO
			this.rowBoundsIndex = null;
		}

		public boolean isReturnsMany() {
			return returnsMany;
		}

		public void setReturnsMany(boolean returnsMany) {
			this.returnsMany = returnsMany;
		}

		public boolean isReturnsMap() {
			return returnsMap;
		}

		public void setReturnsMap(boolean returnsMap) {
			this.returnsMap = returnsMap;
		}

		public boolean isReturnsVoid() {
			return returnsVoid;
		}

		public void setReturnsVoid(boolean returnsVoid) {
			this.returnsVoid = returnsVoid;
		}

		public boolean isReturnsCursor() {
			return returnsCursor;
		}

		public void setReturnsCursor(boolean returnsCursor) {
			this.returnsCursor = returnsCursor;
		}

		public Class<?> getReturnsType() {
			return returnsType;
		}

		public void setReturnsType(Class<?> returnsType) {
			this.returnsType = returnsType;
		}

		public String getMapKey() {
			return mapKey;
		}

		public void setMapKey(String mapKey) {
			this.mapKey = mapKey;
		}

		public Integer getResultHanlderIndex() {
			return resultHanlderIndex;
		}

		public void setResultHanlderIndex(Integer resultHanlderIndex) {
			this.resultHanlderIndex = resultHanlderIndex;
		}

		public Integer getRowBoundsIndex() {
			return rowBoundsIndex;
		}

		public void setRowBoundsIndex(Integer rowBoundsIndex) {
			this.rowBoundsIndex = rowBoundsIndex;
		}
	}
}

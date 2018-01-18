package com.test.MyBatis.executor.statement;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.MyBatis.executor.Executor;
import com.test.MyBatis.executor.parameter.ParameterHandler;
import com.test.MyBatis.executor.resultset.ResultSetHandler;
import com.test.MyBatis.mapping.MappedStatement;
import com.test.MyBatis.mapping.ParameterMapping;
import com.test.MyBatis.reflection.ClassUtils;
import com.test.MyBatis.type.TypeHandler;
import com.test.MyBatis.utils.InnerParameters;

public class PreparedStatementHandler extends BaseStatementHandler {

	public PreparedStatementHandler(MappedStatement mappedStatement, Object parameterObject, Executor executor,
			ParameterHandler parameterHandler) {
		super(mappedStatement, parameterObject, executor, parameterHandler);
	}

	@Override
	public int update(Statement statement) throws SQLException {
		PreparedStatement ps = (PreparedStatement) statement;
		ps.execute();
		int affectedRows = ps.getUpdateCount();
		return affectedRows;
	}

	@Override
	public <E> List<E> query(Statement statement, ResultSetHandler resultSetHandler) throws SQLException {
		PreparedStatement ps = (PreparedStatement) statement;
		ps.execute();
		return resultSetHandler.handleResultSets(ps);
	}


	@Override
	protected Statement instantiateStatement(Connection connection) throws SQLException {
		return connection.prepareStatement(boundSql.getSql());
	}

	@Override
	public void parameterize(Statement statement) throws SQLException {
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		
		if (null == parameterMappings || parameterMappings.isEmpty()) {
			return;
		}
		
		Object parameterObj = boundSql.getParameterObject();
		
		// 如果是简单类型
		if (InnerParameters.contains(parameterObj.getClass().getName())) {
			ParameterMapping pm = parameterMappings.get(0);
			TypeHandler typeHandler = pm.getTypeHandler();
			typeHandler.setParameter((PreparedStatement)statement, 1, parameterObj, pm.getJdbcType());
		} else {
			
			if (parameterObj.getClass().isArray()) {
				Object[] obj = (Object[]) parameterObj;
				parameterObj = obj[0];
			}
			
			Map<String, Field> fieldsMap = new HashMap<String, Field>();
			ClassUtils.resolveClassForFields(parameterObj.getClass(), fieldsMap);
			
			for (int i = 0, len = parameterMappings.size(); i < len; i++) {
				ParameterMapping pm = parameterMappings.get(i);
				String property = pm.getProperty();
				
				if (fieldsMap.containsKey(property)) {
					TypeHandler typeHandler = pm.getTypeHandler();
					Object value = ClassUtils.getFieldValue(fieldsMap.get(property), parameterObj);
					typeHandler.setParameter((PreparedStatement)statement, i + 1, value, pm.getJdbcType());
				} else {
					throw new RuntimeException("com.test.MyBatis.executor.statement.PreparedStatementHandler " + property);
				}
				
				System.out.println(pm.getProperty());
			}
			
		}
		
	}
}

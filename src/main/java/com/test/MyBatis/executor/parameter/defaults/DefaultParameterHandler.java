package com.test.MyBatis.executor.parameter.defaults;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.test.MyBatis.executor.parameter.ParameterHandler;
import com.test.MyBatis.mapping.BoundSql;
import com.test.MyBatis.mapping.MappedStatement;
import com.test.MyBatis.mapping.ParameterMapping;
import com.test.MyBatis.session.Configuration;

public class DefaultParameterHandler implements ParameterHandler {
	
	private Configuration configuration;
	
	private MappedStatement mappedStatement;
	
	private BoundSql boundSql;
	
	private Object parameterObject;
	
	
	public DefaultParameterHandler(MappedStatement mappedStatement,	Object parameterObject) {
		this.configuration = mappedStatement.getConfiguration();
		this.mappedStatement = mappedStatement;
		this.boundSql = mappedStatement.getBoundSql(parameterObject);
		this.parameterObject = parameterObject;
	}

	@Override
	public Object getParameterObject() {
		return parameterObject;
	}

	@Override
	public void setParamenters(PreparedStatement statement) throws SQLException {
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		for (int i = 0, len = parameterMappings.size(); i < len; i++) {
			ParameterMapping pm = parameterMappings.get(i);
			String property = pm.getProperty();
//			statement.set
		}
	}

}

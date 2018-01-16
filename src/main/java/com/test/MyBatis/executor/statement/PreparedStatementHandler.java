package com.test.MyBatis.executor.statement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.test.MyBatis.executor.Executor;
import com.test.MyBatis.executor.parameter.ParameterHandler;
import com.test.MyBatis.executor.resultset.ResultSetHandler;
import com.test.MyBatis.mapping.MappedStatement;
import com.test.MyBatis.mapping.ParameterMapping;

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
		Object parameterObj = boundSql.getParameterObject();
		
		
		
		
		Map<String, Object> map = null;
		
		for (int i = 0, len = parameterMappings.size(); i < len; i++) {
			ParameterMapping pm = parameterMappings.get(i);
			System.out.println(pm.getProperty());
		}
	}
}

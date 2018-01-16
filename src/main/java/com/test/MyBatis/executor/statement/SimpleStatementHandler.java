package com.test.MyBatis.executor.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.test.MyBatis.executor.Executor;
import com.test.MyBatis.executor.parameter.ParameterHandler;
import com.test.MyBatis.executor.resultset.ResultSetHandler;
import com.test.MyBatis.mapping.MappedStatement;

public class SimpleStatementHandler extends BaseStatementHandler {

	public SimpleStatementHandler(MappedStatement mappedStatement, Object parameterObject, Executor executor,
			ParameterHandler parameterHandler) {
		super(mappedStatement, parameterObject, executor, parameterHandler);
	}

	@Override
	public int update(Statement statement) throws SQLException {
		
		return 0;
	}

	@Override
	public <E> List<E> query(Statement statement, ResultSetHandler resultSetHandler) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Statement instantiateStatement(Connection connection) throws SQLException {
		
		return null;
	}

}

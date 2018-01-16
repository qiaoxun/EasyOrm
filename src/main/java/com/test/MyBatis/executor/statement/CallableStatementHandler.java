package com.test.MyBatis.executor.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.test.MyBatis.executor.Executor;
import com.test.MyBatis.executor.parameter.ParameterHandler;
import com.test.MyBatis.executor.resultset.ResultSetHandler;
import com.test.MyBatis.mapping.MappedStatement;

public class CallableStatementHandler extends BaseStatementHandler {
	
	

	public CallableStatementHandler(MappedStatement mappedStatement, Object parameterObject, Executor executor,
			ParameterHandler parameterHandler) {
		super(mappedStatement, parameterObject, executor, parameterHandler);
	}

	@Override
	public int update(Statement statement) throws SQLException {
		return 0;
	}

	@Override
	public <E> List<E> query(Statement statement, ResultSetHandler resultSetHandler) throws SQLException {
		return null;
	}


	@Override
	protected Statement instantiateStatement(Connection connection) {
		return null;
	}

}

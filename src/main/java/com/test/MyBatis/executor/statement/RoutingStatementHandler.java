package com.test.MyBatis.executor.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.test.MyBatis.executor.Executor;
import com.test.MyBatis.executor.parameter.ParameterHandler;
import com.test.MyBatis.executor.resultset.ResultSetHandler;
import com.test.MyBatis.mapping.BoundSql;
import com.test.MyBatis.mapping.MappedStatement;
import com.test.MyBatis.session.ResultHandler;

public class RoutingStatementHandler implements StatementHandler {
	
	private StatementHandler delegate;
	
	public RoutingStatementHandler(Executor executor, MappedStatement ms, Object parameterObject, ResultHandler<?> resultHandler, BoundSql boundSql) {
		switch(ms.getStatementType()) {
			case STATEMENT : 
				delegate = new SimpleStatementHandler(ms, parameterObject, executor, null);
				break;
			case PREPARED :
				delegate = new PreparedStatementHandler(ms, parameterObject, executor, null);
				break;
			case CALLABLE :
				delegate = new CallableStatementHandler(ms, parameterObject, executor, null);
				break;
			default :
				throw new RuntimeException("Unknow statement type " + ms.getStatementType());
		}
	}

	@Override
	public Statement prepare(Connection connection, Integer transactionTimeout) throws SQLException {
		return delegate.prepare(connection, transactionTimeout);
	}

	@Override
	public int update(Statement statement) throws SQLException {
		return delegate.update(statement);
	}

	@Override
	public <E> List<E> query(Statement statement, ResultSetHandler resultSetHandler) throws SQLException {
		return delegate.query(statement, resultSetHandler);
	}

	@Override
	public BoundSql getBoundSql() {
		return delegate.getBoundSql();
	}

	@Override
	public ParameterHandler getParameterHandler() {
		return delegate.getParameterHandler();
	}

	@Override
	public void closeStatement(Statement statement) {
		delegate.closeStatement(statement);
	}

	@Override
	public void parameterize(Statement statement) throws SQLException {
		delegate.parameterize(statement);
	}

}

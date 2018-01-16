package com.test.MyBatis.executor.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.test.MyBatis.executor.Executor;
import com.test.MyBatis.executor.parameter.ParameterHandler;
import com.test.MyBatis.mapping.BoundSql;
import com.test.MyBatis.mapping.MappedStatement;
import com.test.MyBatis.session.Configuration;

public abstract class BaseStatementHandler implements StatementHandler {
	
	protected Configuration configuration;
	
	protected MappedStatement mappedStatement;
	
	protected Executor executor;
	
	protected ParameterHandler parameterHandler;
	
	protected BoundSql boundSql;
	
	
	public BaseStatementHandler(MappedStatement mappedStatement, Object parameterObject
			, Executor executor, ParameterHandler parameterHandler) {
		this.configuration = mappedStatement.getConfiguration();
		this.mappedStatement = mappedStatement;
		this.executor = executor;
		this.parameterHandler = parameterHandler;
		this.boundSql = mappedStatement.getBoundSql(parameterObject);
	}

	@Override
	public Statement prepare(Connection connection, Integer transactionTimeout) throws SQLException {
		Statement statement = null;
		try {
			statement = instantiateStatement(connection);
			setStatementTimeout(statement, transactionTimeout);
			setStatementFatchSize(statement);
			parameterize(statement);
			return statement;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public void closeStatement(Statement statement) {
		try {
			if (null != statement) {
				statement.close();
			}
		} catch (Exception e) {
		}
	}

	@Override
	public BoundSql getBoundSql() {
		return boundSql;
	}

	@Override
	public ParameterHandler getParameterHandler() {
		return null;
	}
	

	@Override
	public void parameterize(Statement statement) throws SQLException {
		
	}
	
	/**
	 * 初始化 statement，留给子类去实现
	 * @param connection
	 * @return
	 * @throws SQLException 
	 */
	protected abstract Statement instantiateStatement(Connection connection) throws SQLException;
	
	/**
	 * 设置超时时间
	 * @param statement
	 * @param transactionTimeout
	 */
	protected void setStatementTimeout(Statement statement, Integer transactionTimeout) {
		
		Integer queryTimeout = null;
		
		if (null != mappedStatement.getTimeout()) {
			queryTimeout = mappedStatement.getTimeout();
		} else if (null != configuration.getDefaultStatementTimeout()) {
			queryTimeout = configuration.getDefaultStatementTimeout();
		}
		
		Integer timeout = null;
		
		if (transactionTimeout == null || transactionTimeout == 0) {
			if (queryTimeout != null && queryTimeout != 0) {
				timeout = queryTimeout;
			}
		} else {
			if (queryTimeout == null || queryTimeout == 0) {
				timeout = transactionTimeout;
			} else {
				if (transactionTimeout < queryTimeout) {
					timeout = transactionTimeout;
				} else {
					timeout = queryTimeout;
				}
			}
		}
		
		if (timeout == null || timeout == 0) {
			return;
		}
		
		try {
			statement.setQueryTimeout(timeout);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 设置取值数量
	 * @param statement
	 */
	public void setStatementFatchSize(Statement statement) {
		if (null != mappedStatement.getFatchSize()) {
			try {
				statement.setFetchSize(mappedStatement.getFatchSize());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

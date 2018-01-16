package com.test.MyBatis.executor;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.test.MyBatis.executor.resultset.defaults.DefaultResultSetHandler;
import com.test.MyBatis.executor.statement.StatementHandler;
import com.test.MyBatis.mapping.BoundSql;
import com.test.MyBatis.mapping.MappedStatement;
import com.test.MyBatis.session.Configuration;
import com.test.MyBatis.transaction.Transaction;

public class SimpleExecutor implements Executor{
	
	private Transaction transaction;
	
	private Configuration configuration;
	
	private boolean closed;
	
	public SimpleExecutor(Transaction transaction, Configuration configuration) {
		this.transaction = transaction;
		this.configuration = configuration;
//		closed = true;
	}
	
	@Override
	public void commit(boolean required) throws SQLException {
		if (required) {
			checkTransactNull();
			transaction.commit();
		}
	}

	@Override
	public void rollback(boolean required) throws SQLException {
		try {
			checkTransactNull();
			transaction.rollback();
		} catch (Exception e) {
		}
	}

	@Override
	public Transaction getTransaction() {
		checkTransactNull();
		return transaction;
	}

	@Override
	public void close(boolean forceRollback) {
		if (forceRollback) {
			try {
				rollback(true);
			} catch (SQLException e) {
			}
		}
		if (null != transaction) {
			try {
				transaction.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public int update(MappedStatement ms, Object parameter) {
		StatementHandler statementHandler = this.configuration.newStatementHandler(this, ms, parameter, null, null);
		int count = 0;
		try {
			Statement statement = statementHandler.prepare(transaction.getConnection(), transaction.getTimeout());
			
			count = statementHandler.update(statement);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public <E> List<E> query(MappedStatement ms, Object parameter, BoundSql boundSql) {
		StatementHandler statementHandler = this.configuration.newStatementHandler(this, ms, parameter, null, null);
		Statement statement;
		try {
			statement = statementHandler.prepare(transaction.getConnection(), transaction.getTimeout());
			statementHandler.query(statement, new DefaultResultSetHandler());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <E> List<E> query(MappedStatement ms, Object parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isClosed() {
		return false;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
	/**
	 * 检查 transaction 是否为空
	 */
	private void checkTransactNull() {
		if (null == transaction) {
			throw new RuntimeException("transaction can not be null");
		}
	}
	
}

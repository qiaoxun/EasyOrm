package com.test.MyBatis.session.defaults;

import java.sql.Connection;

import com.test.MyBatis.executor.Executor;
import com.test.MyBatis.executor.SimpleExecutor;
import com.test.MyBatis.session.Configuration;
import com.test.MyBatis.session.SqlSession;
import com.test.MyBatis.session.SqlSessionFactory;
import com.test.MyBatis.transaction.Transaction;
import com.test.MyBatis.transaction.TransactionFactory;
import com.test.MyBatis.transaction.defaults.DefaultTransactionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory{
	
	private Configuration configuration;
	
	private TransactionFactory transactionFactory;
	
	public DefaultSqlSessionFactory(Configuration configuration) {
		this.configuration = configuration;
		transactionFactory = new DefaultTransactionFactory();
	}
	
	@Override
	public SqlSession openSession() {
		return openSession(false);
	}

	@Override
	public SqlSession openSession(boolean autoCommit) {
		final Transaction transaction = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), autoCommit);
		final Executor executor = new SimpleExecutor(transaction, configuration);
		return new DefaultSqlSession(configuration, executor, autoCommit);
	}

	@Override
	public SqlSession openSession(Connection connection) {
		final Transaction transaction = transactionFactory.newTransaction(connection);
		final Executor executor = new SimpleExecutor(transaction, configuration);
		return new DefaultSqlSession(configuration, executor, false);
	}

	@Override
	public Configuration getConfiguration() {
		return this.configuration;
	}

}

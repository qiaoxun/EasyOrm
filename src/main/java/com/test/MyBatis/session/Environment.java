package com.test.MyBatis.session;

import javax.sql.DataSource;

import com.test.MyBatis.transaction.TransactionFactory;

public class Environment {
	private final String id;
	private final TransactionFactory transactionFactory;
	private final DataSource dataSource;
	
	public Environment(String id, TransactionFactory transactionFactory, DataSource dataSource) {
		this.id = id;
		this.transactionFactory = transactionFactory;
		this.dataSource = dataSource;
	}
	
	public String getId() {
		return id;
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}

	public TransactionFactory getTransactionFactory() {
		return transactionFactory;
	}
}

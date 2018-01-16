package com.test.MyBatis.transaction.defaults;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import com.test.MyBatis.transaction.Transaction;
import com.test.MyBatis.transaction.TransactionFactory;

public class DefaultTransactionFactory implements TransactionFactory{

	@Override
	public void setProperties(Properties prop) {
		
	}

	@Override
	public Transaction newTransaction(Connection conn) {
		return new DefaultTransaction(conn);
	}

	@Override
	public Transaction newTransaction(DataSource dataSource, boolean autoCommit) {
		return new DefaultTransaction(dataSource, autoCommit);
	}

}

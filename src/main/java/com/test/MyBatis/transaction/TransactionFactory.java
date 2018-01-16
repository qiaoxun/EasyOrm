package com.test.MyBatis.transaction;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * create {@link Transaction} instance
 * @author Administrator
 *
 */
public interface TransactionFactory {
	/**
	 * Sets transaction custom properties
	 * @param prop
	 */
	void setProperties(Properties prop);
	
	/**
	 * Create a transaction out of a existing connection
	 * @param conn
	 * @return
	 */
	Transaction newTransaction(Connection conn);
	
	/**
	 * Create a transaction out of a datasource
	 * @param dataSource DataSource to take the connection from
	 * @param aotuCommit Desired autocommit
	 * @return
	 */
	Transaction newTransaction(DataSource dataSource, boolean aotuCommit);
}

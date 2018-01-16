package com.test.MyBatis.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public interface Transaction {
	/**
	 * Retrieve inner database connection
	 * @return
	 * @throws SQLException
	 */
	Connection getConnection() throws SQLException;
	
	/**
	 * Commit inner database connection
	 * @throws SQLException
	 */
	void commit() throws SQLException;
	
	/**
	 * Rollback inner database connection
	 * @throws SQLException
	 */
	void rollback() throws SQLException;
	
	/**
	 * Close inner database connection
	 * @throws SQLException
	 */
	void close() throws SQLException;
	
	/**
	 * Get transact timeout if set
	 * @return
	 * @throws SQLException
	 */
	Integer getTimeout() throws SQLException;
	
	/**
	 * set is transaction auto commit
	 * @param autoCommit
	 * @throws SQLException 
	 */
	void setAutoCommit(boolean autoCommit) throws SQLException;
}

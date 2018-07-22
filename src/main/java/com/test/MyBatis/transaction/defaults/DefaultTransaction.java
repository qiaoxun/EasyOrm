package com.test.MyBatis.transaction.defaults;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.test.MyBatis.transaction.Transaction;

public class DefaultTransaction implements Transaction {

	protected DataSource dataSource;

	protected Connection connection;

	protected boolean autoCommit;

	public DefaultTransaction(DataSource dataSource, boolean autoCommit) {
		this.dataSource = dataSource;
		this.autoCommit = autoCommit;
		try {
			this.connection = dataSource.getConnection();
			setAutoCommit(autoCommit);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public DefaultTransaction(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Connection getConnection() throws SQLException {
		if (connection == null) {
			connection = this.dataSource.getConnection();
		}
		return connection;
	}

	@Override
	public void commit() throws SQLException {
		if (null != connection) {
			connection.commit();
		}
		// close the connection
		close();
	}

	@Override
	public void rollback() throws SQLException {
		if (null != connection) {
			connection.rollback();
		}
		// close the connection
		close();
	}

	@Override
	public void close() throws SQLException {
		if (null != connection) {
			connection.close();
		}
	}

	@Override
	public Integer getTimeout() throws SQLException {
		return null;
	}

	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		this.autoCommit = autoCommit;
		connection.setAutoCommit(autoCommit);
	}
	
}

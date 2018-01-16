package com.test.MyBatis.executor;

import java.sql.SQLException;
import java.util.List;

import com.test.MyBatis.mapping.BoundSql;
import com.test.MyBatis.mapping.MappedStatement;
import com.test.MyBatis.transaction.Transaction;

public interface Executor {
	int update(MappedStatement ms, Object parameter);
	
	<E> List<E> query(MappedStatement ms, Object parameter, BoundSql boundSql);
	
	<E> List<E> query(MappedStatement ms, Object parameter);

	void commit(boolean required) throws SQLException;
	
	void rollback(boolean required) throws SQLException;
	
	Transaction getTransaction();
	
	void close(boolean forceRollback);
	
	boolean isClosed();
}

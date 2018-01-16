package com.test.MyBatis.executor.statement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.test.MyBatis.executor.parameter.ParameterHandler;
import com.test.MyBatis.executor.resultset.ResultSetHandler;
import com.test.MyBatis.mapping.BoundSql;

public interface StatementHandler {
	
	Statement prepare(Connection connection, Integer transactionTimeout) throws SQLException;
	
    void parameterize(Statement statement)
		      throws SQLException;
	
	int update(Statement statement) throws SQLException;
	
	<E> List<E> query(Statement statement, ResultSetHandler resultSetHandler) throws SQLException;
	
	BoundSql getBoundSql();
	
	ParameterHandler getParameterHandler();

	void closeStatement(Statement statement);
	
}

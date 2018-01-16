package com.test.MyBatis.session;

import java.sql.Connection;

public interface SqlSessionFactory {
	SqlSession openSession();
	
	SqlSession openSession(boolean autoCommit);
	
	SqlSession openSession(Connection connection);
	
	Configuration getConfiguration();
}

package com.test.MyBatis.session.defaults;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.test.MyBatis.executor.Executor;
import com.test.MyBatis.mapping.MappedStatement;
import com.test.MyBatis.proxy.MapperProxy;
import com.test.MyBatis.session.Configuration;
import com.test.MyBatis.session.SqlSession;

public class DefaultSqlSession implements SqlSession {
	
	private Configuration configuration;
	
	private Executor executor;
	
	private boolean autoCommit;

	public DefaultSqlSession(Configuration configuration, Executor executor, boolean autoCommit) {
		this.configuration = configuration;
		this.executor = executor;
		this.autoCommit = autoCommit;
	}

	@Override
	public <T> T selectOne(String statement) {
		List<T> list = selectList(statement);
		if (list.size() == 1) {
			return list.get(0);
		} else if (list.size() > 1) {
			throw new RuntimeException("Expected one (or null) result to returned by selectOne, but found " + list.size());
		}
		return null;
	}

	@Override
	public <T> T selectOne(String statement, Object parameter) {
		List<T> list = selectList(statement, parameter);
		if (list.size() == 1) {
			return list.get(0);
		} else if (list.size() > 1) {
			throw new RuntimeException("Expected one (or null) result to returned by selectOne, but found " + list.size());
		}
		return null;
	}

	@Override
	public <E> List<E> selectList(String statement) {
		return executor.query(configuration.getMappedStatement(statement), null);
	}

	@Override
	public <E> List<E> selectList(String statement, Object parameter) {
		return executor.query(configuration.getMappedStatement(statement), parameter);
	}

	@Override
	public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(String statement) {
		return update(statement);
	}

	@Override
	public int insert(String statement, Object parameter) {
		return update(statement, parameter);
	}

	@Override
	public int update(String statement) {
		return update(statement, null);
	}

	@Override
	public int update(String statement, Object parameter) {
		MappedStatement ms = this.configuration.getMappedStatement(statement);
		return this.executor.update(ms, parameter);
	}

	@Override
	public int delete(String statement) {
		return update(statement, null);
	}

	@Override
	public int delete(String statement, Object parameter) {
		return update(statement, parameter);
	}

	@Override
	public void commit() {
		commit(true);
	}

	@Override
	public void commit(boolean force) {
		try {
			executor.commit(force);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void rollback() {
		rollback(false);
	}

	@Override
	public void rollback(boolean force) {
		try {
			executor.rollback(force);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() throws IOException {
		executor.close(false);
	}

	@Override
	public void clearCache() {
		// TODO Auto-generated method stub
	}

	@Override
	public Configuration getConguration() {
		return this.configuration;
	}

	@Override
	public Connection getConnection() {
		try {
			return executor.getTransaction().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error getting a new connection ", e);
		}
	}

	@Override
	public <T> T getMapper(Class<T> interfaceClazz) {
		return MapperProxy.getMapper(this, interfaceClazz);
	}

}

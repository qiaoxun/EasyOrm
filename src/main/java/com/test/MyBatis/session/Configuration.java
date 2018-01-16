package com.test.MyBatis.session;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.test.MyBatis.executor.Executor;
import com.test.MyBatis.executor.statement.RoutingStatementHandler;
import com.test.MyBatis.executor.statement.StatementHandler;
import com.test.MyBatis.mapping.BoundSql;
import com.test.MyBatis.mapping.MappedStatement;

public class Configuration {

	protected Environment environment;

	protected Map<String, MappedStatement> mappedStatements = new HashMap<String, MappedStatement>();
	
	protected Integer defaultStatementTimeout;
	
	protected Map<String, String> typeAliases;
	
	protected List<String> mappersList;

	public Configuration() {
	}

	public Configuration(Environment environment) {
		this.environment = environment;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public StatementHandler newStatementHandler(Executor executor, MappedStatement ms, Object parameter,
			ResultHandler<?> resultHandler, BoundSql boundSql) {
		return new RoutingStatementHandler(executor, ms, parameter, resultHandler, boundSql);
	}

	/**
	 * 
	 * @param statement
	 * @return
	 */
	public boolean hasStatement(String statement) {
		return mappedStatements.containsKey(statement);
	}
	
	/**
	 * add a statement to map
	 * @param ms
	 */
	public void addMappedStatement(MappedStatement ms) {
		this.mappedStatements.put(ms.getId(), ms);
	}
	
	public MappedStatement getMappedStatement(String statement) {
		return this.mappedStatements.get(statement);
	}

	public Integer getDefaultStatementTimeout() {
		return defaultStatementTimeout;
	}

	public void setDefaultStatementTimeout(Integer defaultStatementTimeout) {
		this.defaultStatementTimeout = defaultStatementTimeout;
	}

	public Map<String, String> getTypeAliases() {
		return typeAliases;
	}

	public void setTypeAliases(Map<String, String> typeAliases) {
		this.typeAliases = typeAliases;
	}

	public List<String> getMappersList() {
		return mappersList;
	}

	public void setMappersList(List<String> mappersList) {
		this.mappersList = mappersList;
	}
}

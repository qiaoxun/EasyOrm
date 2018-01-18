package com.test.MyBatis.mapping;

import com.test.MyBatis.session.Configuration;

public class MappedStatement {
	
	private Configuration configuration;
	
	private SqlSource sqlSource;
	
	private StatementType statementType;
	
	private String id;
	
	private SqlCommandType sqlCommandType;
	
	// 来源文件 xx.xml
	private String source;
	
	/**
	 * 事务执行时间
	 */
	private Integer timeout;
	
	/**
	 * 取数
	 */
	private Integer fatchSize;

	public MappedStatement(Configuration configuration, SqlSource sqlSource, SqlCommandType sqlCommandType, String id, String source) {
		this.configuration = configuration;
		this.sqlSource = sqlSource;
		this.statementType = StatementType.PREPARED;
		this.id = id;
		this.sqlCommandType = sqlCommandType;
		this.source = source;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public SqlSource getSqlSource() {
		return sqlSource;
	}

	public void setSqlSource(SqlSource sqlSource) {
		this.sqlSource = sqlSource;
	}

	public StatementType getStatementType() {
		return statementType;
	}

	public void setStatementType(StatementType statementType) {
		this.statementType = statementType;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getTimeout() {
		return timeout;
	}

	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	
	public Integer getFatchSize() {
		return fatchSize;
	}

	public void setFatchSize(Integer fatchSize) {
		this.fatchSize = fatchSize;
	}

	/**
	 * get the boundsql
	 * @param parameterObject
	 * @return
	 */
	public BoundSql getBoundSql(Object parameterObject) {
		return sqlSource.getBoundSql(parameterObject);
	}

	public SqlCommandType getSqlCommandType() {
		return sqlCommandType;
	}

	public void setSqlCommandType(SqlCommandType sqlCommandType) {
		this.sqlCommandType = sqlCommandType;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
}

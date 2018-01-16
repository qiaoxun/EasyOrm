package com.test.MyBatis.builder;

import java.util.List;

import com.test.MyBatis.mapping.BoundSql;
import com.test.MyBatis.mapping.ParameterMapping;
import com.test.MyBatis.mapping.SqlSource;
import com.test.MyBatis.session.Configuration;

public class StaticSqlSource implements SqlSource{

	/**
	 * sql 语句
	 */
	private String sql;
	
	/**
	 * sql语句中需要的参数
	 */
	private List<ParameterMapping> parameterMappings;
	
	/**
	 * 配置信息
	 */
	private Configuration configuration;
	
	public StaticSqlSource(String sql, List<ParameterMapping> parameterMappings, Configuration configuration) {
		this.sql = sql;
		this.parameterMappings = parameterMappings;
		this.configuration = configuration;
	}

	@Override
	public BoundSql getBoundSql(Object parameterObject) {
		return new BoundSql(sql, parameterMappings, parameterObject);
	}

}

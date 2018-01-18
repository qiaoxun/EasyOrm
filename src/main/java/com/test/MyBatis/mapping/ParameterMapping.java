package com.test.MyBatis.mapping;

import com.test.MyBatis.session.Configuration;
import com.test.MyBatis.type.JdbcType;
import com.test.MyBatis.type.TypeHandler;

public class ParameterMapping {
	private Configuration configuration;
	
	private String property;
	
	private Class<?> javaType = Object.class;

	private JdbcType jdbcType;
	
	private TypeHandler<?> typeHandler;
	
	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public Class<?> getJavaType() {
		return javaType;
	}

	public void setJavaType(Class<?> javaType) {
		this.javaType = javaType;
	}

	public JdbcType getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(JdbcType jdbcType) {
		this.jdbcType = jdbcType;
	}

	public TypeHandler<?> getTypeHandler() {
		return typeHandler;
	}

	public void setTypeHandler(TypeHandler<?> typeHandler) {
		this.typeHandler = typeHandler;
	}
}

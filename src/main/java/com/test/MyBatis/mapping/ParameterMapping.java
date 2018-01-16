package com.test.MyBatis.mapping;

import com.test.MyBatis.session.Configuration;

public class ParameterMapping {
	private Configuration configuration;
	
	private String property;
	
	private Class<?> javaType = Object.class;

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
	
}

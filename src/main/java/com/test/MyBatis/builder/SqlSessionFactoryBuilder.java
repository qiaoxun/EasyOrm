package com.test.MyBatis.builder;

import java.io.InputStream;

import com.test.MyBatis.builder.xml.XmlConfigBuilder;
import com.test.MyBatis.session.SqlSessionFactory;
import com.test.MyBatis.session.defaults.DefaultSqlSessionFactory;

public class SqlSessionFactoryBuilder {
	
	public SqlSessionFactory build(String fileName) {
		InputStream is = SqlSessionFactoryBuilder.class.getClassLoader().getResourceAsStream(fileName);
		return build(is);
	}
	
	public SqlSessionFactory build(InputStream is) {
		XmlConfigBuilder xcb = new XmlConfigBuilder(is);
		return new DefaultSqlSessionFactory(xcb.parse());
	}
}

package com.test.MyBatis.jdbc;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSourceUtil {
	private static BasicDataSource dataSource;
	
	public static DataSource getDataSource(Properties prop) {
		if (dataSource == null) {
			// 一、初始化连接池
			dataSource = new BasicDataSource();
			// 设置驱动 (Class.forName())
			dataSource.setDriverClassName(prop.getProperty("driver"));
			// 设置url
			dataSource.setUrl(prop.getProperty("url"));
			// 设置数据库用户名
			dataSource.setUsername(prop.getProperty("username"));
			// 设置数据库密码
			dataSource.setPassword(prop.getProperty("password"));
			// 初始连接数量
			dataSource.setInitialSize(Integer.parseInt(prop.getProperty("initsize")));
			// 连接池允许的最大连接数
			dataSource.setMaxActive(Integer.parseInt(prop.getProperty("maxactive")));
			// 设置最大等待时间
			dataSource.setMaxWait(Integer.parseInt(prop.getProperty("maxwait")));
			// 设置最小空闲数
			dataSource.setMinIdle(Integer.parseInt(prop.getProperty("minidle")));
			// 设置最大空闲数
			dataSource.setMaxIdle(Integer.parseInt(prop.getProperty("maxidle")));
		}
		return dataSource;
	}
}

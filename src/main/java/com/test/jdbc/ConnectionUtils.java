package com.test.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;


public class ConnectionUtils {
	private static BasicDataSource dataSource;
	
	static {
		try {
			Properties prop = new Properties();
			prop.load(ConnectionUtils.class.getClassLoader().getResourceAsStream("db.properties"));
			// 一、初始化连接池
			dataSource = new BasicDataSource();

			// 设置驱动 (Class.forName())
			dataSource.setDriverClassName(prop.getProperty("jdbc.driver"));
			// 设置url
			dataSource.setUrl(prop.getProperty("jdbc.url"));
			// 设置数据库用户名
			dataSource.setUsername(prop.getProperty("jdbc.user"));
			// 设置数据库密码
			dataSource.setPassword(prop.getProperty("jdbc.password"));
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
			
		} catch (Exception e) {
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConn() throws SQLException {
		
		return dataSource.getConnection();
	}
	
	public static int getNumActive() {
		return dataSource.getNumActive();
	}
	
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 30; i++) {
				Connection conn = getConn();
				System.out.println(conn);
				Statement st = conn.createStatement();
				st.executeQuery("select now()");
				st.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

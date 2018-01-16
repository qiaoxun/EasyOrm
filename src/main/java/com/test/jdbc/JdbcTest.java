package com.test.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcTest {
	public static void main(String[] args) {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=UTF8";
		String user = "root";
		String password = "123456";
		
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(url, user, password);
			
			Statement stat = connection.createStatement();
			
			ResultSet rs = stat.executeQuery("select * from stu order by id asc");
			rs.absolute(2);
			rs.updateString("name", "rs.updateString");
//			while (rs.next()) {
//				rs.
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

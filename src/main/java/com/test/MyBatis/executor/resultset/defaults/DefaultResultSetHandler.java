package com.test.MyBatis.executor.resultset.defaults;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.MyBatis.executor.resultset.ResultSetHandler;

public class DefaultResultSetHandler implements ResultSetHandler {
	
	private List<String> columnsName = new ArrayList<String>();
	private List<String> columnsTypeName = new ArrayList<String>();
	private List<Integer> columnsType = new ArrayList<Integer>();
	
	@Override
	public List<Object> handleResultSets(Statement statement) {
		try {
			ResultSet rs = statement.getResultSet();
			ResultSetMetaData metaData = rs.getMetaData();
			resolveColumns(metaData);
//			isSimpleObject();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解析列名信息
	 * @param metaData
	 */
	private void resolveColumns(ResultSetMetaData metaData) {
		try {
			int count = metaData.getColumnCount();
			for (int i = 1; i <= count; i++) {
				columnsName.add(metaData.getColumnName(count));
				columnsTypeName.add(metaData.getColumnTypeName(i));
				columnsType.add(metaData.getColumnType(i));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isSimpleObject(Class<?> clazz) {
		if (int.class.isAssignableFrom(clazz)) {
			
		}
		
		return false;
	}

}

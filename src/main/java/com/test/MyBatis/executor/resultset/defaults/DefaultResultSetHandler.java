package com.test.MyBatis.executor.resultset.defaults;

import java.lang.reflect.ParameterizedType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.test.MyBatis.executor.resultset.ResultSetHandler;
import com.test.MyBatis.utils.ObjectTypeUtils;

public class DefaultResultSetHandler implements ResultSetHandler {
	
	private List<String> columnsName = new ArrayList<String>();
	private List<String> columnsTypeName = new ArrayList<String>();
	private List<Integer> columnsType = new ArrayList<Integer>();
	
	@Override
	public <E> List<E> handleResultSets(Statement statement) {
		try {
			ResultSet rs = statement.getResultSet();
			ResultSetMetaData metaData = rs.getMetaData();
			resolveColumns(metaData);
			
			@SuppressWarnings("unchecked")
			Class<E> persistentClass = (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
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
		Set<Class<?>> simpleClazzSet = ObjectTypeUtils.simpleClazzSet;
		Iterator<Class<?>> iterator = simpleClazzSet.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().isAssignableFrom(clazz)) {
				return true;
			}
		}
		return false;
	}

}

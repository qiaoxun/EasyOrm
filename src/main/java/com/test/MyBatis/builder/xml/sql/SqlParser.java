package com.test.MyBatis.builder.xml.sql;

import java.util.List;

import com.test.MyBatis.mapping.MappedStatement;
import com.test.MyBatis.mapping.ParameterMapping;
import com.test.MyBatis.mapping.SqlSource;

public interface SqlParser {
	
	/**
	 * 预处理sql
	 */
	String prepareSql();
	
	/**
	 * 清理sql的空格，并替换占位符
	 * @return
	 */
	String cleanSql();
	
	/**
	 * 获取id
	 * @return
	 */
	String getId();
	
	/**
	 * 解析所有的参数
	 * @param sql
	 * @return
	 */
	List<ParameterMapping> getParameterMappings();
	
	/**
	 * 创建 SqlSource
	 * @return
	 */
	SqlSource createSqlSource();
	
	/**
	 * 创建 MappedStatement
	 * @return
	 */
	MappedStatement createMappedStatement();

}

package com.test.MyBatis.builder.xml.sql;

import java.util.List;

import org.w3c.dom.Element;

import com.test.MyBatis.builder.StaticSqlSource;
import com.test.MyBatis.mapping.ParameterMapping;
import com.test.MyBatis.mapping.SqlCommandType;
import com.test.MyBatis.mapping.SqlSource;
import com.test.MyBatis.session.Configuration;

public class InsertSqlParser extends BaseSqlParser {

	public InsertSqlParser(Element ele, Configuration configuration, String classNameStr, SqlCommandType sqlCommandType, String source) {
		super(ele, configuration, classNameStr, sqlCommandType, source);
	}
	
	@Override
	public SqlSource createSqlSource() {
		List<ParameterMapping> parameterMappings =  getParameterMappings();
		cleanSql();
		SqlSource sqlSource = new StaticSqlSource(sql, parameterMappings, configuration);
		return sqlSource;
	}

	@Override
	public String prepareSql() {
		return super.prepareSql();
	}

}

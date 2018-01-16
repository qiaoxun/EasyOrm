package com.test.MyBatis.builder.xml.sql;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;

import com.test.MyBatis.mapping.MappedStatement;
import com.test.MyBatis.mapping.ParameterMapping;
import com.test.MyBatis.mapping.SqlCommandType;
import com.test.MyBatis.mapping.SqlSource;
import com.test.MyBatis.session.Configuration;
import com.test.MyBatis.utils.SqlHelper;

public abstract class BaseSqlParser implements SqlParser {

	protected Element ele;
	
	protected Configuration configuration;
	
	protected String classNameStr;
	
	protected String id;
	
	protected String sql;
	
	protected SqlCommandType sqlCommandType;
	
	public BaseSqlParser(Element ele, Configuration configuration, String classNameStr, SqlCommandType sqlCommandType) {
		this.ele = ele;
		this.configuration = configuration;
		this.classNameStr = classNameStr;
		this.sqlCommandType = sqlCommandType;
		init();
	}
	
	private void init() {
		String methodName = ele.getAttribute("id");
		this.id = classNameStr + "." + methodName;
		sql = ele.getTextContent();
	}

	@Override
	public String cleanSql() {
		sql = SqlHelper.cleanAndReplacePlaceHolder(sql);
		return sql;
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public List<ParameterMapping> getParameterMappings() {
		List<String> paramsList = SqlHelper.resolveAllPlaceHolderContent(sql);
		List<ParameterMapping> pmList = new ArrayList<>(paramsList.size());
		for (String param : paramsList) {
			ParameterMapping pm = new ParameterMapping();
			pm.setProperty(param);
			pmList.add(pm);
		}
		return pmList;
	}
	
	@Override
	public MappedStatement createMappedStatement() {
		SqlSource sqlSource = createSqlSource();
		MappedStatement ms = new MappedStatement(configuration, sqlSource, sqlCommandType, id);
		return ms;
	}
}

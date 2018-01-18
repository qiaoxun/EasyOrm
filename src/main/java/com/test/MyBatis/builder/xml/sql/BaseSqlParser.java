package com.test.MyBatis.builder.xml.sql;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Element;

import com.test.MyBatis.mapping.MappedStatement;
import com.test.MyBatis.mapping.ParameterMapping;
import com.test.MyBatis.mapping.SqlCommandType;
import com.test.MyBatis.mapping.SqlSource;
import com.test.MyBatis.reflection.ClassUtils;
import com.test.MyBatis.session.Configuration;
import com.test.MyBatis.utils.ObjectTypeUtils;
import com.test.MyBatis.utils.SqlHelper;

public abstract class BaseSqlParser implements SqlParser {

	// 当前节点
	protected Element ele;
	
	// 配置文件
	protected Configuration configuration;
	
	// 属于类的类路径
	protected String classNameStr;
	
	// 类路径加方法名
	protected String id;
	
	// 真正的sql
	protected String sql;
	
	// 节点的类型
	protected SqlCommandType sqlCommandType;
	
	// 此方法参数的类型
	protected String parameterType;
	
	// 来源 xxx.xml
	protected String source;
	
	// 参数的类型
	protected Class<?> javaType; 
	
	public BaseSqlParser(Element ele, Configuration configuration, String classNameStr, SqlCommandType sqlCommandType, String source) {
		this.ele = ele;
		this.configuration = configuration;
		this.classNameStr = classNameStr;
		this.sqlCommandType = sqlCommandType;
		this.source = source;
		init();
	}
	
	private void init() {
		String methodName = ele.getAttribute("id");
		this.id = classNameStr + "." + methodName;
		
		// 解析sql
		prepareSql();
		
		// deal with the input parameter type
		dealWithParameterType();
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
		MappedStatement ms = new MappedStatement(configuration, sqlSource, sqlCommandType, id, source);
		return ms;
	}
	
	/**
	 * 解析sql
	 */
	@Override
	public String prepareSql() {
		this.sql = ele.getTextContent();
		return sql;
	}
	
	/**
	 * 处理参数类型
	 */
	private void dealWithParameterType() {
		this.parameterType = ele.getAttribute("parameterType");
		// 如果中间有 . 则表明是
		if (StringUtils.contains(parameterType, ".")) {
			javaType = ClassUtils.forName(parameterType, source, id);
		} else if(ObjectTypeUtils.isSimpleObject(javaType)) {
//			javaType 
		}
	}
}

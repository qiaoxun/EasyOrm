package com.test.MyBatis.builder.xml;

import java.io.InputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.test.MyBatis.builder.xml.sql.DeleteSqlParser;
import com.test.MyBatis.builder.xml.sql.InsertSqlParser;
import com.test.MyBatis.builder.xml.sql.SelectSqlParser;
import com.test.MyBatis.builder.xml.sql.SqlParser;
import com.test.MyBatis.builder.xml.sql.UpdateSqlParser;
import com.test.MyBatis.mapping.SqlCommandType;
import com.test.MyBatis.parsing.XPathParser;
import com.test.MyBatis.session.Configuration;

public class XmlMapperBuilder {
	private XPathParser parser;
	
	private Configuration configuration;
	
	private String namespace;
	
	public XmlMapperBuilder(InputStream is, Configuration configuration) {
		parser = new XPathParser(is);
		this.configuration = configuration;
	}
	
	public void parse() {
		Document document = parser.getDocument();
		NodeList mapperRoot = document.getElementsByTagName("mapper");
		
		if (null != mapperRoot) {
			Element mapperEle = (Element) mapperRoot.item(0);
			namespace = mapperEle.getAttribute("namespace");
			
			//解析insert
			parseIDUS(mapperEle, "insert", SqlCommandType.INSERT);
			//解析update
			parseIDUS(mapperEle, "update", SqlCommandType.UPDATE);
			//解析delete
			parseIDUS(mapperEle, "delete", SqlCommandType.DELETE);
			//解析select
			parseIDUS(mapperEle, "select", SqlCommandType.SELECT);
		}
	}
	
	/**
	 * 解析增删改查标签
	 * @param mapperEle
	 * @param tagName
	 * @param sqlCommandType
	 */
	private void parseIDUS(Element mapperEle, String tagName, SqlCommandType sqlCommandType) {
		NodeList insertList = mapperEle.getElementsByTagName(tagName);
		for (int i = 0, len = insertList.getLength(); i < len; i++) {
			Element each = (Element) insertList.item(i);
			SqlParser sqlParser = null;
			
			switch(sqlCommandType) {
				case INSERT : sqlParser = new InsertSqlParser(each, configuration, namespace, sqlCommandType); break;
				case UPDATE : sqlParser = new UpdateSqlParser(each, configuration, namespace, sqlCommandType); break;
				case DELETE : sqlParser = new DeleteSqlParser(each, configuration, namespace, sqlCommandType); break;
				case SELECT : sqlParser = new SelectSqlParser(each, configuration, namespace, sqlCommandType); break;
				default : throw new RuntimeException("SqlCommandType not have yet"); 
			}
			
			sqlParser.prepareSql();
			configuration.addMappedStatement(sqlParser.createMappedStatement());
		}
	}
}

package com.test.MyBatis.builder.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.test.MyBatis.jdbc.DataSourceUtil;
import com.test.MyBatis.parsing.XPathParser;
import com.test.MyBatis.session.Configuration;
import com.test.MyBatis.session.Environment;
import com.test.MyBatis.transaction.defaults.DefaultTransactionFactory;
import com.test.MyBatis.utils.XmlHelper;

public class XmlConfigBuilder {
	private XPathParser parser;
	
	private Configuration configuration;

	public XmlConfigBuilder(InputStream is) {
		parser = new XPathParser(is);
	}
	
	public Configuration parse() {
		configuration = new Configuration();
		parseConfiguration();
		return configuration;
	}
	
	private void parseConfiguration() {
		Document document = parser.getDocument();
		NodeList root = document.getElementsByTagName("configuration");
		
		if (root.getLength() > 0) {
			Element configEle = (Element) root.item(0);
			parseAlias(configEle);
			parseEnvironment(configEle);
			parseMappers(configEle);
		}
	}
	
	/**
	 * 解析 mapper 标签
	 * @param configEle
	 */
	private void parseMappers(Element configEle) {
		List<String> list = new ArrayList<>();
		configuration.setMappersList(list);
		Element mappersEle = XmlHelper.getFirstElementByTagName(configEle, "mappers");
		if (null == mappersEle) {
			return ;
		}
		
		NodeList mapperList = mappersEle.getElementsByTagName("mapper");
		for (int i = 0, len = mapperList.getLength(); i < len; i++) {
			Element each = (Element) mapperList.item(i);
			String resource = each.getAttribute("resource");
			list.add(resource);
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource);
			XmlMapperBuilder mapperBuilder = new XmlMapperBuilder(is, configuration, resource);
			mapperBuilder.parse();
		}
	}
	
	/**
	 * 解析 environments 标签
	 * @param configEle
	 */
	private void parseEnvironment(Element configEle) {
		Element environmentsEle = XmlHelper.getFirstElementByTagName(configEle, "environments");
		if (null == environmentsEle) {
			return;
		}
		
		Element environmentEle = XmlHelper.getFirstElementByTagName(environmentsEle, "environment");
		if (null == environmentEle) {
			return;
		}
		
		Element dataSourceEle = XmlHelper.getFirstElementByTagName(environmentEle, "dataSource");
		if (null == dataSourceEle) {
			return;
		}
		
		NodeList propertyList = dataSourceEle.getElementsByTagName("property");
		String driver = "";
		String url = "";
		String username = "";
		String password = "";
		String initsize = "1";
		String maxactive = "1";
		String maxwait = "1";
		String minidle = "1";
		String maxidle = "1";
		
		for (int i = 0, len = propertyList.getLength(); i < len; i++) {
			Element each = (Element) propertyList.item(i);
			String name = each.getAttribute("name");
			String value = each.getAttribute("value");
			switch (name) {
				case "driver" : driver = value; break;
				case "url" : url = value; break;
				case "username" : username = value; break;
				case "password" : password = value; break;
				case "initsize" : initsize = value; break;
				case "maxactive" : maxactive = value; break;
				case "maxwait" : maxwait = value; break;
				case "minidle" : minidle = value; break;
				case "maxidle" : maxidle = value; break;
			}
		}
		
		Properties prop = new Properties();
		prop.setProperty("driver", driver);
		prop.setProperty("url", url);
		prop.setProperty("username", username);
		prop.setProperty("password", password);
		prop.setProperty("initsize", initsize);
		prop.setProperty("maxactive", maxactive);
		prop.setProperty("maxwait", maxwait);
		prop.setProperty("minidle", minidle);
		prop.setProperty("maxidle", maxidle);
		
		DataSource dataSource = DataSourceUtil.getDataSource(prop);
		
		Environment environment = new Environment("", new DefaultTransactionFactory(), dataSource);
		this.configuration.setEnvironment(environment);
	}
	
	/**
	 * 解析 typeAliases 标签
	 * <typeAliases>
	 *	<typeAlias alias="stu" type="com.test.entity.Stu" />
	 * </typeAliases>
	 * @param configEle
	 */
	private void parseAlias(Element configEle) {
		Map<String, String> typeAliases = new HashMap<String, String>();
		Element aliasEle = XmlHelper.getFirstElementByTagName(configEle, "typeAliases");
		if (null == aliasEle) {
			return;
		}
		NodeList typeAliasNodeList = aliasEle.getElementsByTagName("typeAlias");
		for (int i = 0, len = typeAliasNodeList.getLength(); i < len; i++) {
			Element each = (Element) typeAliasNodeList.item(i);
			String alias = each.getAttribute("alias");
			String type = each.getAttribute("type");
			typeAliases.put(alias, type);
		}
		configuration.setTypeAliases(typeAliases);
	}
	
}

package com.test.MyBatis.parsing;

import java.io.InputStream;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;

import org.w3c.dom.Document;
import org.xml.sax.EntityResolver;

public class XPathParser {
	
	private Document document;
	private EntityResolver resolver;
	private XPath xpath;
	private Properties properties;
	
	public XPathParser(InputStream is) {
		createDocument(is);
	}
	
	private void createDocument(InputStream is) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(is);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Document getDocument() {
		return document;
	}

	public EntityResolver getResolver() {
		return resolver;
	}

	public XPath getXpath() {
		return xpath;
	}

	public Properties getProperties() {
		return properties;
	}
	
}

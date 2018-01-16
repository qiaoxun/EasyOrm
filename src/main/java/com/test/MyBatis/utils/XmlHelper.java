package com.test.MyBatis.utils;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XmlHelper {
	/**
	 * 获取第一个元素
	 * @param ele
	 * @param tagName
	 * @return
	 */
	public static Element getFirstElementByTagName(Element ele, String tagName) {
		NodeList nodeList = ele.getElementsByTagName(tagName);
		if (null != nodeList && nodeList.getLength() > 0) {
			Element aliasEle = (Element) nodeList.item(0);
			return aliasEle;
		}
		return null;
	}
}

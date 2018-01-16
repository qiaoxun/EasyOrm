package com.test.MyBatis.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlHelper {
	private final static String innerContextRegex = "(?<=#\\{)(.+?)(?=\\})";
	private final static String allParameterRegex = "#\\{[^}]*\\}";
	
	private static Pattern pattern = Pattern.compile(innerContextRegex);
	
	/**
	 * 解析出所有的参数 
	 * eg. values(#{name},#{birthDay},#{age},#{description}) 获取 name birthDay age description
	 * @param sql
	 * @return
	 */
	public static List<String> resolveAllPlaceHolderContent(String sql) {
		List<String> list = new ArrayList<>();
		Matcher matcher = pattern.matcher(sql);
		while (matcher.find()) {
			list.add(matcher.group());
		}
		return list;
	}
	
	/**
	 * 去除sql空格，并且将占位符替换为问号
	 * @param sql
	 * @return
	 */
	public static String cleanAndReplacePlaceHolder(String sql) {
		sql = sql.trim().replaceAll("\n\t", "").replaceAll("\t", " ");
		sql = sql.replaceAll(allParameterRegex, "?");
		return sql;
	}
	
}

package com.test.MyBatis.utils;

import java.sql.Blob;
import java.sql.Clob;

import com.test.MyBatis.mapping.ParameterMapping;
import com.test.MyBatis.type.BlobTypeHandler;
import com.test.MyBatis.type.CharacterTypeHandler;
import com.test.MyBatis.type.ClobTypeHandler;
import com.test.MyBatis.type.DoubleTypeHandler;
import com.test.MyBatis.type.FloatTypeHandler;
import com.test.MyBatis.type.IntegerTypeHandler;
import com.test.MyBatis.type.JdbcType;
import com.test.MyBatis.type.StringTypeHandler;
import com.test.MyBatis.type.TypeHandler;

public class JdbcTypeUtils {
	
	public static void resolveParamType(ParameterMapping pm, Class<?> paramType) {
		if (ObjectTypeUtils.isChar(paramType)) {
			pm.setJdbcType(JdbcType.CHAR);
			TypeHandler<Character> th = new CharacterTypeHandler();
			pm.setTypeHandler(th);
		} else if (ObjectTypeUtils.isString(paramType)) {
			pm.setJdbcType(JdbcType.VARCHAR);
			TypeHandler<String> th = new StringTypeHandler();
			pm.setTypeHandler(th);
		} else if (ObjectTypeUtils.isInt(paramType)) {
			pm.setJdbcType(JdbcType.INTEGER);
			TypeHandler<Integer> th = new IntegerTypeHandler();
			pm.setTypeHandler(th);
		} else if (ObjectTypeUtils.isDouble(paramType)) {
			pm.setJdbcType(JdbcType.DOUBLE);
			TypeHandler<Double> th = new DoubleTypeHandler();
			pm.setTypeHandler(th);
		} else if (ObjectTypeUtils.isFloat(paramType)) {
			pm.setJdbcType(JdbcType.FLOAT);
			TypeHandler<Float> th = new FloatTypeHandler();
			pm.setTypeHandler(th);
		} else if (Clob.class.isAssignableFrom(paramType)) {
			pm.setJdbcType(JdbcType.CLOB);
			TypeHandler<String> th = new ClobTypeHandler();
			pm.setTypeHandler(th);
		} else if (Blob.class.isAssignableFrom(paramType)) {
			pm.setJdbcType(JdbcType.BLOB);
			TypeHandler<byte[]> th = new BlobTypeHandler();
			pm.setTypeHandler(th);
		} else {
			throw new RuntimeException("com.test.MyBatis.utils.JdbcTypeUtils.resolveParamType : " + paramType.getName() + " not has been deal yet");
		}
	}
}

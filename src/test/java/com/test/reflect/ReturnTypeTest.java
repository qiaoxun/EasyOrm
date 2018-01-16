package com.test.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.BeanUtilsBean2;

public class ReturnTypeTest {
	
	public static void main(String[] args) {
		
//		BeanUtils.getIndexedProperty(bean, name)
		
		Class<ReturnTypeTest> clazz = ReturnTypeTest.class;
		
		Method[] methods = clazz.getMethods();
		
		Type t = clazz.getClass();
		
		for (Method m : methods) {
			m.getGenericReturnType();
			System.out.println(m.getName() + " - " + m.getReturnType());
		}
		
	}
	
	public void voidType() {
		
	}
	
	public int intType() {
		return 0;
	}
	
	public String stringType(){
		return "";
	}
	
	public Integer integerType() {
		return null;
	}
	
	public byte[] byteArrType() {
		return new byte[0];
	}
	
	public char[] charArrType() {
		return new char[0];
	}
	
	public ReturnTypeTest[] returnTypeTestType() {
		return new ReturnTypeTest[0];
	}
}

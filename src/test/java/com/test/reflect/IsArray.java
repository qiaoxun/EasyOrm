package com.test.reflect;

public class IsArray {
	public static void main(String[] args) {
		byte[] buff = new byte[10];
		System.out.println(buff.getClass().isArray());
	}
}

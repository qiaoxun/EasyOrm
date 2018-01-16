package com.test.designPattern.singleton;

import java.io.Serializable;

public class LazySingleton implements Serializable {

	private static LazySingleton instance = null;

	private LazySingleton() {
		if (null != instance) {
			throw new RuntimeException();
		}
	}

	public static synchronized LazySingleton getInstance() {
		if (null == instance) {
			instance = new LazySingleton();
		}
		return instance;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	// 添加一个方法，避免反序列化时的漏洞
	// readResolve是一个回调方法，表示在反序列化时，直接返回指定对象，不需要在创建新对象
	private Object readResolve() {
		return instance;
	}
}

package com.test.designPattern.singleton;

public class StaticInnerClassSingleton {
	private static class InnerClass {
		private static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
	}
	private StaticInnerClassSingleton() {}
	public static StaticInnerClassSingleton getInstance() {
		return InnerClass.instance;
	}
}

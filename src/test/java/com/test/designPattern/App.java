package com.test.designPattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;

import com.test.designPattern.singleton.DoubleCheckSingleton;
import com.test.designPattern.singleton.EnumSingleton;
import com.test.designPattern.singleton.HungrySingleton;
import com.test.designPattern.singleton.LazySingleton;
import com.test.designPattern.singleton.StaticInnerClassSingleton;

public class App {
	public static void main(String[] args) {
		singletonLazy();
		serializableTest();
	}

	public static void singletonLazy() {
		LazySingleton instance = LazySingleton.getInstance();
		LazySingleton instance2 = LazySingleton.getInstance();
		System.out.println(instance);
		System.out.println(instance2);
	}

	public static void singletonHungry() {
		HungrySingleton instance = HungrySingleton.getInstance();
		HungrySingleton instance2 = HungrySingleton.getInstance();
		System.out.println(instance);
		System.out.println(instance2);
	}

	public static void singletonDoubleCheck() {
		DoubleCheckSingleton instance = DoubleCheckSingleton.getInstance();
		DoubleCheckSingleton instance2 = DoubleCheckSingleton.getInstance();
		System.out.println(instance);
		System.out.println(instance2);
	}

	public static void singletonStaticInnerClass() {
		StaticInnerClassSingleton instance = StaticInnerClassSingleton.getInstance();
		StaticInnerClassSingleton instance2 = StaticInnerClassSingleton.getInstance();
		System.out.println(instance);
		System.out.println(instance2);
	}

	public static void enumClass() {
		EnumSingleton instance = EnumSingleton.INSTANCE;
		EnumSingleton instance2 = EnumSingleton.INSTANCE;
		System.out.println(instance);
		System.out.println(instance2);
	}

	public static void reflectTest() {
		try {
			Class<?> clazz = Class.forName("com.test.designPattern.singleton.LasySingleton");
			Constructor<?> constructor = clazz.getDeclaredConstructor();
			constructor.setAccessible(true);
			Object obj = constructor.newInstance();
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void serializableTest() {
		try {
			LazySingleton instance = LazySingleton.getInstance();
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("c:instance"));
			oos.writeObject(instance);
			oos.close();
			
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("c:instance"));
			Object instance2 = ois.readObject();
			ois.close();
			System.out.println(instance2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

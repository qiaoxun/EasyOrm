package com.test.EasyOrm;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Stu implements Serializable {
	private int id;
	private String name;
	private String birthDay;//yyyyMMDDHHmmss
	private int age;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Stu [id=" + id + ", name=" + name + ", birthDay=" + birthDay + ", age=" + age + ", description="
				+ description + "]";
	}
	
}

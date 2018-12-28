package com.collect.reflect.test;

import java.lang.reflect.Method;
import com.collect.reflect.Person;

public class TestMethod {
	public static void main(String[] args) {
		TestMethod t= new TestMethod();
		t.ref();
	}
	private void ref() {
		try {
			Person person = new Person();
			person.setAge(33);
			person.setName("JASON");
			Method method = this.getClass().getMethod("getMethod", Person.class);
//			Method method = this.getClass().getMethod("getMethod", new Class[]{Person.class});
			Person p = (Person) method.invoke(this,person);
			System.out.println("invoke person : " + p.toString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Person getMethod(Person person){
		System.out.println("getMethod start");
		person.setAge(32);
		return person;
	}
}
package com.collect.reflect;

public class TestReflect {
	
	public static void main(String[] args) {
		
		 Class<?> reflect = null;
		 try {
			reflect = Class.forName("collect.reflect.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 Person person = null;
		 try {
			person = (Person) reflect.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		 
		 person.setName("settle");
		 person.setAge(10);
		 System.out.println(person);
		 
		 Person p = new Person();
		 p.setName("collate");
		 p.setAge(20);
		 System.out.println(p);
	}
}

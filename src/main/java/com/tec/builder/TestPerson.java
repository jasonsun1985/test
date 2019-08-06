package com.tec.builder;

public class TestPerson {
	public static void main(String[] args) throws CloneNotSupportedException {
		Person person = new Person.Builder("jason", "male").age("34").money(888888888).build();
		System.out.println("1-person : " + person.toString());
		Person person2 = person.clone();
		System.out.println("1-person2 : " + person2.toString());
		person.setAge("35");
		System.out.println("2-person : " + person.toString());
		System.out.println("2-person2 : " + person2.toString());
	}
}

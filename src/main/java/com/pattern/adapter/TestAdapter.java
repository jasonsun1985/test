package com.pattern.adapter;

public class TestAdapter {
	public static void main(String[] args) {
		Target target = new Adapter();
		target.sampleOperation1();
		target.sampleOperation2();
		
		Adaptee adaptee = new Adaptee();
		Target targetNew = new Wrapper(adaptee);
		targetNew.sampleOperation1();
		targetNew.sampleOperation2();
	}
}

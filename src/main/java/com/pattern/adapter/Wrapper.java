package com.pattern.adapter;

public class Wrapper implements Target {
	private Adaptee adaptee;
	/**
	 * @param adaptee
	 */
	public Wrapper(Adaptee adaptee) {
		super();
		this.adaptee = adaptee;
	}

	@Override
	public void sampleOperation1() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sampleOperation2() {
		// TODO Auto-generated method stub
		
	}

}

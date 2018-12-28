package com.pattern.decorator;

public class Bird extends Change {

	public Bird(TheGreatestSage theGreatestSage) {
		super(theGreatestSage);
		System.out.println("Bird Construction is started!");
	}

	@Override
	public void move() {
		System.out.println("Bird is Flying");
	}
	
}

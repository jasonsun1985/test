package com.pattern.decorator;

public class Fish extends Change {

	public Fish(TheGreatestSage theGreatestSage) {
		super(theGreatestSage);
		System.out.println("Fish Construction is started!");
	}

	@Override
	public void move() {
		System.out.println("Fish is swimming");
	}
}

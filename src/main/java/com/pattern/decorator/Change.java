package com.pattern.decorator;

public class Change implements TheGreatestSage {
	private TheGreatestSage theGreatestSage;
	/**
	 * @param theGreatestSage
	 */
	public Change(TheGreatestSage theGreatestSage) {
		System.out.println("Change Construction is started!");
		this.theGreatestSage = theGreatestSage;
	}

	@Override
	public void move() {
		theGreatestSage.move();
	}
}

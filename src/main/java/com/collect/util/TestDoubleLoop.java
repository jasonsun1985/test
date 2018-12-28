package com.collect.util;

public class TestDoubleLoop {
	public static void main(String[] args) {
		boolean flag = false;
		for (int i = 0; i < 10; i++) {
			System.out.println("i: "+i);
			for (int j = 0; j < 15; j++) {
				System.out.println("j: "+j);
				break;
			}
		}
	}
}

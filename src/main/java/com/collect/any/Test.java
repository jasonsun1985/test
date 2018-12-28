package com.collect.any;

import org.springframework.util.Assert;

public class Test {
	public static void main(String[] args) {
		//********************* TEST Assert.notNull ***********************************
		String name = null;
		Assert.notNull(name, "Name must not be null");
		System.out.println(name);
		
		
	}
}

package com.pattern.singleton;

public class EagerSingeton {
	private static final EagerSingeton instance = new EagerSingeton();
	private EagerSingeton() {
	}
	public static EagerSingeton getInstance(){
		return instance;
	}
}
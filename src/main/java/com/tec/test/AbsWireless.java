package com.tec.test;

import java.util.List;

public abstract class AbsWireless<T> implements IWireless<Wireless> {
	public abstract void getLocation();
	public abstract <T> List<? extends Wireless> getC();
	public void init(){
		
	}
	public static void test(){
	}
	public String getName(){
		return "Default";
	}

}

package com.collect.thread;

public class WrongDoubleCheck {
private static WrongDoubleCheck instance;
	private WrongDoubleCheck(){
	}
	public static WrongDoubleCheck getInstance(){
		//线程3 进入即将判断
		if(instance == null){
			//线程2等
			synchronized (WrongDoubleCheck.class) {
				if(instance == null){
					//线程1
					instance = new WrongDoubleCheck();
					System.out.println("执行");
				}
			}
		}
		return instance;
	}
}
 

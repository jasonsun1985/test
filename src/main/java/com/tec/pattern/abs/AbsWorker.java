package com.tec.pattern.abs;

public abstract class AbsWorker {
	public abstract void pre();
	public abstract void after();
	public void work(String work){
		System.out.println("START");
		pre();
		System.out.println("干具体工作: " + work);
		after();
		System.out.println("END");
	}
}

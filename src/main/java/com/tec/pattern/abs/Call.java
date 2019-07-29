package com.tec.pattern.abs;

public class Call {
	public static void main(String[] args) {
		//1
//		AbsWorker absWorker = new NormalWork();
//		absWorker.work();
		//2
		WorkFactory workFactory = new WorkFactory(new HardWork());
		workFactory.work("难度较高工作");
		System.out.println("***********************************************");
		//java8
		new Java8WorkTemplateLambda().work("hardwork", (Object[] work)->{
			System.out.println("准备进行高难度工作: " + work[0]);
		});
		new Java8WorkTemplateLambda().work("nomalwork", (Object[] work) ->{
			System.out.println("准备进行普通工作: " +work[0]);
		});
	}
}

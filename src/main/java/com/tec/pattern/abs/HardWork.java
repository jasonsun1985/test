package com.tec.pattern.abs;

public class HardWork extends AbsWorker {

	@Override
	public void pre() {
		System.out.println("复杂工作  pre() " + HardWork.class.getName());
	}

	@Override
	public void after() {
		System.out.println("复杂工作  after() " + HardWork.class.getName());
	}

}

package com.tec.pattern.abs;

public class NormalWork extends AbsWorker {

	@Override
	public void pre() {
		System.out.println("普通工作  pre() " + NormalWork.class.getName());

	}
	@Override
	public void after() {
		System.out.println("普通工作  after() " + NormalWork.class.getName());
	}

}

package com.tec.pattern.abs;

public class WorkFactory {
	private AbsWorker absWorker;
	public WorkFactory(AbsWorker absWorker){
		this.absWorker = absWorker;
	}
	public void work(String work){
		absWorker.work(work);
	}
}

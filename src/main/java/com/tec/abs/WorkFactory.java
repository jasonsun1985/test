package com.tec.abs;

/** 
 * @Description:
 * <p>创建日期：2019年3月7日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class WorkFactory {
	private AbsWorker absWorker;
	public WorkFactory(AbsWorker absWorker){
		this.absWorker = absWorker;
	}
	public void work(){
		absWorker.work();
	}
}

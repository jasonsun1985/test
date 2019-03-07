package com.tec.abs;

/** 
 * @Description:
 * <p>创建日期：2019年3月7日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public abstract class AbsWorker {
	public abstract void pre();
	public abstract void collect();
	public abstract void after();
	public void work(){
		System.out.println("START");
		//TODO other
		pre();
		collect();
		after();
		System.out.println("END");
	}
}

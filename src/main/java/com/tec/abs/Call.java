package com.tec.abs;

/** 
 * @Description:
 * <p>创建日期：2019年3月7日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class Call {
	public static void main(String[] args) {
		//1
		AbsWorker absWorker = new NormalWork();
		absWorker.work();
		//2
		WorkFactory workFactory = new WorkFactory(new HardWork());
		workFactory.work();
	}
}

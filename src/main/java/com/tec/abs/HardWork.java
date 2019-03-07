package com.tec.abs;

/** 
 * @Description:
 * <p>创建日期：2019年3月7日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class HardWork extends AbsWorker {

	@Override
	public void pre() {
		System.out.println("复杂工作  pre() " + HardWork.class.getName());
	}

	@Override
	public void collect() {
		System.out.println("复杂工作  collect() " + HardWork.class.getName());

	}

	@Override
	public void after() {
		System.out.println("复杂工作  after() " + HardWork.class.getName());
	}

}

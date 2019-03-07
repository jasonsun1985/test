package com.tec.abs;

/** 
 * @Description:
 * <p>创建日期：2019年3月7日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class NormalWork extends AbsWorker {

	@Override
	public void pre() {
		System.out.println("普通工作  pre() " + NormalWork.class.getName());

	}
	@Override
	public void collect() {
		System.out.println("普通工作  collect() " + NormalWork.class.getName());
	}
	@Override
	public void after() {
		System.out.println("普通工作  after() " + NormalWork.class.getName());
	}

}

package com.tec.guice;

/** 
 * @Description:
 * <p>创建日期：2020年1月7日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class HelloPrinter implements IHelloPrinter {

	@Override
	public void print() {
		System.out.println("Hello World");
	}

}

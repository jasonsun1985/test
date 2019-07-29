package com.tec.math;

/** 
 * @Description:
 * <p>创建日期：2019年3月20日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class Test {
	public static void main(String[] args) {
		// 生成两个(0~1)的随机数 x1,x2;
		double x1 = Math.random();
		double x2 = Math.random();
		double Z0 = Math.sqrt(-2 * Math.log(x1)) * Math.cos(2 * Math.PI * x2);
		double Z1 = Math.sqrt(-2 * Math.log(x1)) * Math.sin(2 * Math.PI * x2);
		System.out.println(Z0);
		System.out.println(Z1);
	}

}

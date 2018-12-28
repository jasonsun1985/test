/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：StartUp.java
 *  版本变更记录（可选）：修改日期2018年1月28日  上午11:45:27，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.collect.classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/** 
 * @Description:
 * <p>创建日期：2018年1月28日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class StartUp {
	public static void main(String[] args) throws ClassNotFoundException {

		int i = 0;

		while (true) {
			MyClassLoader mcl = new MyClassLoader();
			System.out.println(mcl.getParent());
			Class<?> personClass = mcl.findClass("com.collect.classloader.Person");
			try {
				Object person = personClass.newInstance();
				Method sayHelloMethod = personClass.getMethod("sayHello");
				sayHelloMethod.invoke(person);
				System.out.println(++i);
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}

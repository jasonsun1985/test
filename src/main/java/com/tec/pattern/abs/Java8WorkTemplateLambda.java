package com.tec.pattern.abs;

import java.util.function.Consumer;

/** 
 * @Description:java8策略模式模板
 */
public class Java8WorkTemplateLambda {
	public void work(String work,Consumer<Object[]> execute){
		System.out.println("==========准备开始工作");
		Object[] param = new Object[]{work};
		execute.accept(param);
		System.out.println("==========工作结束");
	}
}

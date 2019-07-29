package com.tec.pattern.chain;

import java.util.function.Consumer;


/** 
 * @Description:java8 链模式模板
 */
public class Java8Test {
	
	public static void main(String[] args) {
		Consumer<String> p1 = param->System.out.println("chan1 " + param);
		Consumer<String> p2 = param->System.out.println("chan2 " + param);
		p1.andThen(p2).accept("参数");
		
	}
}

package com.tec.pattern.strategy;

/** 
 * @Description:
 * <p>创建日期：2019年4月4日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class Java8_OrderServiceTest {
	public static void main(String[] args) {
		OrderServiceExecutor orderServiceExecutor1 = new OrderServiceExecutor((order) -> {
			System.out.println("jdk8以后在这里面处理保存 mysql 逻辑");
			System.out.println("================save mysql === "+order+ " =================");		
		});
		orderServiceExecutor1.save("100");

		OrderServiceExecutor executor2 = new OrderServiceExecutor((order) -> {
			System.out.println("jdk8以后在这里面处理保存 NOSQL 逻辑");
			System.out.println("================save NOSQL === "+order+ " =================");
		});
		executor2.save("200");
	}
}

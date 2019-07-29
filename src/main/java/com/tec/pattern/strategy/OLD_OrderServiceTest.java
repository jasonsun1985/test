package com.tec.pattern.strategy;

/** 
 * @Description:
 * <p>创建日期：2019年4月4日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class OLD_OrderServiceTest {
	public static void main(String[] args) {
		OrderServiceExecutor orderServiceExecutor1 = new OrderServiceExecutor(new MySqlSaveOrderStrategy());
		orderServiceExecutor1.save("100");
		OrderServiceExecutor orderServiceExecutor2 = new OrderServiceExecutor(new NoSqlSaveOrderStrategy());
		orderServiceExecutor2.save("200");
	}
}

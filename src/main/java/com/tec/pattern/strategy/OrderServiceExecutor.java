package com.tec.pattern.strategy;

/** 
 * @Description:
 * <p>创建日期：2019年4月4日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class OrderServiceExecutor {
	private OrderService service;

	public OrderServiceExecutor(OrderService service) {
		this.service = service;
	}

	public void save(String orderNo) {
		this.service.saveOrder(orderNo);
	}
}

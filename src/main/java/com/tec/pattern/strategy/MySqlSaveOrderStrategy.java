package com.tec.pattern.strategy;

/** 
 * @Description:
 * <p>创建日期：2019年4月4日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class MySqlSaveOrderStrategy implements OrderService {

	/**
	 *(非 Javadoc) 
	 * <p>Title: saveOrder</p> 
	 * <p>Description: </p> 
	 * @param orderNo 
	 * @see com.tec.pattern.strategy.OrderService#saveOrder(java.lang.String) 
	 */
	@Override
	public void saveOrder(String orderNo) {
		System.out.println("order:" + orderNo + " save to mysql");
	}

}

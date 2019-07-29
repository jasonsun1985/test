package com.tec.springel;

import org.springframework.beans.factory.annotation.Value;

/** 
 * @Description:
 * <p>创建日期：2019年3月14日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class Bus {
	private People people;
	public Bus(String busName, int money) {
		super();
		this.busName = busName;
		this.money = money;
	}
	private String busName;
	@Value("#{people.age>60?1:2}")
	private int money;
	public String getBusName() {
		return busName;
	}
	public void setBusName(String busName) {
		this.busName = busName;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public People getPeople() {
		return people;
	}
	public void setPeople(People people) {
		this.people = people;
	}
}

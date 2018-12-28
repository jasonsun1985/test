package com.pattern.observer;

import java.util.List;
import java.util.Observable;
import java.util.Observer;


/** 
 * @Description：实际 被 观察者
 * <p>创建日期：2013-8-28 </p>
 * @version V1.0  
 * @author lei.sun
 * @see
 */
public class Police extends Observable {
	private String time;
	public String str;
	public Police(List<Observer> list){
//		super();
		for (Observer observer : list) {
			super.addObserver(observer);
		}
		str = "SHOW UP ";
	}
	public void change(String time){
		this.time = time;
		super.setChanged();  
        super.notifyObservers(this.time); 
	}
	
}

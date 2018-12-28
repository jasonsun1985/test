package com.pattern.observer;

import java.util.Observable;
import java.util.Observer;

/** 
 * @Description：实际观察者B
 * <p>创建日期：2013-8-28 </p>
 * @version V1.0  
 * @author lei.sun
 * @see
 */
public class UndercoverB implements Observer {
	private String time; 
	public void update(Observable o, Object arg) {
		time = (String) arg;
		Police p = (Police) o; 
		System.out.println("police： "+ p.str);
		System.out.println("卧底B接到消息，行动时间为："+time);
	}

}

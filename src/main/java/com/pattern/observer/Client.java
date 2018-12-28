package com.pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/** 
 * @Description：测试
 * <p>创建日期：2013-8-28 </p>
 * @version V1.0  
 * @author lei.sun
 * @see
 */
public class Client {
	public static void main(String[] args) {
		UndercoverA o1 = new UndercoverA();  
        UndercoverB o2 = new UndercoverB();  
        List<Observer> list = new ArrayList<Observer>();  
        list.add(o1);  
        list.add(o2);  
        Police police = new Police(list);  
        police.change("02:25");  
        System.out.println("===========由于消息泄露，行动时间提前=========");
        police.change("01:05");  
	}
}

package com.tec.generic;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {
	public static void main(String[] args) {
		// compile error
//		List <? extends Fruit> appList2 = new ArrayList();
//		appList2.add(new Fruit());
//		appList2.add(new Apple());
//		appList2.add(new RedApple());
		Fruit f = new Apple();
		Fruit ff = new RedApple();
//		Apple a = (Apple) new Fruit();//父类无法强转为子类
		
		List <? super Fruit> appList = new ArrayList();
		appList.add(new Fruit());
		appList.add(new Apple());
		appList.add(new RedApple());
		
	}
}

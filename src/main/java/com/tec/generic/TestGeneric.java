package com.tec.generic;

import java.util.ArrayList;
import java.util.List;

public class TestGeneric {
	public static void main(String[] args) {
		//super可以加,extends不可以加元素
		/*
		 * 如果你想从一个数据类型里获取数据，使用 ? extends 通配符
		 * 如果你想把对象写入一个数据结构里，使用 ? super 通配符
		 * 如果你既想存，又想取，那就别用通配符。
		 */
		List <? super Fruit> appList = new ArrayList<>();
		appList.add(new Fruit());
		appList.add(new Apple());
		appList.add(new RedApple());

		List<Apple> apples = new ArrayList<Apple>();
		apples.add(new RedApple());
		apples.add(new Apple());
		List<? extends Fruit> fruits = apples;
		for(Fruit f:fruits){
			System.out.println(f.getClass());
		}
		

		
		
	}
}

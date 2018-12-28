package com.tec.finalize;

import java.util.Set;

//此类说明了 finalize 关键字以及 System.gc() 的作用
/*
 finalize 类似 c++中析构函数,表示对象即将消亡时，调用此方法
 gc() 方法表示把拉级回收器启动，把拉圾收走
 */

class Person {
	
	private String name;
	private int age;

	public Person() {
	}

	public Person(String name) {
		this.name = name;
	}

	public Person(String name, int age) {
		this(name);
		this.age = age;
	}

	// 每个类里面都有finalize方法，和构造方法功能相反，一个是对象产生时调用，一个是对象消亡时调用
	public void finalize() {
		System.out.println("开始清理对象...");
	}

	public static void main(String[] args) throws InterruptedException {
		// 下面三个对象一产生即成为拉圾
		new Person();
		new Person();
		new Person();
		// 如果不加上下面这句，以上产生的三个对象虽成为拉圾,但不一定马上就会启用拉圾回收机制把拉圾回收走
		// 所以就可能看不到 finalize 方法内部执行的效果
		System.gc();
		Set<Object> set = System.getProperties().keySet();
		for (Object obj: set) {
			System.out.println("System key : " + obj );
			System.out.println("System value : " + System.getProperties().get(obj));
			System.out.println("-----------------------------------------------------");
		}
	}

}
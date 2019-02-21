package com.collect.thread;

/** 
 * @Description:
 * <p>创建日期：2019年2月21日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class NotifyWaitTest {
	static People people = new People();
	private static class People {
		private int age;
		private String name;
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	public static class T1 extends Thread {
		@Override
		public void run() {
			synchronized (people){
				 try {
					System.out.println(System.currentTimeMillis()+ " T1");
					System.out.println(Thread.currentThread().getName()+"锁定对象");
					people.wait();
					System.out.println(System.currentTimeMillis()+"T1 别的线程唤醒了我");
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public static class T2 extends Thread {
		@Override
		public void run() {
			synchronized (people){
				 try {
					System.out.println(System.currentTimeMillis()+ " T2");
					System.out.println(Thread.currentThread().getName()+"释放对象");
					people.notifyAll();
					System.out.println(System.currentTimeMillis()+"6秒后唤醒其他线程");
					Thread.sleep(6000);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		Thread t1 = new T1();
		Thread t2 = new T2();
		t1.start();
		t2.start();
	}
}

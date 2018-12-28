package com.tec.clone;

public class StudentDeepCopy implements Cloneable {
	
	private int number;

	private Address addr;

	public Address getAddr() {
		return addr;
	}

	public void setAddr(Address addr) {
		this.addr = addr;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public Object clone() {
		StudentDeepCopy stu = null;
		try {
			stu = (StudentDeepCopy) super.clone();// 浅复制
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		stu.addr = (Address) addr.clone();// 深度复制
		return stu;
	}
	
	public static void main(String args[]) {
		
		Address addr = new Address();
		addr.setAdd("天津市");
		StudentDeepCopy stu1 = new StudentDeepCopy();
		stu1.setNumber(123);
		stu1.setAddr(addr);
		
		StudentDeepCopy stu2 = (StudentDeepCopy)stu1.clone();
		
		System.out.println("学生1:" + stu1.getNumber() + ",地址:" + stu1.getAddr().getAdd());
		System.out.println("学生2:" + stu2.getNumber() + ",地址:" + stu2.getAddr().getAdd());
		
		addr.setAdd("北京市");
		
		System.out.println("学生1:" + stu1.getNumber() + ",地址:" + stu1.getAddr().getAdd());
		System.out.println("学生2:" + stu2.getNumber() + ",地址:" + stu2.getAddr().getAdd());
	}
}
/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：People.java
 *  版本变更记录（可选）：修改日期2017年12月11日  下午5:34:25，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.collect.any;

/** 
 * @Description:
 * <p>创建日期：2017年12月11日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class People {
	public String name;
	public int age;
	
	public People(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "People [name=" + name + ", age=" + age + "]";
	}
}

package com.tec.builder;

public class Person implements Cloneable {
	private final String name;
	private final String gender;
	private String house;
	private String age;
	private String hight;
	private Integer money;

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getHight() {
		return hight;
	}

	public void setHight(String hight) {
		this.hight = hight;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public static class Builder {
		private final String name;
		private final String gender;
		private String house;
		private String age;
		private String hight;
		private Integer money;
		public Builder(String name,String gender){
			this.name = name;
			this.gender = gender;
		}
		
		public Builder house(String house){
			this.house = house;
			return this;
		}
		
		public Builder age(String age){
			this.age = age;
			return this;
		}
		
		public Builder hight(String hight){
			this.hight = hight;
			return this;
		}
		public Builder money(Integer money){
			this.money = money;
			return this;
		}
		public Person build(){
			return new Person(this);
		}
	}
	
	private Person(Builder builder){
		this.name = builder.name;
		this.gender = builder.gender;
		this.house=builder.house;
		this.age=builder.age;
		this.hight=builder.hight;
		this.money=builder.money;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", gender=" + gender + ", house=" + house + ", age=" + age + ", hight=" + hight
				+ ", money=" + money + "]";
	}

	@Override
	protected Person clone() throws CloneNotSupportedException {
		return (Person) super.clone();
	}
	
}

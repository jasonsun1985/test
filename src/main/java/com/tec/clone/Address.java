package com.tec.clone;

class Address implements Cloneable {
	private String add;

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}
	
	@Override
	public Object clone() {
		Address addr = null;
		try{
			addr = (Address)super.clone();
		}catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return addr;
	}
}

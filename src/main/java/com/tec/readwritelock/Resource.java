package com.tec.readwritelock;

class Resource{
	
    private int value;
 
    public void setValue(int value) {
       this.value = value;
    }
    public int getValue() {
       return value;
    }
}

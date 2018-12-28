package com.collect.proxy;

public class UserServiceImpl implements UserService {
    public void add() {  
        System.out.println("--------------------add---------------");  
    }

	public void reduce() {
		 System.out.println("--------------------reduce---------------");  
	}
	
	public boolean expression(String expression){
		return Boolean.valueOf(expression);
	}
}

package com.collect.some;

import java.util.Arrays;

import org.junit.Test;

public class TestTemp {
	@Test
	public void getMobile() {
		int[] arr = new int[]{8,2,1,0,3,6,4,5,7};
		int[] index = new int[]{2,4,0,1,3,5,0,4,6,7,8};
		String a = "";
		for (int i : index) {
			a+=arr[i];
		}
		System.out.println(a);
	}
	@Test
	public void checkNull(){
		String a = null;
		if(a==null&&a.length()==0){ //ERROR
			System.out.println(a.length());
		}
//		if(a==null||a.length()==0){ //ERROR
//			System.out.println(a.length());
//		}
	}
	public static void main(String[] args) {
		 boolean isTrue = false;  
	        isTrue |= true;  
	        System.out.println("1"+isTrue);  
	          
	        isTrue = false;  
	        isTrue |= false;  
	        System.out.println("2"+isTrue);  
	          
	        isTrue = true;  
	        isTrue |= true;  
	        System.out.println("3"+isTrue);  
	          
	        isTrue = true;  
	        isTrue |= false;  
	        System.out.println("4"+isTrue);  
	        isTrue &= false;  
	        System.out.println("5"+isTrue);  
	        isTrue = true;  
	        isTrue ^= isTrue;  
	        System.out.println("6"+isTrue);  
	}
}

package com.collect.any;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;

public class TestList {
    public static void main(String[] args) {
//        String str1 = "19";// "1号楼"; 
//        String str11 = "1";//"11号楼"; 
//        System.out.println(str1.indexOf(str11));
//        System.out.println(str11.indexOf(str1));
        test4();
    }
    private static void test3() {
        List a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        a.add("3");
        a.add("4");
        List b = new ArrayList<String>();
        b.add("5");
        b.add("6");
        b.add("7");
        a=b;
//        System.out.println(a);
//        System.out.println(b);
        String c = StringUtils.collectionToDelimitedString(a,",");
        System.out.println(c);
    }
    @Test
    public static void test2(){
        List a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        a.add("3");
        a.add("4");
        List b = new ArrayList<String>();
        b = a.subList(0, 2);
        System.out.println(b);
        System.out.println(a);
        b.clear();
        System.out.println(b);
        System.out.println(a);
    }
    private static void test4(){
    	List<People> list = Arrays.asList(new People("xiaoming", 16),new People("xiaozhang",20));
    	People p = null;
    	for (int i = 0; i < list.size(); i++) {
			p = list.get(i);
			p.setAge(p.getAge()+10);
		}
    	System.out.println(list);
    	for (int i = 0; i < list.size(); i++) {
    		list.get(i).toString();
		}
    }
    @Test
    public static void test1(){
        List<String> t_meCol = Arrays.asList("ifStatus", "MACAdress", "IPAdress");
        List<String> a = new ArrayList<String>();
        a.add("1");
        a.add("2");
        for (String temp : a) {
            if ("1".equals(temp)) {//换成2抛出java.util.ConcurrentModificationException异常
                a.remove(temp);
            }
        }
        System.out.println("a : " + a);
        //正确方法，如果遇到多线程请加锁
        List<String> aNew = new ArrayList<String>();
        aNew.add("1");
        aNew.add("2");
        Iterator<String> i = aNew.iterator();
        while (i.hasNext()) {
            if("2".equals(i.next())){
                i.remove();
            }
        }
        System.out.println("aNew : " + aNew);
        
        List<String> aa = Lists.newArrayList("1","2","3");
        for(int n = aa.size()-1; n>0; n-- ) {
        	if("2".equals(aa.get(n))){
        		aa.remove(n);
        	}
        }
        
        System.out.println("aa : " + aa);
       /* System.out.println("九九乘法表");
        int ii,j;
        for(ii=1;ii<=9;ii++){
            for(j=1;j<=ii;j++){
                System.out.print(ii+"*"+j+"="+ii*j+"\t");
            }
            System.out.println();
        }*/
    
    }
    
}

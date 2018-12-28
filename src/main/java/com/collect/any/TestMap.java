package com.collect.any;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

public class TestMap {
	public static void main(String[] args) {
		 	/*int a = 1;
	        long start = 0;
	        long end = 0;
	        // 先垃圾回收
	        System.gc();
	        start = Runtime.getRuntime().freeMemory();
	        HashMap map = new HashMap();
	        for (int i = 0; i < 1000000; i++) {
	            map.put(i, a);
	        }
	        // 快要计算的时,再清理一次
	        System.gc();
	        System.currentTimeMillis();
	        end = Runtime.getRuntime().freeMemory();
	        System.out.println("一个HashMap对象占内存:" + (end - start));
	        */
	        /*Map<String,String> m1 = new HashMap<String,String>();
	        m1.put("A", "A");
	        m1.put("B", "B");
	        m1.put("B", "B");
	        Map<String,String> m2 = new HashMap<String,String>();
	        m2.put("A", "A");
	        m2.put("B", "B");
	        m2.put("B", "B");
	        Maps.difference(m1, m2);
	        System.out.println(Maps.difference(m1, m2).areEqual());*/
			testRemoveMap();
	        
	}
	public static void testRemoveMap(){
		Map map = new HashMap<String,Long>();
		map.put("A", System.currentTimeMillis());
		map.put("B", System.currentTimeMillis());
		map.put("C", System.currentTimeMillis());
		Map map2 = new HashMap<String,Long>();
		map2.put("A", System.currentTimeMillis());
		map2.put("B", System.currentTimeMillis());
		map2.put("C", System.currentTimeMillis());
        Iterator t_it = map.entrySet().iterator();
        while (t_it.hasNext()) {
        	Map.Entry t_entry = (Map.Entry) t_it.next();
            String t_key = (String) t_entry.getKey();
            Long t_value = (Long) t_entry.getValue();
            if ("B".equals(t_key)) {
            	t_it.remove();
            	map2.remove(t_key);
            }
        }
        System.out.println(map);
        System.out.println(map2);
	}
	
}

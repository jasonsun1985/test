package com.tec.google;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Table;
import com.google.common.collect.Table.Cell;

public class GoogleTable {
    @Test
    public void testTable(){
        Table<String,String,String> table = HashBasedTable.create();
        table.put("id", "name", "age");
        table.put("id1", "name1", "age1");
        table.put("id2", "name2", "age2");
        Set<Cell<String,String,String>> cSet = table.cellSet();
        for(Cell<String,String,String> c : cSet){
            System.out.println("c.getRowKey() : " + c.getRowKey());
            System.out.println("c.getColumnKey() : " + c.getColumnKey());
            System.out.println("c.getValue() : "+c.getValue());
        }
        Map<String, Map<Long, List<String>>> map = Maps.newHashMap();
    }
    
    @Test
    public void sort(){
        List<String> l = new ArrayList<String>();
        l.add("bb");
        l.add("aa");
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
        Collections.sort(l, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                System.out.println(o1.compareTo("aa"));
                if (o1.equals("aa")) {
                    return -1;
                }
                return 1;
            }
        });
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(i));
        }
    }
    
    @Test
    public void getCollectMethod() {
        List l = new ArrayList();
        System.out.println(CollectionUtils.isEmpty(l));
    }
}

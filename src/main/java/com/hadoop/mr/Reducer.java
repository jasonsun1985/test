package com.hadoop.mr;

import java.util.Map;  
import java.util.TreeMap;  
  
public abstract class Reducer<K, V, K1, V1> {  
  
    private final TreeMap<K1, V1> map = new TreeMap<K1, V1>();  
  
    public Map<K1, V1> getResult() {  
        return map;  
    }  
  
    public abstract void reduce(K k, Iterable<V> list, Context context)  
            throws RuntimeException;  
  
    public class Context {  
  
        public void write(K1 k, V1 v) {  
            map.put(k, v);  
        }  
  
    }  
  
}  

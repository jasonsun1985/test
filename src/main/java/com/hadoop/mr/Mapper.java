package com.hadoop.mr;

import java.util.ArrayList;  
import java.util.List;  
import java.util.Map;  
import java.util.TreeMap;  
  
public abstract class Mapper<K0, V0, K, V> {

    private final Map<K, List<V>> map = new TreeMap<K, List<V>>();

    public Map<K, List<V>> getResult() {
        return map;
    }

    public abstract void map(K0 key, V0 value, Context context)
            throws RuntimeException;

    public class Context {

        public void write(K k, V v) {
            List<V> list = map.get(k);
            if (list == null) {
                list = new ArrayList<V>();
            }
            list.add(v);
            map.put(k, list);
        }

    }

}

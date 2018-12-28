package com.hadoop.mr;

import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
  
import org.slf4j.Logger;  
import org.slf4j.LoggerFactory;  
  
public class Job {

	private static final Logger LOG = LoggerFactory.getLogger(Job.class);

	private Class<? extends Mapper> map;
	private Class<? extends Reducer> reduce;

	public void setMap(Class<? extends Mapper> map) {
		this.map = map;
	}

	public void setReduce(Class<? extends Reducer> reduce) {
		this.reduce = reduce;
	}

	public int run(String[] input) throws RuntimeException {
		int len = input.length;

		try {
			Mapper m = map.newInstance();
			for (int i = 0; i < len; i++) {
				m.map(0L + i, input[i], m.new Context());
			}
			Map<Object, List<Object>> mapResult = m.getResult();

			Reducer r = reduce.newInstance();
			Iterator<Object> it = mapResult.keySet().iterator();
			while (it.hasNext()) {
				Object k = it.next();
				r.reduce(k, mapResult.get(k), r.new Context());
			}
			Map<Object, Object> reduceResult = r.getResult();

			it = reduceResult.keySet().iterator();
			while (it.hasNext()) {
				Object k = it.next();
				LOG.info("{}\t{}", k.toString(), reduceResult.get(k)); // \t 日志空行
				System.out.println(k.toString() + "  " + reduceResult.get(k)); // \t 日志空行
			}
		}
		catch (InstantiationException e) {
			LOG.error("", e);
			throw new RuntimeException(e);
		}
		catch (IllegalAccessException e) {
			LOG.error("", e);
			throw new RuntimeException(e);
		}
		catch (Exception e) {
			LOG.error("", e);
			throw new RuntimeException(e);
		}

		return 1;
	}

}
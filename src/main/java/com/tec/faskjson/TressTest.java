package com.tec.faskjson;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/** 
 * @Description:
 * <p>创建日期：2018年12月25日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class TressTest {
	
	public static void main(String[] args) {
		JSONObject all = new JSONObject();
		JSONArray allArray = new JSONArray();
		
		JSONObject children = new JSONObject();
		JSONArray childrenArray = new JSONArray();
		
		JSONObject child1 = new JSONObject();
		JSONArray child1Array = new JSONArray();
		
		child1Array.add(null);
		child1.put("id", "0007c749-70a9-f807-d3ce-acaaca565ccc");
		child1.put("name", "云数据中心概要");
		child1.put("children", child1Array);
		
		
		
		childrenArray.add(child1);
		children.put("id", "0217c749-70a9-f807-d3ce-acaaca565ccc");
		children.put("name", "华为FusionCloud");
		children.put("children", childrenArray);

		allArray.add(children);
		all.put("id", "ALL");
		all.put("name", "总览");
		all.put("children", allArray);
		System.out.println(all.toJSONString());
	}
//	public static void main(String[] args) {
//		JSONObject all = new JSONObject();
//		JSONObject children = new JSONObject();
//		JSONArray child1 = new JSONArray();
//		JSONObject mapChild = new JSONObject();
//		JSONArray child2 = new JSONArray();
//		mapChild.put("id", "云数据中心概要ID");
//		child1.add(mapChild);
//		mapChild = new JSONObject();
//		mapChild.put("id", "虚拟化平台概要ID");
//		child1.add(mapChild);
//		mapChild = new JSONObject();
//		mapChild.put("id", "172.17.160.40ID");
//		child2.add(mapChild);
//		mapChild = new JSONObject();
//		mapChild.put("id", "172.17.160.49ID");
//		child2.add(mapChild);
//		mapChild = new JSONObject();
//		children.put("华为FusionCloud", child1);
//		children.put("VMward VSphere", child2);
//		all.put("虚拟化总览-ALL", children);
//		System.out.println(all.toJSONString());
//	}


}

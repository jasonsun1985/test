package com.tec.test;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TestJson {
	public static void main(String[] args) {
        String str = "{\"code\":0,\"msg\":\"OK\",\"totalCount\":4,\"deviceList\":[{\"serialNumber\":\"1\",\"mac\":\"aabb.ccdd.eeff \",\"productClass\":\"AP-130\",\"productType\":\"AP\",\"hardwareVersion\":\"\",\"softwareVersion\":\"\",\"onlineStatus\":\"OFF\",\"offlineReason\":\"INFORM\",\"groupId\":35,\"groupName\":\"测试_f2\",\"parentGroupName\":\"MOVE-1-1-1\",\"remark\":\"\",\"localIp\":\"\",\"cpeIp\":\"\",\"createTime\":1461044473000},{\"serialNumber\":\"2\",\"mac\":\"aabb.ccdd.edff \",\"productClass\":\"\",\"productType\":\"UNKNOWN\",\"hardwareVersion\":\"\",\"softwareVersion\":\"\",\"onlineStatus\":\"OFF\",\"offlineReason\":\"INFORM\",\"groupId\":35,\"groupName\":\"测试_f2\",\"parentGroupName\":\"MOVE-1-1-1\",\"remark\":\"\",\"localIp\":\"\",\"cpeIp\":\"\",\"createTime\":1461044473000}]}";
        String str2 = "{\"code\":0,\"msg\":\"OK\",\"totalCount\":4,\"deviceList\":[{\"serialNumber\":\"3\",\"mac\":\"aabb.ccdd.eeff \",\"productClass\":\"AP-130\",\"productType\":\"AP\",\"hardwareVersion\":\"\",\"softwareVersion\":\"\",\"onlineStatus\":\"OFF\",\"offlineReason\":\"INFORM\",\"groupId\":35,\"groupName\":\"测试_f2\",\"parentGroupName\":\"MOVE-1-1-1\",\"remark\":\"\",\"localIp\":\"\",\"cpeIp\":\"\",\"createTime\":1461044473000},{\"serialNumber\":\"4\",\"mac\":\"aabb.ccdd.edff \",\"productClass\":\"\",\"productType\":\"UNKNOWN\",\"hardwareVersion\":\"\",\"softwareVersion\":\"\",\"onlineStatus\":\"OFF\",\"offlineReason\":\"INFORM\",\"groupId\":35,\"groupName\":\"测试_f2\",\"parentGroupName\":\"MOVE-1-1-1\",\"remark\":\"\",\"localIp\":\"\",\"cpeIp\":\"\",\"createTime\":1461044473000}]}";
//        JSONObject obj = new JSONObject(str); 
//        System.out.println(obj.get("totalCount"));
//        JSONArray array = obj.getJSONArray("deviceList");
//        System.out.println(array.length());
        JSONObject obj = JSONObject.fromObject(str);
        JSONObject obj2 = JSONObject.fromObject(str2);
        JSONArray array2 = obj2.getJSONArray("deviceList");
        System.out.println(obj2.get("totalCount"));
        JSONArray array = obj.getJSONArray("deviceList");
        array.addAll(array2);
        System.out.println(array.size());
        for (int i = 0; i < array.size(); i++) {
        	JSONObject temp = array.getJSONObject(i);
        	System.out.println(temp.getString("serialNumber"));
		}
	}
}

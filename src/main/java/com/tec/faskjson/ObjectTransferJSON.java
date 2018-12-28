/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：ObjectTransferJSON.java
 *  版本变更记录（可选）：修改日期2018年2月11日  上午11:40:08，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.faskjson;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ObjectTransferJSON {
	public static void main(String[] args) {
		List<Device> listDev = new ArrayList<Device>();
		Device d = new Device();
		d.setCameraId("asdf1234");
		d.setColor("true");
		d.setMetricId("availStatus");
		d.setMetricName("状态");
		d.setValue("可用");
		listDev.add(d);
		d = new Device();
		d.setCameraId("eqwqw123123");
		d.setColor("false");
		d.setMetricId("availStatus");
		d.setMetricName("状态");
		d.setValue("可用");
		listDev.add(d);
		
		String result =  JSON.toJSONString(listDev);
		System.out.println(result);
		
		String str ="{\"code\":0,\"msg\":\"\",\"data\":{\"RoamOutAcAddrType\":1,\"RoamOutAcAddr\":\"172.16.80.132\"}}";
		JSONObject json = (JSONObject) JSONObject.parse(str);
		JSONObject json2 = (JSONObject) json.get("data");
		System.out.println(json);
		System.out.println(json.get("data"));
		System.out.println(json.get("code"));
		System.out.println(json2.get("RoamOutAcAddr"));
		String str2 = "{\"code\":0,\"msg\":\"\",\"data\":{\"ApMacAddr\":\"1414.4b5f.a54c\",\"Ip\":\"10.1.222.231\",\"ApRadioId\":2,\"Ipv6\":\"::\",\"Ssid\":\"NKU_WLAN\",\"Rssi\":-57,\"UserName\":\"\",\"TerminalType\":\"\",\"UpTimeIntv\":27061}}";
		JSONObject j1= (JSONObject) JSONObject.parseObject(str2).get("data");
//		JSONObject j2 = (JSONObject) j1.get("data");
		System.out.println(j1);
		
		
	}
}

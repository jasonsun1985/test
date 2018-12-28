/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：HttpClientUtil.java
 *  版本变更记录（可选）：修改日期2017年8月9日  下午3:01:45，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.collect.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;

/** 
 * @Description:
 * <p>创建日期：2017年8月9日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class HttpClientUtil {
	public String doPost(String url, Map<String, String> map, String charset) {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		try {
			httpClient = new SSL();
			httpPost = new HttpPost(url);
			// 设置参数
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			Iterator iterator = map.entrySet().iterator();
			while (iterator.hasNext()) {
				Entry<String, String> elem = (Entry<String, String>) iterator.next();
				list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
			}
			if (list.size() > 0) {
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
				httpPost.setEntity(entity);
			}
			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

	public static void getHttpResult(String URL) {
		try {
			// 创建HttpClient实例
			HttpClient httpclient = HttpClients.createDefault();
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(3000).setConnectTimeout(3000).build();// 设置请求和传输超时时间
			// 创建Get方法实例
			HttpGet httpgets = new HttpGet(URL);
			httpgets.setConfig(requestConfig);
			HttpResponse response = httpclient.execute(httpgets);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instreams = entity.getContent();
				String str = convertStreamToString(instreams);
				System.out.println("原始值 ： " + str);
				// String str = "<page><style>0</style><totalrecord>1</totalrecord></page><datas><dev><name>测试1</name><serial></serial><ip>192.168.1.166</ip><port>8899</port><user>admin</user><pass></pass><factroytype>7</factroytype><hardtype>0</hardtype><netstate>1</netstate></dev><dev><name>测试2</name><serial></serial><ip>192.168.1.167</ip><port>8899</port><user>admin1</user><pass/><factroytype>8</factroytype><hardtype>0</hardtype><netstate>1</netstate></dev></datas>";
				// String str = "<page><style>0</style><totalrecord>1</totalrecord></page><datas><dev><name>测试</name><serial></serial><ip>192.168.1.166</ip><port>8899</port><user>admin</user><pass></pass><factroytype>7</factroytype><hardtype>0</hardtype><longitude></longitude><latitude></latitude></dev></datas>";
				Camera camera = toBean("<camera>" + str.trim() + "</camera>", Camera.class);
				System.out.println(camera.getDatas());
				for (int i = 0; i < camera.getDatas().getDevList().size(); i++) {
					System.out.println("设备： " + camera.getDatas().getDevList().get(i).toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static <T> T toBean(String xmlStr, Class<T> cls) {
		XStream xstream = new XStream(new DomDriver());
		xstream.addPermission(NoTypePermission.NONE);
		xstream.addPermission(NullPermission.NULL);
		xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
		xstream.allowTypesByWildcard(new String[] { HttpClientUtil.class.getPackage().getName() + ".*" });
		xstream.processAnnotations(cls);
		T obj = (T) xstream.fromXML(xmlStr);
		return obj;
	}

	public static String convertStreamToString(InputStream is) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(is, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}
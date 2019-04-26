package com.tec.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSONObject;

public class HttpClient {
	private static final String token1 = "zJvEwHzumy57SXcxCzWB";
	private static ExecutorService executorService = Executors.newFixedThreadPool(50);
	public static void main(String[] args) {
		for (int i = 0; i < 1; i++) {
		String str = "{remoteTest1:"+i+",remoteTest2:"+new Random().nextInt(100)+"}";
			executorService.submit(new Runnable() {
				@Override
				public void run() {
					try {
						httpPost("http://172.17.161.179:8080/api/v1/"+token1+"/telemetry", JSONObject.parseObject(str));
					}
					catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
		}
		executorService.shutdown();
	}
	public static String httpPost(String url,JSONObject jsonParam) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonParam.toString(),"utf-8");
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");
        post.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity news = response.getEntity();
        String str = EntityUtils.toString(news, "utf-8");
        System.out.println("=================result : " + news.toString());
        httpClient.close();
        return str;
    }
	
	public static String httpGet(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)   //设置连接超时时间
                .setConnectionRequestTimeout(5000) // 设置请求超时时间
                .setSocketTimeout(5000)
                .setRedirectsEnabled(true)//默认允许自动重定向
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        HttpEntity entity = httpResponse.getEntity();
        String srtResult = EntityUtils.toString(entity);//获得返回的结果
        httpClient.close();
        return srtResult;
    }
	
	/**
     * 
     * @param url
     * @param xmlData (xml字符串)
     * @return result (String字符串)
     * @throws Exception
     */
	public String httpPost(String url, String xmlData) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		List<BasicNameValuePair> parameters = new ArrayList();
		parameters.add(new BasicNameValuePair("xml", xmlData));
		post.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
		HttpResponse response = client.execute(post);
		System.out.println(response.toString());
		HttpEntity entity = response.getEntity();
		String result = EntityUtils.toString(entity, "UTF-8");
		return result;
	}
    
    /**
     * List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
     * list.add(new BasicNameValuePair("age", "20"));  //请求参数(只能为字符串)
     * list.add(new BasicNameValuePair("name", "zhangsan")); //请求参数
     * @param url
     * @param list
     * @return
     */
    public static String httpPost(String url,List<BasicNameValuePair> list) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        //如果有header请求，添加header请求
        //post.addHeader("x-api-key", "XXX");
        //post.addHeader("x-lang", "en-US");
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
        //设置post求情参数
        post.setEntity(entity);
        //向服务器发送请求，并获取返回数据
        CloseableHttpResponse response = httpClient.execute(post);
        response.getStatusLine().getStatusCode();

        //获取HttpEntity消息载体对象
        HttpEntity news = response.getEntity();
        // EntityUtils中的toString()方法转换服务器的响应数据
        String str = EntityUtils.toString(news, "utf-8");
        httpClient.close();
        return str;
    }
}

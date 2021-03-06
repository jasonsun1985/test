package com.tec.http;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import sun.net.www.content.audio.x_aiff;

  /**
	* @ClassName: HttpClientPoolUtil 
	* @Description: 连接池
	* @version V1.0 
	*/
public class HttpClientPoolUtil {
	private static ExecutorService executorService = Executors.newFixedThreadPool(100);
	public static void main(String[] args) {
		CountDownLatch ct = new CountDownLatch(1000000);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			executorService.execute(() -> {
				try {
//					execute("http://172.17.161.208:8445/api-a/hi");
//					execute("http://172.17.161.90:8445/api-a/hi");
					execute("http://localhost:8888/api-a/hi");
					ct.countDown();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
		});
//		new Thread(()-> {
//			execute("http://172.17.161.208:8445/api-a/hi");
//		}).run();
		}
		try {
			ct.await();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("一共用了 ： " + (end-start)/1000 + " 秒");
		executorService.shutdown();
	}
	
	
	
	
	
	
	
	
	
	private static Logger logger = LoggerFactory.getLogger(HttpClientPoolUtil.class);
	public static PoolingHttpClientConnectionManager cm = null;
	public static CloseableHttpClient httpClient = null;
	/** * 默认content 类型 */
	private static final String DEFAULT_CONTENT_TYPE = "application/json";
	/** * 默认请求超时时间30s */
	private static final int DEFAUL_TTIME_OUT = 15000;
	private static final int count = 10000;
	private static final int totalCount = 10000;
	private static final int Http_Default_Keep_Time =15000;

	/** * 初始化连接池 */
	public static synchronized void initPools() {
		if (httpClient == null) {
			cm = new PoolingHttpClientConnectionManager();
			cm.setDefaultMaxPerRoute(count);
			cm.setMaxTotal(totalCount);
			httpClient = HttpClients.custom().setKeepAliveStrategy(defaultStrategy).setConnectionManager(cm).build();
		}
	}

	/**
	 *  Http connection keepAlive 设置 
	 */
	public static ConnectionKeepAliveStrategy defaultStrategy = new ConnectionKeepAliveStrategy() {
		public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
			HeaderElementIterator it = new BasicHeaderElementIterator(response.headerIterator(HTTP.CONN_KEEP_ALIVE));
			int keepTime = Http_Default_Keep_Time;
			while (it.hasNext()) {
				HeaderElement he = it.nextElement();
				String param = he.getName();
				String value = he.getValue();
				if (value != null && param.equalsIgnoreCase("timeout")) {
					try {
						return Long.parseLong(value) * 1000;
					} catch (Exception e) {
						e.printStackTrace();
						logger.error("format KeepAlive timeout exception, exception:" + e.toString());
					}
				}
			}
			return keepTime * 1000;
		}
	};

	public static CloseableHttpClient getHttpClient() {
		return httpClient;
	}

	public static PoolingHttpClientConnectionManager getHttpConnectionManager() {
		return cm;
	}

	/**
	 * 执行http post请求 
	 * 默认采用Content-Type：application/json，Accept：application/json
	 * @param uri 请求地址 
	 * @param data 请求数据 
	 * @return
	 */
	public static String execute(String uri, String data) {
		long startTime = System.currentTimeMillis();
		HttpEntity httpEntity = null;
		HttpEntityEnclosingRequestBase method = null;
		String responseBody = "";
		try {
			if (httpClient == null) {
				initPools();
			}
			method = (HttpEntityEnclosingRequestBase) getRequest(uri, HttpPost.METHOD_NAME, DEFAULT_CONTENT_TYPE, 0);
			method.setEntity(new StringEntity(data));
			HttpContext context = HttpClientContext.create();
			CloseableHttpResponse httpResponse = httpClient.execute(method, context);
			httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				responseBody = EntityUtils.toString(httpEntity, "UTF-8");
			}
		} catch (Exception e) {
			if (method != null) {
				method.abort();
			}
			e.printStackTrace();
			logger.error("execute post request exception, url:" + uri + ", exception:" + e.toString()
					+ ", cost time(ms):" + (System.currentTimeMillis() - startTime));
		} finally {
			if (httpEntity != null) {
				try {
					EntityUtils.consumeQuietly(httpEntity);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("close response exception, url:" + uri + ", exception:" + e.toString()
							+ ", cost time(ms):" + (System.currentTimeMillis() - startTime));
				}
			}
		}
		return responseBody;
	}

	/**
	 * * 创建请求
	 * @param uri 请求url 
	 * @param methodName 请求的方法类型
     * @param contentType contentType类型 
	 *  @param timeout 超时时间 
	 * @return HttpRequestBase    返回类型
	 * @throws
	 */
	public static HttpRequestBase getRequest(String uri, String methodName, String contentType, int timeout) {
		if (httpClient == null) {
			initPools();
		}
		HttpRequestBase method = null;
		if (timeout <= 0) {
			timeout = DEFAUL_TTIME_OUT;
		}
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(timeout * 1000)
				.setConnectTimeout(timeout * 1000).setConnectionRequestTimeout(timeout * 1000)
				.setExpectContinueEnabled(false).build();
		if (HttpPut.METHOD_NAME.equalsIgnoreCase(methodName)) {
			method = new HttpPut(uri);
		} else if (HttpPost.METHOD_NAME.equalsIgnoreCase(methodName)) {
			method = new HttpPost(uri);
		} else if (HttpGet.METHOD_NAME.equalsIgnoreCase(methodName)) {
			method = new HttpGet(uri);
		} else {
			method = new HttpPost(uri);
		}
		if (Strings.isNullOrEmpty(contentType)) {
			contentType = DEFAULT_CONTENT_TYPE;
		}
		method.addHeader("Content-Type", contentType);
		method.addHeader("Accept", contentType);
		method.setConfig(requestConfig);
		return method;
	}

	/** 
	    * 执行GET 请求 
	 *  @param uri 
	 *  @return
	 */
	public static String execute(String uri) {
		long startTime = System.currentTimeMillis();
		HttpEntity httpEntity = null;
		HttpRequestBase method = null;
		String responseBody = "";
		try {
			if (httpClient == null) {
				initPools();
			}
			method = getRequest(uri, HttpGet.METHOD_NAME, DEFAULT_CONTENT_TYPE, 0);
			HttpContext context = HttpClientContext.create();
			CloseableHttpResponse httpResponse = httpClient.execute(method, context);
			httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				responseBody = EntityUtils.toString(httpEntity, "UTF-8");
//				logger.info("请求URL: " + uri + "+ 返回状态码：" + httpResponse.getStatusLine().getStatusCode());
				System.out.println("请求URL: " + uri + "+ 返回信息：" + responseBody);
			}
		} catch (Exception e) {
			if (method != null) {
				method.abort();
			}
			e.printStackTrace();
			logger.error("execute get request exception, url:" + uri + ", exception:" + e.toString() + ",cost time(ms):"
					+ (System.currentTimeMillis() - startTime));
		} finally {
			if (httpEntity != null) {
				try {
					EntityUtils.consumeQuietly(httpEntity);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("close response exception, url:" + uri + ", exception:" + e.toString()
							+ ",cost time(ms):" + (System.currentTimeMillis() - startTime));
				}
			}
		}
		return responseBody;
	}
}
/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：SSLClient.java
 *  版本变更记录（可选）：修改日期2017年8月9日  下午3:14:09，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.collect.http;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;  
/** 
 * @Description:
 * <p>创建日期：2017年8月9日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
//用于进行Https请求的HttpClient  
public class SSL extends DefaultHttpClient {
	public SSL() throws Exception {
		super();
		// 传输协议需要根据自己的判断
		SSLContext ctx = SSLContext.getInstance("SSL");
		X509TrustManager tm = new X509TrustManager() {
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};
		ctx.init(null, new TrustManager[] { tm }, null);
		SSLSocketFactory ssf = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		ClientConnectionManager ccm = this.getConnectionManager();
		SchemeRegistry sr = ccm.getSchemeRegistry();
		sr.register(new Scheme("https", 443, ssf));
	}
}

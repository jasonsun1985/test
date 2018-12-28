package com.collect.ws;

import java.io.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;


public class GetxmFromurl {

    /**
     * 根据url获得服务器上返回的xml
     * @param url
     * @return
     */
    public static Document getResultInfo(String url){
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet get = new HttpGet(url);
        Document doc = null;
        try{
            HttpResponse response = httpClient.execute(get);
            // 先从响应头得到实体
            HttpEntity entity = response.getEntity();
            int status = response.getStatusLine().getStatusCode();
            if (status == HttpStatus.SC_OK) {
                // 得到实体输入流
                InputStream inSm = entity.getContent();
                BufferedReader br = new BufferedReader(new InputStreamReader(
                        inSm, "UTF-8"));
                String xmlString = "";
                for (String temp = br.readLine(); temp != null; xmlString += temp, temp = br
                        .readLine())
                    ;
                // 去除字符串中的换行符，制表符，回车符。
                InputStream stream2 = new ByteArrayInputStream(xmlString
                        .getBytes("UTF-8"));

                SAXReader saxReader = new SAXReader(); 
                saxReader.setEncoding("UTF-8");
                doc = (Document) saxReader.read(new InputSource(stream2));
                System.out.println(doc.asXML());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return doc;
    }
    public static void main(String[] args) {
        GetxmFromurl.getResultInfo("http://webservice.webxml.com.cn/WebServices/TraditionalSimplifiedWebService.asmx?wsdl");
    }
}

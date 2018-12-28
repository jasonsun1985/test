package com.collect.any;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class BrokenPointDownloadTest {

    /**

     * <一句话功能简述>

     * <功能详细描述>

     * @param args

     * @throws Exception

     * @see [类、类#方法、类#成员]

     */
    public static void main(String[] args) throws Exception {
        brokenPointDownload("http://123.57.138.106:8088/preview/res/14.pdf", "C:\\Users\\R07400\\Desktop");
    }
    
    /**

     * 断点下载（只处理了pdf文件）

     * http://123.57.138.106:8088/preview/res/14.pdf

     * @param req

     * @param basePath

     * @throws Exception

     * @see [类、类#方法、类#成员]

     */
    public static void brokenPointDownload(String req, String basePath) throws Exception {
        URL url = new URL(req);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("HEAD");
        connection.setReadTimeout(200000);
        connection.setConnectTimeout(60000);
        connection.connect();
        
        int httpCode = connection.getResponseCode();
        if (httpCode == HttpURLConnection.HTTP_OK) {
            Map<String, List<String>> map = connection.getHeaderFields();
            String contentType = map.get("Content-Type").get(0);
            int contentLength = Integer.valueOf(map.get("Content-Length").get(0));
            if ("application/pdf".equals(contentType)) {
                File baseFile = new File(basePath);
                if (!baseFile.exists()) {
                    baseFile.mkdirs();
                }
                String urlPath = url.getPath();
                if (urlPath.lastIndexOf("/") == urlPath.length() - 1 ) {
                    urlPath = urlPath.substring(0, urlPath.lastIndexOf("/"));
                }
                String fileName = urlPath.substring(urlPath.lastIndexOf("/") + 1);
                // 循环断点下载，每次下载51200字节

                int size = 51200;
                int times = contentLength / size + 1;
                for (int i = 0; i < times; i++) {
                    int startOffset = i == 0 ? i : i*size+1;
                    int targetOffset = i == times-1 ? contentLength : startOffset + size;
                    URL ui = new URL(req);
                    HttpURLConnection ci = (HttpURLConnection) ui.openConnection();
                    ci.setDoOutput(true);
                    ci.setDoInput(true);
                    ci.setRequestMethod("GET");
                    ci.setReadTimeout(200000);
                    ci.setConnectTimeout(60000);
                    ci.addRequestProperty("Range", "bytes=" + startOffset + "-" + targetOffset);// 请求头

                    ci.connect();
                    
                    int hci = ci.getResponseCode();
                    if (hci == HttpURLConnection.HTTP_PARTIAL || hci == HttpURLConnection.HTTP_OK) {
                        InputStream is = ci.getInputStream();
                        Map<String, List<String>> mi = connection.getHeaderFields();
                        int cti = Integer.valueOf(mi.get("Content-Length").get(0));
                        // 下载文件到本地

                        System.out.println("downloading " + urlPath + " ...");
                        int finishByte = 0;
                        File desFile = new File(baseFile, fileName);
                        RandomAccessFile raf = new RandomAccessFile(desFile, "rw");
                        BufferedInputStream bis = new BufferedInputStream(is);
                        // 跳过字节

                        raf.seek(startOffset);
                        byte[] buf = new byte[1024*1024];
                        int len = 0;
                        while ((len = bis.read(buf)) != -1 ) {
                            raf.write(buf, 0, len);
                            finishByte += len;
                            System.out.println((double)finishByte/cti * 100 + "%");
                        }
                        raf.close();
                        System.out.println("downloaded " + urlPath);
                    }
                    ci.disconnect();
                }
            }
        }
        connection.disconnect();
    }

}

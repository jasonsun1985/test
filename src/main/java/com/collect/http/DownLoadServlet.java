/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：DownLoadServlet.java
 *  版本变更记录（可选）：修改日期2017年8月14日  上午10:55:38，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.collect.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @Description:
 * <p>创建日期：2017年8月14日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class DownLoadServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		File file = new File("");
		FileInputStream fis = new FileInputStream(file);
		byte[] b = new byte[fis.available()];
		fis.read(b);
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Content-Disposition", "attachment;filename="+file.getName());
		ServletOutputStream sout = resp.getOutputStream();
		sout.write(b);
		sout.flush();
		sout.close();
	}

}

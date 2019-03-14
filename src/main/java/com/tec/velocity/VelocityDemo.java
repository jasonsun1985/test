package com.tec.velocity;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

/** 
 * @Description:
 * <p>创建日期：2019年2月26日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class VelocityDemo {
	public static void main(String[] args) {
		VelocityEngine ve=new VelocityEngine();
		 //设置模板加载路径，这里设置的是class下  
		 ve.setProperty(Velocity.RESOURCE_LOADER, "class");
		 ve.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
		 Map<String, String> map = new HashMap<>();
		 map.put("title", "GOOD");
	        try {   
	            //进行初始化操作   
	            ve.init();   
	            //加载模板，设定模板编码
	            Template t=ve.getTemplate("velocity/hello.html.vm","gbk");
	            //设置初始化数据   
	            VelocityContext context = new VelocityContext(map);   
	            //设置输出   
	            PrintWriter writer = new PrintWriter("D:\\mars-workspace\\test\\helloword.html");   
	            //将环境数据转化输出   
	            t.merge(context, writer);   
	            writer.close();   
	        } catch (Exception e) {   
	            e.printStackTrace();   
	        }
	}
}

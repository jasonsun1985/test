/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：SafeDataUtil.java
 *  版本变更记录（可选）：修改日期2018年5月23日  上午10:34:10，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.collect.dataformat;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * @Description:
 * <p>创建日期：2018年5月23日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class SafeDataUtil {
	private static final ThreadLocal<SimpleDateFormat> dateFormat = new ThreadLocal<SimpleDateFormat>();
	public static String format(Date date){
		SimpleDateFormat sdf = dateFormat.get();
		if(sdf==null){
			System.out.println("创建SimpleDateFormat对象");
			sdf = new SimpleDateFormat("yyyy-MM-dd");  
			dateFormat.set(sdf);  
		}
		return sdf.format(date);
	}
}

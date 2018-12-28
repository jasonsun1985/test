/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：MyClassLoader.java
 *  版本变更记录（可选）：修改日期2018年1月28日  上午11:44:18，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.collect.classloader;

import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import java.nio.ByteBuffer;  
import java.nio.channels.FileChannel;  
  
public class MyClassLoader extends ClassLoader {
    /**
     * 导致jvm使用MyClassLoader来加载Object、String等等一些类。  当然，这些类在classpath是找不到的。 所以就会抛出ClassNotFoundException 
     */
//	 @Override
//	public Class<?> loadClass(String name) throws ClassNotFoundException {
//		return findClass(name);
//	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String classPath = MyClassLoader.class.getResource("/").getPath(); // 得到classpath
		System.out.println("classPath : " + classPath);
		String fileName = name.replace(".", "/") + ".class";
		System.out.println("fileName : "+ fileName);
		File classFile = new File(classPath, fileName);
		if (!classFile.exists()) {
			throw new ClassNotFoundException(classFile.getPath() + " 不存在");
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ByteBuffer bf = ByteBuffer.allocate(1024);
		FileInputStream fis = null;
		FileChannel fc = null;
		try {
			fis = new FileInputStream(classFile);
			fc = fis.getChannel();
			while (fc.read(bf) > 0) {
				bf.flip();
				bos.write(bf.array(), 0, bf.limit());
				bf.clear();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				fc.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return defineClass(bos.toByteArray(), 0, bos.toByteArray().length);
	}
}  
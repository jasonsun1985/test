/**
 *  软件版权：SUNLEI
 *  系统名称：test
 *  文件名称：IEvent.java
 *  版本变更记录（可选）：修改日期2018年5月6日  下午3:02:44，修改人SUNLEI，工单号（手填），修改描述（手填）
 */
package com.tec.rtsp;

import java.io.IOException;  
import java.nio.channels.SelectionKey;  
  
/** *//** 
* IEvent.java 网络事件处理器，当Selector可以进行操作时，调用这个接口中的方法. 
* 2007-3-22 下午03:35:51 
* @author sycheng 
* @version 1.0 
*/  
public interface IEvent {  
    /** *//** 
    * 当channel得到connect事件时调用这个方法. 
    * @param key 
    * @throws IOException 
    */  
    void connect(SelectionKey key) throws IOException;  
  
    /** *//** 
    * 当channel可读时调用这个方法. 
    * @param key 
    * @throws IOException 
    */  
    void read(SelectionKey key) throws IOException;  
  
    /** *//** 
    * 当channel可写时调用这个方法. 
    * @throws IOException 
    */  
    void write() throws IOException;  
  
    /** *//** 
    * 当channel发生错误时调用. 
    * @param e 
    */  
    void error(Exception e);  
}  
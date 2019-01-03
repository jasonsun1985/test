package com.tec.mockito;

import org.junit.Test;

import scala.Array;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
/** 
 * @Description:
 * <p>创建日期：2019年1月2日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class TestMockito {
	@Test
	public void test(){
		// mock creation
        List mockedList = mock(List.class);
        // using mock object - it does not throw any "unexpected interaction" exception
        //使用模拟对象(而不是真实创建出来的对象那)
//        mockedList.add("one");
//        mockedList.clear();
        // selective, explicit, highly readable verification
//        verify(mockedList).add("one");
//        verify(mockedList).clear();
        when(getList()).thenReturn(mockedList);
        when(mockedList.get(0)).thenReturn("first");
        System.out.println(mockedList.get(0));
	}
	public static List getList(){
		List list = new ArrayList<>();
		list.add("one");
		return list;
	}
}

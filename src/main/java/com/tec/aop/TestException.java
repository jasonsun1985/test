package com.tec.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component("test")
public class TestException {
	public static void main(String[] args) {
		ApplicationContext context =
                new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        TestException obj = (TestException) context.getBean("test");
//		_Test obj = new _Test();
        System.out.println("==========>"+obj.test());
        //System.out.println("==========>"+obj.test2());
    }

    @ErrorException(code = 100)
    public Object test(){
        System.out.println("---test---");
        int a = 10/0;
        return 20;
    }

    @ErrorException(code = 22)
    public Object test2(){
        System.out.println("---test2---");
        //int a = 10/0;
        return false;
    }
}

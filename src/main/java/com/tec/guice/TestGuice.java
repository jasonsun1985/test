package com.tec.guice;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;

/** 
 * @Description:
 * <p>创建日期：2020年1月7日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class TestGuice {
	@Inject
//	@Named("hello")
	private IHelloPrinter helloPrinter;
	public void hello() {
		helloPrinter.print();
	}
	public static void main(String[] args) {
		Injector injector = Guice.createInjector(new SimpleModule());
		TestGuice testGuice = injector.getInstance(TestGuice.class);
		testGuice.hello();
		
	}
}

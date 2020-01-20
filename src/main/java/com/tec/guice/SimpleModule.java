package com.tec.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;

/** 
 * @Description:
 * <p>创建日期：2020年1月7日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class SimpleModule extends AbstractModule {
	@Override
	protected void configure() {
//		bind(IHelloPrinter.class).annotatedWith(Names.named("hello")).to(HelloPrinter.class);
		bind(IHelloPrinter.class).to(HelloPrinter.class).in(Scopes.SINGLETON);
	}
}

package com.pattern.decorator;

/*
 * 装饰角色
 */
public class Decorator implements Component {
	private Component component;
	
	public Decorator(Component component) {
		this.component = component;
	}
	
	@Override
	public void sampleOperation() {
		// 委派给构件
		component.sampleOperation();
	}
}

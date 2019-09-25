package com.tec.stack;

public interface IStack<T> {
	//元素出栈
	public T pop();
	//元素入栈
	public void push(T element);
	//获取栈顶元素
	public T peek();
	//判断是否为空
	public boolean isEmpty();
	//清栈
	public void clear();
}

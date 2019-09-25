package com.tec.stack;

/** 
 * @Description:
 * <p>创建日期：2019年9月25日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class ArrayStackTest {
	public static void main(String[] args) {
		ArrayStack<Object> stack = new ArrayStack<Object>();
        // 入栈
		stack.push("hello");
        stack.push(6);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push("world");

        System.out.println("此时栈是否为空 stack.isEmpty()： " + stack.isEmpty());// 判断栈是否为空
        System.out.println("获取栈顶元素 stack.peek()：    " + stack.peek());// 获取栈顶元素
        System.out.println("当前栈内元素共有：    " + stack.size() + " 个");
        System.out.println("元素 stack.pop()：    " + stack.pop() + "  出栈");// 元素出栈
        System.out.println("当前栈内元素共有：    " + stack.size() + "个");
        stack.clear();
        System.out.println("stack.clear() 后当前栈内元素共有：    " + stack.size() + "个");
	}
}

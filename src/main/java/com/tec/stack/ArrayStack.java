package com.tec.stack;

import com.sun.glass.ui.Size;

import scala.Array;

/** 
 * @Description:
 * <p>创建日期：2019年9月25日 </p>
 * @version V1.0  
 * @author SUNLEI
 * @see
 */
public class ArrayStack<T> implements IStack<T> {
	private final int DEFAULT_SIZE = 6;
	private int capacity = 0;
	private int size = 0;
	//指向下一个能够添加元素的位置
	private int top = 0;
	private Object[] array;
	public ArrayStack() {
		this.capacity = DEFAULT_SIZE;
		this.array = new Object[this.capacity];
		this.size=0;
	}
	
	public ArrayStack(int capacity) {
		this.capacity = capacity;
		this.array = new Object[this.capacity];
		this.size = 0;
	}
	
	@Override
	public T pop() {
		//取出栈顶元素
		T element = (T) this.array[top-1];
		//将原栈顶元素置空
		this.array[top-1]=null;
		//将栈长度-1
		this.size--;
		return element;
	}

	@Override
	public void push(T element) {
		if (this.size < this.capacity) {
			this.array[top] = element;
			this.top++;
			this.size++;
		} else {
			enlarge();
			push(element);
		}
	}

	@Override
	public T peek() {
		return (T) this.array[this.top-1];
	}

	@Override
	public boolean isEmpty() {
		return this.size==0;
	}

	@Override
	public void clear() {
		fillArray(array, null);
        this.top = 0;
        this.size = 0;
        this.capacity = this.DEFAULT_SIZE;
        this.array = new Object[this.capacity];
	}
	
	private void enlarge() {
		this.capacity += this.DEFAULT_SIZE;
		Object[] newarray = new Object[this.capacity];
//		Array.copy(array, 0, newarray, 0, array.length);
		System.arraycopy(array, 0, newarray, 0, array.length);
		for (int i = 0; i < newarray.length; i++) {
			System.out.println("复制后的数组元素为 ：" + newarray[i]);
		}
		this.array=newarray;
		
	}
	// 向数组中装填元素
    public void fillArray(Object[] array, Object val) {
        for (int i = 0; i < array.length; i++) {
            array[i] = val;
        }
    }
    // 求出此时栈中元素的大小
    public int size() {
        return this.size;
    }
}

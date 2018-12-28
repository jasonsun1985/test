package com.tec.queue;

/** 
 * @Description：
 * <p>创建日期：2012-11-5 </p>
 * @version V1.0  
 * @author lei.sun
 * @see
 */
/**
 * 数组实现的循环队列
 * @author TongQiang
 */
public class QueueArray {
    Object[] a; //对象数组，队列最多存储a.length-1个对象
    int front;  //队首下标
    int rear;   //队尾下标
    public QueueArray(){
        this(10); //调用其它构造方法
    }
    public QueueArray(int size){
        a = new Object[size];
        front = 0;
        rear =0;
    }
    /**
     * 将一个对象追加到队列尾部
     * @param obj 对象
     * @return 队列满时返回false,否则返回true
     */
    public boolean enqueue(Object obj){
        if((rear+1)%a.length==front){
        	System.out.println("下标："+(rear+1)%a.length+" 对象："+obj.toString()+" 无法进入队列，原因队列已满");
            return false;
        }
        System.out.println("下标："+(rear+1)%a.length+"对象："+obj.toString()+" 进入队列。下标为："+rear);
        a[rear]=obj;
        rear = (rear+1)%a.length;
        return true;
    }
    /**
     * 队列头部的第一个对象出队
     * @return 出队的对象，队列空时返回null
     */
    public Object dequeue(){
        if(rear==front){
            return null;
        }
        Object obj = a[front];
        System.out.println("对象："+obj.toString()+" 出队。下标为："+front);
        front = (front+1)%a.length;
        return obj;
    }
    public static void main(String[] args) {
        QueueArray q = new QueueArray(4);
        q.enqueue("张三");
        q.enqueue("李四");
        q.enqueue("王五");
        q.enqueue("贺六");//无法入队列，队列满
        for(int i=0;i<4;i++) {
            q.dequeue();
        }
    }
}

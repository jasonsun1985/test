package com.collect.weakreference;

import java.lang.ref.WeakReference;

public class WeakReferenceCar extends WeakReference<Car> {//SoftReference SoftReference（软引用）和WeakReference的区别在于，假如下次GC的时候，发现内存没有溢出，则不会回收SoftReference关联的对象，但是对于WeakReference，在下一次GC的时候，一定会回收，无论内存是否满。

    public WeakReferenceCar(Car car) {
        super(car);
    }

    public static void main(String[] args) {
        Car car = new Car(2000.0, "red");
        WeakReferenceCar wrc = new WeakReferenceCar(car); 
        int i = 0;
        while (true) {
            if (wrc.get() != null) {
                i++;
                System.out.println("WeakReferenceCar's Car is alive for " + i + ", loop - " + wrc);
            } else {
                System.out.println("WeakReferenceCar's Car has bean collected");
                break;
            }
        }
    }
}

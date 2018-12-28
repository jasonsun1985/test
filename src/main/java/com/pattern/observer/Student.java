package com.pattern.observer;

import java.util.Observable;
import java.util.Observer;

public class Student implements Observer {
    private String time;
    @Override
    public void update(Observable o, Object arg) {
        time = (String) arg;
        Teacher teacher = (Teacher) o;
        System.out.println("学生收到作业,老师下发作业时间为： " + time);
        System.out.println("作业为: " + teacher.getHomeWork());
    }

}

package com.pattern.observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

public class TeachSendHomeWork {
    public static void main(String[] args) {
        List<Observer> listStudent = new ArrayList<Observer>();
        listStudent.add(new Student());
        listStudent.add(new Student());
        Teacher t = new Teacher(listStudent);
        t.setHomeWork("语文寒假作业");
        t.send();
    }
}

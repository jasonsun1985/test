package com.pattern.observer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Teacher extends Observable {
    private String time;
    private String homeWork;
    /**
     * @param teacher
     */
    public Teacher(List<Observer> observer) {
        super();
        for (int i = 0; i < observer.size(); i++) {
            super.addObserver(observer.get(i));
            time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        }
    }
    
    public void send(){
        this.homeWork=homeWork;
        super.setChanged();
        super.notifyObservers(this.time);
    }

    public String getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(String homeWork) {
        this.homeWork = homeWork;
    }
    
    
}

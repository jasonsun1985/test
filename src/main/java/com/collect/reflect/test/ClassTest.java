package com.collect.reflect.test;

public class ClassTest {
    private String id;
    private String name;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        System.out.println("get name!");
        return name;
    }
    public void setName(String name) {
        System.out.println("set name!");
        this.name = name;
    }
    
}

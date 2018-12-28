package com.collect.ws;

import com.collect.ws.autoGenerate.HelloWebservice;
import com.collect.ws.autoGenerate.HelloWebserviceService;

public class TestWSFinal {
    public static void main(String[] args) {
        HelloWebservice hello = new HelloWebserviceService().getHelloWebservicePort();
        String name = hello.getValue("SL");
        System.out.println(name);
    }
}

package com.collect.ws;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class HelloWebservice {
    public String getValue(String name){
        return "My name is "+name;
        
    }
    public static void main(String[] args) {
        //http://localhost:8080/service/serviceHello?wsdl
        Endpoint.publish("http://localhost:8080/service/serviceHello", new HelloWebservice());
    }
}
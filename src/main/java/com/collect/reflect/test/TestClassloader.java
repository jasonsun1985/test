package com.collect.reflect.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestClassloader {

    public TestClassloader() {
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, InstantiationException {
       Class.forName("com.mysql.jdbc.Driver");
       String dbUrl = "jdbc:mysql://127.0.0.1:3306/mysql?useUnicode=true&amp;characterEncoding=UTF-8";
       Connection conn=DriverManager.getConnection(dbUrl,"root","root");
       Statement stmt=conn.createStatement();
       ResultSet rs = stmt.executeQuery("select * from classloader");
       while(rs.next()){
           System.out.println(rs.getString("class"));
           Class cls = ClassLoader.getSystemClassLoader().loadClass(rs.getString("class"));
           Method m1 = cls.getMethod("setName", String.class);
           m1.invoke(cls.newInstance(),"aaa");
           System.out.println(cls.getMethod("setName", String.class));
           System.out.println(cls.getMethod("getName"));
           Method m2 = cls.getMethod("getName");
           m2.invoke(cls.newInstance());
       }
    }

}

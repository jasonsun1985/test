package com.tec.generic;

public class NewGeneric {
    public static <T> void out(T... args) {
        for (T t : args) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        out("findingsea", 123, 11.11, true);
        out("start");
        out(null,null,"start");
    }
}

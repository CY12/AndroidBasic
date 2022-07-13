package com.example.apt_library;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class MyAptApi {

    @SuppressWarnings("all")
    public static void init() {
        try {
            Class c = Class.forName("com.example.space.HelloWorld");
            Constructor declaredConstructor = c.getDeclaredConstructor();
            Object o = declaredConstructor.newInstance();
            Method test = c.getDeclaredMethod("test", String.class);
            test.invoke(o, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


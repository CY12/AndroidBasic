package com.example.space.proxy;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    private Object object;
    public ProxyHandler(Object object){
        this.object = object;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Log.e("Test","Before invoke " + method.getName());
        method.invoke(object, args);
        Log.e("Test","After invoke " + method.getName());
        return null;
    }
}

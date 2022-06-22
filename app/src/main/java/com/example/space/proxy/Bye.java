package com.example.space.proxy;


import android.util.Log;

public class Bye implements ByeInterface {
    @Override
    public void sayBye() {
        Log.e("Test","Bye Bye sd!");
    }

    @Override
    public void sayHello() {
        Log.e("Test","Bye Bye hello!");
    }


}


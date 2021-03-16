package com.example.space.thinking;

public class Single {

    private static volatile Single single;

    public Single (){};

    public Single getSingleInstance(){
        if (single == null){
            synchronized (Single.class){
                if (single == null){
                    single = new Single();
                }
            }
        }
        return single;
    }
}

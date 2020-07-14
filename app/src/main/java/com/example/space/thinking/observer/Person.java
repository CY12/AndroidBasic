package com.example.space.thinking.observer;

import android.util.Log;

public class Person {

    private String news;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNews() {
        return news;
    }

    public Person(String name){
        this.name=name;
    }

    public void sayNews(String s){
        Log.d("Test",name+"sayNews:"+s);
        if (iNews!=null){
            iNews.publish(name+": "+s+"\n");
        }
    }

    public void setNews(String news) {
        this.news = news;
    }

    private INews iNews;

    public void setiNews(INews iNews){
        this.iNews=iNews;
    }
}

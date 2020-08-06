package com.example.space.thinking.observer;


import android.util.Log;

import java.util.HashSet;
import java.util.Set;

public class News {
    private Set<Person> personSet = new HashSet<>();

    public void subscribe(Person person) {
        personSet.add(person);
    }

    public void cancelSubscribe(Person person) {
        personSet.remove(person);
    }

    public void notifyAllPeople(String s) {
        Log.d("Test", "notify" + s + "  人数:" + personSet.size());
        for (Person p : personSet) {
            p.sayNews(s);
        }
    }

}

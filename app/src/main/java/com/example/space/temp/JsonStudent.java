package com.example.space.temp;

import java.util.List;

public class JsonStudent {
    private String name;
    private String location;
    private boolean man;
    private Float age;
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Float getAge() {
        return age;
    }

    public void setAge(Float age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isMan() {
        return man;
    }

    public void setMan(boolean man) {
        this.man = man;
    }

    @Override
    public String toString() {
        return "JsonStudent{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", man=" + man +
                ", age=" + age +
                ", list=" + list +
                '}';
    }
}

package com.example.space.temp;

public class JsonStudent {
    private String name;
    private String location;
    private boolean man;

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
                '}';
    }
}

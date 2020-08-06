package com.example.space.databinding;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.space.BR;

public class Student extends BaseObservable {
    private String name;
    private String addr;
    @Bindable
    public String sex;


    public Student() {
    }

    public Student(String name, String addr, String sex) {
        this.name = name;
        this.addr = addr;
        this.sex = sex;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getAddr() {
        return this.addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
        notifyPropertyChanged(BR.addr);
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }


    public void resetAll(Student student) {
        this.name = student.name;
        this.sex = student.sex;
        this.addr = student.addr;
        notifyChange();
    }
}

package com.example.space.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Student {
    @Id(autoincrement = true)
    private Long id;
    @Unique
    private String sNode;
    private String name;
    private String sex;
    private String grade;
    @Generated(hash = 1928863968)
    public Student(Long id, String sNode, String name, String sex, String grade) {
        this.id = id;
        this.sNode = sNode;
        this.name = name;
        this.sex = sex;
        this.grade = grade;
    }
    @Generated(hash = 1556870573)
    public Student() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSNode() {
        return this.sNode;
    }
    public void setSNode(String sNode) {
        this.sNode = sNode;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getGrade() {
        return this.grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student"+"name"+name+"id";
    }
}

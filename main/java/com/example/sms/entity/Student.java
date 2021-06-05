package com.example.sms.entity;


/**
 * @author: LiQingLin
 * @date: 2021/6/4 11:27
 * @description:
 */

public class Student {

    private int sId;
    private String name;
    private String sex;
    private int age;
    private String password;

    public Student(int sId, String password) {
        this.sId = sId;
        this.password = password;
    }

    public Student(String name, String sex, int age, String password) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.password = password;
    }

    public Student() {

    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

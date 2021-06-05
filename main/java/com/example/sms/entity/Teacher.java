package com.example.sms.entity;


/**
 * @author: LiQingLin
 * @date: 2021/6/4 11:25
 * @description:
 */

public class Teacher {

    private int tId;
    private String name;
    private String sex;
    private String title;
    private String password;

    public Teacher(int tId, String password) {
        this.tId = tId;
        this.password = password;
    }

    public Teacher(String name, String sex, String title, String password) {
        this.name = name;
        this.sex = sex;
        this.title = title;
        this.password = password;
    }

    public Teacher() {
    }

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

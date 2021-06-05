package com.example.sms.entity;


/**
 * @author: LiQingLin
 * @date: 2021/6/4 13:50
 * @description:
 */

public class Course {

    private int courseNo;
    private String name;
    private int time;

    public Course() {
    }

    public Course(String name, int time) {
        this.name = name;
        this.time = time;
    }

    public int getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

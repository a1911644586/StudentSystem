package com.example.stu.studentsystem;

import java.io.Serializable;

public class Student implements Serializable {
    private long id;
    private String name;
    private int age;
    private String sex;
    private String profession;
    private String score;
    private String trainDate;
    private String modifyDateTime;
    public Student() {
        super();
    }
    public Student(long id, String name, int age, String sex, String profession, String score,
                   String trainDate, String modifyDateTime) {
        super();
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.profession = profession;
        this.score = score;
        this.trainDate = trainDate;
        this.modifyDateTime = modifyDateTime;
    }
    public Student(String name, int age, String sex, String profession, String score,
                   String trainDate, String modifyDateTime) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.profession = profession;
        this.score = score;
        this.trainDate = trainDate;
        this.modifyDateTime = modifyDateTime;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSex() {
        return sex;     }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getProfession() {
        return profession;
    }
    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getScore() {
        return score;
    }
    public void setScore(String score) {
        this.score = score;     }
    public String getTrainDate() {
        return trainDate;
    }
    public void setTrainDate(String trainDate) {
        this.trainDate = trainDate;
    }
    public String getModifyDateTime() {
        return modifyDateTime;
    }
    public void setModifyDateTime(String modifyDateTime) {
        this.modifyDateTime = modifyDateTime;
    }

}

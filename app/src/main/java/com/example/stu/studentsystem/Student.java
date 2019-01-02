package com.example.stu.studentsystem;

import java.io.Serializable;

public class Student implements Serializable {
    private long id;
    private String name;
    private String grade;
    private String sex;
    private String profession;
    private String score;
    public Student() {
        super();
    }
    public Student(long id,String name, String grade, String profession, String score) {
        super();
        this.name = name;
        this.grade = grade;
        this.profession = profession;
        this.score = score;
    }
    public Student(String name, String grade, String sex, String profession, String score) {
        super();
        this.name = name;
        this.grade = grade;
        this.sex = sex;
        this.profession = profession;
        this.score = score;
    }
    public Student(long id, String name, String grade, String sex, String profession, String score) {
        super();
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.sex = sex;
        this.profession = profession;
        this.score = score;
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
    public String getGrade() {
        return grade;
    }
    public void setGrade(String grade) {
        this.grade = grade;
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

}

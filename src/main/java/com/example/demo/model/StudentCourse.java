package com.example.demo.model;

public class StudentCourse {


    private int id;
    private String student_name;
    private String course_name;

    public StudentCourse() {
    }

    public StudentCourse(String course_name) {
        this.course_name = course_name;
    }

    public StudentCourse(String student_name, String course_name) {
        this.student_name = student_name;
        this.course_name = course_name;
    }

    public StudentCourse(int id, String student_name, String course_name) {
        this.id = id;
        this.student_name = student_name;
        this.course_name = course_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }


}

package com.example.demo.model;

public class InstructorCourse {

    private int id;
    private String instructor_name;
    private String course_name;

    public InstructorCourse() {
    }

    public InstructorCourse(String instructor_name, String course_name) {
        this.instructor_name = instructor_name;
        this.course_name = course_name;
    }

    public InstructorCourse(int id, String instructor_name, String course_name) {
        this.id = id;
        this.instructor_name = instructor_name;
        this.course_name = course_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstructor_name() {
        return instructor_name;
    }

    public void setInstructor_name(String instructor_name) {
        this.instructor_name = instructor_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String showInstructor_courseQuery() {
        return "SELECT instructor_course.id, instructor.name,instructor.family, course.name\n" +
                "FROM ((instructor_course\n" +
                "INNER JOIN instructor ON instructor_course.insID = instructor.id)\n" +
                "INNER JOIN course ON instructor_course.CID = course.id);";

    }

}

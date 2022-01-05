package com.example.demo.model;

public class Student {
    private String name;
    private String family;
    private String NC;
    private String SID;

    public Student(String name, String family, String NC, String SID) {
        this.name = name;
        this.family = family;
        this.NC = NC;
        this.SID = SID;
    }

    public Student() {
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getNC() {
        return NC;
    }

    public void setNC(String NC) {
        this.NC = NC;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getRegisterQuery() {
        return "INSERT INTO student(name, family, NC, SID) VALUES ('" + name + "','" + family + "','" + NC + "','" + SID + "');";
    }

}

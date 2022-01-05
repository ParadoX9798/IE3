package com.example.demo.model;

public class Instructor {

    private int id;
    private String name;
    private String family;
    private String NC;

    public Instructor(int id, String name, String family, String NC) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.NC = NC;
    }

    public Instructor(String name, String family, String NC) {
        this.name = name;
        this.family = family;
        this.NC = NC;
    }

    public Instructor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRegisterQuery() {
        return "INSERT INTO instructor(name, family, NC) VALUES ('" + name + "','" + family + "','" + NC + "');";
    }

    public String showInstructorsQuery() {
        return "SELECT * FROM instructor";
    }
}

package com.example.demo.service;

import com.example.demo.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Lazy
public class EduService {
    IEDatabaseManager ie ;

    @Autowired
    public EduService(IEDatabaseManager ie){
        this.ie = ie;
    }
    public String registerServiceInstructor(String name, String family, String NC) {
        return  ie.registerInstructor(new Instructor(name,family, NC));
    }

    public String registerServiceStudent(String name, String family, String NIC, String SID) {
        return  ie.registerStudent(new Student(name,family,NIC,SID));
    }
    public String assignServiceCourse(int CID,int IID) {
        return ie.assignCourse(new Course(CID,""), new Instructor(IID,"","",""));
    }
    public String registerServiceCourse(String Cname) {
        return ie.registerCourse(new Course(Cname.toLowerCase()));
    }

    public String takeServiceCourse(String SID, int r_ID) {
        return ie.takeCourse(new Student("","","",SID),r_ID);
    }

    public List<Instructor> showServiceInstructor(){
        return ie.showInstructors(new Instructor());
    }
    public List<Course> showServiceCourse(){
        return ie.showCourse(new Course());
    }
    public List<InstructorCourse> showServiceInstructorCourse(){
        return ie.showInstructorCourse(new InstructorCourse());
    }
    public List<InstructorCourse> scheduleServiceInstructorCourse(String NC){
        return ie.showInstructorCourse(NC);
    }

    public List<StudentCourse> scheduleServiceStudentCourse(String SID){
        return ie.showStudentCourse(SID);
    }
}

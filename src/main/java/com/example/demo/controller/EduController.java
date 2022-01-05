package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Instructor;
import com.example.demo.model.InstructorCourse;
import com.example.demo.model.StudentCourse;
import com.example.demo.service.EduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class EduController {

    EduService es;

    @Autowired
    public EduController(EduService es) {
        this.es = es;
    }

    @GetMapping("/register_instructor")
    public String registerFunctionInstructor(@RequestParam String name, @RequestParam String family, @RequestParam String NC) {
        return es.registerServiceInstructor(name, family, NC);
    }

    @GetMapping("/register_student")
    public String registerFunctionStudent(@RequestParam String name, @RequestParam String family
            , @RequestParam String NC, @RequestParam String SID) {
        return es.registerServiceStudent(name, family, NC, SID);
    }

    @GetMapping("/register_course")
    public String registerFunctionCourse(@RequestParam String Cname) {
        return es.registerServiceCourse(Cname);
    }


    @GetMapping("/assign_course")
    public String registerFunctionCourse(@RequestParam int CID, @RequestParam int IID) {
        return es.assignServiceCourse(CID, IID);
    }

    @GetMapping("/take_course")
    public String TakeFunctionCourse(@RequestParam String SID, @RequestParam int ID) {
        return es.takeServiceCourse(SID, ID);
    }


    @GetMapping("")
    public ModelAndView showInstructors(ModelAndView mav) {
        List<Instructor> list_instructors = es.showServiceInstructor();
        List<Course> list_courses = es.showServiceCourse();
        List<InstructorCourse> list_instructor_course = es.showServiceInstructorCourse();
        mav.addObject("instructors", list_instructors);
        mav.addObject("courses", list_courses);
        mav.addObject("instructor_course", list_instructor_course);
        mav.setViewName("index");
        return mav;
    }

    @GetMapping("show_schedule_instructor")
    public ModelAndView showScheduleInstructor(ModelAndView mav,@RequestParam String NC) {
        List<InstructorCourse> list = es.scheduleServiceInstructorCourse(NC);
        mav.addObject("schedule", list);
        mav.setViewName("schedule");
        return mav;
    }

    @GetMapping("show_schedule_student")
    public ModelAndView showScheduleStudent(ModelAndView mav,@RequestParam String SID) {
        List<StudentCourse> list = es.scheduleServiceStudentCourse(SID);
        mav.addObject("schedule", list);
        mav.setViewName("schedule");
        return mav;
    }


}

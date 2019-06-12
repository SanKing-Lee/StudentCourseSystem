package com.sean.scs.controller;

import com.sean.scs.dao.CourseDAO;
import com.sean.scs.dao.SelectCourseDAO;
import com.sean.scs.dao.StudentDAO;
import com.sean.scs.entity.Course;
import com.sean.scs.entity.Student;
import com.sean.scs.relationship.SelectCourse;
import com.sean.scs.service.SelectCourseService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Sean
 * Date: Created In 0:12 2019/6/8
 * Title:
 * Description:
 * Version: 0.1
 * Update History:
 * [Date][Version][Author] What has been done;
 */

@RestController
@RequestMapping("/sc")
public class SelectCourseController {
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private SelectCourseDAO selectCourseDAO;
    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private SelectCourseService selectCourseService;

    @GetMapping("/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("/sc/index");
        return modelAndView;
    }

    @GetMapping("/select")
    public ModelAndView select(ModelAndView modelAndView, HttpSession httpSession) {
        Object user = httpSession.getAttribute("user");
        if(!(user instanceof Student)){
            modelAndView.addObject("error", "非学生无法进行该操作！");
            modelAndView.setViewName("/index");
            return modelAndView;
        }
        Student student = (Student)user;
        List<Course> courses = selectCourseService.getUnselectedCourses(student.getId());
        modelAndView.getModelMap().put("courses", courses);
        modelAndView.setViewName("/sc/select");
        return modelAndView;
    }

    @RequestMapping("/select")
    public ModelAndView select(ModelAndView modelAndView, HttpSession httpSession, String cid) {

        Student student =(Student)httpSession.getAttribute("user");
        Course selectedCourse = courseDAO.findById(cid).orElse(null);
        if (student == null || selectedCourse == null) {
            modelAndView.setViewName("/sc/select");
            return modelAndView;
        }
        selectCourseDAO.save(new SelectCourse(student, selectedCourse));
        modelAndView.addObject("courses", selectCourseService.getUnselectedCourses(student.getId()));
        modelAndView.setViewName("/sc/select");
        return modelAndView;
    }

    @GetMapping("/withdraw")
    public ModelAndView withdraw(ModelAndView modelAndView) {
        List<Course> courses = selectCourseService.getSelectedCourses("s1");
        modelAndView.addObject("courses", courses);
        modelAndView.setViewName("/sc/withdraw");
        return modelAndView;
    }

    @RequestMapping("/withdraw")
    public ModelAndView withdraw(ModelAndView modelAndView, String cid){
        Student student = studentDAO.findById("s1").orElse(null);
        Course selectedCourse = courseDAO.findById(cid).orElse(null);
        if (student == null || selectedCourse == null) {
            modelAndView.setViewName("/sc/withdraw");
            return modelAndView;
        }
        selectCourseDAO.deleteByStudentIdAndCourseId(student.getId(), selectedCourse.getId());
        modelAndView.addObject("courses", selectCourseService.getSelectedCourses("s1"));
        modelAndView.setViewName("/sc/withdraw");
        return modelAndView;
    }
}

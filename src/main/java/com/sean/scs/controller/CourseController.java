package com.sean.scs.controller;

import com.sean.scs.dao.CourseDAO;
import com.sean.scs.dao.TeacherDAO;
import com.sean.scs.entity.Course;
import com.sean.scs.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private TeacherDAO teacherDAO;

    @GetMapping("/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("/course/index");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView){
        modelAndView.setViewName("/course/add");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView add(ModelAndView modelAndView, String id, String name,
                            String dept, Double credits, String teacherId) {
        Course course = new Course(id, name, dept, credits);
        Teacher teacher = teacherDAO.findById(teacherId).orElse(null);
        if(teacher == null){
            modelAndView.addObject("error", "未找到该教师！");
            modelAndView.setViewName("course/add");
            return modelAndView;
        }
        course.setTeacher(teacher);
        courseDAO.save(course);
        modelAndView.setViewName("course/add");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(ModelAndView modelAndView){
        modelAndView.setViewName("/course/delete");
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView delete(ModelAndView modelAndView, String id) {
        Course course = courseDAO.findById(id).orElse(null);
        if (course == null) {
            modelAndView.addObject("error", "未找到该课程");
            return modelAndView;
        }
        courseDAO.delete(course);
        modelAndView.setViewName("course/delete");
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(ModelAndView modelAndView){
        modelAndView.setViewName("/course/search");
        return modelAndView;
    }

    @PostMapping("/search")
    public ModelAndView search(ModelAndView modelAndView, String id) {
        Course course = courseDAO.findById(id).orElse(null);
        if (course == null) {
            modelAndView.addObject("error", "未找到该课程！");
            modelAndView.setViewName("course/search");
            return modelAndView;
        }
        modelAndView.addObject("course", course);
        modelAndView.setViewName("course/searchResult");
        return modelAndView;
    }

    @GetMapping("/update")
    public ModelAndView update(ModelAndView modelAndView){
        modelAndView.setViewName("/course/update");
        return modelAndView;
    }

    @PostMapping("/update")
    public ModelAndView update(ModelAndView modelAndView, String id, String name,
                               String dept, Double credits, String teacherId) {
        Course course = courseDAO.findById(id).orElse(null);
        if (course == null) {
            modelAndView.addObject("error", "未找到该课程");
            modelAndView.setViewName("/course/update");
            return modelAndView;
        }
        course.setName(name);
        course.setDept(dept);
        course.setCredits(credits);
        Teacher teacher = teacherDAO.findById(teacherId).orElse(null);
        if(teacher == null){
            modelAndView.addObject("error", "未找到该教师！");
            modelAndView.setViewName("course/update");
            return modelAndView;
        }
        course.setTeacher(teacher);
        courseDAO.save(course);
        modelAndView.setViewName("/course/update");
        return modelAndView;
    }
}

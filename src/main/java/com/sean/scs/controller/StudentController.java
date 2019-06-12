package com.sean.scs.controller;

import com.sean.scs.dao.StudentDAO;
import com.sean.scs.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: Sean
 * Date: Created In 16:49 2019/6/12
 * Title:
 * Description:
 * Version: 0.1
 * Update History:
 * [Date][Version][Author] What has been done;
 */

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("/student/index");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView) {
        modelAndView.setViewName("/student/add");
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(ModelAndView modelAndView, String id, String name,
                            String dept, String gender, Integer age) {
        studentDAO.save(new Student(id, name, dept, age, gender));
        modelAndView.setViewName("student/add");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(ModelAndView modelAndView) {
        modelAndView.setViewName("/student/delete");
        return modelAndView;
    }

    @RequestMapping("/delete")
    public ModelAndView delete(ModelAndView modelAndView, String id) {
        Student student = studentDAO.findById(id).orElse(null);
        if (student == null) {
            modelAndView.addObject("error", "未找到该学生");
            return modelAndView;
        }
        studentDAO.delete(student);
        modelAndView.setViewName("student/delete");
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(ModelAndView modelAndView) {
        modelAndView.setViewName("/student/search");
        return modelAndView;
    }

    @RequestMapping("/search")
    public ModelAndView search(ModelAndView modelAndView, String id) {
        Student student = studentDAO.findById(id).orElse(null);
        if (student == null) {
            modelAndView.addObject("error", "未找到该学生！");
            modelAndView.setViewName("student/search");
            return modelAndView;
        }
        modelAndView.addObject("student", student);
        modelAndView.setViewName("student/searchResult");
        return modelAndView;
    }

    @GetMapping("/update")
    public ModelAndView update(ModelAndView modelAndView) {
        modelAndView.setViewName("/student/update");
        return modelAndView;
    }

    @RequestMapping("/update")
    public ModelAndView update(ModelAndView modelAndView, String id, String name,
                               String dept, String gender, Integer age) {
        Student student = studentDAO.findById(id).orElse(null);
        if (student == null) {
            modelAndView.addObject("error", "未找到该学生");
            modelAndView.setViewName("/student/update");
            return modelAndView;
        }
        student.setName(name);
        student.setDept(dept);
        student.setGender(gender);
        student.setAge(age);
        studentDAO.save(student);
        modelAndView.setViewName("student/add");
        return modelAndView;
    }
}

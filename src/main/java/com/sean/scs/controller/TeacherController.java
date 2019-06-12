package com.sean.scs.controller;

import com.sean.scs.dao.TeacherDAO;
import com.sean.scs.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author: Sean
 * Date: Created In 19:29 2019/6/12
 * Title:
 * Description:
 * Version: 0.1
 * Update History:
 * [Date][Version][Author] What has been done;
 */

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherDAO teacherDAO;

    @GetMapping("/index")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("/teacher/index");
        return modelAndView;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView modelAndView) {
        modelAndView.setViewName("/teacher/add");
        return modelAndView;
    }

    @RequestMapping("/add")
    public ModelAndView add(ModelAndView modelAndView, String id, String name,
                            String dept, Double salary) {
        teacherDAO.save(new Teacher(id, name, dept, salary));
        modelAndView.setViewName("teacher/add");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView delete(ModelAndView modelAndView) {
        modelAndView.setViewName("/teacher/delete");
        return modelAndView;
    }

    @RequestMapping("/delete")
    public ModelAndView delete(ModelAndView modelAndView, String id) {
        Teacher teacher = teacherDAO.findById(id).orElse(null);
        if (teacher == null) {
            modelAndView.addObject("error", "未找到该老师！");
            return modelAndView;
        }
        teacherDAO.delete(teacher);
        modelAndView.setViewName("teacher/delete");
        return modelAndView;
    }

    @GetMapping("/search")
    public ModelAndView search(ModelAndView modelAndView) {
        modelAndView.setViewName("/teacher/search");
        return modelAndView;
    }

    @RequestMapping("/search")
    public ModelAndView search(ModelAndView modelAndView, String id) {
        Teacher teacher = teacherDAO.findById(id).orElse(null);
        if (teacher == null) {
            modelAndView.addObject("error", "未找到该学生！");
            modelAndView.setViewName("teacher/search");
            return modelAndView;
        }
        modelAndView.addObject("teacher", teacher);
        modelAndView.setViewName("teacher/searchResult");
        return modelAndView;
    }

    @GetMapping("/update")
    public ModelAndView update(ModelAndView modelAndView) {
        modelAndView.setViewName("/teacher/update");
        return modelAndView;
    }

    @RequestMapping("/update")
    public ModelAndView update(ModelAndView modelAndView, String id, String name,
                               String dept, Double salary) {
        Teacher teacher = teacherDAO.findById(id).orElse(null);
        if (teacher == null) {
            modelAndView.addObject("error", "未找到该学生");
            modelAndView.setViewName("/teacher/update");
            return modelAndView;
        }
        teacher.setName(name);
        teacher.setDept(dept);
        teacher.setSalary(salary);
        teacherDAO.save(teacher);
        modelAndView.setViewName("teacher/add");
        return modelAndView;
    }
}

package com.sean.scs.controller;

import com.sean.scs.dao.StudentDAO;
import com.sean.scs.dao.TeacherDAO;
import com.sean.scs.entity.Student;
import com.sean.scs.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Author: Sean
 * Date: Created In 13:27 2019/6/12
 * Title:
 * Description:
 * Version: 0.1
 * Update History:
 * [Date][Version][Author] What has been done;
 */

@RestController
public class Controller {
    @Autowired
    private TeacherDAO teacherDAO;
    @Autowired
    private StudentDAO studentDAO;

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping("/index")
    public ModelAndView index(ModelAndView modelAndView, HttpSession httpSession, @Valid Integer identity, @Valid String id){
        if(identity==1){
            Teacher teacher = teacherDAO.findById(id).orElse(null);
            if(teacher==null){
                modelAndView.addObject("error", "未找到该用户！");
                modelAndView.setViewName("login");
                return modelAndView;
            }
            httpSession.setAttribute("user", teacher);
        }
        else{
            Student student = studentDAO.findById(id).orElse(null);
            if(student==null){
                modelAndView.addObject("error", "未找到该用户！");
                modelAndView.setViewName("login");
                return modelAndView;
            }
            httpSession.setAttribute("user", student);
        }
        modelAndView.setViewName("index");
        return modelAndView;
    }
}

package com.sean.scs.controller;

import com.sean.scs.dao.CourseDAO;
import com.sean.scs.dao.SelectCourseDAO;
import com.sean.scs.entity.Course;
import com.sean.scs.entity.Teacher;
import com.sean.scs.relationship.SelectCourse;
import com.sean.scs.service.SelectCourseService;
import org.hibernate.sql.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Author: Sean
 * Date: Created In 16:06 2019/6/16
 * Title:
 * Description:
 * Version: 0.1
 * Update History:
 * [Date][Version][Author] What has been done;
 */

@RestController
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private SelectCourseService selectCourseService;
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private SelectCourseDAO selectCourseDAO;

    private Teacher teacher;

    @GetMapping("/index")
    public ModelAndView index(ModelAndView modelAndView, HttpSession httpSession){
        Object user = httpSession.getAttribute("user");
        if(!(user instanceof Teacher)){
            modelAndView.addObject("error", "非教师账号无法进行该类操作！");
            modelAndView.setViewName("/index");
            return modelAndView;
        }
        teacher = (Teacher)user;
        modelAndView.setViewName("/grade/index");
        return modelAndView;
    }

    @GetMapping("/set")
    public ModelAndView setGrade(ModelAndView modelAndView){
        // 获得这些课程的选课记录
        List<SelectCourse> selectCourses = selectCourseService.getTeachCoursesByCourseIds(teacher.getId());
        // 将这些选课记录放入modelAndView由前端进行显示
        modelAndView.addObject("selectCourses", selectCourses);
        modelAndView.setViewName("/grade/set");
        return modelAndView;
    }

    @PostMapping("/set")
    public ModelAndView setGrade(ModelAndView modelAndView, Integer grade, Long scId){
        System.out.println(grade + scId);
        // 保存成绩
        SelectCourse selectCourse = selectCourseDAO.findById(scId).orElse(null);
        if(selectCourse != null) {
            selectCourse.setGrade(grade);
            selectCourseDAO.save(selectCourse);
        }
        else{
            modelAndView.addObject("error", "选课记录无效！");
        }
        // 获得这些课程的选课记录
        List<SelectCourse> selectCourses = selectCourseService.getTeachCoursesByCourseIds(teacher.getId());
        // 将这些选课记录放入modelAndView由前端进行显示
        modelAndView.addObject("selectCourses", selectCourses);
        modelAndView.setViewName("grade/set");
        return modelAndView;
    }
}

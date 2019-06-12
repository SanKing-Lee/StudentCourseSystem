package com.sean.scs.service;

import com.sean.scs.dao.CourseDAO;
import com.sean.scs.dao.SelectCourseDAO;
import com.sean.scs.dao.StudentDAO;
import com.sean.scs.entity.Course;
import com.sean.scs.entity.Student;
import com.sean.scs.relationship.SelectCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Sean
 * Date: Created In 0:56 2019/6/13
 * Title:
 * Description:
 * Version: 0.1
 * Update History:
 * [Date][Version][Author] What has been done;
 */

@Service
public class SelectCourseService {
    @Autowired
    private SelectCourseDAO selectCourseDAO;
    @Autowired
    private CourseDAO courseDAO;
    @Autowired
    private StudentDAO studentDAO;

    public List<Course> getUnselectedCourses(String id){
        List<Course> courses = courseDAO.findAll();
        List<SelectCourse> selectCourses = selectCourseDAO.findByStudentId(id);
        for(SelectCourse selectCourse:selectCourses){
            courses.remove(selectCourse.getCourse());
        }
        return courses;
    }

    public List<Course> getSelectedCourses(String id){
        List<Course> courses = new ArrayList<>();

        List<SelectCourse> selectCourses = selectCourseDAO.findByStudentId(id);
        for(SelectCourse selectCourse:selectCourses){
            courses.add(selectCourse.getCourse());
        }
        return courses;
    }
}

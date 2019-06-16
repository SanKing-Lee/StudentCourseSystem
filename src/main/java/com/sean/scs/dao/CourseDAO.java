package com.sean.scs.dao;

import com.sean.scs.entity.Course;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Author: Sean
 * Date: Created In 16:42 2019/5/13
 * Title:
 * Description:
 * Version: 0.1
 * Update History:
 * [Date][Version][Author] What has been done;
 */

public interface  CourseDAO extends JpaRepository<Course, String>{
    @Query(value = "select * from course where teacher_id = ?1", nativeQuery = true)
    public List<Course> findCoursesByTeacher(String teacherId);
}

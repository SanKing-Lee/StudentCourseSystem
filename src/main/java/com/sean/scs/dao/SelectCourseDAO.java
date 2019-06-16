package com.sean.scs.dao;

import com.sean.scs.relationship.SelectCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Author: Sean
 * Date: Created In 13:09 2019/6/12
 * Title:
 * Description:
 * Version: 0.1
 * Update History:
 * [Date][Version][Author] What has been done;
 */

public interface SelectCourseDAO extends JpaRepository<SelectCourse, Long> {
    @Query(value = "select * from sc where student_id = ?1", nativeQuery = true)
    public List<SelectCourse> findByStudentId(String student_id);

    @Query(value = "select * from sc where course_id = ?1", nativeQuery = true)
    public List<SelectCourse> findByCourseId(String courseId);

    @Modifying
    @Transactional
    @Query(value = "delete from sc where student_id=?1 and course_id = ?2", nativeQuery = true)
    public void deleteByStudentIdAndCourseId(String studentId, String courseId);
}

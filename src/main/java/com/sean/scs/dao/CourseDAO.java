package com.ex4.dao;

import com.ex4.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Sean
 * Date: Created In 16:42 2019/5/13
 * Title:
 * Description:
 * Version: 0.1
 * Update History:
 * [Date][Version][Author] What has been done;
 */

public interface CourseDAO extends JpaRepository<Course, Long> {
}

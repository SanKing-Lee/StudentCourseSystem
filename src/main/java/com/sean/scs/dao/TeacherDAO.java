package com.ex4.dao;

import com.ex4.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author: Sean
 * Date: Created In 16:55 2019/5/13
 * Title:
 * Description:
 * Version: 0.1
 * Update History:
 * [Date][Version][Author] What has been done;
 */

public interface TeacherDAO extends JpaRepository<Teacher, Long> {
}
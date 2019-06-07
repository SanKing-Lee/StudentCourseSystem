package com.ex4.relationship;

import com.ex4.entity.Course;
import com.ex4.entity.Student;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Author: Sean
 * Date: Created In 16:40 2019/5/13
 * Title:
 * Description:
 * Version: 0.1
 * Update History:
 * [Date][Version][Author] What has been done;
 */

@Entity(name = "sc")
public class SelectCourse implements Serializable {
    @Id
    @ManyToOne
    private Student student;

    @Id
    @ManyToOne
    private Course course;

    private int grade;

    public SelectCourse() {
    }

    public SelectCourse( int grade) {
        this.grade = grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SelectCourse that = (SelectCourse) o;
        return grade == that.grade &&
                Objects.equals(student, that.student) &&
                Objects.equals(course, that.course);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, course, grade);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}

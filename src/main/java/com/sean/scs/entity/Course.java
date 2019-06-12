package com.sean.scs.entity;

import com.sean.scs.relationship.SelectCourse;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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

@Entity(name = "course")
public class Course implements Serializable {
    @Id
    @Column(name = "id", length = 100)
    private String id;

    private String name;
    private String dept;
    private double credits;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SelectCourse> students = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "teacher_id", foreignKey = @ForeignKey(name = "TEACHER_ID_FK"))
    private Teacher teacher;

    public Course() {
    }

    public Course(String id, String name, String dept, double credits) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.credits = credits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Double.compare(course.credits, credits) == 0 &&
                Objects.equals(id, course.id) &&
                Objects.equals(name, course.name) &&
                Objects.equals(dept, course.dept) &&
                Objects.equals(students, course.students);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dept, credits, students);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}

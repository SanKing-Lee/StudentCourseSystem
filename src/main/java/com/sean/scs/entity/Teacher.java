package com.ex4.entity;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: Sean
 * Date: Created In 16:55 2019/5/13
 * Title:
 * Description:
 * Version: 0.1
 * Update History:
 * [Date][Version][Author] What has been done;
 */

@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {
    @Id
    private Long id;

    @NaturalId
    @Column(name = "tid", length = 100)
    private String tid;
    private String tname;
    private String dept;
    private double salary;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Course> courses = new ArrayList<>();

    public Teacher() {
    }

    public Teacher(String tid, String tname, String dept, double salary) {
        this.tid = tid;
        this.tname = tname;
        this.dept = dept;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Double.compare(teacher.salary, salary) == 0 &&
                Objects.equals(id, teacher.id) &&
                Objects.equals(tid, teacher.tid) &&
                Objects.equals(tname, teacher.tname) &&
                Objects.equals(dept, teacher.dept) &&
                Objects.equals(courses, teacher.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tid, tname, dept, salary, courses);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

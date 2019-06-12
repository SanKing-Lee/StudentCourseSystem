package com.sean.scs.entity;

import com.sean.scs.relationship.SelectCourse;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
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

@Entity(name = "student")
public class Student {
    @Id
    @Column(name = "id", length = 100)
    private String id;

    @Column(name = "name")
    private String name;
    @Column(name = "dept")
    private String dept;
    @Column(name = "age")
    private int age;
    @Column(name = "gender")
    private String gender;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<SelectCourse> courses = new ArrayList<>();

    public Student() {
    }

    public Student(String id, String name, String dept, int age, String gender) {
        this.id = id;
        this.name = name;
        this.dept = dept;
        this.age = age;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(id, student.id) &&
                Objects.equals(name, student.name) &&
                Objects.equals(dept, student.dept) &&
                Objects.equals(gender, student.gender) &&
                Objects.equals(courses, student.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dept, age, gender, courses);
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

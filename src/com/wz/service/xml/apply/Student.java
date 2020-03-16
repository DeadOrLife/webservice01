package com.wz.service.xml.apply;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author jamesbean
 */
@XmlRootElement
public class Student {
    private int id;
    private String name;
    private String age;
    private Classroom classroom;

    public Student() {
    }

    public Student(int id, String name, String age, Classroom classroom) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.classroom = classroom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", classroom=" + classroom +
                '}';
    }
}

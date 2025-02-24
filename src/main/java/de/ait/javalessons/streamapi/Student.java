package de.ait.javalessons.streamapi;

import java.util.Objects;

public class Student {
    private String name;
    private String sex;
    private int age;
    private int course;
    private double avgGrade;

    public Student(String name, String sex, int age, int course, double avgGrade) {
        this.age = age;
        this.course = course;
        this.name = name;
        this.sex = sex;
        this.avgGrade = avgGrade;
    }

    public int getAge() {
        return age;
    }

    public double getAvgGrade() {
        return avgGrade;
    }

    public int getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAvgGrade(int avgGrade) {
        this.avgGrade = avgGrade;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof de.ait.javalessons.streamapi.Student student)) return false;
        return age == student.age && course == student.course && avgGrade == student.avgGrade && java.util.Objects.equals(name, student.name) && java.util.Objects.equals(sex, student.sex);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(name, sex, age, course, avgGrade);
    }

    @Override
    public String toString() {
        return "Student{" +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                "age=" + age +
                ", course=" + course +
                ", avgGrade=" + avgGrade +
                '}';
    }
}

package by.epam.learn.ErrorsAndExceptions;

import java.util.*;

public class Student {
    private int id;
    private String surname;
    private String name;
    private Map<Subject, Integer[]> subjects;

    public Student(int id, String surname, String name, Map<Subject, Integer[]> subjects) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.subjects = subjects;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Subject, Integer[]> getSubjects() {
        return subjects;
    }

    public void setSubjects(Map<Subject, Integer[]> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(surname, student.surname) &&
                Objects.equals(name, student.name) &&
                Objects.equals(subjects, student.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, subjects);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", subjects=" + subjects +
                '}';
    }
}

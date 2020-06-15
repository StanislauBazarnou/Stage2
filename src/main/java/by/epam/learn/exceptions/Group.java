package by.epam.learn.exceptions;

import java.util.List;

public class Group {

    private String name;
    private List<Student> students;

    Group(String name, List<Student> students) {
        this.name = name;
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Group{" +
                "students=" + students +
                '}';
    }
}

package by.epam.learn.exceptions.util;

import by.epam.learn.exceptions.*;
import by.epam.learn.exceptions.exception.NullGroupException;
import by.epam.learn.exceptions.exception.NullStudentException;
import by.epam.learn.exceptions.exception.WrongMarkException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static by.epam.learn.exceptions.util.ValidationUtil.*;

public class StudentUtil {

    private StudentUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static double averageMark(Integer[] marks) {
        return Arrays.stream(marks)
                .mapToInt(Integer::intValue)
                .average().orElse(Double.NaN);
    }

    public static List<Student> searchSubjectThroughoutFacultiesAndGroups(University university, Faculty searchFaculty, Group searchGroup,
                                Subject searchSubject) throws NullStudentException, NullGroupException {
        checkStudentPresence(searchGroup);
        checkGroupPresence(searchFaculty);
        List<Student> studentsOnSpecificSubject = new ArrayList<>();
        for (Faculty faculty : university.getFaculties()) {
            if (faculty.equals(searchFaculty)) {
                for (Group group : faculty.getGroups()) {
                    if (group.equals(searchGroup)) {
                        for (Student student : group.getStudents()) {
                            if (student.getSubjects().containsKey(searchSubject)) {
                                studentsOnSpecificSubject.add(student);
                            }
                        }
                    }
                }
            }
        }
        return studentsOnSpecificSubject;
    }

    public static List<Student> searchSubjectThroughoutUniversity(Subject subject, List<Student> students) {
        List<Student> specificSubject = new ArrayList<>();
        for (Student student : students) {
            if (student.getSubjects().containsKey(subject)) {
                specificSubject.add(student);
            }
        }
        return specificSubject;
    }

    public static double averageMarkInOneSubject(List<Student> students, Subject searchSubject) throws WrongMarkException {
        double sum = 0;
        int count = 0;
        double average = 0;
        for (Student student : students) {
            for (Map.Entry<Subject, Integer[]> entry : student.getSubjects().entrySet()) {
                if (entry.getKey().equals(searchSubject)) {
                    checkMarkValue(students, searchSubject);
                    sum += (StudentUtil.averageMark(entry.getValue()));
                    count++;
                }
            }
        }
        if (count != 0) {
            average = sum / count;
        }
        average = (double) Math.round(average * 1000d) / 1000d;
        return average;
    }

    public static double studentAverageMarkInAllSubjects(List<Student> studentList, Student name) {
        double sum = 0;
        int count = 0;
        double average = 0;
        for (Student student : studentList) {
            if (student.equals(name)) {
                for (Map.Entry<Subject, Integer[]> entry : student.getSubjects().entrySet()) {
                    sum += (StudentUtil.averageMark(entry.getValue()));
                    count++;
                }
            }
        }
        if (count != 0) {
            average = sum / count;
        }
        average = (double) Math.round(average * 100d) / 100d;
        return average;
    }
}

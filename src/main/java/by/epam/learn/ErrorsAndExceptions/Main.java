package by.epam.learn.ErrorsAndExceptions;

import by.epam.learn.ErrorsAndExceptions.exception.NullFacultyException;
import by.epam.learn.ErrorsAndExceptions.exception.NullGroupException;
import by.epam.learn.ErrorsAndExceptions.exception.NullStudentException;
import by.epam.learn.ErrorsAndExceptions.exception.WrongMarkException;
import by.epam.learn.ErrorsAndExceptions.util.StudentUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static by.epam.learn.ErrorsAndExceptions.Subject.*;
import static by.epam.learn.ErrorsAndExceptions.util.ValidationUtil.*;

public class Main {

    static Student kellyJoe = new Student(1, "Kelly", "Joe", new HashMap<Subject, Integer[]>() {{
        put(ECONOMETRICS, new Integer[]{10, 8});
        put(GLOBAL_FINANCE, new Integer[]{3, 7, 8});
    }});
    static Student codyHolmes = new Student(2, "Cody", "Holmes", new HashMap<Subject, Integer[]>() {{
        put(ECONOMETRICS, new Integer[]{8, 4});
        put(GLOBAL_FINANCE, new Integer[]{7, 8, 6});
    }});
    static Student rileyGrant = new Student(3, "Riley", "Grant", new HashMap<Subject, Integer[]>() {{
        put(INTERNATIONAL_TRADE, new Integer[]{7, 6});
        put(OPERATING_SYSTEMS, new Integer[]{8, 7, 6});
        put(ECONOMETRICS, new Integer[]{6});
    }});
    static Student harleyByrne = new Student(4, "Harley", "Byrne", new HashMap<Subject, Integer[]>() {{
        put(MONEY_AND_BANKING, new Integer[]{6});
        put(OPERATING_SYSTEMS, new Integer[]{4, 7, 6});
    }});
    static Student harryDuncan = new Student(5, "Harry", "Duncan", new HashMap<Subject, Integer[]>() {{
        put(COMPUTER_NETWORKS, new Integer[]{4, 6});
        put(OPERATING_SYSTEMS, new Integer[]{8, 5, 6});
    }});
    static Student joshuaOliver = new Student(6, "Joshua", "Oliver", new HashMap<Subject, Integer[]>() {{
        put(COMPUTER_NETWORKS, new Integer[]{6, 6});
        put(OPERATING_SYSTEMS, new Integer[]{7, 7});
    }});
    static Student simonSparks = new Student(7, "Simon", "Sparks", new HashMap<Subject, Integer[]>() {{
        put(PHYSICS, new Integer[]{4, 7, 8});
        put(CHEMISTRY, new Integer[]{8, 5});
        put(ECONOMETRICS, new Integer[]{7, 8});
    }});
    static Student everettGuy = new Student(8, "Everett", "Guy", new HashMap<Subject, Integer[]>() {{
        put(PHYSICS, new Integer[]{4, 7, 7});
        put(CHEMISTRY, new Integer[]{7, 5, 6});
    }});
    static Student adityaChase = new Student(9, "Aditya", "Chase", new HashMap<Subject, Integer[]>() {{
        put(PHYSICS, new Integer[]{4, 7, 6, 8});
        put(CHEMISTRY, new Integer[]{8, 5, 7});
    }});
    static Student johnWhitney = new Student(10, "John", "Whitney", new HashMap<Subject, Integer[]>() {{
        put(PHYSICS, new Integer[]{4, 7, 6});
        put(CHEMISTRY, new Integer[]{8});
    }});

    static List<Student> students = Arrays.asList(kellyJoe, codyHolmes, rileyGrant, harleyByrne, harryDuncan,
            joshuaOliver, simonSparks, everettGuy, adityaChase, johnWhitney);


    public static void main(String[] args) {

        University mitso = new University("MITSO", Arrays.asList(Faculty.FACULTY_OF_ECONOMICS,
                Faculty.FACULTY_OF_COMPUTER_SCIENCE, Faculty.FACULTY_OF_NATURAL_SCIENCES));

        try {
            checkFacultyPresence(mitso);
        } catch (NullFacultyException e) {
            e.printStackTrace();
        }

        //Посчитать средний балл по всем предметам студента
        String studentName = joshuaOliver.getName();
        System.out.printf("The average %s's mark in all subjects is " +
                StudentUtil.studentAverageMarkInAllSubjects(students, joshuaOliver)
                + "\n", studentName);

        //Посчитать средний балл по конкретному предмету в конкретной группе и на конкретном факультете
        Faculty searchFaculty = Faculty.FACULTY_OF_ECONOMICS;
        Group searchGroup = Group.DISTANCE_611;
        Subject searchSubject = ECONOMETRICS;

        String faculty = Faculty.FACULTY_OF_ECONOMICS.name();
        String group = Group.FULL_TIME_815.name();
        String specificSubject = ECONOMETRICS.name();
        try {
            System.out.printf("The average mark on %s in group %s on the %s subject is " +
                    StudentUtil.averageMarkInOneSubject(StudentUtil.findSubjectOnSpecificFacultyAndSpecificGroup
                            (searchFaculty, searchGroup, searchSubject), searchSubject), faculty, group, specificSubject);
        } catch (WrongMarkException | NullStudentException | NullGroupException e) {
            e.printStackTrace();
        }

        //Посчитать средний балл по предмету для всего университета
        Subject subjectSearch = Subject.PHYSICS;
        String subject = subjectSearch.name();
        try {
            System.out.printf("%nThe average mark on the %s throughout the University is " +
                    StudentUtil.averageMarkInOneSubject(StudentUtil.findSpecificSubjectAroundUniversity(searchSubject,
                            students), subjectSearch) + "\n", subject);
        } catch (WrongMarkException e) {
            e.printStackTrace();
        }
    }
}

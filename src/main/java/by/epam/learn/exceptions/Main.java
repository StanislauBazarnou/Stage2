package by.epam.learn.exceptions;

import by.epam.learn.exceptions.exception.NullFacultyException;
import by.epam.learn.exceptions.exception.NullGroupException;
import by.epam.learn.exceptions.exception.NullStudentException;
import by.epam.learn.exceptions.exception.WrongMarkException;
import by.epam.learn.exceptions.util.StudentUtil;

import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;

import static by.epam.learn.exceptions.Subject.*;
import static by.epam.learn.exceptions.util.ValidationUtil.checkFacultyPresence;

public class Main {

    public static void main(String[] args) {

        Student kellyJoe = new Student(1, "Kelly", "Joe", new EnumMap<>(Subject.class));
        kellyJoe.getSubjects().put(ECONOMETRICS, new Integer[]{10, 8});
        kellyJoe.getSubjects().put(GLOBAL_FINANCE, new Integer[]{3, 7, 8});

        Student codyHolmes = new Student(2, "Cody", "Holmes", new EnumMap<>(Subject.class));
        codyHolmes.getSubjects().put(ECONOMETRICS, new Integer[]{8, 4});
        codyHolmes.getSubjects().put(GLOBAL_FINANCE, new Integer[]{7, 8, 6});

        Student rileyGrant = new Student(3, "Riley", "Grant", new EnumMap<>(Subject.class));
        rileyGrant.getSubjects().put(INTERNATIONAL_TRADE, new Integer[]{7, 6});
        rileyGrant.getSubjects().put(OPERATING_SYSTEMS, new Integer[]{8, 7, 6});
        rileyGrant.getSubjects().put(ECONOMETRICS, new Integer[]{6});
         
        Student harleyByrne = new Student(4, "Harley", "Byrne", new EnumMap<>(Subject.class));
        harleyByrne.getSubjects().put(MONEY_AND_BANKING, new Integer[]{6});
        harleyByrne.getSubjects().put(OPERATING_SYSTEMS, new Integer[]{4, 7, 6});
         
        Student harryDuncan = new Student(5, "Harry", "Duncan", new EnumMap<>(Subject.class));
        harryDuncan.getSubjects().put(COMPUTER_NETWORKS, new Integer[]{4, 6});
        harryDuncan.getSubjects().put(OPERATING_SYSTEMS, new Integer[]{8, 5, 6});
         
        Student joshuaOliver = new Student(6, "Joshua", "Oliver", new EnumMap<>(Subject.class));
        joshuaOliver.getSubjects().put(COMPUTER_NETWORKS, new Integer[]{6, 6});
        joshuaOliver.getSubjects().put(OPERATING_SYSTEMS, new Integer[]{7, 7});
         
        Student simonSparks = new Student(7, "Simon", "Sparks", new EnumMap<>(Subject.class));
        simonSparks.getSubjects().put(PHYSICS, new Integer[]{4, 7, 8});
        simonSparks.getSubjects().put(CHEMISTRY, new Integer[]{8, 5});
        simonSparks.getSubjects().put(ECONOMETRICS, new Integer[]{7, 8});
         
        Student everettGuy = new Student(8, "Everett", "Guy", new EnumMap<>(Subject.class));
        everettGuy.getSubjects().put(PHYSICS, new Integer[]{4, 7, 7});
        everettGuy.getSubjects().put(CHEMISTRY, new Integer[]{7, 5, 6});
         
        Student adityaChase = new Student(9, "Aditya", "Chase", new EnumMap<>(Subject.class));
        adityaChase.getSubjects().put(PHYSICS, new Integer[]{4, 7, 6, 8});
        adityaChase.getSubjects().put(CHEMISTRY, new Integer[]{8, 5, 7});
         
        Student johnWhitney = new Student(10, "John", "Whitney", new EnumMap<>(Subject.class));
        johnWhitney.getSubjects().put(PHYSICS, new Integer[]{4, 7, 6});
        johnWhitney.getSubjects().put(CHEMISTRY, new Integer[]{8});
         

        List<Student> students = Arrays.asList(kellyJoe, codyHolmes, rileyGrant, harleyByrne, harryDuncan,
                joshuaOliver, simonSparks, everettGuy, adityaChase, johnWhitney);

        Group fullTime815 = new Group("FULL_TIME_815", Arrays.asList(kellyJoe, codyHolmes, rileyGrant));
        Group distance715 = new Group("DISTANCE_715", Arrays.asList(harleyByrne, harryDuncan));
        Group fullTime915 = new Group("FULL_TIME_915", Arrays.asList(simonSparks, everettGuy));
        Group fullTime911 = new Group("FULL_TIME_911", Arrays.asList(adityaChase, johnWhitney));
        Group distance611 = new Group("DISTANCE_611", Collections.singletonList(joshuaOliver));


        Faculty economy = new Faculty("FACULTY_OF_ECONOMICS", Arrays.asList(fullTime815, distance715));
        Faculty computerScience = new Faculty("FACULTY_OF_COMPUTER_SCIENCE", Arrays.asList(fullTime915, fullTime911));
        Faculty naturalScience = new Faculty("FACULTY_OF_NATURAL_SCIENCES", Collections.singletonList(distance611));

        University mitso = new University("MITSO", Arrays.asList(economy, computerScience, naturalScience));

        try {
            checkFacultyPresence(mitso);
        } catch (NullFacultyException e) {
            e.printStackTrace();
        }

        kellyJoe.getName();

        //Посчитать средний балл по всем предметам студента
        String studentName = joshuaOliver.getName();
        System.out.printf("The average %s's mark in all subjects is %.2f %n", studentName,
                StudentUtil.studentAverageMarkInAllSubjects(students, joshuaOliver));

        //Посчитать средний балл по конкретному предмету в конкретной группе и на конкретном факультете
        Faculty searchFaculty = economy;
        Group searchGroup = fullTime815;
        Subject searchSubject = ECONOMETRICS;

        String faculty = economy.getName();
        String group = fullTime815.getName();
        String specificSubject = ECONOMETRICS.name();
        try {
            System.out.printf("The average mark on %s in group %s on the %s subject is %.2f", faculty, group, specificSubject,
                    StudentUtil.averageMarkInOneSubject(StudentUtil.searchSubjectThroughoutFacultiesAndGroups
                            (mitso, searchFaculty, searchGroup, searchSubject), searchSubject));
        } catch (WrongMarkException | NullStudentException | NullGroupException e) {
            e.printStackTrace();
        }

        //Посчитать средний балл по предмету для всего университета
        Subject subjectSearch = Subject.PHYSICS;
        String subject = subjectSearch.name();
        try {
            System.out.printf("%nThe average mark on the %s throughout the University is %.2f", subject,
                    StudentUtil.averageMarkInOneSubject(StudentUtil.searchSubjectThroughoutUniversity(searchSubject,
                            students), subjectSearch));
        } catch (WrongMarkException e) {
            e.printStackTrace();
        }
    }
}

package by.epam.learn.exceptions.util;

import by.epam.learn.exceptions.*;
import by.epam.learn.exceptions.exception.NullFacultyException;
import by.epam.learn.exceptions.exception.NullGroupException;
import by.epam.learn.exceptions.exception.NullStudentException;
import by.epam.learn.exceptions.exception.WrongMarkException;

import java.util.List;
import java.util.Map;

public class ValidationUtil {

    private ValidationUtil() {
        throw new IllegalStateException("Utility class");
    }

    //Релизовать исключение: Оценка ниже 0 или выше 10
    public static void checkMarkValue(List<Student> students, Subject searchSubject) throws WrongMarkException {
        for (Student student : students) {
            for (Map.Entry<Subject, Integer[]> entry : student.getSubjects().entrySet()) {
                if (entry.getKey().equals(searchSubject)) {
                    for (int i = 0; i < entry.getValue().length; i++) {
                        if (entry.getValue()[i] < 1 || entry.getValue()[i] > 10) {
                            throw new WrongMarkException("Wrong mark for student with id" + student.getId());
                        }
                    }
                }
            }
        }
    }

    //Релизовать исключение: Отсутсвие предметов у студента (должен быть хотя бы один)
    /*по логике приложения исключено наличие студента без предмета, потому что предмет является
    ключом в HashMap, а значение - массив оценок студента, и т.к. HashMap не может быть реализован без ключа,
    то студент не может не иметь предмета*/

    //Релизовать исключение: Отсутствие студентов в группе
    public static void checkStudentPresence(Group searchGroup) throws NullStudentException {
        if (searchGroup.getStudents().isEmpty()) {
            throw new NullStudentException("Amount of students in " + searchGroup.getName() + " should be more than 0");
        }
    }

    //Релизовать исключение: Отсутствие групп на факультете
    public static void checkGroupPresence(Faculty searchFaculty) throws NullGroupException {
        if (searchFaculty.getGroups().isEmpty()) {
            throw new NullGroupException("Amount of group in " + searchFaculty.getName() + " should be more than 0");
        }
    }

    //Релизовать исключение: Отсутствие факультетов в университете
    public static void checkFacultyPresence(University university) throws NullFacultyException {
        if (university.getFaculties().isEmpty()) {
            throw new NullFacultyException("Amount of faculties should be more than 0 for University " +
                    university.getName());
        }
    }
}

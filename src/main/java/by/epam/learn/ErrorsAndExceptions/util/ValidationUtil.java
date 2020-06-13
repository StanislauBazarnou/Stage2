package by.epam.learn.ErrorsAndExceptions.util;

import by.epam.learn.ErrorsAndExceptions.*;
import by.epam.learn.ErrorsAndExceptions.exception.NullFacultyException;
import by.epam.learn.ErrorsAndExceptions.exception.NullGroupException;
import by.epam.learn.ErrorsAndExceptions.exception.NullStudentException;
import by.epam.learn.ErrorsAndExceptions.exception.WrongMarkException;

import java.util.List;
import java.util.Map;

public class ValidationUtil {

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
        if (searchGroup.getStudents().size() < 1) {
            throw new NullStudentException("Amount of students in " + searchGroup.name() + " should be more than 0");
        }
    }

    //Релизовать исключение: Отсутствие групп на факультете
    public static void checkGroupPresence(Faculty searchFaculty) throws NullGroupException {
        if (searchFaculty.getGroups().size() < 1) {
            throw new NullGroupException("Amount of group in " + searchFaculty.name() + " should be more than 0");
        }
    }

    //Релизовать исключение: Отсутствие факультетов в университете
    public static void checkFacultyPresence(University university) throws NullFacultyException {
        if (University.getFaculties().size() < 1) {
            throw new NullFacultyException("Amount of faculties should be more than 0 for University " +
                    university.getName());
        }
    }
}

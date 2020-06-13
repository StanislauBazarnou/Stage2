package by.epam.learn.ErrorsAndExceptions;

import java.util.Arrays;
import java.util.List;

import static by.epam.learn.ErrorsAndExceptions.Main.*;

public enum Group {
    FULL_TIME_815(Arrays.asList(kellyJoe, codyHolmes, rileyGrant)),
    DISTANCE_715(Arrays.asList(harleyByrne, harryDuncan)),
    FULL_TIME_915(Arrays.asList(simonSparks, everettGuy)),
    FULL_TIME_911(Arrays.asList(adityaChase, johnWhitney)),
    DISTANCE_611(Arrays.asList(joshuaOliver));

    private List<Student> students;

    Group(List<Student> students) {
        this.students = students;
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

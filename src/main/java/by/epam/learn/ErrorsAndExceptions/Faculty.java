package by.epam.learn.ErrorsAndExceptions;

import java.util.Arrays;
import java.util.List;

public enum Faculty {
    FACULTY_OF_ECONOMICS(Arrays.asList(Group.FULL_TIME_815, Group.DISTANCE_715)),
    FACULTY_OF_COMPUTER_SCIENCE(Arrays.asList(Group.FULL_TIME_915, Group.FULL_TIME_911)),
    FACULTY_OF_NATURAL_SCIENCES(Arrays.asList(Group.DISTANCE_611));

    private List<Group> groups;

    Faculty(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "groups=" + groups +
                '}';
    }
}

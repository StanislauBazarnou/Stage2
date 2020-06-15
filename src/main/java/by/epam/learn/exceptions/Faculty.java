package by.epam.learn.exceptions;

import java.util.List;

public class Faculty {
    private String name;
    private List<Group> groups;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    Faculty(String name, List<Group> groups) {
        this.name = name;
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

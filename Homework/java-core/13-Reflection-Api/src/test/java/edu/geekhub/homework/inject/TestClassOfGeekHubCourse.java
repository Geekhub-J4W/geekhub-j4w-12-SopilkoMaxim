package edu.geekhub.homework.inject;

import java.util.Objects;

public class TestClassOfGeekHubCourse {

    @Injectable
    private String name;
    @Injectable
    private int duration;
    @Injectable(propertyName = "description")
    private String description;
    @Injectable(propertyName = "mentor")
    private String mentorName;
    @Injectable
    private int registrations;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestClassOfGeekHubCourse that)) return false;
        return duration == that.duration && registrations == that.registrations && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(mentorName, that.mentorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration, description, mentorName, registrations);
    }
}

package edu.geekhub.homework;

import edu.geekhub.homework.inject.Injectable;

import java.util.Objects;

public class GeekHubCourse {


    @Injectable(propertyName = "courseName")
    private String name;
    @Injectable(propertyName = "duration")
    private int duration;
    @Injectable
    private String description;
    @Injectable(propertyName = "mentor")
    private String mentorName;
    @Injectable
    private int registrations;



    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private int getDuration() {
        return duration;
    }

    private void setDuration(int duration) {
        this.duration = duration;
    }

    private String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private String getMentorName() {
        return mentorName;
    }

    private void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    private int getRegistrations() {
        return registrations;
    }

    private void setRegistrations(int registrations) {
        this.registrations = registrations;
    }

    @Override
    public String toString() {
        return "GeekHubCourse{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                ", description='" + description + '\'' +
                ", mentorName='" + mentorName + '\'' +
                ", registrations=" + registrations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeekHubCourse that)) return false;
        return duration == that.duration && registrations == that.registrations && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(mentorName, that.mentorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, duration, description, mentorName, registrations);
    }
}

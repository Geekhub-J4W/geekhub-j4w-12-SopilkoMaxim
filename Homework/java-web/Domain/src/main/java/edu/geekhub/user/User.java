package edu.geekhub.user;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class User {
    private int id;

    private String email;

    private String password;

    private String fullName;

    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;
    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;

    public User() {
    }

    public User(int id, String email, String password, String fullName, String role, String status) {
        this.id=id;
        this.email=email;
        this.password=password;
        this.fullName=fullName;
        this.role= Role.valueOf(role);
        this.status= Status.valueOf(status);
    }
    public User(int id, String email, String password, String fullName) {
        this.id=id;
        this.email=email;
        this.password=password;
        this.fullName=fullName;

    }

    public User(String name, String email, String password) {
        this.fullName=name;
        this.email=email;
        this.password=password;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}


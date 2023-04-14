package edu.geekhub.Entity;

public class User {
    private int id;
    private String name;
    private int age;

    private String email;
    private String password;

    private float balance;

    private Wallet wallet;

    public User(String name, int age, String email, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }

    public User(int id, String name, int age, String email, String password, float balance) {
        this.id=id;
        this.name=name;
        this.age=age;
        this.email=email;
        this.password=password;
        this.balance=balance;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}

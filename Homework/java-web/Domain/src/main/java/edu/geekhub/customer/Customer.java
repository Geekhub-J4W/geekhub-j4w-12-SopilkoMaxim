package edu.geekhub.customer;

public class Customer {
    public static int COUNTER = 0;
    int id;
    private String name;
    private int age;

    public Customer(String name, int age) {
        this.id=COUNTER++;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }
}

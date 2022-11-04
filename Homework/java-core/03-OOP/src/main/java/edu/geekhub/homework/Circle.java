package edu.geekhub.homework;

public class Circle extends Shape implements InterfaceShape{

    private final double radius;
    private final String name = "Circle";

    public Circle(double radius) {
        super();
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

}

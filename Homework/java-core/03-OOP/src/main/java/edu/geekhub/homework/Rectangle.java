package edu.geekhub.homework;

public class Rectangle extends Shape implements InterfaceShape{
    private final double side1;
    private final double side2;
    private final String name = "Rectangle";

    public Rectangle(double side1, double side2) {
        super();
        this.side1 = side1;
        this.side2 = side2;
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return side1*side2;
    }

    public double getPerimeter() {
        return 2*side1+2*side2;
    }
}

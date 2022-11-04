package edu.geekhub.homework;

public class Triangle extends Shape implements InterfaceShape{
    private final double side;
    private final String name = "Triangle";

    public Triangle(double side) {
        super();
        this.side = side;
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return (Math.pow(side, 2) * Math.sqrt(3)) / 4;
    }

    public double getPerimeter() {
        return 3*side;
    }
}

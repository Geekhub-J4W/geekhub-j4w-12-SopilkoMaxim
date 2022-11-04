package edu.geekhub.homework;

public class Square extends Shape implements InterfaceShape{
    private final double side;
    private final String name = "Square";

    public Square (double side) {
        super();
        this.side = side;
    }

    public String getName() {
        return name;
    }

    public double getArea() {
        return Math.pow(side, 2);
    }

    public double getPerimeter() {
        return 4*side;
    }

}

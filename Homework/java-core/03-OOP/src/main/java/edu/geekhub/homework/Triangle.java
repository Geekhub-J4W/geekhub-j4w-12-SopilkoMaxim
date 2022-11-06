package edu.geekhub.homework;

import java.util.Scanner;

public class Triangle extends Shape implements InterfaceShape{
    private final double side;
    private final String name = "Triangle";

    public Triangle() {
        super();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter side for Triangle:");
        side = scan.nextInt();
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

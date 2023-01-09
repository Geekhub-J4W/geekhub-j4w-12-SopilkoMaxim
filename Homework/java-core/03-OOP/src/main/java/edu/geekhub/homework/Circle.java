package edu.geekhub.homework;

import java.util.Scanner;

public class Circle extends Shape{

    private final double radius;
    private final String name = "Circle";

    public Circle() {
        super();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter radius for Circle:");
        radius = scan.nextInt();

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

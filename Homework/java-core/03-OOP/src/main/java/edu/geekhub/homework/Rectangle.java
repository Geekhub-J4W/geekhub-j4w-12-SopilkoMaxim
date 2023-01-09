package edu.geekhub.homework;

import java.util.Scanner;

public class Rectangle extends Shape{
    private final double side1;
    private final double side2;
    private final String name = "Rectangle";

    public Rectangle() {
        super();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter side1 for Rectangle:");
        side1 = scan.nextInt();
        System.out.println("Enter side2 for Rectangle:");
        side2 = scan.nextInt();
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

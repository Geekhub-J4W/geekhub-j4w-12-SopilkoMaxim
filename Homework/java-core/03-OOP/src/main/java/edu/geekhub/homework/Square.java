package edu.geekhub.homework;

import java.util.Scanner;

public class Square extends Shape{
    private final double side;
    private final String name = "Square";

    public Square () {
        super();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter side for Square:");
        side = scan.nextInt();
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

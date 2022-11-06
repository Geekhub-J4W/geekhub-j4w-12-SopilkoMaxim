package edu.geekhub.homework;


import java.util.Scanner;

public class ApplicationStarter {
    public static void main(String[] arg) {
        System.out.println("Choose the shape#1 you want:");
        System.out.println("1 = Circle \n 2 = Rectangle \n 3 = Square \n 4 = Triangle");
        Scanner scan = new Scanner(System.in);
        int numberShape = scan.nextInt();
        Shape shape1 = null;
        boolean check = true;
        do {
            if (numberShape == 1 || numberShape == 2 || numberShape == 3 || numberShape == 4)
                check = false;
            else {
                System.out.println("You entered wrong number, try again:");
                numberShape = scan.nextInt();
            }
        } while (check);
        switch (numberShape) {
            case 1: {
                shape1 = new Circle();
                break;
            }
            case 2: {
                shape1 = new Rectangle();
                break;
            }
            case 3: {
                shape1 = new Square();
                break;
            }
            case 4: {
                shape1 = new Triangle();
                break;
            }

        }
        System.out.println("Choose the shape#2 you want:");
        System.out.println("1 = Circle \n 2 = Rectangle \n 3 = Square \n 4 = Triangle");
        numberShape = scan.nextInt();
        Shape shape2 = null;
        check = true;
        do {
            if (numberShape == 1 || numberShape == 2 || numberShape == 3 || numberShape == 4)
                check = false;
            else {
                System.out.println("You entered wrong number, try again:");
                numberShape = scan.nextInt();
            }
        } while (check);
        switch (numberShape) {
            case 1: {
                shape2 = new Circle();
                break;
            }
            case 2: {
                shape2 = new Rectangle();
                break;
            }
            case 3: {
                shape2 = new Square();
                break;
            }
            case 4: {
                shape2 = new Triangle();
                break;
            }
        }
        System.out.println("Figure #1: \nName: " + shape1.getName());
        System.out.println("Area: " + shape1.getArea());
        System.out.println("Perimeter: " + shape1.getPerimeter() + "\nColor: " + shape1.getColor());
        System.out.println("Figure #2: \nName: " + shape2.getName());
        System.out.println("Area: " + shape2.getArea());
        System.out.println("Perimeter: " + shape2.getPerimeter() + "\nColor: " + shape2.getColor());
        if (shape1.getArea() > shape2.getArea()) {
            System.out.println("Figure 1 is bigger than figure2");
        } else {
            System.out.println("Figure 2 is bigger than figure1");
        }

    }

}

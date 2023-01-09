package edu.geekhub.homework;


import java.util.Scanner;

public class ApplicationStarter {
    public static void main(String[] arg) {
        System.out.println("Choose the shape#1 you want:");
        System.out.println("1 = Circle \n 2 = Rectangle \n 3 = Square \n 4 = Triangle");
        Scanner scan = new Scanner(System.in);
        int numberShape = scan.nextInt();


        numberShape = ApplicationStarter.checkNumberOfMenu(numberShape);
        Shape shape1 = ApplicationStarter.createSomeShape(numberShape);

        System.out.println("Choose the shape#2 you want:");
        System.out.println("1 = Circle \n 2 = Rectangle \n 3 = Square \n 4 = Triangle");
        numberShape = scan.nextInt();

        numberShape = ApplicationStarter.checkNumberOfMenu(numberShape);
        Shape shape2 = ApplicationStarter.createSomeShape(numberShape);

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
    public static int checkNumberOfMenu(int entered){
        boolean check = true;
        Scanner scan = new Scanner(System.in);
        do {
            if (entered == 1 || entered == 2 || entered == 3 || entered == 4)
                check = false;
            else {
                System.out.println("You entered wrong number, try again:");
                entered = scan.nextInt();
            }
        } while (check);
        return entered;

    }
    public static Shape createSomeShape(int numberOfShape){
        switch (numberOfShape) {
            case 1: return new Circle();
            case 2: return new Rectangle();
            case 3: return new Square();
            case 4: return new Triangle();
        }
        return null;
    }

}

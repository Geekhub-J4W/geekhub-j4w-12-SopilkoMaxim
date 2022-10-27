package edu.geekhub.homework;

import java.util.Scanner;

public class ApplicationStarter {
    public static void main(String[] arg) {
        System.out.println("Enter N:");
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        Calculator cal = new Calculator();
        cal.checkingForExceptions(N);
        double result = Calculator.calculate(N);
        System.out.println("result: " + result);

    }

}

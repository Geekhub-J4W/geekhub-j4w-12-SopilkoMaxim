package edu.geekhub.homework;

import java.util.Scanner;

public class ApplicationStarter {
    public static void main(String[] arg) {
        System.out.println("Enter N:");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Calculator cal = new Calculator();
        cal.divideForZeroTest(n);
        cal.divideForTwoAndThreeTest(n);
        double result = Calculator.calculate(n);
        System.out.println("result: " + result);

    }

}

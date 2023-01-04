package edu.geekhub.homework;

import java.util.Scanner;

public class ApplicationStarter {
    public static void main(String[] arg) {
        System.out.println("Enter N:");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Calculator calculator = new Calculator();
        calculator.validateNumber(n);
        System.out.println("result: " + calculator.calculate(n));

    }

}

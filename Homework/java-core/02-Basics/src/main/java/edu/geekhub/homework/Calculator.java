package edu.geekhub.homework;

public class Calculator {
    public void checkingForExceptions(int N) {
        if (N == 0) {
            System.out.println("You entered wrong number 0 ");
            System.exit(0);
        }
        if (N % 2 == 0 && N % 3 == 0) {
            double result1, result2;
            result1 = N * 2;
            result2 = Math.PI * Math.pow(N, 2);
            System.out.println("Your number divides for 2 and 3 \n S square  = " + result1 + "\n S circle = " + result2);
            System.exit(0);
        }
    }

    public static double calculate(int N) {
        double result;
        if (N % 2 == 0) {
            result = N * 2;
            System.out.println("Your number divides by 2");
            return result;
        } else if (N % 3 == 0) {
            System.out.println("Your number divides by 3");
            result = Math.PI * Math.pow(N, 2);
            return result;
        } else {
            System.out.println("Your number is not divide by 2 and 3");
            result = (Math.pow(N, 2) * Math.sqrt(3)) / 4;
            return result;
        }
    }
}

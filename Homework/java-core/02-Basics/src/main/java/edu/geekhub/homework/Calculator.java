package edu.geekhub.homework;

public class Calculator {
    public void divideForZeroTest(int n) {
        if (n == 0) {
            System.out.println("You entered wrong number 0 ");
            System.exit(0);
        }
    }
    public void divideForTwoAndThreeTest(int n)
    {
        if (n % 2 == 0 && n % 3 == 0) {
            double result1;
            double result2;
            result1 = n * n;
            result2 = Math.PI * Math.pow(n, 2);
            System.out.println("Your number divides for 2 and 3 \n S square  = " + result1 + "\n S circle = " + result2);
            System.exit(0);
        }
    }

    public static double calculate(int n) {
        double result;
        if (n % 2 == 0) {
            result = n * n;
            System.out.println("Your number divides by 2");
            return result;
        } else if (n % 3 == 0) {
            System.out.println("Your number divides by 3");
            result = Math.PI * Math.pow(n, 2);
            return result;
        } else {
            System.out.println("Your number is not divide by 2 and 3");
            result = (Math.pow(n, 2) * Math.sqrt(3)) / 4;
            return result;
        }
    }
}

package edu.geekhub.homework;

public class Calculator {

    public void validateNumber(int n)
    {
        divideForZeroTest(n);
        divideForTwoAndThreeTest(n);
    }
    public void divideForZeroTest(int n) {
        if (n == 0) {
            throw new IllegalArgumentException("Can not divide by 0");
        }
    }
    public void divideForTwoAndThreeTest(int n)
    {
        if (n % 2 == 0 && n % 3 == 0) {
            System.out.println("Your number divides for 2 and 3 \n S square  = " + n * n + "\n S circle = " + Math.PI * Math.pow(n, 2));
            System.exit(0);
        }
    }

    public double calculate(int n) {
        if (n % 2 == 0) {
            System.out.println("Your number divides by 2");
            return n*n;
        } else if (n % 3 == 0) {
            System.out.println("Your number divides by 3");
            return Math.PI * Math.pow(n, 2);
        } else {
            System.out.println("Your number is not divide by 2 and 3");
            return (Math.pow(n, 2) * Math.sqrt(3)) / 4;
        }
    }
}

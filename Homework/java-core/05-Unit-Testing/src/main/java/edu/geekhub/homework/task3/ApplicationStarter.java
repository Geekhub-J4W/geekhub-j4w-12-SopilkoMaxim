package edu.geekhub.homework.task3;

public class ApplicationStarter {
    public static void main(String[] args) {
        SequenceCalculator calculator = new SequenceCalculator();
        int result = calculator.calculate("23ffs123fsfds5 %$@^", ArithmeticOperation.MULTIPLICATION);
        System.out.println("Result is:" + result);
    }

}

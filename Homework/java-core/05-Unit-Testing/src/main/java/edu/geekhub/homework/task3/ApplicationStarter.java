package edu.geekhub.homework.task3;

public class ApplicationStarter {
    public static void main(String[] args) {
        SequenceCalculator calculator = new SequenceCalculator();
        int result = calculator.calculate("25ffs0fsfds5 %$@^", ArithmeticOperation.DIVISION);
        System.out.println("Result is:" + result);
    }

}

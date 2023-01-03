package edu.geekhub.homework.task3;

import static edu.geekhub.homework.util.NotImplementedException.TODO_TYPE;


public class SequenceCalculator {



    public int calculate(String input, ArithmeticOperation operation) {
        int counterOfNumbersArray = 0;
        int result=0;
        validation(input,operation);
        String[] numbers = input.split("\\D");
        int[] parsedNumbers = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++)
            if (!numbers[i].isBlank()) {
                parsedNumbers[counterOfNumbersArray] = Integer.parseInt(numbers[i]);
                counterOfNumbersArray++;
            }
        System.out.println("Input data:");
        for (int i = 0; i < counterOfNumbersArray; i++)
            System.out.println(parsedNumbers[i]);

        if (operation == ArithmeticOperation.ADDITION) {
            for (int i = 0; i < counterOfNumbersArray; i++)
                result = result + parsedNumbers[i];
            return result;
        }
        if (operation == ArithmeticOperation.SUBTRACTION) {
            result = parsedNumbers[0];
            for (int i = 1; i < counterOfNumbersArray; i++)
                result = result - parsedNumbers[i];
            return result;
        }
        if (operation == ArithmeticOperation.MULTIPLICATION) {
            result = parsedNumbers[0];
            for (int i = 1; i < counterOfNumbersArray; i++)
                result = result * parsedNumbers[i];
            return result;
        }
        if (operation == ArithmeticOperation.DIVISION) {
            ckeckZeroArguments(parsedNumbers,counterOfNumbersArray);
            result = parsedNumbers[0];
            for (int i = 1; i < counterOfNumbersArray; i++)
            {
                result = result / parsedNumbers[i];
            }
            return result;
        }
        return result;
    }

    private void ckeckZeroArguments(int[] parsedNumbers, int counter) {
        for (int i=0;i<counter;i++) {
            if(parsedNumbers[i]==0)
                throw new IllegalArgumentException(
                        "Dividing by 0 is not possible"
                );
        }
    }

    private void validation(String input, ArithmeticOperation operation) {
    checkInputIsEmpty(input);
    checkOperationsIsRight(operation);
    }
    private void checkInputIsEmpty(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(
                    "Cant process empty field state"
            );
        }

    }
    private void checkOperationsIsRight(ArithmeticOperation operation)
    {
        if(!ArithmeticOperation.ADDITION.equals(operation)&&!ArithmeticOperation.MULTIPLICATION.equals(operation)&&!ArithmeticOperation.DIVISION.equals(operation)&&!ArithmeticOperation.SUBTRACTION.equals(operation))
        {
            throw new IllegalArgumentException(
                    "Illegal argument of operation"
            );
        }
    }
}

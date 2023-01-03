package edu.geekhub.homework.task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceCalculatorTest {

    private SequenceCalculator calculator;

    @BeforeEach
    void setUp(){
        calculator = new SequenceCalculator();
    }

    @Test
    void whenCalculateAddition_ExpectResult(){
        int result = calculator.calculate("5ffs5fsfds5 %$@^", ArithmeticOperation.ADDITION);

        assertEquals(15,result);
    }
    @Test
    void whenCalculateSubtraction_ExpectResult(){
        int result = calculator.calculate("5ffs5fsfds5 %$@^", ArithmeticOperation.SUBTRACTION);

        assertEquals(-5,result);
    }

    @Test
    void whenCalculateMultiplication_ExpectResult(){
        int result = calculator.calculate("5ffs5fsfds5 %$@^", ArithmeticOperation.MULTIPLICATION);

        assertEquals(125,result);
    }

    @Test
    void whenCalculateDivision_ExpectResult(){
        int result = calculator.calculate("25ffs5fsfds5 %$@^", ArithmeticOperation.DIVISION);

        assertEquals(1,result);
    }

    @Test
    void whenCalculateDivisionByZero_ExpectException(){

        Exception exception = assertThrows(IllegalArgumentException.class,()->calculator.calculate("25ffs0fsfds5 %$@^", ArithmeticOperation.DIVISION));

        assertEquals("Dividing by 0 is not possible",exception.getMessage());
    }

    @Test
    void whenCalculateEmptyInput_ExpectException(){

        Exception exception = assertThrows(IllegalArgumentException.class,()->calculator.calculate("", ArithmeticOperation.DIVISION));

        assertEquals("Cant process empty field state",exception.getMessage());
    }

    @Test
    void whenCalculateWrongArithmeticOperation_ExpectException(){

        Exception exception = assertThrows(IllegalArgumentException.class,()->calculator.calculate("25ffs0fsfds5 %$@^", null));

        assertEquals("Illegal argument of operation",exception.getMessage());
    }


}
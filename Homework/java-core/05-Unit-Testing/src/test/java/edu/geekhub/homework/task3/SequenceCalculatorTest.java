package edu.geekhub.homework.task3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SequenceCalculatorTest {

    private SequenceCalculator calculator = new SequenceCalculator();
    private int result;

    @Test
    void correctInputAddition()
    {
        result=calculator.calculate("1rrwerw4eqeqw5",ArithmeticOperation.ADDITION);
        assertEquals(10,result);
    }

    @Test
    void correctInputSubtraction()
    {
        result=calculator.calculate("10rrwerw4eqeqw5",ArithmeticOperation.SUBTRACTION);
        assertEquals(1,result);
    }

    @Test
    void correctInputDivision()
    {
        result=calculator.calculate("12rrwerw2eqeqw2",ArithmeticOperation.DIVISION);
        assertEquals(3,result);
    }

    @Test
    void correctInputMultiplication()
    {
        result=calculator.calculate("1rrwerw4eqeqw5",ArithmeticOperation.MULTIPLICATION);
        assertEquals(20,result);
    }

    @Test
    void correctInputMultiplicationWithSpaces()
    {
        result=calculator.calculate("1rr   we  rw4eq    eqw5",ArithmeticOperation.MULTIPLICATION);
        assertEquals(20,result);
    }

    @Test
    void incorrectInputWithEptyInputData()
    {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate(" ",ArithmeticOperation.MULTIPLICATION));
    }

    /*@Test
    void incorrectInputWithArithmeticOperation()
    {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate("fwehfw 3 iuhfswd 3 huih1 ",null));
    }*/




}
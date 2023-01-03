package edu.geekhub.homework.task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeGameFieldTest {

    TicTacToeGameField ticTacToeGameField;

    @BeforeEach
    void setUp(){
        ticTacToeGameField = new TicTacToeGameField();
    }
    @Test
    void whenGenerateEmptyField_ExpectException() {

        Exception exception = assertThrows(IllegalArgumentException.class,()->ticTacToeGameField.generateField(""));

        assertEquals("Cant process empty field state",exception.getMessage());
    }

    @Test
    void whenGenerateLongerField_ExpectException() {

        Exception exception = assertThrows(IllegalArgumentException.class,()->ticTacToeGameField.generateField("XXXOOOXXXO"));

        assertEquals("Field length: 10 is not equal allowed length: 9",exception.getMessage());
    }

    @Test
    void whenGenerateFieldWithWrongCharacters_ExpectException() {

        Exception exception = assertThrows(IllegalArgumentException.class,()->ticTacToeGameField.generateField("XXXYYYXXX"));

        assertEquals("Player turn: Y can be only 'X' or 'O' or ' '",exception.getMessage());
    }

    @Test
    void whenGenerateWrightField_ExpectField() {
        String actual = ticTacToeGameField.generateField("XXXOOOXXX");
        String expected = "+-----+" + System.lineSeparator()+
                "|X|X|X|" + System.lineSeparator()+
                "|O|O|O|" + System.lineSeparator()+
                "|X|X|X|" + System.lineSeparator()+
                "+-----+";

        assertEquals(expected,actual);
    }

    @Test
    void whenSaveFieldState_ExpectFieldState() {
        String actual = ticTacToeGameField.saveFieldState("XXXOOOXXX");
        String expected = "XXXOOOXXX";

        assertEquals(expected,actual);
    }
    @Test
    void whenSaveEmptyFieldState_ExpectException() {
        
        Exception exception = assertThrows(IllegalArgumentException.class,()->ticTacToeGameField.saveFieldState(""));

        assertEquals("Cant process empty field state",exception.getMessage());
    }
    @Test
    void whenSaveLongerFieldState_ExpectException() {

        Exception exception = assertThrows(IllegalArgumentException.class,()->ticTacToeGameField.saveFieldState("XXXOOOXXXO"));

        assertEquals("Field length: 10 is not equal allowed length: 9",exception.getMessage());

    }

    @Test
    void whenSaveFieldStateWithWrongCharacters_ExpectException() {

        Exception exception = assertThrows(IllegalArgumentException.class,()->ticTacToeGameField.saveFieldState("XXXYYYXXX"));

        assertEquals("Player turn: Y can be only 'X' or 'O' or ' '",exception.getMessage());

    }
}
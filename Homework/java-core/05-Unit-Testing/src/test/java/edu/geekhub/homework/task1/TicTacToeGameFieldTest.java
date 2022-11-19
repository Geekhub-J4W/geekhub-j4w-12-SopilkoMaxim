package edu.geekhub.homework.task1;

import org.junit.jupiter.api.Test;

import static edu.geekhub.homework.util.NotImplementedException.TODO_TYPE;
import static org.junit.jupiter.api.Assertions.*;

class TicTacToeGameFieldTest {
    private final TicTacToeGameField ticTacToeGameField = new TicTacToeGameField();

    @Test
    void generateEmptyField() {
        assertThrows(IllegalArgumentException.class,
                () -> ticTacToeGameField.generateField(" "));
    }

    @Test
    void putTenCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> ticTacToeGameField.generateField("XOXOXOXOXOXOXO"));
    }

    @Test
    void tryToPutIllegalCharacters() {
        assertThrows(IllegalArgumentException.class,
                () -> ticTacToeGameField.generateField("abcd"));
    }

   /* @Test
    void tryToGenerateField()
    {
        String compare = "+-----+\n|X|O|X|\n| | | |\n|O|X|O|\n+-----+";
        assertEquals(compare,ticTacToeGameField.generateField("XOX   OXO"));
    }*/

    @Test
    void tryToSaveFieldState() {
        String compare = "XOXOXOXOX";
        ticTacToeGameField.generateField(compare);
        assertEquals(compare, ticTacToeGameField.saveFieldState(compare));
    }

}
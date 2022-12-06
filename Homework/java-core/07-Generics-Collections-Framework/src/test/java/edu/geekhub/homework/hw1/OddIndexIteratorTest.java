package edu.geekhub.homework.hw1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OddIndexIteratorTest {
    private OddIndexIterable<Integer> integers;

    @BeforeEach
    void setUp() {
        integers = new OddIndexIterable<>();
    }

    @Test
    void tryToAddSixElementsAndReturnThree() {
        int counter =0;
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        integers.add(5);
        integers.add(6);

        for (Integer integer : integers)
            counter++;
        assertEquals(3,counter );
    }


}
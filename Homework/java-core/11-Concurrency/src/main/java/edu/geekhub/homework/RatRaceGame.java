package edu.geekhub.homework;

import edu.geekhub.homework.field.EmptyField;
import edu.geekhub.homework.field.Field;
import edu.geekhub.homework.field.Fieldable;
import edu.geekhub.homework.players.Car;

import java.util.Random;

public class RatRaceGame {

    private int sizeX;
    private int sizeY;
    //private Fieldable[][] field;

    private boolean isGameOver = false;

    public static void main(String[] args) {
            Field field = new Field().generateGameField();
        Random random = new Random();
        Car car = new Car();
    }

}

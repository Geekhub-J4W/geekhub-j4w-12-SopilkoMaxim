package edu.geekhub.homework;

import edu.geekhub.homework.field.Field;
import edu.geekhub.homework.players.Motorcycle;

public class RatRaceGameStarter {


    public static void main(String[] args) {
        Field field = new Field().generateGameField();

        Thread carThread = new Thread(new Motorcycle(field));
        carThread.start();
            try {
                carThread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }



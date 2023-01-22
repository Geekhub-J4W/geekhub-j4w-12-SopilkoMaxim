package edu.geekhub.homework;

import edu.geekhub.homework.field.Field;
import edu.geekhub.homework.players.Car;
import edu.geekhub.homework.players.Motorcycle;
import edu.geekhub.homework.players.Truck;

import java.util.Random;

public class RatRaceGameStarter {

    public static volatile Field field;

    public static void main(String[] args) {
        field = new Field().generateGameField();

        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            playersSpawn();
        }
    }

    public static void playersSpawn() {
        Random random = new Random();
        int randomPlayer = random.nextInt(3);
        switch (randomPlayer) {
            case 0: {
                Thread motoThread = new Thread(new Motorcycle(field));
                motoThread.start();
                break;
            }
            case 1: {
                Thread carThread = new Thread(new Car(field));
                carThread.start();
                break;
            }
            case 2: {
                Thread truckThread = new Thread(new Truck(field));
                truckThread.start();
                break;
            }
        }
    }

}



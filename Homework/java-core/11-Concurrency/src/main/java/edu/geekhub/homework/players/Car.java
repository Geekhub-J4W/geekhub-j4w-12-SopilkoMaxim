package edu.geekhub.homework.players;

import edu.geekhub.homework.field.Field;
import edu.geekhub.homework.field.Fieldable;

import java.util.Random;

public class Car extends Transport implements Runnable, Fieldable {

    public final int steps = 1;
    public final int sleepTime = 200;
    public Car(Field field){
        super(field);
        this.name = "Car";
    }


    @Override
    public void run() {

        PlayerController playerController = new PlayerController(field);
        playerController.movebility(new Car(field),steps,sleepTime);


    }

    @Override
    public String getFieldValue() {
        return "c";
    }
}

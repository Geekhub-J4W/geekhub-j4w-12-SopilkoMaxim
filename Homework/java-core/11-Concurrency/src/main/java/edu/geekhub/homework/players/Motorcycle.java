package edu.geekhub.homework.players;

import edu.geekhub.homework.field.Field;
import edu.geekhub.homework.field.Fieldable;

public class Motorcycle extends Transport implements Runnable, Fieldable {

    public final int steps = 2;
    public final int sleepTime = 300;
    public Motorcycle(Field field) {
        super(field);
        this.name = "Motocycle";
    }

    @Override
    public void run() {
        PlayerController playerController = new PlayerController(field);
        playerController.movebility(new Motorcycle(field),steps,sleepTime);
    }

    @Override
    public String getFieldValue() {
        return "m";
    }
}

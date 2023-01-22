package edu.geekhub.homework.players;

import edu.geekhub.homework.field.Field;
import edu.geekhub.homework.field.Fieldable;

public class Truck extends Transport implements Runnable, Fieldable {

    public final int steps = 1;
    public final int sleepTime = 400;
    public Truck(Field field) {
        super(field);
        this.name = "Truck";
    }

    @Override
    public void run() {

        PlayerController playerController = new PlayerController(field);
        playerController.movebility(new Truck(field),steps,sleepTime);

    }

    @Override
    public String getFieldValue() {
        return "t";
    }
}

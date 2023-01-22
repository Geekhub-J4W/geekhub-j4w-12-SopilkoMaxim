package edu.geekhub.homework.players;

import edu.geekhub.homework.field.Fieldable;

public class Truck extends Transport implements Runnable, Fieldable {

    public static final String name = "Truck";

    @Override
    public void run() {

    }

    @Override
    public String getFieldValue() {
        return "t";
    }
}

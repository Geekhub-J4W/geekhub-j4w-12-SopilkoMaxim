package edu.geekhub.homework.players;

import edu.geekhub.homework.field.Fieldable;

public class Motorcycle extends Transport implements Runnable, Fieldable {

    public static final String name = "Motorcycle";

    @Override
    public void run() {

    }

    @Override
    public String getFieldValue() {
        return "m";
    }
}

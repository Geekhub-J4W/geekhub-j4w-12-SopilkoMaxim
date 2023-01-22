package edu.geekhub.homework.players;

import edu.geekhub.homework.field.Fieldable;

public class Car extends Transport implements Runnable, Fieldable {

    public static final String name = "Car";
    @Override
    public void run() {

    }

    @Override
    public String getFieldValue() {
        return "c";
    }
}

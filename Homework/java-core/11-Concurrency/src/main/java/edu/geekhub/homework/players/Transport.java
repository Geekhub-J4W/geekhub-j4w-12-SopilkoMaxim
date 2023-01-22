package edu.geekhub.homework.players;

import edu.geekhub.homework.field.Field;
import edu.geekhub.homework.field.Fieldable;
import edu.geekhub.homework.field.StartField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class Transport implements Fieldable {

    private int coordinateX;
    private int coordinateY;

    private String color;

    public static String name=null;
    private Fieldable prevValue;

    public Field field = null;

    public Transport(Field field) {
        this.field=field;
        ArrayList<String> colors = new ArrayList<String>();
        colors.add("Green");
        colors.add("Red");
        colors.add("Blue");
        Random random = new Random();
        int randomColor= random.nextInt(3);
        this.color = colors.get(randomColor);
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public String getColor() {
        return color;
    }

    public Fieldable getPrevValue() {
        return prevValue;
    }

    public void setPrevValue(Fieldable prevValue) {
        this.prevValue = prevValue;
    }

    public void setCoordinate(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }



}

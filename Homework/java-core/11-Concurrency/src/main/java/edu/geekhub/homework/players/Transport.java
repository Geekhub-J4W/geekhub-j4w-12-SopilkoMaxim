package edu.geekhub.homework.players;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class Transport {

    private int coordinateX;
    private int coordinateY;

    private String color;

    public Transport() {
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

    public void setCoordinate(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }



}

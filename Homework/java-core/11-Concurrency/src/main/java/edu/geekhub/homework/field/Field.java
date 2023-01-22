package edu.geekhub.homework.field;

import edu.geekhub.homework.players.Transport;

import java.util.Arrays;
import java.util.Random;

public class Field {

    private final int sizeX;
    private final int sizeY;

    static int endPointX;
    static int endPointY;

    public static final int STARTX = 20;
    public static final int STARTY = 20;



    private final Fieldable[][] field;

    public Field(int x, int y) {
        this.sizeX = x;
        this.sizeY = y;
        field = new Fieldable[x][y];
    }
    public Field() {
        this.sizeX =0;
        this.sizeY =0;
        field = new Fieldable[sizeX][sizeY];
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public Fieldable getFieldable(int x, int y) {
        return field[x][y];
    }

    public void setFieldable(int x, int y, Fieldable someField) {

        field[x][y] = someField;

    }

    public Field generateGameField() {

        Field gameField = new Field(40, 40);
        markAllFieldsBlank(gameField);
        generateStartField(gameField);
        generateEmptyFields(gameField);
        addBottomField(gameField,new FinishField());

        //drawField(gameField);
        return gameField;

    }



    private void generateEmptyFields(Field gameField) {
        Random random = new Random();

        for (int i = 0; i < 3; i++) {

            int randomNumber = random.nextInt(12) + 1;
            if (randomNumber <= 3) addLeftField(gameField,new EmptyField());
            if (randomNumber > 3 && randomNumber <= 6) addRightField(gameField,new EmptyField());
            if (randomNumber > 6 && randomNumber <= 9) addBottomField(gameField,new EmptyField());
            if (randomNumber > 9) addTopField(gameField, new EmptyField());
        }

    }

    private void markAllFieldsBlank(Field field) {
        for (int i = 0; i < field.sizeX; i++)
            for (int j = 0; j < field.sizeY; j++)
                field.setFieldable(i, j, new BlankField());
    }

    private void generateStartField(Field field) {
        Field startField = new Field(3, 3);
        for (int i = STARTX; i < startField.sizeX + 20; i++)
            for (int j = STARTY; j < startField.sizeY + 20; j++)
                field.setFieldable(i, j, new StartField());
        endPointX = STARTX;
        endPointY = STARTY+3;
    }

    private void addBottomField(Field gameField,Fieldable fieldValue) {
        if(gameField.getFieldable(endPointX+4,endPointY).getFieldValue()=="s")
            addTopField(gameField,fieldValue);
        else {
            for (int i = endPointX + 3; i < endPointX + 6; i++) {

                for (int j = endPointY - 3; j < endPointY; j++){
                    gameField.setFieldable(i, j, fieldValue);}
            }
            endPointX += 3;
        }
    }
    private void addTopField(Field gameField, Fieldable fieldValue) {
        if(gameField.getFieldable(endPointX-4,endPointY).getFieldValue()=="s")
            addBottomField(gameField,fieldValue);
        else {
            for (int i = endPointX - 3; i < endPointX; i++) {

                for (int j = endPointY - 3; j < endPointY; j++){
                    gameField.setFieldable(i, j,fieldValue);}
            }
            endPointX -= 3;
        }
    }
    private void addLeftField(Field gameField,Fieldable fieldValue) {
        if(gameField.getFieldable(endPointX,endPointY-4).getFieldValue()=="s")
            addRightField(gameField,fieldValue);
        else {
            for (int i = endPointX; i < endPointX + 3; i++) {

                for (int j = endPointY - 6; j < endPointY - 3; j++){
                    gameField.setFieldable(i, j, fieldValue);}
            }
            endPointY -= 3;
        }
    }
    private void addRightField(Field gameField,Fieldable fieldValue) {
        if(gameField.getFieldable(endPointX,endPointY+4).getFieldValue()=="s")
            addLeftField(gameField,fieldValue);
        else {
            for (int i = endPointX; i < endPointX + 3; i++) {

                for (int j = endPointY; j < endPointY + 3; j++){
                    if(gameField.getFieldable(i,j).getFieldValue()=="s")
                    {addLeftField(gameField,new EmptyField());
                    break;}
                    gameField.setFieldable(i, j, fieldValue);}
            }
            endPointY += 3;
        }
    }

     public void drawField(Field field) {

        for (int i = 0; i < field.sizeX; i++) {
            System.out.println();
            for (int j = 0; j < field.sizeY; j++)
                System.out.printf(field.getFieldable(i, j).getFieldValue());
        }

    }

}

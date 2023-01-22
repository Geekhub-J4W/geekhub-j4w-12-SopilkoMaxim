package edu.geekhub.homework.players;

import edu.geekhub.homework.field.Field;

import java.util.Random;

public class PlayerController {
    Field field;

    public PlayerController(Field field) {
        this.field = field;
    }

    public void addPlayerOnTheStartField(Transport transport) {
        Random random = new Random();
        boolean flag = true;
        do {
            int startX = random.nextInt(3) + Field.STARTX;
            int startY = random.nextInt(3) + Field.STARTY;
            if (field.getFieldable(startX, startY).getFieldValue() == "s"){
                transport.setPrevValue(field.getFieldable(startX,startY));
                field.setFieldable(startX, startY, transport);
                transport.setCoordinate(startX,startY);
                flag=false;
            }
        }
        while (flag);
    }
    public boolean movePlayerOnTheField(Transport player, int newX,int newY){
        field.setFieldable(player.getCoordinateX(),player.getCoordinateY(),player.getPrevValue());
        if(field.getFieldable(newX,newY).getFieldValue().isBlank()){
            System.out.println(player.getColor() + " "+ player.name + " left the road");
            return false;
        }
        if(field.getFieldable(newX,newY).getFieldValue()=="f")
        {
            System.out.println(player.getColor() + " " + player.name + "is a winner!!!");
            field.setFieldable(newX,newY,player);
            field.drawField(field);
            return false;
        }
        //if(field.getFieldable(newX,newY))

        field.setFieldable(newX,newY,player);
        return true;
    }


    public void movebility(Transport transport, int steps, int sleepTime){
        Random random = new Random();
        int randomSide;
        int tmpX;
        int tmpY;
        addPlayerOnTheStartField(transport);
        tmpX=transport.getCoordinateX();
        tmpY= transport.getCoordinateY();
        do{
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            randomSide=random.nextInt(3);
            switch (randomSide){
                case 0: tmpX-=steps; break;
                case 1: tmpX+=steps; break;
                case 2: tmpY-=steps; break;
                case 3: tmpY+=sleepTime; break;
            }

        }while (movePlayerOnTheField(transport,tmpX,tmpY));
    }
}

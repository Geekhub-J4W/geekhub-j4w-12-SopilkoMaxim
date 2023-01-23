package edu.geekhub.homework.players;

import edu.geekhub.homework.field.EmptyField;
import edu.geekhub.homework.field.Field;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class PlayerController {
    volatile Field field;

    public PlayerController(Field field) {
        this.field = field;
    }

    public synchronized void addPlayerOnTheStartField(Transport transport) {
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
    public synchronized boolean movePlayerOnTheField(Transport player, int newX,int newY){
        Date date = new Date();
        field.setFieldable(player.getCoordinateX(),player.getCoordinateY(),player.getPrevValue());
        if(field.getFieldable(newX,newY).getFieldValue().isBlank()){
            System.out.println(player.getColor() + " "+ player.name + " left the road");
            return false;
        }
        if(field.getFieldable(newX,newY).getFieldValue()=="f")
        {
            System.out.println(player.getColor() + " " + player.name + " is a winner!!!");
            field.setFieldable(newX,newY,player);
            field.drawField(field);
            System.exit(0);
            return false;
        }
        if(field.getFieldable(newX,newY).getFieldValue()=="c"){
            Car crashCar =(Car) field.getFieldable(newX,newY);
            System.out.println(player.getColor() + " " + player.name + " crashed into "
                    + crashCar.getColor()+ " " + crashCar.name + " at " + date
                    + " at coordinates X:" + newX + " Y:" + newY);
            field.setFieldable(newX,newY,new EmptyField());
            return false;}
        if(field.getFieldable(newX,newY).getFieldValue()=="m"){
            Motorcycle crashMoto =(Motorcycle) field.getFieldable(newX,newY);
            System.out.println(player.getColor() + " " + player.name + " crashed into "
                    + crashMoto.getColor()+ " " + crashMoto.name + " at " + date
                    + " at coordinates X:" + newX + " Y:" + newY);
            field.setFieldable(newX,newY,new EmptyField());
            return false;}
        if(field.getFieldable(newX,newY).getFieldValue()=="t"){
            Truck crashTruck =(Truck) field.getFieldable(newX,newY);
            System.out.println(player.getColor() + " " + player.name + " crashed into "
                    + crashTruck.getColor()+ " " + crashTruck.name + " at " + date
                    + " at coordinates X:" + newX + " Y:" + newY);
            field.setFieldable(newX,newY,new EmptyField());
            return false;}
        player.setPrevValue(field.getFieldable(newX,newY));
        field.setFieldable(newX,newY,player);
        player.setCoordinate(newX,newY);

        return true;
    }


    public synchronized void movebility(Transport transport, int steps, int sleepTime){
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
            randomSide=random.nextInt(4);
            switch (randomSide){
                case 0: tmpX-=steps; break;
                case 1: tmpX+=steps; break;
                case 2: tmpY-=steps; break;
                case 3: tmpY+=steps; break;
            }

        }while (movePlayerOnTheField(transport,tmpX,tmpY));
    }
}

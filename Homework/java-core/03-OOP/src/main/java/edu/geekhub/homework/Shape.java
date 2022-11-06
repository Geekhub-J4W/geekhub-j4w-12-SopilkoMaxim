package edu.geekhub.homework;

import java.util.Scanner;

public abstract class Shape implements InterfaceShape {
    private final String color;

    public Shape() {
        System.out.println("Do you want to change a color?(Y/N):");
        Scanner scan = new Scanner(System.in);
        String answer = scan.next();
        if (answer.equals("Y")||answer.equals("y")) {
            System.out.println("Choose the color: \n 1 Red \n 2 Blue \n 3 Green");
            int colornumber = scan.nextInt();
            switch (colornumber){
                case 1 : {this.color = "Red"; break;}
                case 2 : {this.color = "Blue"; break;}
                case 3 : {this.color = "Green"; break;}
                default: {
                    System.out.println("You entered wrong number");
                    this.color = "black";
                    break;
                }
            }

        }
        else {
            this.color = "black";
        }
    }

    public abstract String getName();

    public String getColor() {
        return color;
    }
}

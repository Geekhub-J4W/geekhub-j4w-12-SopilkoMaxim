package edu.geekhub.orcostat;

import edu.geekhub.orcostat.model.*;

import java.util.Scanner;

public class ApplicationStarter {
    public static void main(String[] args) {
        System.out.println("Menu:");
        int menu=0;
        int orcs=0;
        int tanks=0;
        OrcoStatService orcoStatService = new OrcoStatService();
        while(menu!=5) {
            System.out.println("Menu:");
            System.out.println("1.Add orc \n2.Add tank\n3.Add Armoured Personnel Vehicle \n4.Show statistic\n5 Exit");
            Scanner scan = new Scanner(System.in);
            menu = scan.nextInt();
            menu = checkNumbers(menu, 4);
            if (menu == 1) {

                System.out.println("Enter numbers of orcs:");
                orcs=scan.nextInt();
                System.out.println("Enter date:");
                String dateOfOrcsAdd = scan.next();
                orcoStatService.addNegativelyAliveOrc(new Orc(orcs,dateOfOrcsAdd));
            }
            if (menu == 2) {
                System.out.println("Enter numbers of tanks:");
                tanks = scan.nextInt();
                System.out.println("Enter number of equipage");
                int numberOfEquipage = scan.nextInt();
                System.out.println("Enter date of add:");
                String dateOfTankAdd = scan.next();
                orcoStatService.addNegativelyAliveOrc(new Orc(numberOfEquipage,dateOfTankAdd));
                orcoStatService.addDestroyedTank(new Tank(tanks,dateOfTankAdd));
            }
            if (menu == 3){
                System.out.println("Enter numbers of APV:");
                int apv = scan.nextInt();
                System.out.println("Enter number of equipage");
                int numberOfEquipage = scan.nextInt();
                System.out.println("Enter date of add:");
                String dateOfAPVadd = scan.next();
                orcoStatService.addNegativelyAliveOrc(new Orc(numberOfEquipage,dateOfAPVadd));
                orcoStatService.addDestroyedAPV(new APV(dateOfAPVadd,apv));
            }
            if(menu==4)
            {
                orcoStatService.printStat();
            }
        }

    }



    public static int checkNumbers(int enterNumber, int maxExpectedNumber) {
        while (enterNumber < 0 || enterNumber > maxExpectedNumber) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Wrong number,try again:");
            enterNumber = scan.nextInt();

        }
        return enterNumber;
    }
}


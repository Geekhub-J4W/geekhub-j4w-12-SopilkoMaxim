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
        StatBase.fillStatistic(orcoStatService);
        while(menu!=5) {
            System.out.println("Menu:");
            System.out.println("1.Add orc \n2.Add tank\n3.Add Armoured Personnel Vehicle \n4.Show statistic\n5 Exit");
            Scanner scan = new Scanner(System.in);
            menu = scan.nextInt();
            menu = ValidateEnterData.validateMenuNumber(menu, 5);
            if (menu == 1) {

                System.out.println("Enter numbers of orcs:");
                orcs=ValidateEnterData.validateNumber();
                System.out.println("Enter date:");
                String dateOfOrcsAdd = ValidateEnterData.validateDate();
                orcoStatService.addNegativelyAliveOrc(new Orc(dateOfOrcsAdd, orcs));
            }
            if (menu == 2) {
                System.out.println("Enter numbers of tanks:");
                tanks = ValidateEnterData.validateNumber();
                System.out.println("Enter number of equipage");
                int numberOfEquipage = ValidateEnterData.validateNumber();
                System.out.println("Enter date of add:");
                String dateOfTankAdd = scan.next();
                orcoStatService.addNegativelyAliveOrc(new Orc(dateOfTankAdd, numberOfEquipage));
                orcoStatService.addDestroyedTank(new Tank(dateOfTankAdd, tanks));
            }
            if (menu == 3){
                System.out.println("Enter numbers of APV:");
                int apv = ValidateEnterData.validateNumber();
                System.out.println("Enter number of equipage");
                int numberOfEquipage = ValidateEnterData.validateNumber();
                System.out.println("Enter date of add:");
                String dateOfAPVadd = scan.next();
                orcoStatService.addNegativelyAliveOrc(new Orc(dateOfAPVadd, numberOfEquipage));
                orcoStatService.addDestroyedAPV(new APV(dateOfAPVadd,apv));
            }
            if(menu==4)
            {
                orcoStatService.printStat();
            }
        }

    }

}


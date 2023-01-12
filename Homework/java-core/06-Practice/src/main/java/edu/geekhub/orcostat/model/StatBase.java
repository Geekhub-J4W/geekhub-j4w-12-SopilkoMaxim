package edu.geekhub.orcostat.model;

import edu.geekhub.orcostat.OrcoStatService;

public class StatBase {
    public static void fillStatistic(OrcoStatService orcoStatService){

        orcoStatService.addDestroyedAPV(new APV("22.10.2022",5));
        orcoStatService.addDestroyedAPV(new APV("23.10.2022",7));
        orcoStatService.addDestroyedAPV(new APV("24.10.2022",4));
        orcoStatService.addDestroyedAPV(new APV("25.10.2022",3));
        orcoStatService.addDestroyedAPV(new APV("26.10.2022",1));

        orcoStatService.addNegativelyAliveOrc(new Orc("22.10.2022",100));
        orcoStatService.addNegativelyAliveOrc(new Orc("23.10.2022",200));
        orcoStatService.addNegativelyAliveOrc(new Orc("24.10.2022",300));
        orcoStatService.addNegativelyAliveOrc(new Orc("25.10.2022",250));
        orcoStatService.addNegativelyAliveOrc(new Orc("26.10.2022",150));

        orcoStatService.addDestroyedTank(new Tank("22.10.2022",5));
        orcoStatService.addDestroyedTank(new Tank("23.10.2022",15));
        orcoStatService.addDestroyedTank(new Tank("24.10.2022",25));
        orcoStatService.addDestroyedTank(new Tank("25.10.2022",35));
        orcoStatService.addDestroyedTank(new Tank("26.10.2022",5));

    }
}

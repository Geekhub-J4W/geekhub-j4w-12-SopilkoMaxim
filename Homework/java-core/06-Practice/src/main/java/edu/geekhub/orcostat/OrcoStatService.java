package edu.geekhub.orcostat;

import edu.geekhub.orcostat.model.*;

import java.util.ArrayList;
import java.util.Comparator;

public class OrcoStatService {
    private final TrivialCollection negativeAliveOrcs;
    private final TrivialCollection destroyedTanks;

    private final TrivialCollection destroyedAPV;

    public OrcoStatService() {
        this.negativeAliveOrcs = new TrivialCollection();
        this.destroyedTanks = new TrivialCollection();
        this.destroyedAPV = new TrivialCollection();
    }

    public int getNegativelyAliveOrcCount() {
        return negativeAliveOrcs.count();
    }

    public void addNegativelyAliveOrc(Orc orc) {
        boolean flag = true;
        for (Object orcSearch : negativeAliveOrcs.getData())
        {
            if(orc.getAddDate().equals(((Orc)orcSearch).getAddDate())) {
                ((Orc) orcSearch).setNumber(((Orc) orcSearch).getNumber() + orc.getNumber());
                flag=false;
            }
       }
        if(flag)negativeAliveOrcs.add(orc);
    }

    public int getDestroyedTanksCount() {
        return destroyedTanks.count();
    }

    public void addDestroyedTank(Tank tank) {
        destroyedTanks.add(tank);

        for (Object orc : tank.getEquipage().getData()) {
            negativeAliveOrcs.add(orc);
        }
    }

    public void addDestroyedAPV(APV apv){
        destroyedAPV.add(apv);
    }
     public int getDestroyedAPVcount(){
        return destroyedAPV.count();
     }

    public int getTotalDeadOrcs() {
        int totalOrcs = 0;
        for (Object orc : negativeAliveOrcs.getData()) {
            totalOrcs += ((Orc) orc).getNumber();
        }
        return totalOrcs;
    }

    public int getTotalDeadTanks() {
        int totalTanks = 0;
        for (Object tank : destroyedTanks.getData()) {
            totalTanks += ((Tank) tank).getNumber();
        }
        return totalTanks;

    }

    public int getTotalDeadAPV(){
        int totalAPV =0;
        for (Object apv : destroyedAPV.getData()) {
            totalAPV += ((APV) apv).getNumber();
        }
        return totalAPV;
    }

    public int getLosesInDollars() {
        int totalPrice = (getTotalDeadOrcs() * Orc.getPrice())+(getTotalDeadTanks() * Tank.getPrice())+(getTotalDeadAPV()*APV.getPrice());
        return totalPrice;
    }
    public void printStat(){
        printSomeLoses(Orc.name,negativeAliveOrcs);
        printSomeLoses(Tank.name,destroyedTanks);
        printSomeLoses(APV.name,destroyedAPV);
        System.out.printf("Total %s $ lost\n",getLosesInDollars());

    }

    public void printSomeLoses(String name,TrivialCollection trivialCollection)
    {
        System.out.println(name+":\nData      Number");
        ArrayList<Integer> allLoses = new ArrayList<>();
        for (Object loses : trivialCollection.getData()) {
            allLoses.add(((SomeLoses)loses).getNumber());
        }
        int max = allLoses.stream().max(Comparator.comparing(Integer::valueOf)).get();

        for (Object loses : trivialCollection.getData()) {
            System.out.printf("%s %s ",((SomeLoses) loses).getAddDate(),((SomeLoses)loses).getNumber());
            for(int i=0;i<((SomeLoses)loses).getNumber()*50/max;i++)
                System.out.print("#");
            System.out.println("");
        }
    }

}

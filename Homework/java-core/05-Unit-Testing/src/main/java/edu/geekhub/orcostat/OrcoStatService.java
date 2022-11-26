package edu.geekhub.orcostat;

import edu.geekhub.orcostat.model.APV;
import edu.geekhub.orcostat.model.Orc;
import edu.geekhub.orcostat.model.Tank;
import edu.geekhub.orcostat.model.TrivialCollection;

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
            totalTanks += ((Tank) tank).getNumberOfTanks();
        }
        return totalTanks;

    }

    public int getTotalDeadAPV(){
        int totalAPV =0;
        for (Object apv : destroyedAPV.getData()) {
            totalAPV += ((APV) apv).getNumberOfAPV();
        }
        return totalAPV;
    }

    public int getLosesInDollars() {
        int totalPrice = (getTotalDeadOrcs() * Orc.getPrice())+(getTotalDeadTanks() * Tank.getPrice())+(getTotalDeadAPV()*APV.getPrice());
        return totalPrice;
    }
    public void printStat(){
        System.out.println("Orcs:\nData      Number");
        for (Object orc : negativeAliveOrcs.getData()) {
            System.out.printf("%s %s \n",((Orc) orc).getAddDate(),((Orc)orc).getNumber());
        }
        System.out.println("\nTanks:\nData      Number");
        for (Object tank : destroyedTanks.getData()) {
            System.out.printf("%s %s \n",((Tank) tank).getAddDate(),((Tank)tank).getNumberOfTanks());
        }
        System.out.println("\nAPV:\nData      Number");
        for (Object apv : destroyedAPV.getData()) {
            System.out.printf("%s %s \n",((APV) apv).getAddDate(),((APV)apv).getNumberOfAPV());
        }
        System.out.printf("Total %s $ lost\n",getLosesInDollars());

    }

}

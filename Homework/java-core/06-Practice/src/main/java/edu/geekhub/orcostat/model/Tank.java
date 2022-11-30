package edu.geekhub.orcostat.model;



public class Tank {
    private final TrivialCollection equipage;
    private String addDate ;
    private int numberOfTanks=0;


    public Tank(TrivialCollection equipage) {
        /*if (equipage.count() > 6) {
            throw new IllegalArgumentException("Too many orcs");
        }*/

        this.equipage = equipage;

    }

    public Tank(int numberOfTanks,String dateOfAdd) {
        this(new TrivialCollection()
        );
        this.numberOfTanks=numberOfTanks;
        this.addDate=dateOfAdd;
    }

    public TrivialCollection getEquipage() {
        return equipage;
    }

    public static int getPrice() {
        return 3_000_000;
    }

    public int getNumberOfTanks() {return this.numberOfTanks;}
    public String getAddDate(){return this.addDate;}
}

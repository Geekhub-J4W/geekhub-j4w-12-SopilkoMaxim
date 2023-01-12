package edu.geekhub.orcostat.model;



public class Tank extends SomeLoses{
    private final TrivialCollection equipage;

    public static String name ="Tank";



    public Tank(TrivialCollection equipage) {
        /*if (equipage.count() > 6) {
            throw new IllegalArgumentException("Too many orcs");
        }*/

        this.equipage = equipage;

    }

    public Tank(String dateOfAdd, int numberOfTanks) {
        this(new TrivialCollection()
        );
        this.number=numberOfTanks;
        this.addDate=dateOfAdd;
    }

    public TrivialCollection getEquipage() {
        return equipage;
    }

    public static int getPrice() {
        return 3_000_000;
    }


}

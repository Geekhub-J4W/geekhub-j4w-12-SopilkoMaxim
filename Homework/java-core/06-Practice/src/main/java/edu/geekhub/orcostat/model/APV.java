package edu.geekhub.orcostat.model;

public class APV extends SomeLoses{

    private static final int price = 5_000_000;

    public static String name = "APV";

    public APV(String addDate, int numberOfAPV) {
        this.addDate = addDate;
        this.number = numberOfAPV;
    }

        public static int getPrice()
    {
        return price;
    }

}

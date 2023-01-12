package edu.geekhub.orcostat.model;

public class Orc extends SomeLoses{
    private static final int LADA_VESTA_SPORT_PRICE = 10_000;
    private static int price;

    public static String name = "Orc";


    public Orc(String addDate, int number) {
        this.price=LADA_VESTA_SPORT_PRICE;
        super.addDate = addDate;
        super.number=number;
    }


    public static int getPrice() {
        return price;
    }

    public void setNumber(int number)
    {
        this.number=number;
    }
}

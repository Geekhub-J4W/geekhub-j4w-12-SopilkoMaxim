package edu.geekhub.orcostat.model;

public class Orc {
    private static final int LADA_VESTA_SPORT_PRICE = 10_000;
    private static int price;
    private String addDate;
    int number;

    public Orc(int number,String addDate) {
        this.price=LADA_VESTA_SPORT_PRICE;
        this.addDate = addDate;
        this.number=number;
    }

   /* public Orc(int price) {
        this.price = price;
    }*/

    public static int getPrice() {
        return price;
    }

    public int getNumber(){ return number;}

    public String getAddDate(){return addDate;}

    public void setNumber(int number)
    {
        this.number=number;
    }
}

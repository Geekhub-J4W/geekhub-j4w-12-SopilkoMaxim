package edu.geekhub.orcostat.model;

public class APV {
    private String addDate ;
    private int numberOfAPV=0;
    private static final int price = 5_000_000;
    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public APV(String addDate, int numberOfAPV) {
        this.addDate = addDate;
        this.numberOfAPV = numberOfAPV;
    }

    public void setNumberOfAPV(int numberOfAPV) {
        this.numberOfAPV = numberOfAPV;
    }



    public String getAddDate() {
        return addDate;
    }

    public int getNumberOfAPV() {
        return numberOfAPV;
    }

    public static int getPrice()
    {
        return price;
    }

}

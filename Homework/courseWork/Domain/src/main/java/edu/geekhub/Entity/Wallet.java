package edu.geekhub.Entity;

public class Wallet {

    int id;
    private float lse;
    private float bitcoin;
    private float ethereum;
    private float bnb;
    private float xrp;
    private float polygon;
    private float tether;
    private float usd;
    private float cardano;
    private float dogecoin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLse() {
        return lse;
    }

    public void setLse(float lse) {
        this.lse = lse;
    }

    public float getBitcoin() {
        return bitcoin;
    }

    public void setBitcoin(float bitcoin) {
        this.bitcoin = bitcoin;
    }

    public float getEthereum() {
        return ethereum;
    }

    public void setEthereum(float ethereum) {
        this.ethereum = ethereum;
    }

    public float getBnb() {
        return bnb;
    }

    public void setBnb(float bnb) {
        this.bnb = bnb;
    }

    public float getXrp() {
        return xrp;
    }

    public void setXrp(float xrp) {
        this.xrp = xrp;
    }

    public float getPolygon() {
        return polygon;
    }

    public void setPolygon(float polygon) {
        this.polygon = polygon;
    }

    public float getTether() {
        return tether;
    }

    public void setTether(float tether) {
        this.tether = tether;
    }

    public float getUsd() {
        return usd;
    }

    public void setUsd(float usd) {
        this.usd = usd;
    }

    public float getCardano() {
        return cardano;
    }

    public void setCardano(float cardano) {
        this.cardano = cardano;
    }

    public float getDogecoin() {
        return dogecoin;
    }

    public void setDogecoin(float dogecoin) {
        this.dogecoin = dogecoin;
    }

    public float returnByName(String name)
    {
        switch (name)
        {
            case "lse": return lse;
            case "bitcoin": return bitcoin;
            case "ethereum": return ethereum;
            case "bnb": return bnb;
            case "xrp": return xrp;
            case "polygon": return polygon;
            case "tether": return tether;
            case "usd": return usd;
            case "cardano": return cardano;
            case "dogecoin": return dogecoin;
            default: return 0;
        }
    }

    public void setByName(float amount, String name) {
        switch (name)
        {
            case "lse": this.lse = amount;
            case "bitcoin": this.bitcoin=amount;
            case "ethereum": this.ethereum=amount;
            case "bnb": this.bnb=amount;
            case "xrp": this.xrp=amount;
            case "polygon": this.polygon=amount;
            case "tether": this.tether=amount;
            case "usd": this.usd=amount;
            case "cardano": this.cardano=amount;
            case "dogecoin": this.dogecoin=amount;
        }
    }
}

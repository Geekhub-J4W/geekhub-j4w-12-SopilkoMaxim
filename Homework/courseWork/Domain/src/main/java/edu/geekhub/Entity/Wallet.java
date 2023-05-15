package edu.geekhub.Entity;

public class Wallet {

    int id;
    private float lse;
    private float bitcoin;
    private float ethereum;
    private float bnb;
    private float xrp;
    private float solana;
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

    public float getSolana() {
        return solana;
    }

    public void setSolana(float solana) {
        this.solana = solana;
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

    public Wallet() {
    }

    public Wallet(int id,float lse, float bitcoin, float ethereum, float bnb, float xrp, float solana, float tether, float usd, float cardano, float dogecoin) {
        this.id=id;
        this.lse = lse;
        this.bitcoin = bitcoin;
        this.ethereum = ethereum;
        this.bnb = bnb;
        this.xrp = xrp;
        this.solana = solana;
        this.tether = tether;
        this.usd = usd;
        this.cardano = cardano;
        this.dogecoin = dogecoin;
    }
    public Wallet(float lse, float bitcoin, float ethereum, float bnb, float xrp, float solana, float tether, float usd, float cardano, float dogecoin) {
        this.lse = lse;
        this.bitcoin = bitcoin;
        this.ethereum = ethereum;
        this.bnb = bnb;
        this.xrp = xrp;
        this.solana = solana;
        this.tether = tether;
        this.usd = usd;
        this.cardano = cardano;
        this.dogecoin = dogecoin;
    }

    public float returnByName(String name)
    {
        switch (name)
        {
            case "lse", "Lido_Staked_Ether": return lse;
            case "bitcoin","btc": return bitcoin;
            case "ethereum": return ethereum;
            case "bnb": return bnb;
            case "xrp": return xrp;
            case "solana": return solana;
            case "tether": return tether;
            case "usd","usd_coin": return usd;
            case "cardano": return cardano;
            case "dogecoin": return dogecoin;
            default: return 0;
        }
    }

    public void setByName(float amount, String name) {
        switch (name)
        {
            case "lse", "Lido_Staked_Ether": {this.lse = amount;break;}
            case "bitcoin","btc": {this.bitcoin=amount;break;}
            case "ethereum": {this.ethereum=amount;break;}
            case "bnb": {this.bnb=amount;break;}
            case "xrp": {this.xrp=amount;break;}
            case "solana": {this.solana=amount;break;}
            case "tether": {this.tether=amount;break;}
            case "usd","usd_coin": {this.usd=amount;break;}
            case "cardano": {this.cardano=amount;break;}
            case "dogecoin": {this.dogecoin=amount;break;}
        }
    }
}

package edu.geekhub.Entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Transfer {
    private long id;
    private String coinName;
    private double amount;
    private LocalDateTime date;
    private double price;
    private int userId;

    private String operation;

    public Transfer(String coinName, double amount, LocalDateTime date, double price,int userId, String operation) {
        this.coinName = coinName;
        this.amount = (float)amount;
        this.date = date;
        this.price = (float)price;
        this.userId=userId;
        this.operation=operation;
    }


    public Transfer(long id, String coinName, float amount, LocalDateTime date, float price,String operation) {
        this.id = id;
        this.coinName = coinName;
        this.amount = amount;
        this.date = date;
        this.price = price;
        this.operation=operation;
    }

    public Transfer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}

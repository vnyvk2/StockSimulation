package com.stocksim.model;

import jakarta.persistence.*;

@Entity
@Table(name="stocks")
public class Stock {
    @Id
    private String symbol; // e.g., AAPL
    private String name;
    private Double price;

    public Stock(){}
    public Stock(String symbol, String name, Double price){
        this.symbol=symbol; this.name=name; this.price=price;
    }
    // getters/setters
    public String getSymbol(){return symbol;}
    public void setSymbol(String symbol){this.symbol=symbol;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public Double getPrice(){return price;}
    public void setPrice(Double price){this.price=price;}
}

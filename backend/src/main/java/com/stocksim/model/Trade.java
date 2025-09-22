package com.stocksim.model;

import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name="trades")
public class Trade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symbol;
    private Integer quantity;
    private Double price;
    @ManyToOne
    private User user;
    private boolean buy; // true = buy, false = sell
    private LocalDateTime timestamp = LocalDateTime.now();

    public Trade(){}
    // getters/setters...
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public String getSymbol(){return symbol;}
    public void setSymbol(String symbol){this.symbol=symbol;}
    public Integer getQuantity(){return quantity;}
    public void setQuantity(Integer quantity){this.quantity=quantity;}
    public Double getPrice(){return price;}
    public void setPrice(Double price){this.price=price;}
    public User getUser(){return user;}
    public void setUser(User user){this.user=user;}
    public boolean isBuy(){return buy;}
    public void setBuy(boolean buy){this.buy=buy;}
    public LocalDateTime getTimestamp(){return timestamp;}
    public void setTimestamp(LocalDateTime timestamp){this.timestamp=timestamp;}
}

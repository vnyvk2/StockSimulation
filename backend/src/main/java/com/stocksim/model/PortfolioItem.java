package com.stocksim.model;

import jakarta.persistence.*;

@Entity
@Table(name="portfolio")
public class PortfolioItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    private String symbol;
    private Integer quantity;

    public PortfolioItem(){}
    // getters/setters...
    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}
    public User getUser(){return user;}
    public void setUser(User user){this.user=user;}
    public String getSymbol(){return symbol;}
    public void setSymbol(String symbol){this.symbol=symbol;}
    public Integer getQuantity(){return quantity;}
    public void setQuantity(Integer quantity){this.quantity=quantity;}
}

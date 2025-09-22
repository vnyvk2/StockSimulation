package com.stocksim.model;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable=false)
    private String username;
    private String password;
    private Double cash = 100000.0; // starting virtual cash

    // getters/setters
    public User(){}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername(){return username;}
    public void setUsername(String username){this.username=username;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password=password;}
    public Double getCash(){return cash;}
    public void setCash(Double cash){this.cash=cash;}
}

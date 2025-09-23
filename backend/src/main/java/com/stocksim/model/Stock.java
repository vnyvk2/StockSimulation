package com.stocksim.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter; // Add this import
import lombok.Setter; // Add this import

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor // <-- This was the missing piece! It creates the constructor needed.
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;
    private double price;

    // This constructor is needed for the new StockService logic
    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}
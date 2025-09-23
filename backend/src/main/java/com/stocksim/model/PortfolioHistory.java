package com.stocksim.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data // This single annotation adds getters, setters, toString(), etc.
@NoArgsConstructor // Creates the default no-argument constructor
@AllArgsConstructor // Creates a constructor with all arguments
public class PortfolioHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private LocalDate date;
    private double value;

    // Getters and Setters
}
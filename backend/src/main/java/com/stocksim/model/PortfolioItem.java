package com.stocksim.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "portfolio")
@Data // Generates getters, setters, toString, etc.
@NoArgsConstructor // Generates the empty constructor: public PortfolioItem() {}
@AllArgsConstructor // Generates the constructor with all arguments
public class PortfolioItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Good practice to specify the join column
    private User user;

    @Column(nullable = false)
    private String symbol;

    @Column(nullable = false)
    private Integer quantity;

}
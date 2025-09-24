package com.stocksim.repository;

import com.stocksim.model.PortfolioItem;
import com.stocksim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
    // This method will find a portfolio item by user and stock symbol
    Optional<PortfolioItem> findByUserAndSymbol(User user, String symbol);
}
package com.stocksim.repository;

import com.stocksim.model.PortfolioHistory;
import com.stocksim.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PortfolioHistoryRepository extends JpaRepository<PortfolioHistory, Long> {
    List<PortfolioHistory> findByUser(User user);
}
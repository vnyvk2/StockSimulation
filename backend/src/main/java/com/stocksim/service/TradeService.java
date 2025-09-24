package com.stocksim.service;

import com.stocksim.model.PortfolioItem;
import com.stocksim.model.Stock;
import com.stocksim.model.User;
import com.stocksim.repository.PortfolioItemRepository; // <-- Add import
import com.stocksim.repository.UserRepository;         // <-- Add import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private StockService stockService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PortfolioItemRepository portfolioItemRepository; // <-- Use the new repository

    @Transactional // This ensures the entire trade operation is a single database transaction
    public void executeTrade(User user, String symbol, int quantity, String type) {
        Stock stock = stockService.getStockData(symbol);
        if (stock == null) {
            throw new RuntimeException("Stock not found: " + symbol);
        }

        double tradeValue = stock.getPrice() * quantity;
        Optional<PortfolioItem> portfolioItemOptional = portfolioItemRepository.findByUserAndSymbol(user, symbol);

        if (type.equalsIgnoreCase("BUY")) {
            if (user.getBalance() < tradeValue) {
                throw new RuntimeException("Insufficient balance");
            }

            user.setBalance(user.getBalance() - tradeValue);

            PortfolioItem portfolioItem = portfolioItemOptional.orElse(new PortfolioItem(null, user, symbol, 0));
            portfolioItem.setQuantity(portfolioItem.getQuantity() + quantity);
            portfolioItemRepository.save(portfolioItem);

        } else if (type.equalsIgnoreCase("SELL")) {
            PortfolioItem portfolioItem = portfolioItemOptional
                    .orElseThrow(() -> new RuntimeException("You do not own this stock"));

            if (portfolioItem.getQuantity() < quantity) {
                throw new RuntimeException("Insufficient stock quantity to sell");
            }

            user.setBalance(user.getBalance() + tradeValue);
            portfolioItem.setQuantity(portfolioItem.getQuantity() - quantity);

            if (portfolioItem.getQuantity() == 0) {
                portfolioItemRepository.delete(portfolioItem);
            } else {
                portfolioItemRepository.save(portfolioItem);
            }
        } else {
            throw new RuntimeException("Invalid trade type: " + type);
        }
        userRepository.save(user); // Save the updated user balance
    }
}
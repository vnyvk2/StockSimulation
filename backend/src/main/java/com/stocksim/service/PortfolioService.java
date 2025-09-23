package com.stocksim.service;

// V V V V Add all of these imports V V V V
import com.stocksim.model.PortfolioHistory;
import com.stocksim.model.User;
import com.stocksim.repository.PortfolioHistoryRepository;
import com.stocksim.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PortfolioService {

    @Autowired
    private PortfolioHistoryRepository portfolioHistoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockService stockService;

    @Scheduled(cron = "0 0 18 * * MON-FRI")
    public void recordPortfolioValues() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            double portfolioValue = user.getPortfolio().stream()
                    .mapToDouble(item -> item.getQuantity() * stockService.getStockData(item.getSymbol()).getPrice())
                    .sum();

            PortfolioHistory history = new PortfolioHistory();
            // user is a reserved keyword in some databases, so using setUserAccount
            history.setUser(user);
            history.setDate(LocalDate.now());
            history.setValue(portfolioValue);
            portfolioHistoryRepository.save(history);
        }
    }

    public List<PortfolioHistory> getPortfolioHistory(User user) {
        return portfolioHistoryRepository.findByUser(user);
    }
}
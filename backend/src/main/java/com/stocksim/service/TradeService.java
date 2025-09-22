package com.stocksim.service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import com.stocksim.model.*;
import com.stocksim.repository.*;

@Service
public class TradeService {
    private final TradeRepository tradeRepo;
    private final PortfolioRepository portRepo;
    private final StockRepository stockRepo;
    private final UserRepository userRepo;

    public TradeService(TradeRepository tradeRepo, PortfolioRepository portRepo,
                        StockRepository stockRepo, UserRepository userRepo){
        this.tradeRepo = tradeRepo; this.portRepo = portRepo; this.stockRepo = stockRepo; this.userRepo = userRepo;
    }

    @Transactional
    public Trade executeBuy(User user, String symbol, int qty){
        Stock stock = stockRepo.findById(symbol).orElseThrow();
        double cost = stock.getPrice() * qty;
        if(user.getCash() < cost) throw new RuntimeException("Insufficient cash");
        user.setCash(user.getCash() - cost);
        userRepo.save(user);

        PortfolioItem item = portRepo.findByUserIdAndSymbol(user.getId(), symbol);
        if(item==null){ item = new PortfolioItem(); item.setUser(user); item.setSymbol(symbol); item.setQuantity(qty); }
        else item.setQuantity(item.getQuantity()+qty);
        portRepo.save(item);

        Trade t = new Trade();
        t.setUser(user); t.setSymbol(symbol); t.setQuantity(qty); t.setPrice(stock.getPrice()); t.setBuy(true);
        return tradeRepo.save(t);
    }

    @Transactional
    public Trade executeSell(User user, String symbol, int qty){
        PortfolioItem item = portRepo.findByUserIdAndSymbol(user.getId(), symbol);
        if(item==null || item.getQuantity() < qty) throw new RuntimeException("Not enough shares");
        Stock stock = stockRepo.findById(symbol).orElseThrow();
        double proceeds = stock.getPrice() * qty;
        user.setCash(user.getCash() + proceeds);
        userRepo.save(user);

        item.setQuantity(item.getQuantity() - qty);
        portRepo.save(item);

        Trade t = new Trade();
        t.setUser(user); t.setSymbol(symbol); t.setQuantity(qty); t.setPrice(stock.getPrice()); t.setBuy(false);
        return tradeRepo.save(t);
    }

    public List<Trade> historyForUser(Long userId){ return tradeRepo.findByUserId(userId); }
}

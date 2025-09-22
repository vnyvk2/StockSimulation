package com.stocksim.service;

import org.springframework.stereotype.Service;
import org.springframework.scheduling.annotation.Scheduled;
import java.util.*;
import com.stocksim.repository.StockRepository;
import com.stocksim.model.Stock;

@Service
public class StockService {
    private final StockRepository stockRepo;
    public StockService(StockRepository stockRepo){ this.stockRepo = stockRepo; }

    // Simulate price updates every 5 seconds (for demo); in real app use real feed
    @Scheduled(fixedRate = 5000)
    public void randomWalkPrices(){
        List<Stock> all = stockRepo.findAll();
        Random r = new Random();
        for(Stock s: all){
            double change = (r.nextDouble() - 0.5) * 2.0; // -1..+1
            double newPrice = Math.max(1.0, s.getPrice() + change);
            s.setPrice(Math.round(newPrice * 100.0)/100.0);
        }
        stockRepo.saveAll(all);
    }

    public List<Stock> listAll(){ return stockRepo.findAll(); }
    public Optional<Stock> get(String symbol){ return stockRepo.findById(symbol); }
}

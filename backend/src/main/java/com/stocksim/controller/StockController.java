package com.stocksim.controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.stocksim.service.StockService;
import com.stocksim.model.Stock;

@RestController
@RequestMapping("/api/stocks")
public class StockController {
    private final StockService stockService;
    public StockController(StockService stockService){ this.stockService = stockService; }

    @GetMapping
    public List<Stock> list(){ return stockService.listAll(); }

    @GetMapping("/{symbol}")
    public Stock get(@PathVariable String symbol){
        return stockService.get(symbol).orElseThrow();
    }
}

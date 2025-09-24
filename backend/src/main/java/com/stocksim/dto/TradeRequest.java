package com.stocksim.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeRequest {

    // The stock symbol (e.g., "AAPL", "GOOGL")
    private String symbol;

    // The number of shares to trade
    private int quantity;

    // The type of trade: "BUY" or "SELL"
    private String type;

}
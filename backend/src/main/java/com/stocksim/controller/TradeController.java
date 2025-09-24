package com.stocksim.controller;

import com.stocksim.dto.TradeRequest;
import com.stocksim.model.User;
import com.stocksim.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping
    public ResponseEntity<?> executeTrade(@RequestBody TradeRequest tradeRequest, @AuthenticationPrincipal User user) {
        // We can directly use the 'user' object provided by @AuthenticationPrincipal
        try {
            tradeService.executeTrade(user,
                    tradeRequest.getSymbol(),
                    tradeRequest.getQuantity(),
                    tradeRequest.getType());
            return ResponseEntity.ok("Trade executed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
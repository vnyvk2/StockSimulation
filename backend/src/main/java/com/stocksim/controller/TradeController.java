package com.stocksim.controller;

import com.stocksim.dto.TradeRequest; // <-- Add import
import com.stocksim.model.User;
import com.stocksim.service.TradeService;
import com.stocksim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<?> executeTrade(@RequestBody TradeRequest tradeRequest, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            User user = (User) userDetails;
            tradeService.executeTrade(user, tradeRequest.getSymbol(), tradeRequest.getQuantity(), tradeRequest.getType());
            return ResponseEntity.ok("Trade executed successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
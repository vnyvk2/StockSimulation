package com.stocksim.controller;

// V V V V Add all of these imports V V V V
import com.stocksim.model.PortfolioHistory;
import com.stocksim.model.User;
import com.stocksim.service.PortfolioService;
import com.stocksim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private UserService userService;

    @GetMapping("/history")
    public ResponseEntity<List<PortfolioHistory>> getPortfolioHistory(@AuthenticationPrincipal UserDetails userDetails) {
        User user = (User) userService.loadUserByUsername(userDetails.getUsername());
        List<PortfolioHistory> history = portfolioService.getPortfolioHistory(user);
        return ResponseEntity.ok(history);
    }
}
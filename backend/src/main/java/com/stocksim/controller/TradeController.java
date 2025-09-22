package com.stocksim.controller;
import org.springframework.web.bind.annotation.*;
import com.stocksim.service.TradeService;
import com.stocksim.service.UserService;
import com.stocksim.dto.TradeRequest;
import com.stocksim.model.User;
import com.stocksim.model.Trade;

@RestController
@RequestMapping("/api/trades")
public class TradeController {
    private final TradeService tradeService;
    private final UserService userService;
    public TradeController(TradeService tradeService, UserService userService){
        this.tradeService = tradeService; this.userService = userService;
    }

    // NOTE: For demo we pass userId in body; replace with auth in real app
    @PostMapping("/buy")
    public Trade buy(@RequestBody TradeRequest req){
        User user = userService.findById(req.getUserId()).orElseThrow();
        return tradeService.executeBuy(user, req.getSymbol(), req.getQuantity());
    }

    @PostMapping("/sell")
    public Trade sell(@RequestBody TradeRequest req){
        User user = userService.findById(req.getUserId()).orElseThrow();
        return tradeService.executeSell(user, req.getSymbol(), req.getQuantity());
    }
}

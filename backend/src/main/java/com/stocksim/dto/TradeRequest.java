package com.stocksim.dto;
public class TradeRequest {
    private Long userId;
    private String symbol;
    private Integer quantity;
    public Long getUserId(){return userId;}
    public void setUserId(Long id){this.userId = id;}
    public String getSymbol(){return symbol;}
    public void setSymbol(String s){this.symbol=s;}
    public Integer getQuantity(){return quantity;}
    public void setQuantity(Integer q){this.quantity=q;}
}

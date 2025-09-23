package com.stocksim.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocksim.model.Stock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockService {

    @Value("${alphavantage.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public StockService(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public Stock getStockData(String symbol) {
        String apiUrl = "https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=" + symbol + "&apikey=" + apiKey;
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode quote = root.path("Global Quote");

            if (quote.isMissingNode() || quote.isEmpty()) {
                return null;
            }

            String stockSymbol = quote.path("01. symbol").asText();
            double price = quote.path("05. price").asDouble();

            return new Stock(stockSymbol, price);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
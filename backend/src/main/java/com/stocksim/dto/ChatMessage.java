package com.stocksim.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // This single annotation adds getters, setters, toString(), etc.
@NoArgsConstructor // Creates the default no-argument constructor
@AllArgsConstructor // Creates a constructor with all arguments
public class ChatMessage {
    private String from;
    private String text;
}
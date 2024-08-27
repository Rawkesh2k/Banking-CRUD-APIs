package com.fullstack.springboot.banking_app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccountDTO {
    private long id;
    private String accountHolderName;
    private double balance;
}

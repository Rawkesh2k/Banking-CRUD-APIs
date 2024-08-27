package com.fullstack.springboot.banking_app.service;

import com.fullstack.springboot.banking_app.dto.AccountDTO;

import java.util.List;


public interface AccountService {
    AccountDTO createAccount(AccountDTO accountDTO);

    AccountDTO getAccountById(long id);

    AccountDTO deposit(long id, double amount);

    AccountDTO withdraw(long id, double amount);

    List<AccountDTO> getAllAccounts();

    void deleteAccount(long id);
}

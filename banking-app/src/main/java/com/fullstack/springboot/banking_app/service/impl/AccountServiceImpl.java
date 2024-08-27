package com.fullstack.springboot.banking_app.service.impl;

import com.fullstack.springboot.banking_app.dto.AccountDTO;
import com.fullstack.springboot.banking_app.entity.Account;
import com.fullstack.springboot.banking_app.mapper.AccountMapper;
import com.fullstack.springboot.banking_app.respository.AccountRepository;
import com.fullstack.springboot.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {


    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        Account account = AccountMapper.mapToAccount(accountDTO);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO getAccountById(long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account doesn't exist"));
        return AccountMapper.mapToAccountDTO(account);
    }

    @Override
    public AccountDTO deposit(long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account doesn't exist"));
        double total = account.getBalance() + amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public AccountDTO withdraw(long id, double amount) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account doesn't exist"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("INSUFFICIENT AMOUNT");
        }
        double balance = account.getBalance() - amount;
        account.setBalance(balance);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDTO(savedAccount);
    }

    @Override
    public List<AccountDTO> getAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        return allAccounts.stream()
                .map((account) -> AccountMapper.mapToAccountDTO(account))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(long id) {
        Account account = accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account doesn't exist"));
        accountRepository.deleteById(id);
    }
}

package com.fullstack.springboot.banking_app.controller;

import com.fullstack.springboot.banking_app.dto.AccountDTO;
import com.fullstack.springboot.banking_app.entity.Account;
import com.fullstack.springboot.banking_app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    //Add Account Rest API

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.createAccount(accountDTO), HttpStatus.CREATED);
    }

    //Get Account Rest API
    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable long id) {
        AccountDTO accountDTO = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDTO);
    }

    //Deposit Account Rest API
    @PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDTO> depositAmount(@PathVariable long id, @RequestBody Map<String, Double> request) {
        AccountDTO accountDTO = accountService.deposit(id, request.get("amount"));
        return ResponseEntity.ok(accountDTO);
    }
    //Withdraw Account Rest API

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDTO> withdrawAmount(@PathVariable long id, @RequestBody Map<String, Double> requestDrawAmount) {
        AccountDTO accountDTO = accountService.withdraw(id, requestDrawAmount.get("amount"));
        return ResponseEntity.ok(accountDTO);
    }

    //Get All Account Rest API

    @GetMapping
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> allAccounts = accountService.getAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }

    //Delete Account Rest API

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account with the ID: "+id+" deleted successfully");
    }
}

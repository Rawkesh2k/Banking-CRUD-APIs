package com.fullstack.springboot.banking_app.respository;

import com.fullstack.springboot.banking_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}

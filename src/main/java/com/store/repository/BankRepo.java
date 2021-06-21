package com.store.repository;

import com.store.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepo extends JpaRepository<BankAccount,Integer> {
    BankAccount findByCreditCardNumber(String creditCardNumber);

}

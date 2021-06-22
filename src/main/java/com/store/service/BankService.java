package com.store.service;

import com.store.dtos.payment.BankTransactionDto;
import com.store.dtos.payment.CreditCardAuthDto;
import com.store.dtos.payment.CreditCardDto;
import com.store.exceptions.BankException;

public interface BankService {
    CreditCardDto validateCreditCard(CreditCardAuthDto creditCardAuthDto) throws BankException;
    Boolean doBankTransaction(BankTransactionDto bankTransactionDto) throws BankException;

    String STORE_ACCOUNT_NUMBER = "STORE_CARD_NUMBER";
    String STORE_ACCOUNT_CVV = "STORE_CVV";

}

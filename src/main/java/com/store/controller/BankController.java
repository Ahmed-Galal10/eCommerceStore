package com.store.controller;

import com.store.dtos.GenericResponse;
import com.store.dtos.payment.BankTransactionDto;
import com.store.dtos.payment.CreditCardAuthDto;
import com.store.dtos.payment.CreditCardDto;
import com.store.exceptions.BankException;
import com.store.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping
    @PreAuthorize("ROLE_CUSTOMER or ROLE_SELLER")
    public ResponseEntity<GenericResponse<CreditCardDto>> validateCreditCard(@RequestBody CreditCardAuthDto cardCredentials) {
        try {
            CreditCardDto creditCardDto = bankService.validateCreditCard(cardCredentials);
            creditCardDto.setCvv("");
            return ResponseEntity.ok(new GenericResponse<CreditCardDto>(creditCardDto, HttpStatus.OK,
                    "Credit Card Retrieved Successfully"));
        } catch (BankException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse<CreditCardDto>(null, HttpStatus.FORBIDDEN, e.getMessage()));
        }
    }

    @PreAuthorize("ROLE_CUSTOMER or ROLE_SELLER")
    @PostMapping("/creditCards")
    public ResponseEntity<GenericResponse<Boolean>> validateCreditCardBalance(@RequestBody CreditCardDto requiredCreditCardDto) {
        try {
            CreditCardDto creditCard = bankService.validateCreditCard(requiredCreditCardDto);
            if (creditCard.getBalance() >= requiredCreditCardDto.getBalance()) {
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse<>(Boolean.TRUE,
                        HttpStatus.OK, "Sufficient Balance"));
            } else {
                throw new BankException("InSufficient Balance", 1410);
            }
        } catch (BankException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse<>(Boolean.FALSE,
                    HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }

    @Transactional
    @PatchMapping
    @PreAuthorize("ROLE_CUSTOMER or ROLE_SELLER")
    public ResponseEntity<GenericResponse<Boolean>> applyBankTransaction(@RequestBody BankTransactionDto bankTransactionDto) {
        try {
            Boolean status = bankService.doBankTransaction(bankTransactionDto);
            if (status) {
                return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse<>(Boolean.TRUE,
                        HttpStatus.OK, "Transaction done successfully"));
            } else {
                throw new BankException("Transaction Failed", 1405);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GenericResponse<>(Boolean.FALSE,
                    HttpStatus.BAD_REQUEST, e.getMessage()));
        }
    }
}

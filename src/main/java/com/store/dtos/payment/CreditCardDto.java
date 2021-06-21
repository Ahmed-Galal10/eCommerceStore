package com.store.dtos.payment;

import org.springframework.data.annotation.Transient;

import javax.persistence.Column;

public class CreditCardDto extends CreditCardAuthDto{
    private Integer id;
    private Double balance;

    public CreditCardDto() {
    }

    public CreditCardDto(Integer id,Double balance, String creditCardNumber) {
        super(creditCardNumber,"");
        this.id = id;
        this.balance = balance;
    }

    public CreditCardDto(Double balance, String creditCardNumber, String cvv) {
        super(creditCardNumber,cvv);
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}

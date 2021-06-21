package com.store.dtos.payment;

import java.io.Serializable;

public class CreditCardAuthDto implements Serializable {
    private String creditCardNumber;
    private String cvv;

    public CreditCardAuthDto() {
    }

    public CreditCardAuthDto(String creditCardNumber, String cvv) {
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}

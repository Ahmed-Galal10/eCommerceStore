package com.store.dtos.checkout;

import com.store.dtos.payment.CreditCardAuthDto;

public class PaymentInfoDto {
    private String paymentMethod;
    private CreditCardAuthDto creditCardAuthDto;

    public PaymentInfoDto() {
    }

    public PaymentInfoDto(String paymentMethod, CreditCardAuthDto creditCardAuthDto) {
        this.paymentMethod = paymentMethod;
        this.creditCardAuthDto = creditCardAuthDto;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public CreditCardAuthDto getCreditCardAuthDto() {
        return creditCardAuthDto;
    }

    public void setCreditCardAuthDto(CreditCardAuthDto creditCardAuthDto) {
        this.creditCardAuthDto = creditCardAuthDto;
    }
}

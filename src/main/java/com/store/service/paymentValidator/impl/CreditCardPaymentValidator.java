package com.store.service.paymentValidator.impl;

import com.store.dtos.cart.CartDto;
import com.store.dtos.checkout.PaymentInfoDto;
import com.store.dtos.payment.CreditCardDto;
import com.store.exceptions.BankException;
import com.store.service.BankService;
import com.store.service.paymentValidator.PaymentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditCardPaymentValidator implements PaymentValidator {
    private static final String PAYMENT_METHOD = "CREDIT_CARD";

    @Autowired
    private BankService bankService;

    @Override
    public Boolean validate(PaymentInfoDto paymentInfoDto, CartDto cartDto) throws BankException {
        Double totalPrice = cartDto.getItems().stream().map(cartItemDto -> cartItemDto.getPrice()).reduce(Double::sum).get();
        System.out.println(paymentInfoDto.getCreditCardAuthDto());
        System.out.println(bankService);
        //todo: refactor to authenticate from an external API
        CreditCardDto creditCardDto = bankService.validateCreditCard(paymentInfoDto.getCreditCardAuthDto());
        if (creditCardDto.getBalance() >= totalPrice) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static String getPaymentMethod() {
        return PAYMENT_METHOD;
    }
}

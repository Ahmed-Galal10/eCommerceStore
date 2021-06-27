package com.store.service.paymentValidator.impl;

import com.store.dtos.cart.CartDto;
import com.store.dtos.checkout.PaymentInfoDto;
import com.store.dtos.payment.CreditCardDto;
import com.store.exceptions.BankException;
import com.store.service.BankService;
import com.store.service.paymentValidator.PaymentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("creditCardPaymentValidator")
public class CreditCardPaymentValidator implements PaymentValidator {
    private static final String PAYMENT_METHOD = "CREDIT_CARD";

    private BankService bankService;

    @Autowired
    public CreditCardPaymentValidator(BankService bankService) {
        this.bankService = bankService;
    }

    @Override
    public Boolean validate(PaymentInfoDto paymentInfoDto, CartDto cartDto) throws BankException {
        Double totalPrice = cartDto.getItems().stream()
                .map(cartItemDto -> cartItemDto.getPrice() * cartItemDto.getQuantity())
                .reduce(Double::sum)
                .get();
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

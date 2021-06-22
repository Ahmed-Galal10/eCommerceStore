package com.store.service.paymentValidator.impl;

import com.store.dtos.cart.CartDto;
import com.store.dtos.checkout.PaymentInfoDto;
import com.store.service.paymentValidator.PaymentValidator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("paypalPaymentValidator")
public class PaypalPaymentValidator implements PaymentValidator {
    private static final String PAYMENT_METHOD = "PAYPAL";
    @Override
    public Boolean validate(PaymentInfoDto paymentInfoDto, CartDto cartDto) {
        return Boolean.TRUE;
    }

    public static String getPaymentMethod() {
        return PAYMENT_METHOD;
    }
}

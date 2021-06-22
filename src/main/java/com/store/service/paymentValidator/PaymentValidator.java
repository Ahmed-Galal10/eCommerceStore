package com.store.service.paymentValidator;

import com.store.dtos.cart.CartDto;
import com.store.dtos.checkout.PaymentInfoDto;
import com.store.exceptions.BankException;

public interface PaymentValidator {
    Boolean validate(PaymentInfoDto paymentInfoDto, CartDto cartDto) throws BankException;
    static String getPaymentMethod(){
        return null;
    }
}

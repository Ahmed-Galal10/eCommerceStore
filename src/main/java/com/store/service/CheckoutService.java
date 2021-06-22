package com.store.service;

import com.store.dtos.cart.CartDto;
import com.store.dtos.checkout.CheckoutStateDto;
import com.store.dtos.checkout.PaymentInfoDto;
import com.store.exceptions.BankException;

public interface CheckoutService {
    CheckoutStateDto validateCartItemsQuantity(CartDto cartDto);
    Boolean validatePayment(PaymentInfoDto paymentInfoDto , CartDto cartDto) throws BankException;
}

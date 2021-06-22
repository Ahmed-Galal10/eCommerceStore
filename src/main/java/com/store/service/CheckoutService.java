package com.store.service;

import com.store.dtos.cart.CartDto;
import com.store.dtos.checkout.CheckoutStateDto;
import com.store.dtos.checkout.PaymentInfoDto;
import com.store.dtos.order.OrderDto;
import com.store.exceptions.BankException;

public interface CheckoutService {
    CheckoutStateDto validateCartItemsQuantity(CartDto cartDto);
    Boolean validatePayment(PaymentInfoDto paymentInfoDto , CartDto cartDto) throws BankException;

    OrderDto doCheckout(PaymentInfoDto paymentInfo, CartDto cartDto) throws BankException;
}

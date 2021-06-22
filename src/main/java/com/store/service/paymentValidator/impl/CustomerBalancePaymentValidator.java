package com.store.service.paymentValidator.impl;

import com.store.dtos.cart.CartDto;
import com.store.dtos.checkout.PaymentInfoDto;
import com.store.dtos.customer.CustomerDto;
import com.store.service.CustomerService;
import com.store.service.paymentValidator.PaymentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("customerBalancePaymentValidator")
public class CustomerBalancePaymentValidator implements PaymentValidator {
    private static final String PAYMENT_METHOD = "CUSTOMER_BALANCE";
    @Autowired
    private CustomerService customerService;
    @Override
    public Boolean validate(PaymentInfoDto paymentInfoDto, CartDto cartDto) {
        CustomerDto customerDto = customerService.getCustomerById(cartDto.getCustomerId());
        Double totalPrice = cartDto.getItems().stream().map(cartItemDto -> cartItemDto.getPrice()).reduce(Double::sum).get();
        if(customerDto.getBalance()>=totalPrice){
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }


    public static String getPaymentMethod() {
        return PAYMENT_METHOD;
    }
}

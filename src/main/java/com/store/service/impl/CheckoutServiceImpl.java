package com.store.service.impl;

import com.store.dtos.cart.CartDto;
import com.store.dtos.cart.CartItemDto;
import com.store.dtos.checkout.CheckoutStateDto;
import com.store.dtos.checkout.PaymentInfoDto;
import com.store.exceptions.BankException;
import com.store.service.CheckoutService;
import com.store.service.ProductService;
import com.store.service.paymentValidator.PaymentValidator;
import com.store.service.paymentValidator.impl.CreditCardPaymentValidator;
import com.store.service.paymentValidator.impl.CustomerBalancePaymentValidator;
import com.store.service.paymentValidator.impl.PaypalPaymentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private ProductService productService;

    @Override
    public CheckoutStateDto validateCartItemsQuantity(CartDto cartDto) {
        CheckoutStateDto checkoutStateDto = new CheckoutStateDto();
        CartDto validCartDto = new CartDto();
        List<CartItemDto> cartItemDtoList = cartDto.getItems();
        AtomicReference<Boolean> state = new AtomicReference<>(true);
        validCartDto.setItems(cartItemDtoList.stream().map(cartItemDto -> {
            Integer storeQuantity = productService.getProductById(cartItemDto.getProductId()).getProductQuantity();
            if (cartItemDto.getQuantity() > storeQuantity) {
                state.set(false);
                cartItemDto.setQuantity(storeQuantity);
            }
            return cartItemDto;
        }).collect(Collectors.toList()));
        checkoutStateDto.setCartDto(validCartDto);
        checkoutStateDto.setState(state.get());
        return checkoutStateDto;
    }

    @Override
    public Boolean validatePayment(PaymentInfoDto paymentInfoDto, CartDto cartDto) throws BankException {
        PaymentValidator paymentValidator = null;
        if (paymentInfoDto.getPaymentMethod().equals(PaypalPaymentValidator.getPaymentMethod())) {
            paymentValidator = new PaypalPaymentValidator();
            return paymentValidator.validate(paymentInfoDto, cartDto);
        } else if (paymentInfoDto.getPaymentMethod().equals(CreditCardPaymentValidator.getPaymentMethod())) {
            paymentValidator = new CreditCardPaymentValidator();
            return paymentValidator.validate(paymentInfoDto, cartDto);
        } else if (paymentInfoDto.getPaymentMethod().equals(CustomerBalancePaymentValidator.getPaymentMethod())) {
            paymentValidator = new CustomerBalancePaymentValidator();
            return paymentValidator.validate(paymentInfoDto, cartDto);
        }
        return Boolean.FALSE;
    }
}

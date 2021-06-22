package com.store.service.impl;

import com.store.dtos.cart.CartDto;
import com.store.dtos.cart.CartItemDto;
import com.store.dtos.checkout.CheckoutStateDto;
import com.store.dtos.checkout.PaymentInfoDto;
import com.store.dtos.order.OrderDto;
import com.store.dtos.order.OrderItemRequest;
import com.store.dtos.order.OrderRequest;
import com.store.dtos.payment.BankTransactionDto;
import com.store.exceptions.BankException;
import com.store.repository.SellerRepo;
import com.store.service.*;
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
    @Autowired
    private PaymentValidator creditCardPaymentValidator;
    @Autowired
    private PaymentValidator paypalPaymentValidator;
    @Autowired
    private PaymentValidator customerBalancePaymentValidator;
    //todo: replace it with an external API
    @Autowired
    private BankService bankService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private SellerRepo sellerRepo;

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
        if (paymentInfoDto.getPaymentMethod().equals(PaypalPaymentValidator.getPaymentMethod())) {
            return paypalPaymentValidator.validate(paymentInfoDto, cartDto);
        } else if (paymentInfoDto.getPaymentMethod().equals(CreditCardPaymentValidator.getPaymentMethod())) {
            return creditCardPaymentValidator.validate(paymentInfoDto, cartDto);
        } else if (paymentInfoDto.getPaymentMethod().equals(CustomerBalancePaymentValidator.getPaymentMethod())) {
            return customerBalancePaymentValidator.validate(paymentInfoDto, cartDto);
        }
        return Boolean.FALSE;
    }

    @Override
    public OrderDto doCheckout(PaymentInfoDto paymentInfo, CartDto cartDto) throws BankException {
        if (paymentInfo.getPaymentMethod().equals("PAYPAL")) {
            return getOrderDto(cartDto);

        } else if (paymentInfo.getPaymentMethod().equals("CREDIT_CARD")) {
            Double totalPrice = cartDto.getItems().stream().map(cartItemDto -> cartItemDto.getPrice() * cartItemDto.getQuantity()).reduce(Double::sum).get();
            BankTransactionDto bankTransactionDto = new BankTransactionDto(paymentInfo.getCreditCardAuthDto(),
                    BankService.STORE_ACCOUNT_NUMBER,totalPrice);
            bankService.doBankTransaction(bankTransactionDto);
            return getOrderDto(cartDto);
        } else if (paymentInfo.getPaymentMethod().equals("CUSTOMER_BALANCE")) {
            Double totalPrice = cartDto.getItems().stream().map(cartItemDto -> cartItemDto.getPrice() * cartItemDto.getQuantity()).reduce(Double::sum).get();
            var customerDto = customerService.getCustomerById(cartDto.getCustomerId());
            Double updatedBalance = customerDto.getBalance() - totalPrice;
            customerDto.setBalance(updatedBalance);
            customerService.updateCustomer(customerDto);
            return getOrderDto(cartDto);
        }
        return null;
    }

    private OrderDto getOrderDto(CartDto cartDto) {
        var orderItemList = cartDto.getItems().stream().map(cartItemDto -> {
            var product = productService.getProductById(cartItemDto.getProductId());
            Integer updatedQuantity = product.getProductQuantity() - cartItemDto.getQuantity();
            product.setProductQuantity(updatedQuantity);
            //persist the product
            productService.addOrUpdateProduct(product);

            var seller = sellerRepo.findById(product.getSellerId());
            Double updatedBalance = seller.get().getBalance() + cartItemDto.getQuantity() * cartItemDto.getPrice();
            seller.get().setBalance(updatedBalance);
            sellerRepo.save(seller.get());

            var orderItem = new OrderItemRequest();
            orderItem.setQuantity(cartItemDto.getQuantity());
            orderItem.setProductId(cartItemDto.getProductId());
            return orderItem;
        }).collect(Collectors.toList());
        var orderRequest = new OrderRequest();
        orderRequest.setOrderItems(orderItemList);
        orderRequest.setCustomerId(cartDto.getCustomerId());
        return orderService.createOrder(orderRequest);
    }
}

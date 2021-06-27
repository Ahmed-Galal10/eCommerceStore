package com.store;

import com.store.controller.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SmokeTest {


    private SellerController sellerController;
    private CategoryController categoryController;
    private CartItemController cartItemController;
    private CustomerController customerController;
    private OrderController orderController;
    private ProductController productController;


    @Autowired
    public void init(SellerController sellerController,
                     CategoryController categoryController,
                     CartItemController cartItemController,
                     CustomerController customerController,
                     OrderController orderController,
                     ProductController productController) {

        this.sellerController = sellerController;
        this.categoryController = categoryController;
        this.cartItemController = cartItemController;
        this.customerController = customerController;
        this.orderController = orderController;
        this.productController = productController;
    }

    @Test
    void contextLoads() {

        //checks that the context has been loaded and the controllers injected
        Assertions.assertThat(sellerController).isNotNull();
        Assertions.assertThat(productController).isNotNull();
        Assertions.assertThat(customerController).isNotNull();
        Assertions.assertThat(cartItemController).isNotNull();
        Assertions.assertThat(categoryController).isNotNull();
        Assertions.assertThat(orderController).isNotNull();
    }

}

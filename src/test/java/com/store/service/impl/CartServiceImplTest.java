package com.store.service.impl;

import com.store.dtos.cart.CartDto;
import com.store.dtos.cart.CartItemDto;
import com.store.dtos.cart.CartItemRequest;
import com.store.model.CartItem;
import com.store.model.CartItemId;
import com.store.model.Product;
import com.store.repository.CartItemRepo;
import com.store.repository.ProductRepo;
import com.store.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CartServiceImplTest {

    @Mock
    private CartItemRepo repo;

    @Mock
    ProductRepo productRepo;

    @InjectMocks
    private final CartService cartService = new CartServiceImpl();


    private CartItemDto getCartItemDto(int prodId,
                                       int quantity,
                                       double price,
                                       String name) {

        CartItemDto cartItemDto = new CartItemDto();

        cartItemDto.setProductId(prodId);
        cartItemDto.setPrice(price);
        cartItemDto.setName(name);
        cartItemDto.setQuantity(quantity);

        return cartItemDto;
    }

    private CartItem getCartItem(int prodId,
                                 int customerId,
                                 int quantity) {

        CartItemId id = new CartItemId();
        id.setCustomerId(customerId);
        id.setProdId(prodId);

        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        cartItem.setQuantity(quantity);

        return cartItem;
    }


    private CartDto getCartDto() {

        CartDto cart = new CartDto();

        List<CartItemDto> dtosList = new ArrayList<>();

        CartItemDto dto = getCartItemDto(1, 100, 2.0, "sugar");

        dtosList.add(dto);

        cart.setItems(dtosList);

        return cart;
    }


    private CartItemRequest getCartItemRequest(int prodId,
                                               int customerId,
                                               int quantity) {

        CartItemRequest request = new CartItemRequest();

        request.setQuantity(quantity);
        request.setProductId(prodId);
        request.setCustomerId(customerId);

        return request;
    }


    @BeforeEach
    void setMockOutput() {
        List<CartItem> cartItemList;
        cartItemList = new ArrayList<>();


        CartItemId id = new CartItemId();
        id.setCustomerId(2);
        id.setProdId(1);


        Product product = new Product();
        product.setId(1);
        product.setQuantity(100);
        product.setName("sugar");
        product.setPrice(2.0);
        product.setImg("sss");


        CartItem cartItem = getCartItem(1, 2, 100);
        cartItem.setProduct(product);
        cartItemList.add(cartItem);


        Mockito.when(repo.getAllCartItemsByUserId(2)).thenReturn(cartItemList);
//        cartItem.setQuantity(5);

        Mockito.when(productRepo.getOne(1)).thenReturn(product);
        Mockito.when(repo.findById(id)).thenReturn(Optional.of(cartItem));

    }


    @DisplayName("Test Mock Cart . getCartByUserId")
    @Test
    void testGetCartByUserId() {

        CartItemDto required = getCartDto().getItems().get(0);
        CartItemDto found = cartService.getCartByUserId(2).getItems().get(0);

        assertEquals(required.getName(), found.getName());
        assertEquals(required.getPrice(), found.getPrice());
        assertEquals(required.getQuantity(), found.getQuantity());

    }


    @DisplayName("Test Mock Cart . updateCartItem")
    @Test
    void testUpdateCartItem() {

        CartItemDto expected = getCartItemDto(1, 90, 2.0, "sugar");

        CartItem item = getCartItem(1, 2, 90);

        CartItemRequest request = getCartItemRequest(1, 2, 90);

        CartItemDto found = cartService.updateCartItem(request);


        Mockito.when(repo.save(item)).thenReturn(item);

        assertEquals(expected.getName(), found.getName());
        assertEquals(expected.getPrice(), found.getPrice());
        assertEquals(expected.getQuantity(), found.getQuantity());

    }


    @DisplayName("Test Mock Cart . addCartItem")
    @Test
    void testAddCartItem() {

        CartItemDto expected = getCartItemDto(1, 90, 2.0, "sugar");

        CartItemRequest request = getCartItemRequest(1, 2, 90);

        CartItemDto actual = cartService.updateCartItem(request);

        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPrice(), actual.getPrice());
        assertEquals(expected.getQuantity(), actual.getQuantity());

    }


    @DisplayName("Test Mock Cart . getCartItemRequest")
    @Test
    void testDeleteCartItem() {

        CartItemRequest request = getCartItemRequest(1, 2, 500);

        CartItem item = getCartItem(1, 2, 1222);

//        Mockito.when(repo.delete(item)).thenReturn(true);

        boolean deleted = cartService.deleteCartItem(request);

        assertEquals(true, deleted);

    }


}
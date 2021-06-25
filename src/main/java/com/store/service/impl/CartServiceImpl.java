package com.store.service.impl;


import com.store.exceptions.CartException;
import com.store.model.CartItem;
import com.store.model.CartItemId;
import com.store.model.Product;
import com.store.model.User;
import com.store.dtos.cart.CartDto;
import com.store.dtos.cart.CartItemDto;
import com.store.dtos.cart.CartItemRequest;
import com.store.repository.CartItemRepo;
import com.store.repository.ProductRepo;
import com.store.repository.UserRepo;
import com.store.service.CartService;
import com.store.util.mappers.CartItemMapper;
import com.store.util.mappers.EntityDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private CartItemRepo cartItemRepo;
    private UserRepo userRepo;
    private ProductRepo productRepo;

    @Autowired
    public void setup(CartItemRepo cartItemRepo, UserRepo userRepo,
                      ProductRepo productRepo) {
      this.cartItemRepo = cartItemRepo;
      this.productRepo = productRepo;
      this.userRepo = userRepo;
    }

    @Override
    public  CartDto getCartByUserId(Integer userId) {
        List<CartItem> cartItems = cartItemRepo.getAllCartItemsByUserId(userId);

        EntityDtoMapper<CartItem, CartItemDto> mapper = new CartItemMapper();
        List<CartItemDto> cartItemDtos = mapper.entityListToDtoList(cartItems);

        CartDto cartDto = new CartDto();
        cartDto.setItems(cartItemDtos);
        cartDto.setCustomerId(userId);

        return  cartDto;
    }

    @Override
    public CartItemDto addCartItem(CartItemRequest cartItemRequest) {

        int customerId = cartItemRequest.getCustomerId();
        int productId = cartItemRequest.getProductId();
        int quantity = cartItemRequest.getQuantity();


        //First Check if this cart Item Exists
        CartItemId cartItemId = new CartItemId(customerId, productId);
        boolean isExist =cartItemRepo.existsById(cartItemId);
        if(isExist){
            throw new CartException("Product Already in Cart");
        }

        CartItem cartItem = new CartItem();

        Optional<Product> p = productRepo.findById(productId);
        Optional<User> u = userRepo.findById(customerId);

        //TODO Change in fut.
        Product product = p.orElse(null);
        User user = u.orElse(null);

        if(product == null || user == null){
            //TODO Throw Custom Exception
        }

        cartItem.setId(cartItemId);
        cartItem.setProduct(product);
        cartItem.setUser(user);
        cartItem.setQuantity(quantity);

        CartItem persistedCartItem = cartItemRepo.save(cartItem);

        EntityDtoMapper<CartItem, CartItemDto> mapper = new CartItemMapper();
        CartItemDto cartItemDto = mapper.toDto(persistedCartItem);

        return cartItemDto;
    }

    @Override
    public CartItemDto updateCartItem(CartItemRequest cartItemRequest) {

        int customerId = cartItemRequest.getCustomerId();
        int productId = cartItemRequest.getProductId();
        int quantity = cartItemRequest.getQuantity();


        CartItemId cartItemId = new CartItemId(customerId, productId);

        Product product = productRepo.getOne(productId);
        int productStock = product.getQuantity();

        //If quantity wanted > stock there's an Error.
        if( quantity > productStock){
            throw new CartException("Product has only ( "+ productStock +" ) available in Stock." );
        } else if(quantity <= 0){
            throw new CartException("Invalid Quantity" );
        }

        Optional<CartItem> ci = cartItemRepo.findById(cartItemId);

        //TODO Change in fut.
        CartItem cartItem = ci.orElse(null);
        System.out.println( cartItem);
        if(cartItem == null){
            //TODO Throw Custom Exception
        }
        cartItem.setQuantity(quantity);

        cartItemRepo.save(cartItem);

        EntityDtoMapper<CartItem, CartItemDto> mapper = new CartItemMapper();
        CartItemDto cartItemDto = mapper.toDto(cartItem);

        return cartItemDto;
    }

    @Override
    public boolean deleteCartItem(CartItemRequest cartItemRequest) {
        int customerId = cartItemRequest.getCustomerId();
        int productId = cartItemRequest.getProductId();
        int quantity = cartItemRequest.getQuantity();

        CartItemId cartItemId = new CartItemId(customerId, productId);

        Optional<CartItem> ci = cartItemRepo.findById(cartItemId);
        //TODO Change in fut.
        CartItem cartItem = ci.orElse(null);
        System.out.println( cartItem);
        if(cartItem == null){
            throw new CartException("Couldn't delete , Try again later");
        }

        cartItemRepo.delete(cartItem);
        return  true;
    }


}

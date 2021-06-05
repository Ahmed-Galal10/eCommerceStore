package com.store.service.impl;

import com.store.dto.CartItemDto;
import com.store.dto.CartItemRequest;
import com.store.model.dao.CartItemDao;
import com.store.model.dao.ProductDao;
import com.store.model.dao.SubCategoryDao;
import com.store.model.dao.UserDao;
import com.store.model.entities.*;
import com.store.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    private CartItemDao cartItemDao;
    private SubCategoryDao subCategoryDao;
    private UserDao userDao;
    private ProductDao productDao;
    private ModelMapper modelMapper;

    @Autowired
    public void setup(CartItemDao cartItemDao, SubCategoryDao subCategoryDao,
                      UserDao userDao, ProductDao productDao, ModelMapper modelMapper) {
        this.subCategoryDao = subCategoryDao;
        this.cartItemDao = cartItemDao;
        this.userDao = userDao;
        this.productDao = productDao;
    }

    @Override
    public CartItemDto addCartItem(int cartId, CartItemRequest cartItemRequest) {
        CartItem cart = new CartItem();
        User user = userDao.findById(cartItemRequest.getUserId()).get();
        Product product = productDao.findById(cartItemRequest.getProductId()).get();
//        CartItem cartItem = modelMapper.map(cartItemRequest,CartItem.class);
//        cartItem.setId(new CartItemId(cartItemRequest.getUserId(),cartItemRequest.getProductId()));
        cart.setUser(user);
        cart.setId(new CartItemId(cartItemRequest.getUserId(), cartItemRequest.getProductId()));
        cart.setQuantity(cartItemRequest.getQuantity());
        cart.setProduct(product);
        cart = cartItemDao.save(cart);

        return new CartItemDto();
    }

    @Override
    public CartItem getByCartItemId(Integer id ) {
        return cartItemDao.findById(new CartItemId(id,id)).get();
    }

    @Override
    public SubCategory getSub() {
        return subCategoryDao.findById(1).get();
    }


}

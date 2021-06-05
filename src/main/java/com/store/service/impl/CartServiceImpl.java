package com.store.service.impl;

import com.store.dto.CartItemDto;
import com.store.model.dao.CartItemDao;
import com.store.model.dao.SubCategoryDao;
import com.store.model.entities.CartItem;
import com.store.model.entities.CartItemId;
import com.store.model.entities.SubCategory;
import com.store.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    CartItemDao cartItemDao ;
    SubCategoryDao subCategoryDao;
    @Autowired
    public void setup(CartItemDao cartItemDao, SubCategoryDao subCategoryDao ){
        this.subCategoryDao = subCategoryDao ;
        this.cartItemDao = cartItemDao ;
    }
    @Override
    public CartItemDto addCartItem(CartItem cartItem) {
        cartItemDao.save(cartItem);
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

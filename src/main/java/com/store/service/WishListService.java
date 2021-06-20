package com.store.service;

import com.store.dtos.customer.ProductWishListDto;
import com.store.dtos.wishlist.WishlistProdRequest;

public interface WishListService {
    ProductWishListDto addProduct(WishlistProdRequest request);
    Boolean deleteProduct(WishlistProdRequest request);

}

package com.store.service;

import com.store.dtos.product.SellerProductRequestDto;
import com.store.dtos.seller.SellerDto;
import com.store.dtos.seller.SellerRequest;
import com.store.dtos.seller.SellerRequestDto;

import java.util.List;

public interface SellerService {

    List<SellerDto> getAll();

    SellerRequestDto addSeller(SellerRequestDto sellerRequestDto);

    SellerDto getBySellerId(int sellerId);

    SellerDto updateSeller(int sellerId, SellerRequest sellerRequest);

    SellerDto deleteById(int sellerId);

    SellerProductRequestDto updateSellerProduct(SellerProductRequestDto productDto);
}

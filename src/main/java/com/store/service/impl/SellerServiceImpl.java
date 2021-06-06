package com.store.service.impl;

import com.store.dtos.seller.SellerOrderDto;
import com.store.repository.OrderRepo;
import com.store.repository.ProductRepo;
import com.store.repository.SellerRepo;
import com.store.repository.SoldItemRepo;
import com.store.service.SellerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellerServiceImpl  implements SellerService {

    private  SellerRepo sellerRepo;
    private ProductRepo productRepo;
    private OrderRepo orderRepo;
    private SoldItemRepo soldItemRepo;

    public SellerServiceImpl(SellerRepo sellerRepo, ProductRepo productRepo,
                             OrderRepo orderRepo, SoldItemRepo soldItemRepo){
        this.sellerRepo = sellerRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.soldItemRepo = soldItemRepo;
    }





}

package com.store.service.impl;

import com.store.dtos.seller.SellerDto;
import com.store.dtos.seller.SellerRequest;
import com.store.model.Seller;
import com.store.repository.OrderRepo;
import com.store.repository.ProductRepo;
import com.store.repository.SellerRepo;
import com.store.repository.SoldItemRepo;
import com.store.service.SellerService;
import com.store.util.mappers.SellerMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerMapper mapper;
    private final SellerRepo sellerRepo;
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final SoldItemRepo soldItemRepo;

    public SellerServiceImpl(SellerRepo sellerRepo, ProductRepo productRepo,
                             OrderRepo orderRepo, SoldItemRepo soldItemRepo,
                             SellerMapper mapper) {
        this.sellerRepo = sellerRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.soldItemRepo = soldItemRepo;
        this.mapper = mapper;
    }


    @Override
    public List<SellerDto> getAll() {

        List<Seller> sellers = sellerRepo.findAll().stream().filter(seller -> !seller.getIsDeleted()).collect(Collectors.toList());
        List<SellerDto> sellersDto = mapper.entityListToDtoList(sellers);
        return sellersDto;
    }

    @Override
    public SellerDto addSeller(SellerRequest sellerRequest) {
        Boolean verified = sellerRequest.getIsEmailVerified() != null && sellerRequest.getIsEmailVerified();
        Boolean deleted = sellerRequest.getIsDeleted() != null && sellerRequest.getIsDeleted();

        Seller seller = new Seller();
        seller.setBalance(sellerRequest.getBalance());
        seller.setAddress(sellerRequest.getAddress());
        seller.setName(sellerRequest.getName());
        seller.setEmail(sellerRequest.getEmail());
        seller.setImage(sellerRequest.getImage());
        seller.setPhone(sellerRequest.getPhone());
        seller.setPassword(sellerRequest.getPassword());
        seller.setRegDate(sellerRequest.getRegDate());
        seller.setRole("ROLE_SELLER");
        seller.setIsEmailVerified(verified);
        seller.setIsDeleted(deleted);
        Seller result = sellerRepo.save(seller);

        if (result == null) {
            //todo throw custom exception
        }
        SellerDto sellerDto = mapper.toDto(result);
        return sellerDto;

    }

    @Override
    public SellerDto getBySellerId(int sellerId) {
        Seller seller = null;
        Optional<Seller> sellerOptional = sellerRepo.findById(sellerId);
        if (sellerOptional.isPresent()) {
            seller = sellerOptional.get();
        } else {
            //todo throw custom exception
        }
        return mapper.toDto(seller);
    }

    @Override
    public SellerDto updateSeller(int sellerId, SellerRequest sellerRequest) {
        Optional<Seller> sellerOptional = sellerRepo.findById(sellerId);
        Seller result = null;
        if (sellerOptional.isPresent()) {
            LoggerFactory.getLogger(this.getClass().getName()).info("id of updated seller " + sellerRequest.getId());
            Seller seller = sellerOptional.get();
            LoggerFactory.getLogger(this.getClass().getName()).info("id of updated seller " + seller);
            seller.setId(sellerId);
            seller.setBalance(sellerRequest.getBalance());
            seller.setAddress(sellerRequest.getAddress());
            seller.setName(sellerRequest.getName());
            seller.setEmail(sellerRequest.getEmail());
            seller.setImage(sellerRequest.getImage());
            seller.setPhone(sellerRequest.getPhone());
            if (sellerRequest.getPassword() != null)
                seller.setPassword(sellerRequest.getPassword());
            seller.setRegDate(sellerRequest.getRegDate());
            seller.setRole("ROLE_SELLER");
            seller.setIsEmailVerified(sellerRequest.getIsEmailVerified());
            seller.setIsDeleted(sellerRequest.getIsDeleted());
            result = sellerRepo.save(seller);
        } else {
            //todo throw custom exception
        }
        return mapper.toDto(result);
    }

    @Override
    public SellerDto deleteById(int sellerId) {
        Optional<Seller> sellerOptional = sellerRepo.findById(sellerId);
        Seller seller = sellerOptional.orElseThrow(RuntimeException::new
                //todo throw custom exception here
        );
        sellerRepo.deleteById(sellerId);
        return mapper.toDto(seller);
    }
}

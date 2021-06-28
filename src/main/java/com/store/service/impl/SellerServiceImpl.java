package com.store.service.impl;

import com.store.dtos.seller.SellerDto;
import com.store.dtos.seller.SellerRequest;
import com.store.dtos.seller.SellerRequestDto;
import com.store.dtos.product.SellerProductRequestDto;
import com.store.exceptions.RegisterException;
import com.store.model.Product;
import com.store.model.Seller;
import com.store.repository.*;
import com.store.service.SellerService;
import com.store.util.mappers.EntityDtoMapper;
import com.store.util.mappers.SellerMapper;
import com.store.util.mappers.seller.SellerProductRequestMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final SellerProductRequestMapper sellerProductMapper;

    @Autowired
    private EntityDtoMapper<Seller, SellerRequestDto> sellerRequestMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    public SellerServiceImpl(SellerRepo sellerRepo, ProductRepo productRepo,
                             OrderRepo orderRepo, SoldItemRepo soldItemRepo,
                             SellerMapper mapper, SellerProductRequestMapper sellerProductMapper) {
        this.sellerRepo = sellerRepo;
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.soldItemRepo = soldItemRepo;
        this.mapper = mapper;
        this.sellerProductMapper = sellerProductMapper;
    }


    @Override
    public List<SellerDto> getAll() {

        System.out.println( sellerRepo.findAll().size() );


        List<Seller> sellers = sellerRepo
                .findAll()
                .stream()
                .filter(seller -> !seller.getIsDeleted())
                .collect(Collectors.toList());
        List<SellerDto> sellersDto = mapper.entityListToDtoList(sellers);
        return sellersDto;
    }

    @Override
    public SellerRequestDto addSeller(SellerRequestDto sellerDto) {

        boolean isSellerExist = userRepo.existsByEmail(sellerDto.getEmail());


        System.out.println("asasdsadas");
        System.out.println(sellerDto.getEmail());
        System.out.println(isSellerExist);



        if(isSellerExist){
            throw new RegisterException("This Email Already Exists");
        }

        Seller seller = sellerRequestMapper.toEntity(sellerDto);
        Seller savedSeller = sellerRepo.save(seller);

        return sellerRequestMapper.toDto(savedSeller);
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

        SellerDto dto = mapper.toDto(seller);

        return dto;
    }

    @Override
    public SellerDto updateSeller(int sellerId, SellerRequest sellerRequest) {

        Optional<Seller> sellerOptional = sellerRepo.findById(sellerId);
        Seller result = null;

        if (sellerOptional.isPresent()) {

            LoggerFactory.getLogger(this.getClass().getName()).info("id of updated seller " + sellerRequest.getId());

            Seller seller = sellerOptional.get();

            seller.setId(sellerId);
            seller.setBalance(sellerRequest.getBalance());
            seller.setAddress(sellerRequest.getAddress());
            seller.setName(sellerRequest.getName());
            seller.setImage(sellerRequest.getImage());
            seller.setPhone(sellerRequest.getPhone());
            seller.setIsEmailVerified(sellerRequest.getIsEmailVerified());
            seller.setIsDeleted(sellerRequest.getIsDeleted());

            if (sellerRequest.getPassword() != null) {
                seller.setPassword(sellerRequest.getPassword());
            }

            if (!sellerRequest.getEmail().equals(seller.getEmail())) {
                seller.setEmail(sellerRequest.getEmail());
            }

            result = sellerRepo.save(seller);

        } else {

            //todo throw custom exception
        }

        SellerDto dto = mapper.toDto(result);

        return dto;
    }

    @Override
    public SellerDto deleteById(int sellerId) {

        Optional<Seller> sellerOptional = sellerRepo.findById(sellerId);

        Seller seller = sellerOptional.orElseThrow(RuntimeException::new
                //todo throw custom exception here
        );

        seller.setIsDeleted(true);
        sellerRepo.save(seller);

        SellerDto dto = mapper.toDto(seller);

        return dto;
    }

    @Override
    public SellerProductRequestDto updateSellerProduct(SellerProductRequestDto sellerProductDto) {

        Product product = productRepo.findById(sellerProductDto.getId())
                .orElseThrow(() -> new RuntimeException("Product" + sellerProductDto.getId() + " Not found"));

        product.setName(sellerProductDto.getProductName());
        product.setPrice(sellerProductDto.getProductPrice());
        product.setQuantity(sellerProductDto.getProductQuantity());
        product.setDescription(sellerProductDto.getProductDescription());

        productRepo.save(product);

        return sellerProductMapper.toDto(product);
    }

    @Transactional
    @Override
    public SellerProductRequestDto updateSellerProductSale(SellerProductRequestDto productDto) {
        boolean saleState = productDto.isOnSale();
        Product product = productRepo.findById(productDto.getId())
                .orElseThrow(() -> new RuntimeException("Product" + productDto.getId() + " Not found"));

        product.setIsOnSale(saleState);


        return  sellerProductMapper.toDto( product );
    }

}

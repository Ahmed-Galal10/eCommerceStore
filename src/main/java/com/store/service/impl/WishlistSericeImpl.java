package com.store.service.impl;

import com.store.dtos.customer.ProductWishListDto;
import com.store.dtos.wishlist.WishlistProdRequest;
import com.store.model.Customer;
import com.store.model.Product;
import com.store.model.Wishlist;
import com.store.repository.CustomerRepo;
import com.store.repository.ProductRepo;
import com.store.repository.ReviewRepo;
import com.store.repository.WishlistRepo;
import com.store.service.WishListService;
import com.store.util.mappers.EntityDtoMapper;
import com.store.util.mappers.ProductWishListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WishlistSericeImpl implements WishListService {

   private WishlistRepo wishlistRepo;
   private CustomerRepo customerRepo;
   private ProductRepo productRepo;
   private ReviewRepo reviewRepo;

   @Autowired
   EntityDtoMapper< Product,ProductWishListDto> mapper ;


   @Autowired
    public  WishlistSericeImpl(WishlistRepo wishlistRepo,
                               CustomerRepo customerRepo,
                               ProductRepo productRepo,
                               ReviewRepo reviewRepo){
        this.wishlistRepo = wishlistRepo;
        this.customerRepo = customerRepo;
        this.productRepo = productRepo;
        this.reviewRepo = reviewRepo;
    }

    private Wishlist createWishlist(Integer customerId){

        Customer customer = customerRepo.getOne(customerId);

        //TODO Refactor for ERR Handling

        Wishlist wishlist = new Wishlist();
        wishlist.setCustomer( customer );

        Wishlist persisted = wishlistRepo.save( wishlist );
        return  persisted;
    }


    @Override
    public ProductWishListDto addProduct(WishlistProdRequest request) {

        Integer productId = request.getProductId();
        Integer customerId = request.getCustomerId();
        //Try to find the customer wishlist
        //if not reate before create it

        Wishlist wishlist = wishlistRepo.getWishlistByCustomerId( customerId );
        if( wishlist == null ){
            wishlist = createWishlist( customerId );
        }

        Product prod = productRepo.getOne(productId);
        //TODO REFACTOR FOR ERRORS

        wishlist.getProducts().add( prod );

        //TODO REFACTOR
        ProductWishListDto productWishListDto =  mapper.toDto( prod );
        Double avgRating = reviewRepo.findProductAverageRatingById(productId);
        if(avgRating == null) avgRating = 0.0;
        productWishListDto.setRating( avgRating);
        return  productWishListDto;
    }

    @Override
    public Boolean deleteProduct(WishlistProdRequest request) {

        Integer productId = request.getProductId();
        Integer customerId = request.getCustomerId();

        Wishlist wishlist = wishlistRepo.getWishlistByCustomerId( customerId );
        Product product = productRepo.getOne(productId);

        //TODO Refactor For Errors.

        boolean  isRemoved = wishlist.getProducts().remove( product );
        return  isRemoved;
    }


}

package com.store.service.impl;


import com.store.dtos.customer.*;
import com.store.dtos.order.OrderDto;
import com.store.exceptions.CartException;
import com.store.exceptions.RegisterException;
import com.store.model.*;
import com.store.repository.CustomerRepo;
import com.store.repository.OrderRepo;
import com.store.repository.UserRepo;
import com.store.repository.WishlistRepo;
import com.store.service.CustomerService;
import com.store.util.mappers.EntityDtoMapper;
import com.store.util.mappers.order.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EntityDtoMapper<Customer, CustomerDto> customerMapper;

    @Autowired
    private EntityDtoMapper<Customer, CustomerRequestDto> customerRequestMapper;

    @Autowired
    private EntityDtoMapper<Order, CustomerOrderDto> customerOrderMapper;

    @Autowired
    private EntityDtoMapper<Review, CustomerReviewDto> customerReviewMapper;

    @Autowired
    private EntityDtoMapper<Wishlist, CustomerWishListDto> customerWishListMapper;

    @Autowired
    private EntityDtoMapper<Product, ProductWishListDto> productWishListMapper;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private WishlistRepo wishlistRepo;

    public CustomerServiceImpl() {
    }

    @Override
    public List<CustomerDto> getAllCustomers() {

        List<Customer> userList = customerRepo.findAll();
        System.out.println(userList);
        System.out.println("before");

        List<CustomerDto> customerDtoList = customerMapper.entityListToDtoList(userList);

        System.out.println("after");
        System.out.println(customerDtoList);

        return customerDtoList;
    }

    @Override
    public CustomerDto getCustomerById(Integer customerId) {

        Optional<Customer> customer = customerRepo.findById(customerId);
        System.out.println(customer.get());

        CustomerDto customerDto = customerMapper.toDto(customer.get());
        System.out.println(customerDto);

        return customerDto;
    }

    @Override
    public CustomerDetailsDto getCustomerDetailsById(Integer customerId) {
        Customer customer = customerRepo.findById(customerId).get();
        CustomerDto customerDto = customerMapper.toDto(customer);
        List<Order> orderList = orderRepo.findOrderByUser(customer);
        List<OrderDto>  orderDtoList = orderMapper.entityListToDtoList(orderList);
        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto(customerDto,orderDtoList);
        return customerDetailsDto;
    }

    @Override
    public CustomerRequestDto addCustomer(CustomerRequestDto customerDto) {

        boolean isCustomerExist = userRepo.existsByEmail(customerDto.getEmail());
        if(isCustomerExist){
            throw new RegisterException("This Email Already Exists");
        }

        Customer customer = customerRequestMapper.toEntity(customerDto);
        Customer savedCustomer = customerRepo.save(customer);

        return customerRequestMapper.toDto(savedCustomer);
    }

    @Override
    public void deleteCustomer(Integer customerId) {
        customerRepo.deleteById(customerId);
    }

    @Override
    public void updateCustomer(CustomerDto customerDto) {

        Customer customer = customerMapper.toEntity(customerDto);
        customerRepo.save(customer);
    }

    @Override
    public List<CustomerOrderDto> getCustomerOrders(Integer customerId) {

        Optional<Customer> customer = customerRepo.findById(customerId);

        List<Order> orderList = customer.get().getOrders().stream().collect(Collectors.toList());

        List<CustomerOrderDto> customerOrderDtoList = customerOrderMapper.entityListToDtoList(orderList);

        return customerOrderDtoList;
    }

    @Override
    public List<CustomerReviewDto> getCustomerReviews(Integer customerId) {

        Optional<Customer> customer = customerRepo.findById(customerId);

        List<Review> reviewList = customer.get().getReviews().stream().collect(Collectors.toList());

        List<CustomerReviewDto> customerReviewDtoList = customerReviewMapper.entityListToDtoList(reviewList);

        return customerReviewDtoList;
    }

    @Override
    public CustomerWishListDto getCustomerWishList(int customerId) {
        Optional<Customer> customer = customerRepo.findById(customerId);

        Wishlist wishlist = customer.get().getWishlist();

        if(wishlist == null){
            wishlist =  createWishlist(customerId);
        }
        CustomerWishListDto customerWishListDto = customerWishListMapper.toDto(wishlist);

        return customerWishListDto;
    }

    private Wishlist createWishlist(Integer customerId){

        Customer customer = customerRepo.getOne(customerId);

        //TODO Refactor for ERR Handling

        Wishlist wishlist = new Wishlist();
        wishlist.setCustomer( customer );

        Wishlist persisted = wishlistRepo.save( wishlist );
        return  persisted;
    }

}

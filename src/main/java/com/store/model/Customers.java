package com.store.model;

import com.store.dto.CustomerDto;

import java.util.ArrayList;
import java.util.List;

public class Customers {

    private List<CustomerDto> customerList;

    public List<CustomerDto> getCustomerList() {
        if (customerList == null){
            customerList = new ArrayList<>();
        }
        return customerList;
    }

    public void setCustomerList(List<CustomerDto> customerList) {
        this.customerList = customerList;
    }
}

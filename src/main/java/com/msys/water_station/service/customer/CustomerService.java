package com.msys.water_station.service.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msys.water_station.dto.customer.response.CustomerResponse;
import com.msys.water_station.repo.CustomerRepo;
import com.msys.water_station.util.CustomerMapper;

@Service
public class CustomerService {

    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    CustomerMapper customerMapper;

    public List<CustomerResponse> getAllCustomer() {

        return customerMapper.toDTO(customerRepo.findAll());
    }
}

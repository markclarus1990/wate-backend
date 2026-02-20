package com.msys.water_station.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msys.water_station.dto.customer.response.CustomerResponse;
import com.msys.water_station.service.customer.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService cService;

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getAllCustomer() {
        return new ResponseEntity<>(cService.getAllCustomer(), HttpStatus.OK);
    }
}

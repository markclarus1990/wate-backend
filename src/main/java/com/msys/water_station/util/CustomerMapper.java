package com.msys.water_station.util;

import java.util.List;

import org.mapstruct.Mapper;

import com.msys.water_station.Model.Customer;
import com.msys.water_station.dto.customer.response.CustomerResponse;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    List<CustomerResponse> toDTO(List<Customer> customer);
}

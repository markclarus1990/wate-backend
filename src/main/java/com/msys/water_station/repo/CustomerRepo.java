package com.msys.water_station.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msys.water_station.Model.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {
    Customer findByFullName(String fullName);
}

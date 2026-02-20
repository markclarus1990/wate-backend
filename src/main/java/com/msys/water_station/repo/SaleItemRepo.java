package com.msys.water_station.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msys.water_station.Model.SaleItem;

public interface SaleItemRepo extends JpaRepository<SaleItem, Long> {

}

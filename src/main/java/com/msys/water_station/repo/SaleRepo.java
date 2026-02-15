package com.msys.water_station.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msys.water_station.Model.Sale;

public interface SaleRepo extends JpaRepository<Sale, Long> {

}

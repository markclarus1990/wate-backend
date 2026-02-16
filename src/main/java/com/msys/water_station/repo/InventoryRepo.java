package com.msys.water_station.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msys.water_station.Model.InventoryItems;

public interface InventoryRepo extends JpaRepository<InventoryItems, Long> {

}

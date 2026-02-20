package com.msys.water_station.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msys.water_station.Model.InventoryMovement;

public interface InventoryMovementRepo extends JpaRepository<InventoryMovement, Long> {

}

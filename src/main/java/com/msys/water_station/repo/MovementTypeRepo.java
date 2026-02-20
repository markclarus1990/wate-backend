package com.msys.water_station.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msys.water_station.Model.MovementType;

public interface MovementTypeRepo extends JpaRepository<MovementType, Long> {
    Optional<MovementType> findByName(String name);
}

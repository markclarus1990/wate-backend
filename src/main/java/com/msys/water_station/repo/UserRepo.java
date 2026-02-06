package com.msys.water_station.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.msys.water_station.Model.User;
import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

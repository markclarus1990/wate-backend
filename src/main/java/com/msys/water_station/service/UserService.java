package com.msys.water_station.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.msys.water_station.repo.UserRepo;
import com.msys.water_station.util.UserMapper;
import jakarta.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    JwtService jwtService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserMapper userMapper;

    // Get All Users
    @Transactional
    public Page<?> getAllUsers(Pageable pageable) {
        return userRepo.findAll(pageable).map(userMapper::toDTO);
    }

}

package com.msys.water_station.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.msys.water_station.Model.User;
import com.msys.water_station.dto.user.UserResponse;
import com.msys.water_station.exceptions.UserNotFoundException;
import com.msys.water_station.exceptions.UsersNotFoundException;
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

    @Transactional
    public UserResponse getUserByUsername(String username) {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        return userMapper.toDTO(user);
    }

    @Transactional
    public List<UserResponse> getUserContains(String username) {

        List<User> users = userRepo.findByUsernameContainingIgnoreCase(username);

        if (users.isEmpty()) {
            throw new UsersNotFoundException("No users found");
        }

        return userMapper.toDTOContains(users);
    }

    public UserResponse getUserById(Long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        return userMapper.toDTO(user);
    }

}
